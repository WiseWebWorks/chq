/*
 * CHQAppletFrame.java
 *
 * Created on June 8, 2005, 7:47 PM
 */

package kevw.games.chq.view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CHQApplicationFrame extends javax.swing.JFrame {

  private Board board1;
  private JButton jButton1;
  private JButton jButton2;
  private JButton jButton3;
  private JButton jButton4;
  private JLabel jLabel1;
  private JPanel jPanel1;

  public CHQApplicationFrame() {
    initComponents();
  }

  private void initComponents() {
    jLabel1 = new JLabel();
    board1 = new Board(jLabel1);
    jPanel1 = new JPanel();
    jButton1 = new JButton();
    jButton2 = new JButton();
    jButton3 = new JButton();
    jButton4 = new JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Command HQ");
    jLabel1.setText("jLabel1");
    getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

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

    jPanel1.add(jButton1);
    jPanel1.add(jButton2);
    jPanel1.add(jButton3);
    jPanel1.add(jButton4);

    jButton1.addActionListener(this::jButton1ActionPerformed);
    jButton2.addActionListener(this::jButton2ActionPerformed);
    jButton3.addActionListener(this::jButton3ActionPerformed);
    jButton4.addActionListener(this::jButton4ActionPerformed);

    getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

    pack();
  }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    board1.toggleGeographyShown();
  }

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private int i = 0;

  private void board1KeyPressed(java.awt.event.KeyEvent evt) {
    int keyCode = evt.getKeyCode();
    jLabel1.setText(
        KeyEvent.getKeyText(keyCode) + ":" + InputEvent.getModifiersExText(evt.getModifiersEx())
            + ":" + i++);
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
  }

  private void board1MouseClicked(java.awt.event.MouseEvent evt) {
    // TODO add your handling code here:
  }

}
