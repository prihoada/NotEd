package cz.cvut.fit.project.noted.menus.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
public class OpenHelpAction extends JDialog implements ActionListener {
    
   private JFrame mainFrame;
   private JLabel statusLabel;
   private JPanel closePanel;
    
    
  public OpenHelpAction(JFrame parent, String title) {
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    createGUI();
  }
  
  private void createGUI(){
      mainFrame = new JFrame("Help"); 
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.setSize(350, 400);
       
      statusLabel = new JLabel("Help for NotEd", JLabel.CENTER);    
      
      final JTextArea textArea = 
         new JTextArea("Editace se provádí v dolní části aplikace v toolbaru s notami\n" +
                        "V prvním toolbaru vyberete délku noty\n" +
                        "V druhém poté k případnému výběru posuvky\n" +
                         "\n" +
                   " K editaci také můžete použít zkratky (enter na přidání noty\n"
                 + " mezerník na přidání pomlky, šipkama pohybujete kurzorem,\n"
                 + " backspace maže symbol před kurzorem, delete maže symbol\n"
                 + " za kurzorem,\n"
                 + " tabulátor vloží oddělovač taktu)",5,20);
      textArea.setEditable(false);

      JScrollPane scrollPane = new JScrollPane(textArea);            
      
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
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);  
   }
  

}
