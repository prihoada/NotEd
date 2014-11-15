
package cz.cvut.fit.project.noted.keyboard.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.keyboard.KeyMappings;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.AccidentalToolbar;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Adam Příhoda
 */
public class AddAction extends AbstractAction
{

    private final TabManager tabManager;
    private final KeyMappings type;
    private final DurationToolbar durationToolbar;
    private final AccidentalToolbar accidentalToolbar;

    public AddAction(TabManager tabManager, KeyMappings type, DurationToolbar durationToolbar, AccidentalToolbar accidentalToolbar) {
        this.tabManager = tabManager;
        this.type = type;
        this.durationToolbar = durationToolbar;
        this.accidentalToolbar = accidentalToolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ModelEditor editor = tabManager.getActiveTab().getEditor();
        
        switch (type)
        {
            case ADD_NOTE:
                editor.addNote(durationToolbar.getDuration());
                editor.getCursor().moveToRight();
                break;
                
            case ADD_REST:
                editor.addRest(durationToolbar.getDuration());
                editor.getCursor().moveToRight();
                break;
                
            case ADD_BARLINE:
                editor.addMeasure();
                editor.getCursor().moveToRight();
                break;
        }
        
        tabManager.getActiveTab().getRenderPanel().rebuild();
    }
    
}
