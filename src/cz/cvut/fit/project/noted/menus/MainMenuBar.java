
package cz.cvut.fit.project.noted.menus;

import java.awt.Component;
import javax.swing.JMenuBar;

/**
 *
 * @author Adam Příhoda
 */
public class MainMenuBar extends JMenuBar 
{

    public MainMenuBar()
    {
    
        this.add(new FileMenu());
        this.add(new HelpMenu());
    
    }
    
    
    
    
}
