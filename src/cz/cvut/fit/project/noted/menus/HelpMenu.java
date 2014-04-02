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

    public HelpMenu()
    {
        this.setText(LocalizationManager.getInstance().getString("menu_help").getName());
        
        this.addSeparator();
        
        JMenuItem about = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_about").getName());
        about.addActionListener(new OpenAboutAction());
        about.setToolTipText(LocalizationManager.getInstance().getString("menu_item_about").getTooltip());
        about.setMnemonic(LocalizationManager.getInstance().getString("menu_item_about").getMnemonicKeyCode());
        this.add(about);
        
        
    }
    
}