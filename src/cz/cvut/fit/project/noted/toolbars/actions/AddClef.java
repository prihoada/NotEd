package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author david
 */
public class AddClef implements ActionListener
{
    private final TabManager tabManager;

    public AddClef(TabManager tabManager)
    {
        this.tabManager = tabManager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        {            
           ModelEditor modelEditor = activeTab.getEditor();
            
           modelEditor.addClef();
           modelEditor.getCursor().moveToRight();

           activeTab.getRenderPanel().rebuild();
        }
    }
}
