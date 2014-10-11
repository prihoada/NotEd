

package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.PlayAction;
import cz.cvut.fit.project.noted.toolbars.actions.StopAction;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Adam Příhoda
 */
public class Toolbar extends JToolBar
{

    public Toolbar(TabManager tabManager, String name)
    {
        super(name);
        
        this.setRollover(true);
        this.setFloatable(true);
        
        JButton playButton = new JButton(new ImageIcon("assets/images/playButton.png"));
        playButton.setFocusPainted(false);
        this.add(playButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(playButton));
        playButton.addActionListener(new PlayAction(tabManager));
        
        JButton stopButton = new JButton(new ImageIcon("assets/images/stopButton.png"));
        stopButton.setFocusPainted(false);
        this.add(stopButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(stopButton));
        stopButton.addActionListener(new StopAction(tabManager));
        
        JButton addNote = new JButton(new ImageIcon("assets/images/noteButton.png"));
        addNote.setFocusPainted(false);
        this.add(addNote);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addNote));
        //addNote.addActionListener(new AddNote(tabManager));
    }
    
}
