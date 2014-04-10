package cz.cvut.fit.project.noted.rendering;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
    }
    
    /**
     * Removes the tab at the given index.
     * @param index 
     */
    public void removeTab(int index)
    {
        this.tabs.removeTabAt(index);
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
    
    
    
    
    
    
    
    
    
}
