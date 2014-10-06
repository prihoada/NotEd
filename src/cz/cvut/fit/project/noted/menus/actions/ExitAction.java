

package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam Příhoda
 */
public class ExitAction extends WindowAdapter implements ActionListener{

    private final LocalizationManager localizationManager;
    private final TabManager tabManager;

    public ExitAction (LocalizationManager localizationManager,
                       TabManager tabManager)
    {
        this.localizationManager = localizationManager;
        this.tabManager = tabManager;

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        exitApplication();
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        exitApplication();
        super.windowClosing(e);
    }
    
    
    
    private void exitApplication()
    {
        int tabCount = tabManager.getTabCount();
        for (int i = 0; i < tabCount; i++)
        {
            Tab tab = tabManager.getTabAt(i);
            if(!tab.isSaved())
            {
                int result = JOptionPane.showConfirmDialog(null, localizationManager.getString("confirm_application_close").getTooltip(),
                                                                 localizationManager.getString("confirm_application_close").getName(),
                                                                 JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
                else
                {
                    return;
                }
                
            }
        }
        
        System.exit(0);
    }
    
    
    
}
