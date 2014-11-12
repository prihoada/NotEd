package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 *
 * @author Adam Příhoda
 */
public class ZoomAction extends AbstractAction{

    private final TabManager tabManager;
    private final TYPE type;

    public ZoomAction(TabManager TabManager, TYPE type, ImageIcon icon) {

        super("Zoom", icon);
        this.tabManager = TabManager;
        this.type = type;
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (type)
        {
            case Zoom_in:
                tabManager.getActiveTab().getRenderPanel().scale += 0.3;
                break;
            case Zoom_out:
                tabManager.getActiveTab().getRenderPanel().scale -= 0.3;
                break;
        }
        
        tabManager.getActiveTab().getRenderPanel().repaint();
        tabManager.getActiveTab().getRenderPanel().revalidate();
    }
    
    
    

    public static enum TYPE {
        Zoom_in,
        Zoom_out;
    }
    
}
