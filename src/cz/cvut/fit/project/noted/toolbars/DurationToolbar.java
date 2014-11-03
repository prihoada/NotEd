package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.AddClef;
import cz.cvut.fit.project.noted.toolbars.actions.AddNote;
import cz.cvut.fit.project.noted.toolbars.actions.AddRest;
import cz.cvut.fit.project.noted.toolbars.actions.SetDuration;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author david
 */
public final class DurationToolbar extends JToolBar
{
    private Duration duration;

    public DurationToolbar(TabManager tabManager, String name)
    {
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
        
        Dimension buttonSize = new Dimension(40, 40);
        
        GlyphToggleButton Whole = new GlyphToggleButton();
            Whole.setGlyph(new NoteGlyph(Duration.Whole, 1));
            Whole.setPreferredSize(buttonSize);
            
        GlyphToggleButton Half = new GlyphToggleButton();
            Half.setGlyph(new NoteGlyph(Duration.Half, 4));
            Half.setGlyphScale(0.9f);
            Half.setPreferredSize(buttonSize);
            
        GlyphToggleButton Quarter = new GlyphToggleButton();
            Quarter.setGlyph(new NoteGlyph(Duration.Quarter, 4));
            Quarter.setGlyphScale(0.9f);
            Quarter.setPreferredSize(buttonSize);
        
        GlyphToggleButton Eighth = new GlyphToggleButton();
            Eighth.setGlyph(new NoteGlyph(Duration.Eighth, 4));
            Eighth.setGlyphScale(0.8f);
            Eighth.setPreferredSize(buttonSize);
            
        GlyphToggleButton Sixteenth = new GlyphToggleButton();
            Sixteenth.setGlyph(new NoteGlyph(Duration.Sixteenth, 4));
            Sixteenth.setGlyphScale(0.8f);
            Sixteenth.setPreferredSize(buttonSize);
            
        GlyphToggleButton ThirtySecond = new GlyphToggleButton();
            ThirtySecond.setGlyph(new NoteGlyph(Duration.ThirtySecond, 4));
            ThirtySecond.setGlyphScale(0.7f);
            ThirtySecond.getGlyph().setY(10);
            ThirtySecond.setPreferredSize(buttonSize);
            
        GlyphToggleButton SixtyFourth = new GlyphToggleButton();
            SixtyFourth.setGlyph(new NoteGlyph(Duration.SixtyFourth, 4));
            SixtyFourth.getGlyph().setY(10);
            SixtyFourth.setGlyphScale(0.7f);
            SixtyFourth.setPreferredSize(buttonSize);
            
        Quarter.setSelected(true);
        this.setDuration(Duration.Quarter);
        
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
        
        Component[] components = getComponents();
        for (Component component : components) {
            component.setFocusable(false);
        }
        
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
