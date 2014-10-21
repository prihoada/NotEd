package cz.cvut.fit.project.noted.keyboard;

import cz.cvut.fit.project.noted.editor.Cursor;
import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.awt.event.*;

/**
 *
 * @author smejkka3
 */
public class KeyHandling extends KeyAdapter {
    
    private final TabManager tabManager;
    private Duration duration;
    
    public KeyHandling(Cursor cursor, TabManager tabManager){   
        this.tabManager = tabManager;
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        Tab activeTab = tabManager.getActiveTab();
        
        ModelEditor modelEditor = activeTab.getEditor();
        
        if(activeTab != null)
        { 
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
            if (key == KeyEvent.VK_SPACE ) {
                modelEditor.addRest(duration);
            }
            if (key == KeyEvent.VK_ENTER ) {
                modelEditor.addNote(duration);
            }
            activeTab.getRenderPanel().rebuild();
        }
    }
    
}
