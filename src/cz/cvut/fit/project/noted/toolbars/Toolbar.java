

package cz.cvut.fit.project.noted.toolbars;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        
    }
    
    
    
}
