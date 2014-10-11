
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.IModelProvider;
import cz.cvut.fit.project.noted.model.ModelEdit;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This shit will in future add Note (type by param) to current position
 * 
 * tedko chci aspon aby to pridalo nejakou pevne definovanou notu nakonec.
 * @author david
 */
public class AddNote implements ActionListener
{

    private final TabManager tabManager;
    
    public AddNote(TabManager tabManager) {
        this.tabManager = tabManager;
        
    }
  

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        {            
           
            Model model = activeTab.getModel();
            
            ModelEdit modelEdit = new ModelEdit(model);
            
           //TODO MODEL->naprogramovat addNoty. 
           modelEdit.addNote();
            
        }
        
        
        
        
    }

}
