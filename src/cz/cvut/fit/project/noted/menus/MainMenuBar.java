
package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.model.ProxyMusicHandler;

import java.awt.Component;
import javax.swing.JMenuBar;

/**
 *
 * @author Adam Příhoda
 */
public class MainMenuBar extends JMenuBar 
{

    public MainMenuBar(XMLFileChooser xmlFileChooser,
                       ProxyMusicHandler proxyMusicHandler)
    {
    
        this.add(new FileMenu(xmlFileChooser, proxyMusicHandler));
        this.add(new HelpMenu());
    
    }
    
    
    
    
}
