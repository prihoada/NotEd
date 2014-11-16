

package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.MainMenuBar;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.menus.actions.ExitAction;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.rendering.TabManager;

import java.awt.Dimension;
import javax.swing.JFrame;
import jm.music.data.Score;
import jm.util.Play;

/**
 *
 * @author Adam Příhoda
 */
class MainFrame extends JFrame implements Runnable
{

    public MainFrame()
    {
        super("NotEd");
        
    }

    @Override
    public void run()
    {
        /*
         * Services setup, can be replaced with proper DI Container
         */
        LocalizationManager localizationManager = new LocalizationManager();
        TabManager tabManager = new TabManager(localizationManager);
        XMLFileChooser xmlFileChooser = new XMLFileChooser(localizationManager);
        ProxyMusicHandler proxyMusicHandler = new ProxyMusicHandler();
        
        //init jMusic playback synthetizer to minimize lag on playback start
        new Thread(new Runnable() {
            @Override
            public void run() {
                Play.midi(new Score());
            }
        }).start();
        
        
        /*
         * end of setup
         */

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ExitAction(localizationManager, tabManager));
        
        this.setJMenuBar(new MainMenuBar(xmlFileChooser, proxyMusicHandler, localizationManager, tabManager));
        this.add(new MainPanel(xmlFileChooser, proxyMusicHandler, tabManager));
        
        this.setFocusTraversalKeysEnabled(false);
        this.setVisible(true);
    }
    
}
