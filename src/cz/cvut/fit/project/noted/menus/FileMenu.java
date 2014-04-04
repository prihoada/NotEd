
package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.actions.ExitAction;
import cz.cvut.fit.project.noted.menus.actions.NewProjectAction;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Adam Příhoda
 */
class FileMenu extends JMenu {

    public FileMenu()
    {
        this.setText(LocalizationManager.getInstance().getString("menu_file").getName());
        
        
        JMenuItem newProject = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_new").getName());
        newProject.addActionListener(new NewProjectAction());
        newProject.setToolTipText(LocalizationManager.getInstance().getString("menu_item_new").getTooltip());
        newProject.setMnemonic(LocalizationManager.getInstance().getString("menu_item_new").getMnemonicKeyCode());
        this.add(newProject);
        
        JMenuItem openProject = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_open").getName());
        openProject.addActionListener(new OpenProjectAction());
        openProject.setToolTipText(LocalizationManager.getInstance().getString("menu_item_open").getTooltip());
        openProject.setMnemonic(LocalizationManager.getInstance().getString("menu_item_open").getMnemonicKeyCode());
        this.add(openProject);
        
        
        this.addSeparator();
        
        JMenuItem exit = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_exit").getName());
        exit.addActionListener(new ExitAction());
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));

        this.add(exit);
        
    }
    
}
