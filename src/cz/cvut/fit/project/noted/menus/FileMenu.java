
package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.menus.actions.SaveProjectAction;
import cz.cvut.fit.project.noted.menus.actions.OpenProjectAction;
import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.actions.CloseProjectAction;
import cz.cvut.fit.project.noted.menus.actions.ExitAction;
import cz.cvut.fit.project.noted.menus.actions.NewProjectAction;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
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
        
        JMenuItem save = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_save").getName());
        save.addActionListener(new SaveProjectAction(false));
        save.setToolTipText(LocalizationManager.getInstance().getString("menu_item_save").getTooltip());
        save.setMnemonic(LocalizationManager.getInstance().getString("menu_item_save").getMnemonicKeyCode());
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        TabManager.getInstance().addChangeListener(new TabbedPaneDisableComponentChangeListener(save));
        this.add(save);
        
        JMenuItem saveAs = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_saveAs").getName());
        saveAs.addActionListener(new SaveProjectAction(true));
        saveAs.setToolTipText(LocalizationManager.getInstance().getString("menu_item_saveAs").getTooltip());
        saveAs.setMnemonic(LocalizationManager.getInstance().getString("menu_item_saveAs").getMnemonicKeyCode());
        TabManager.getInstance().addChangeListener(new TabbedPaneDisableComponentChangeListener(saveAs));
        this.add(saveAs);
        
        
        
        
        JMenuItem closeProject = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_close").getName());
        closeProject.setEnabled(false);
        closeProject.addActionListener(new CloseProjectAction());
        closeProject.setToolTipText(LocalizationManager.getInstance().getString("menu_item_close").getTooltip());
        closeProject.setMnemonic(LocalizationManager.getInstance().getString("menu_item_close").getMnemonicKeyCode());
        TabManager.getInstance().addChangeListener(new TabbedPaneDisableComponentChangeListener(closeProject));
        this.add(closeProject);
        
        this.addSeparator();
        
        JMenuItem exit = new JMenuItem(LocalizationManager.getInstance().getString("menu_item_exit").getName());
        exit.addActionListener(new ExitAction());
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));

        this.add(exit);
        
    }
    
}
