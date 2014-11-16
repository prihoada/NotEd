
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Stops the player playback as soon as possible.
 * @author Adam Příhoda
 */
public class StopAction extends AbstractAction
{
    private final TabManager tabManager;

    public StopAction(TabManager tabManager) {
        
        this.tabManager = tabManager;
        
        this.putValue(SHORT_DESCRIPTION, "Stop");
        this.putValue(SMALL_ICON, new ImageIcon("assets/images/stopButton.png"));
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tabManager.getPlayer().stop();
    }

}
