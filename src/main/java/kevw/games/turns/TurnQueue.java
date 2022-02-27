package kevw.games.turns;
import java.util.*;
import kevw.games.turns.Turn;

/**
 * Blocking, thread-safe queue for Turns.
 * 
 * @author Kevin Wise
 * @version 1.0
 */
public class TurnQueue  {
	private LinkedList<Turn> turns;
	private boolean	open;
	private int waitingThreadCount;
	private static final String lineSeparator = System.getProperty("line.separator");

//	//TODO: Should I implement a queue size limit?
	public TurnQueue(){
		turns = new LinkedList<Turn>();
		waitingThreadCount = 0;
		open = true;
	}

	public synchronized String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("TurnQueue:  status=");
		strBuff.append(open?"open":"closed");
		strBuff.append("  WaitingThreads=");
		strBuff.append(waitingThreadCount);
		strBuff.append(lineSeparator);
		strBuff.append("Contents=");
		strBuff.append(turns);
		return strBuff.toString();
	}

	public synchronized void enqueue( Turn gatherer ) throws TurnQueue.QueueClosedException {
		if (!open) {
			throw new TurnQueue.QueueClosedException();
		} else {
			turns.add(gatherer);
			notify();
		}
	}

	public synchronized Turn dequeue() throws InterruptedException, TurnQueue.QueueClosedException {
		if (!open) {
			throw new TurnQueue.QueueClosedException();
		} else {
			if (turns.size() <= 0) {
				waitingThreadCount++;
				try {
					while (turns.size() <= 0) {
						wait();
						if (!open) {
							throw new TurnQueue.QueueClosedException();
						}
					}
				} finally {
					waitingThreadCount--;
				}
			}
			return (Turn)turns.removeFirst();
		}
	}

	 public synchronized int getWaitingThreadCount() {
		return waitingThreadCount;
	 }
	 	
	 public synchronized int getSize() {
		return turns.size();
	 }

	 public synchronized void close() {
		open = false;
		turns.clear();
		notifyAll();
	 }

	 public class QueueClosedException extends Exception {
		private QueueClosedException() {
			super("Tried to access closed TurnQueue");
		}
	 }
}