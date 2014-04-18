
package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Closes an active project (tab). Disables itself when no tab is selected.
 * @author Adam Příhoda
 */
public class CloseProjectAction implements ActionListener, ChangeListener
{
    private final JMenuItem closeButton;

    public CloseProjectAction(JMenuItem closeButton)
    {
        this.closeButton = closeButton;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        TabManager.getInstance().closeTab(TabManager.getInstance().getActiveTabIndex());
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        JTabbedPane pane = (JTabbedPane) e.getSource();
        if(pane.getSelectedIndex() == -1) closeButton.setEnabled(false);
        else closeButton.setEnabled(true);
    }
    
}
