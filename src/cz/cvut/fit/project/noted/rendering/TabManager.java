package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.modelplayer.Player;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

/**
 * Top level container that handles tabs.
 * @author Adam Příhoda
 */
public class TabManager extends JPanel
{

    private final JTabbedPane tabs;

    private final LocalizationManager localizationManager;
    private Player player;
    
    public TabManager(LocalizationManager localizationManager)
    {
        this.localizationManager = localizationManager;
        this.player = new Player();
        
        this.setLayout(new BorderLayout());
        this.tabs = new JTabbedPane();
        this.tabs.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        this.add(tabs, BorderLayout.CENTER);
        
        tabs.setFocusable(false);
    }
    
    /**
     * Adds a tab to the tabbed pane.
     * @param title Title of the tab.
     * @param tab Tab to add.
     */
    public void addTab(String title, Tab tab)
    {
        this.tabs.addTab(title, tab);
        this.tabs.setSelectedComponent(tab);
        this.tabs.setTabComponentAt(this.tabs.getSelectedIndex(), new TabTitleComponent(tabs, this, localizationManager));
    }
    
    /**
     * Removes the tab at the given index without checking file save state. (For that use closeTab() instead)
     * @param index index of the tab to close.
     */
    public void removeTab(int index)
    {
        this.tabs.removeTabAt(index);
    }
    
    public String getTitleAt(int index)
    {
        return tabs.getTitleAt(index);
    }
    public void setTitleAt(int index, String title)
    {
        tabs.getTabComponentAt(index).invalidate();
        tabs.setTitleAt(index, title);
    }
    
    /**
     * Attempts to close the tab. May prompt for file save.
     * @param index index of the tab to close.
     */
    public void closeTab(int index)
    {
        Tab tab = (Tab)tabs.getComponentAt(index);
        if(tab.isSaved())
        {
            removeTab(index);
        }
        else
        {
            int result = JOptionPane.showConfirmDialog(this,
                    localizationManager.getString("confirm_tab_close").getTooltip(),
                    localizationManager.getString("confirm_tab_close").getName(),
                    JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(result == JOptionPane.YES_OPTION)
            {
                tab.save(false);
            }
            else if(result == JOptionPane.NO_OPTION)
            {
                removeTab(index);
            }
        }
    }
    
    /**
     * Returns the currently selected tab or null if no tabs are selected.
     * @return currently selected tab or null if no tabs are selected.
     */
    public Tab getActiveTab()
    {
        return (Tab) tabs.getSelectedComponent();
    }
    /**
     * Returns the currently selected index.
     * @return index of the selected tab or -1 if no tabs are selected.
     */
    public int getActiveTabIndex()
    {
        return this.tabs.getSelectedIndex();
    }
    
    public Tab getTabAt(int index)
    {
        return (Tab) tabs.getComponentAt(index);
    }
    public int getTabIndex(Tab tab)
    {
        return tabs.indexOfComponent(tab);
    }
    public int getTabCount()
    {
        return tabs.getTabCount();
    }
    /**
     * Adds a change listener to the tabbed pane.
     * @param listener listener to add.
     */
    public void addChangeListener(ChangeListener listener)
    {
        this.tabs.addChangeListener(listener);
    }
    /**
     * Removes a change listener from the tabbed pane.
     * @param listener listener to remove.
     */
    public void removeChangeListener(ChangeListener listener)
    {
        this.tabs.removeChangeListener(listener);
    }

    /**
     * Returns the music player.
     * @return 
     */
    public Player getPlayer() {
        return player;
    }
}
