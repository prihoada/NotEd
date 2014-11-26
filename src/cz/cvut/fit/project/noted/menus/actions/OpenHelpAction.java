package cz.cvut.fit.project.noted.menus.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class OpenHelpAction extends JDialog implements ActionListener {
    
   private JFrame mainFrame;
   private JLabel statusLabel;
   private JPanel closePanel;
    
    
  public OpenHelpAction(JFrame parent, String title) {
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    prepareGUI();
  }
  
  private void prepareGUI(){
      mainFrame = new JFrame("Help"); 
      mainFrame.setLayout(new GridLayout(3, 1));
       
      statusLabel = new JLabel("Help for NotEd", JLabel.CENTER);    
      
      final JTextArea commentTextArea = 
         new JTextArea("This help for NotEd.",5,20);

      JScrollPane scrollPane = new JScrollPane(commentTextArea); 
      
      closePanel = new JPanel();
      
      JButton button = new JButton("Close"); 
     
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            mainFrame.dispose();        
         }
      }); 
       
      closePanel.add(button);
       
      mainFrame.add(statusLabel);
      mainFrame.add(scrollPane);
      mainFrame.add(closePanel);
      mainFrame.pack();
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);  
   }
  

}
