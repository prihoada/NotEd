
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Plays an active tab from the begining.
 * @author Adam Příhoda
 */
public class PlayAction implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = TabManager.getInstance().getActiveTab();
        if(activeTab != null)
        {
            activeTab.getPlayer().getReadyToPlay();
            activeTab.getPlayer().play();
        }
    }

}
