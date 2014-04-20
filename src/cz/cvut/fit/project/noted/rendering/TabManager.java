package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
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
    
    private static TabManager instance;
    public static TabManager getInstance()
    {
        if(instance == null) instance = new TabManager();
        return instance;
    }

    
    private final JTabbedPane tabs;
    
    private TabManager()
    {
        this.setLayout(new BorderLayout());
        this.tabs = new JTabbedPane();
        this.tabs.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        this.add(tabs, BorderLayout.CENTER);
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
        this.tabs.setTabComponentAt(this.tabs.getSelectedIndex(), new TabTitleComponent(tabs));
    }
    
    /**
     * Removes the tab at the given index without checking file save state. (For that use closeTab() instead)
     * @param index index of the tab to close.
     */
    public void removeTab(int index)
    {
        this.tabs.removeTabAt(index);
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
                    LocalizationManager.getInstance().getString("confirm_tab_close").getTooltip(),
                    LocalizationManager.getInstance().getString("confirm_tab_close").getName(),
                    JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(result == JOptionPane.YES_OPTION)
            {
                //TODO
                //open save dialog
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
    
}