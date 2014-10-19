package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author david
 */
public class AddRest implements ActionListener
{
    
    private final TabManager tabManager;
    private final DurationToolbar toolbar;

    public AddRest(TabManager tabManager, DurationToolbar toolbar)
    {
        this.tabManager = tabManager;
        this.toolbar = toolbar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        {            
           
           Model model = activeTab.getModel();
           ModelEditor modelEditor = activeTab.getEditor();
            
           modelEditor.addRest(toolbar.getDuration());
           modelEditor.getCursor().moveToRight();
           
           activeTab.getRenderPanel().rebuild();
           
        }
        
    }
    
    
}
