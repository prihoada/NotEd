
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Plays an active tab from the begining.
 * @author Adam Příhoda
 */
public class StopAction implements ActionListener
{
    private final TabManager tabManager;

    public StopAction(TabManager tabManager) {
        this.tabManager = tabManager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        if(activeTab != null)
        {            
            activeTab.getPlayer().stop();
        }
    }

}
