package cz.cvut.fit.project.noted.keyboard.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.keyboard.KeyMappings;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Adam Příhoda
 */
public class CursorMoveAction extends AbstractAction
{
    private final TabManager tabManager;
    private final KeyMappings type;

    public CursorMoveAction(TabManager tabManager, KeyMappings type) {
        this.tabManager = tabManager;
        this.type = type;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        ModelEditor editor = tabManager.getActiveTab().getEditor();
        
        switch (type)
        {
            case MOVE_LEFT:
                editor.getCursor().moveToLeft();
                break;
            case MOVE_RIGHT:
                editor.getCursor().moveToRight();
                break;
            case MOVE_UP:
                editor.getCursor().moveUp();
                break;
            case MOVE_DOWN:
                editor.getCursor().moveDown();
                break;
        }
        
        tabManager.getActiveTab().getRenderPanel().rebuild();
    }
}
