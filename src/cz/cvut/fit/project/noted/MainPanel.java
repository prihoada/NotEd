package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.keyboard.KeyHandling;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import cz.cvut.fit.project.noted.toolbars.Toolbar;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Adam Příhoda
 */
public class MainPanel extends JPanel
{

    public MainPanel(XMLFileChooser xmlFileChooser,
                     ProxyMusicHandler proxyMusicHandler,
                     TabManager tabManager)
    {
        
        DurationToolbar durationToolbar = new DurationToolbar(tabManager,"Duration toolbar");
        
        this.setLayout(new BorderLayout());
        this.add(new Toolbar(tabManager,"Main toolbar"), BorderLayout.NORTH);
        this.add(durationToolbar, BorderLayout.SOUTH);
        this.add(tabManager, BorderLayout.CENTER);
        
        
        //Temporary - create an empty file
        Tab tab = new Tab(xmlFileChooser, proxyMusicHandler, tabManager);
        tab.setModel(proxyMusicHandler.createEmptyModel());
        tabManager.addTab("Unnamed song", tab);
        
        this.setFocusable(true);
        this.addKeyListener(new KeyHandling(null, tabManager, durationToolbar));
        
    }  
    
}
