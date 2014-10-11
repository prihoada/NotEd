
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.IModelProvider;
import cz.cvut.fit.project.noted.musicrenderer.RenderBuilder;
import cz.cvut.fit.project.noted.musicrenderer.RenderPanel;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author david
 */
public class AddNote implements ActionListener
{

    private final TabManager tabManager;
    private final RenderPanel renderPanel;
    
    
    public AddNote(TabManager tabManager) {
        this.tabManager = tabManager;
        this.renderPanel = new RenderPanel();
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        {            
           
           Model model = activeTab.getModel();
           ModelEditor modelEditor = new ModelEditor(model); 
            
           //TODO Vyber typu NOTY udelat PAK!!!!!!!!!!
           modelEditor.addNote("A");
           
           //znovu vykresleni.
           renderPanel.buildFromModel(model);
            
        }
        
    }

    
    
}
