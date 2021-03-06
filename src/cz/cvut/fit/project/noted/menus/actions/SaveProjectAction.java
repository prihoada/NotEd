package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Saves the currently active tab to a file. (Can ask for file destination).
 * @author Adam Příhoda
 */
public class SaveProjectAction implements ActionListener
{
    private final boolean saveAs;

    private final TabManager tabManager;

    /**
     * Action listener for clicking save or save as menu item.
     * @param saveAs true if the action is save as.
     */
    public SaveProjectAction(TabManager tabManager,
                             boolean saveAs)
    {
        this.tabManager = tabManager;
        this.saveAs = saveAs;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab tab = tabManager.getActiveTab();
        tab.save(saveAs);
    }
    
}
