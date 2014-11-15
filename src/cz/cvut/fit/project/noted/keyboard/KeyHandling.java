package cz.cvut.fit.project.noted.keyboard;

import cz.cvut.fit.project.noted.editor.Cursor;
import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.*;

/**
 *
 * @author smejkka3
 */
public class KeyHandling extends KeyAdapter {
    
    private final TabManager tabManager;
    private final DurationToolbar durationToolbar;
    
    public KeyHandling(TabManager tabManager, DurationToolbar durationToolbar){   
        this.tabManager = tabManager;
        this.durationToolbar = durationToolbar;
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        { 
        
            ModelEditor modelEditor = activeTab.getEditor();
        
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                modelEditor.getCursor().moveToLeft();
            }

            if (key == KeyEvent.VK_RIGHT) {
                modelEditor.getCursor().moveToRight();
            }
            if (key == KeyEvent.VK_UP) {
                modelEditor.getCursor().moveUp();
            }

            if (key == KeyEvent.VK_DOWN) {
                modelEditor.getCursor().moveDown();
            }
            if (key == KeyEvent.VK_ENTER) {
                modelEditor.addNote(durationToolbar.getDuration());
                modelEditor.getCursor().moveToRight();
            }
            if (key == KeyEvent.VK_SPACE) {
                modelEditor.addRest(durationToolbar.getDuration());
                modelEditor.getCursor().moveToRight();
            }
            if(key == KeyEvent.VK_BACK_SPACE)
            {
                modelEditor.removePrevious();
            }
            if(key == KeyEvent.VK_DELETE)
            {
                modelEditor.removeNext();
            }
            
            activeTab.getRenderPanel().rebuild();
        }
    }
    
}
