/*
 * CHQAppletFrame.java
 *
 * Created on June 8, 2005, 7:47 PM
 */

package kevw.games.chq;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class CHQAppletFrame extends javax.swing.JFrame {

  private kevw.games.chq.Board board1;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;

  public CHQAppletFrame() {
    initComponents();
  }

  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    board1 = new kevw.games.chq.Board();
    jPanel1 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Command HQ");
    jLabel1.setText("jLabel1");
    getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

    board1.setStatusLabel(jLabel1);
		board1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				board1KeyPressed(evt);
			}
		});
		board1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				board1MouseClicked(evt);
			}
		});

    getContentPane().add(board1, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.GridLayout(1, 4));

		jPanel1.setMinimumSize(new java.awt.Dimension(300, 180));
		jPanel1.setPreferredSize(new java.awt.Dimension(300, 180));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

    jPanel1.add(jButton1);

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

    jPanel1.add(jButton2);

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

    jPanel1.add(jButton3);

		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

    jPanel1.add(jButton4);

    getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

    pack();
  }
  // </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
	}//GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		board1.toggleGeographyShown();
	}//GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
	}//GEN-LAST:event_jButton3ActionPerformed

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
  }//GEN-LAST:event_jButton4ActionPerformed

	private int i = 0;
	private void board1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_board1KeyPressed
// TODO add your handling code here:
		int keyCode = evt.getKeyCode();
		jLabel1.setText(evt.getKeyText(keyCode) + ":" + evt.getModifiersExText(evt.getModifiersEx()) + ":" + i++);
		switch (keyCode) {
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
		}
//		board1.repaint();
	}//GEN-LAST:event_board1KeyPressed

	private void board1MouseClicked (java.awt.event.MouseEvent evt) {//GEN-FIRST:event_board1MouseClicked
// TODO add your handling code here:
	}//GEN-LAST:event_board1MouseClicked

}
