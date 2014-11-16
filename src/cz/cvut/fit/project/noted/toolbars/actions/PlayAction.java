
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Plays the active tab model from the current measure.
 * @author Adam Příhoda
 */
public class PlayAction extends AbstractAction
{
    private final TabManager tabManager;

    public PlayAction(TabManager tabManager) {
        this.tabManager = tabManager;
        
        this.putValue(SHORT_DESCRIPTION, "Play");
        this.putValue(SMALL_ICON, new ImageIcon("assets/images/playButton.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        if(activeTab != null)
        {
            tabManager.getPlayer().playModel(activeTab.getModel(), activeTab.getEditor().getCursor().getMeasure());
        }
    }

}
