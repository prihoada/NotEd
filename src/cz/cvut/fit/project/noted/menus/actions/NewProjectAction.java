package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Adam Příhoda
 */
public class NewProjectAction implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {

        TabManager.getInstance().addTab(LocalizationManager.getInstance().getString("file_untitled").getName(), new Tab());
    
    }
    
}
