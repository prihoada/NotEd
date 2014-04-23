
package cz.cvut.fit.project.noted.utils;

import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Disables the supplied component when the tabbed pane has no tabs.
 * @author Adam Příhoda
 */
public class TabbedPaneDisableComponentChangeListener implements ChangeListener
{
    private final Component component;

    public TabbedPaneDisableComponentChangeListener(Component component)
    {
        this.component = component;
    }

    
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        JTabbedPane pane = (JTabbedPane) e.getSource();
        if(pane.getSelectedIndex() == -1) component.setEnabled(false);
        else component.setEnabled(true);
    }
    
}
