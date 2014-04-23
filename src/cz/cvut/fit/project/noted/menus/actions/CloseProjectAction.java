
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        TabManager.getInstance().closeTab(TabManager.getInstance().getActiveTabIndex());
    }

}
