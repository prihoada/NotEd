
package cz.cvut.fit.project.noted.keyboard.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.keyboard.KeyMappings;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Adam Příhoda
 */
public class DeleteAction extends AbstractAction
{
    private final TabManager tabManager;
    private final KeyMappings type;

    public DeleteAction(TabManager tabManager, KeyMappings type) {
        this.tabManager = tabManager;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        ModelEditor editor = tabManager.getActiveTab().getEditor();
    
        switch (type)
        {
            case DELETE_PREVIOUS:
                editor.removePrevious();
                break;
                
            case DELETE_NEXT:
                editor.removeNext();
                break;
        }
        
        tabManager.getActiveTab().getRenderPanel().rebuild();
    
    }
    
    
    
    
}
