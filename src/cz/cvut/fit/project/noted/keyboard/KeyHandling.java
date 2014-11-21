package cz.cvut.fit.project.noted.keyboard;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.keyboard.actions.CursorMoveAction;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.AccidentalToolbar;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.*;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import static cz.cvut.fit.project.noted.keyboard.KeyMappings.*;
import cz.cvut.fit.project.noted.keyboard.actions.AddAction;
import cz.cvut.fit.project.noted.keyboard.actions.DeleteAction;
import cz.cvut.fit.project.noted.toolbars.AccidentalToolbar;
/**
 *
 * @author smejkka3, Adam Příhoda
 */
public class KeyHandling {

    private final JComponent component;
    private final TabManager tabManager;
    private final DurationToolbar durationToolbar;
    private final AccidentalToolbar accidentalToolbar;
    
    public KeyHandling(JComponent component, TabManager tabManager, DurationToolbar durationToolbar, AccidentalToolbar accidentalToolbar){   

        this.component = component;
        this.tabManager = tabManager;
        this.durationToolbar = durationToolbar;
        this.accidentalToolbar = accidentalToolbar;
        
        attach(component);
    }

    
    private void attach(JComponent component) {
        
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();
        
        Tab activeTab = tabManager.getActiveTab();
        final ModelEditor modelEditor = activeTab.getEditor();
       
        
        //cursor movement
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), MOVE_LEFT);
        actionMap.put(MOVE_LEFT, new CursorMoveAction(tabManager, MOVE_LEFT));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), MOVE_RIGHT);
        actionMap.put(MOVE_RIGHT, new CursorMoveAction(tabManager, MOVE_RIGHT));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), MOVE_UP);
        actionMap.put(MOVE_UP, new CursorMoveAction(tabManager, MOVE_UP));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), MOVE_DOWN);
        actionMap.put(MOVE_DOWN, new CursorMoveAction(tabManager, MOVE_DOWN));
        
       
        //adding symbols
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ADD_NOTE);
        actionMap.put(ADD_NOTE, new AddAction(tabManager, ADD_NOTE, durationToolbar, accidentalToolbar));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), ADD_REST);
        actionMap.put(ADD_REST, new AddAction(tabManager, ADD_REST, durationToolbar, accidentalToolbar));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), ADD_BARLINE);
        actionMap.put(ADD_BARLINE, new AddAction(tabManager, ADD_BARLINE, durationToolbar, accidentalToolbar));
        
        
        //deletion
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), DELETE_PREVIOUS);
        actionMap.put(DELETE_PREVIOUS, new DeleteAction(tabManager, DELETE_PREVIOUS));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE_NEXT);
        actionMap.put(DELETE_NEXT, new DeleteAction(tabManager, DELETE_NEXT));
    }
    
    
}
