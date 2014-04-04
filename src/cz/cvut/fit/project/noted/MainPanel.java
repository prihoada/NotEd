package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.Toolbar;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Adam Příhoda
 */
public class MainPanel extends JPanel
{

    public MainPanel()
    {
        
        this.setLayout(new BorderLayout());
        this.add(new Toolbar("Test toolbar"), BorderLayout.NORTH);
        this.add(TabManager.getInstance(), BorderLayout.CENTER);
        
        
        //Temporary
        TabManager.getInstance().addTab("Test tab", new Tab());
    }
    
    
    
}
