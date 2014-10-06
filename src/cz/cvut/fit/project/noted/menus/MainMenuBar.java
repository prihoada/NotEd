
package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.rendering.TabManager;

import java.awt.Component;
import javax.swing.JMenuBar;

/**
 *
 * @author Adam Příhoda
 */
public class MainMenuBar extends JMenuBar 
{

    public MainMenuBar(XMLFileChooser xmlFileChooser,
                       ProxyMusicHandler proxyMusicHandler,
                       LocalizationManager localizationManager,
                       TabManager tabManager)
    {
    
        this.add(new FileMenu(xmlFileChooser, proxyMusicHandler, localizationManager, tabManager));
        this.add(new HelpMenu(localizationManager));
    
    }
    
    
    
    
}
