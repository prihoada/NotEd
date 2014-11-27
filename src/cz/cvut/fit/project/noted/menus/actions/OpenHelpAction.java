package cz.cvut.fit.project.noted.menus.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
      mainFrame.setLayout(new BorderLayout());
      mainFrame.setSize(350, 400);
       
      JPanel top = new JPanel();
      statusLabel = new JLabel("Help for NotEd", JLabel.CENTER);    
      top.add(statusLabel);
      top.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
      
      mainFrame.add(top, BorderLayout.NORTH);
      
      final JTextArea textArea = 
         new JTextArea("Editace se provádí v dolní části aplikace v toolbaru s notami\n" +
                        "V prvním toolbaru vyberete délku noty\n" +
                        "V druhém poté k případnému výběru posuvky\n" +
                         "\n" +
                   "K editaci také můžete použít zkratky (enter na přidání noty\n"
                 + "mezerník na přidání pomlky, šipkama pohybujete kurzorem,\n"
                 + "backspace maže symbol před kurzorem, delete maže symbol\n"
                 + "za kurzorem,\n"
                 + "tabulátor vloží oddělovač taktu)",5,20);
      textArea.setEditable(false);
      textArea.setOpaque(false);
      textArea.setFont(new Font("serif", Font.PLAIN, 15));

      JScrollPane scrollPane = new JScrollPane(textArea);            
      scrollPane.setPreferredSize(new Dimension(800,600));
      
      mainFrame.add(scrollPane, BorderLayout.CENTER);
      scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      
      closePanel = new JPanel();
      
      JButton button = new JButton("Close"); 
     
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            mainFrame.dispose();        
         }
      }); 
      
      closePanel.add(button);
       
      mainFrame.add(closePanel, BorderLayout.SOUTH);
      mainFrame.pack();
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);  
   }
  

}
