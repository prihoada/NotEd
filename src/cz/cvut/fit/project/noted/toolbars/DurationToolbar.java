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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import static jm.music.tools.ga.NormalDistributionFE.duration;

/**
 *
 * @author david
 */
public class DurationToolbar extends JToolBar
{
    Duration duration;

    public DurationToolbar(TabManager tabManager, String name)
    {
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
        
        //DURATION
        JRadioButton Whole = new JRadioButton(new ImageIcon("assets/images/1.png"));
        JRadioButton Half = new JRadioButton(new ImageIcon("assets/images/1d2.png"));
        JRadioButton Quarter = new JRadioButton(new ImageIcon("assets/images/1d4.png"));
        JRadioButton Eighth = new JRadioButton(new ImageIcon("assets/images/1d8.png"));
        JRadioButton Sixteenth = new JRadioButton(new ImageIcon("assets/images/1d16.png"));
        JRadioButton ThirtySecond = new JRadioButton(new ImageIcon("assets/images/1d32.png"));
        JRadioButton SixtyFourth = new JRadioButton(new ImageIcon("assets/images/1d64.png"));        
        
        //NASTAVENI NEJCASTEJI POUZIVANE NOTY JAKO DEFAULT-Adame prenastav, nevim ktera..
        Whole.setSelected(true);
        this.setDuration(Duration.Whole);
        
        ButtonGroup group = new ButtonGroup();
        group.add(Whole);
        group.add(Half);
        group.add(Quarter);
        group.add(Eighth);
        group.add(Sixteenth);
        group.add(ThirtySecond);
        group.add(SixtyFourth);
        
        this.add(Whole);
        this.add(Half);
        this.add(Quarter);
        this.add(Eighth);
        this.add(Sixteenth);
        this.add(ThirtySecond);
        this.add(SixtyFourth);
        
        Whole.addActionListener(new SetDuration(this, "Whole"));
        Half.addActionListener(new SetDuration(this,"Half"));
        Quarter.addActionListener(new SetDuration(this,"Quarter"));
        Eighth.addActionListener(new SetDuration(this,"Eighth"));
        Sixteenth.addActionListener(new SetDuration(this,"Sixteenth"));
        ThirtySecond.addActionListener(new SetDuration(this,"ThirtySecond"));
        SixtyFourth.addActionListener(new SetDuration(this,"SixtyFourth"));
        
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
