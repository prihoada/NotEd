package cz.cvut.fit.project.noted.keyboard;

import cz.cvut.fit.project.noted.editor.Cursor;
import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.*;

/**
 *
 * @author smejkka3
 */
public class KeyHandling implements KeyListener {
    private final Cursor cursor;
    private final TabManager tabManager;
    
    public KeyHandling(Cursor cursor, TabManager tabManager){   
        this.cursor = cursor;
        this.tabManager = tabManager;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        Tab activeTab = tabManager.getActiveTab();
        
        Model model = activeTab.getModel();
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
            activeTab.getRenderPanel().rebuild();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Tab activeTab = tabManager.getActiveTab();
        
        Model model = activeTab.getModel();
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
            activeTab.getRenderPanel().rebuild();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Tab activeTab = tabManager.getActiveTab();
        
        Model model = activeTab.getModel();
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
            activeTab.getRenderPanel().rebuild();
        }
    }
    
}
