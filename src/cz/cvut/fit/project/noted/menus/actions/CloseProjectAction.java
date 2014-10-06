
package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Closes an active project (tab). Disables itself when no tab is selected.
 * @author Adam Příhoda
 */
public class CloseProjectAction implements ActionListener
{
    private final TabManager tabManager;

    public CloseProjectAction (TabManager tabManager) {
        this.tabManager = tabManager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tabManager.closeTab(tabManager.getActiveTabIndex());
    }

}
