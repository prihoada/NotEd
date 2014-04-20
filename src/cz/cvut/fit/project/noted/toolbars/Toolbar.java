

package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.model.ParsingException;
import cz.cvut.fit.project.noted.modelplayer.ModelPlayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

/**
 *
 * @author Adam Příhoda
 */
public class Toolbar extends JToolBar
{

    public Toolbar(String name)
    {
        super(name);
        
        this.setRollover(true);
        this.setFloatable(true);
        
        JButton playButton = new JButton(new ImageIcon("assets/images/playButton.png"));
        playButton.setFocusPainted(false);
        this.add(playButton);
        
        playButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ModelPlayer modelPlayer = new ModelPlayer(null);
                    JOptionPane.showMessageDialog(null, "Score to be played...\n" + modelPlayer.getReadyToPlay(), "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    modelPlayer.play();
                } catch (FileNotFoundException | ParsingException ex)
                {
                    Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    
    
    
}
