
package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.AddClef;
import cz.cvut.fit.project.noted.toolbars.actions.AddNote;
import cz.cvut.fit.project.noted.toolbars.actions.AddSpace;
import cz.cvut.fit.project.noted.toolbars.actions.PlayAction;
import cz.cvut.fit.project.noted.toolbars.actions.StopAction;
import cz.cvut.fit.project.noted.toolbars.actions.SetDuration;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

/**
 *
 * @author Adam Příhoda
 */
public class Toolbar extends JToolBar
{
    Duration duration;
    
    public Toolbar(TabManager tabManager, String name)
    {   
        super(name);
        
        this.setRollover(true);
        this.setFloatable(true);
        
        
        //PLAY
        JButton playButton = new JButton(new ImageIcon("assets/images/playButton.png"));
        playButton.setFocusPainted(false);
        this.add(playButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(playButton));
        playButton.addActionListener(new PlayAction(tabManager));
        
        
        //STOP
        JButton stopButton = new JButton(new ImageIcon("assets/images/stopButton.png"));
        stopButton.setFocusPainted(false);
        this.add(stopButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(stopButton));
        stopButton.addActionListener(new StopAction(tabManager));
        
        
        //DURATION OF THE NOTE
        String[] durations = { "Eighth", "Half", "Quarter", "Sixteenth",
            "SixtyFourth", "ThirtySecond", "Whole" };
        
        JComboBox durationsBox = new JComboBox(durations);
        durationsBox.setSelectedIndex(2); // nastaveni defaultni (nejcasteji pouzivane noty) Adame prenastav!
        this.add(durationsBox);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(durationsBox));
        durationsBox.addActionListener(new SetDuration(this));
        
        
        //ADD NOTE
        JButton addNote = new JButton(new ImageIcon("assets/images/note.png"));
        addNote.setFocusPainted(false);
        this.add(addNote);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addNote));
        addNote.addActionListener(new AddNote(tabManager, this));
        
        //ADD CLEF
        JButton addClef = new JButton(new ImageIcon("assets/images/key.png"));
        addClef.setFocusPainted(false);
        this.add(addClef);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addClef));
        addClef.addActionListener(new AddClef(tabManager));
        
        //ADD REST
        JButton addRest = new JButton(new ImageIcon("assets/images/space.png"));
        addRest.setFocusPainted(false);
        this.add(addRest);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addRest));
        addRest.addActionListener(new AddSpace(tabManager, duration));
    }
  
    
    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }
    
    public Duration getDuration()
    {
        return this.duration;
    }
    
}
