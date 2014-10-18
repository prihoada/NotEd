package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.IModelProvider;
import cz.cvut.fit.project.noted.musicrenderer.RenderBuilder;
import cz.cvut.fit.project.noted.musicrenderer.RenderPanel;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
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
           
           Model model = activeTab.getModel();
           ModelEditor modelEditor = activeTab.getEditor();
            
           modelEditor.addClef();
           modelEditor.getCursor().moveToRight();
           
           activeTab.getRenderPanel().rebuild();
           
        }
        
    }
    
    
    
    
    
}
