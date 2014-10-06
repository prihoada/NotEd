package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.actions.OpenAboutAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Adam Příhoda
 */
class HelpMenu extends JMenu
{

    public HelpMenu(LocalizationManager localizationManager)
    {
        this.setText(localizationManager.getString("menu_help").getName());
        
        this.addSeparator();
        
        JMenuItem about = new JMenuItem(localizationManager.getString("menu_item_about").getName());
        about.addActionListener(new OpenAboutAction());
        about.setToolTipText(localizationManager.getString("menu_item_about").getTooltip());
        about.setMnemonic(localizationManager.getString("menu_item_about").getMnemonicKeyCode());
        this.add(about);
        
        
    }
    
}
