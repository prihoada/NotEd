package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.keyboard.KeyHandling;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.musicrenderer.RenderPanel;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.AccidentalToolbar;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import cz.cvut.fit.project.noted.toolbars.Toolbar;
import cz.cvut.fit.project.noted.toolbars.AddToolbar;
import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Adam Příhoda
 */
public class MainPanel extends JPanel
{

    public MainPanel(XMLFileChooser xmlFileChooser,
                     ProxyMusicHandler proxyMusicHandler,
                     final TabManager tabManager)
    {

        this.setLayout(new BorderLayout());
        
        Toolbar toolbar = new Toolbar(tabManager,"Main toolbar");
        this.add(toolbar, BorderLayout.NORTH);
        this.add(tabManager, BorderLayout.CENTER);
        
        
        JPanel bottomToolbars = new JPanel();
        this.add(bottomToolbars, BorderLayout.SOUTH);

            DurationToolbar durationToolbar = new DurationToolbar(tabManager, "Set duration");
            bottomToolbars.add(durationToolbar);

            AccidentalToolbar accidentalToolbar = new AccidentalToolbar(tabManager, "Set accidental");
            bottomToolbars.add(accidentalToolbar);
            
            AddToolbar addToolbar = new AddToolbar(tabManager, accidentalToolbar, durationToolbar, "Add symbols");
            bottomToolbars.add(addToolbar);
        
        
        //Temporary - create an empty file
        Tab tab = new Tab(xmlFileChooser, proxyMusicHandler, tabManager);
        tabManager.addTab("Unnamed song", tab);
        
        KeyHandling keyHandling = new KeyHandling(this, tabManager, durationToolbar, accidentalToolbar);
    }  
    
}
