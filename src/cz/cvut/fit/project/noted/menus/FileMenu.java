
package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.menus.actions.SaveProjectAction;
import cz.cvut.fit.project.noted.menus.actions.OpenProjectAction;
import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.actions.CloseProjectAction;
import cz.cvut.fit.project.noted.menus.actions.ExitAction;
import cz.cvut.fit.project.noted.menus.actions.NewProjectAction;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
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

    public FileMenu(XMLFileChooser xmlFileChooser,
                    ProxyMusicHandler proxyMusicHandler,
                    LocalizationManager localizationManager,
                    TabManager tabManager)
    {
        this.setText(localizationManager.getString("menu_file").getName());
        

        JMenuItem newProject = new JMenuItem(localizationManager.getString("menu_item_new").getName());
        newProject.addActionListener(new NewProjectAction(xmlFileChooser, proxyMusicHandler, localizationManager, tabManager));
        newProject.setToolTipText(localizationManager.getString("menu_item_new").getTooltip());
        newProject.setMnemonic(localizationManager.getString("menu_item_new").getMnemonicKeyCode());
        this.add(newProject);
        
        JMenuItem openProject = new JMenuItem(localizationManager.getString("menu_item_open").getName());
        openProject.addActionListener(new OpenProjectAction(xmlFileChooser,proxyMusicHandler, localizationManager, tabManager));
        openProject.setToolTipText(localizationManager.getString("menu_item_open").getTooltip());
        openProject.setMnemonic(localizationManager.getString("menu_item_open").getMnemonicKeyCode());
        this.add(openProject);
        
        this.addSeparator();
        
        JMenuItem save = new JMenuItem(localizationManager.getString("menu_item_save").getName());
        save.addActionListener(new SaveProjectAction(tabManager, false));
        save.setToolTipText(localizationManager.getString("menu_item_save").getTooltip());
        save.setMnemonic(localizationManager.getString("menu_item_save").getMnemonicKeyCode());
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(save));
        this.add(save);
        
        JMenuItem saveAs = new JMenuItem(localizationManager.getString("menu_item_saveAs").getName());
        saveAs.addActionListener(new SaveProjectAction(tabManager, true));
        saveAs.setToolTipText(localizationManager.getString("menu_item_saveAs").getTooltip());
        saveAs.setMnemonic(localizationManager.getString("menu_item_saveAs").getMnemonicKeyCode());
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(saveAs));
        this.add(saveAs);
        
        
        
        
        JMenuItem closeProject = new JMenuItem(localizationManager.getString("menu_item_close").getName());
        closeProject.setEnabled(false);
        closeProject.addActionListener(new CloseProjectAction(tabManager));
        closeProject.setToolTipText(localizationManager.getString("menu_item_close").getTooltip());
        closeProject.setMnemonic(localizationManager.getString("menu_item_close").getMnemonicKeyCode());
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(closeProject));
        this.add(closeProject);
        
        this.addSeparator();
        
        JMenuItem exit = new JMenuItem(localizationManager.getString("menu_item_exit").getName());
        exit.addActionListener(new ExitAction(localizationManager, tabManager));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));

        this.add(exit);
        
    }
    
}
