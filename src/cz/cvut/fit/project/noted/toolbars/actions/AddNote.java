package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.AccidentalToolbar;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author david
 */
public class AddNote implements ActionListener
{

    private final TabManager tabManager;
    private final AccidentalToolbar accToolbar;
    private final DurationToolbar durToolbar;
    
    public AddNote(TabManager tabManager,
            AccidentalToolbar accToolbar, DurationToolbar durToolbar)
    {
        this.tabManager = tabManager;
        this.accToolbar = accToolbar;
        this.durToolbar = durToolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Tab activeTab = tabManager.getActiveTab();
        
        if(activeTab != null)
        {            
           
           Model model = activeTab.getModel();
           ModelEditor modelEditor = activeTab.getEditor();
            
           modelEditor.addNote(accToolbar.getSelectedType(), durToolbar.getDuration());
           modelEditor.getCursor().moveToRight();
           
           activeTab.getRenderPanel().rebuild();
           
           
           //posunuti doprava
           activeTab.scrollToRight();
           
        }
        
    }
      
}
