
package cz.cvut.fit.project.noted.musicrenderer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Toolbar for the renderer test window.
 * @author Adam Příhoda
 */
public class ToolsPanel extends JToolBar
{
    private final RenderPanel panel;

    ToolsPanel(RenderPanel panel) {
        this.panel = panel;
    
        
        JButton scaleUp = new JButton();
        this.add(scaleUp);
        scaleUp.setAction(new ScaleAction("+", panel));
        
        
        JButton scaleDown = new JButton();
        this.add(scaleDown);
        scaleDown.setAction(new ScaleAction("-", panel));
        
    }

    private static class ScaleAction extends AbstractAction {
        private final String type;
        private RenderPanel panel;

        private ScaleAction(String type, RenderPanel panel) {
            
            super(" " + type + " ");
            
            this.type = type;
            this.panel = panel;
            
            
        }

        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(type.equals("+"))
            {
                panel.scale += 0.5;
            }
            else if(type.equals("-"))
            {
                panel.scale -= 0.5;
            }
            
            panel.revalidate();
            panel.repaint();
        }
    }
    
    

    
    

    
    
}
