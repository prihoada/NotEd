/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author david
 */
public class AddAccidental implements ActionListener
{
    private final TabManager tabManager;
    private final AccidentalToolbar toolbar;
    private final DurationToolbar durToolbar;
    
    public AddAccidental(TabManager tabManager,
            AccidentalToolbar toolbar, DurationToolbar durToolbar)
    {
        this.tabManager = tabManager;
        this.toolbar = toolbar;
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
            
            modelEditor.addAccidental(toolbar.getSelectedType(), durToolbar.getDuration());
            modelEditor.getCursor().moveToRight();
            
            activeTab.getRenderPanel().rebuild();
      
        }
        
    }
    
}
