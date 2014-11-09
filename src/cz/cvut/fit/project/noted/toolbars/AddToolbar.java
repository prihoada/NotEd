
package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarSeparator;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.ClefGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.RestGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.BarSeparatorType;
import cz.cvut.fit.project.noted.musicrenderer.model.Clef;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import cz.cvut.fit.project.noted.toolbars.GlyphButton;
import cz.cvut.fit.project.noted.toolbars.actions.AddClef;
import cz.cvut.fit.project.noted.toolbars.actions.AddMeasure;
import cz.cvut.fit.project.noted.toolbars.actions.AddNote;
import cz.cvut.fit.project.noted.toolbars.actions.AddRest;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Toolbar for adding various symbols.
 * @author Adam Příhoda
 */
public class AddToolbar extends JToolBar
{

    public AddToolbar(TabManager tabManager, DurationToolbar durationToolbar, String name) {
    
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
    
        
        Dimension buttonSize = new Dimension(40, 40);
        
        this.addSeparator();
        
        //ADD NOTE
        GlyphButton addNote = new GlyphButton();
            addNote.setGlyph(new NoteGlyph(Duration.Quarter, 4));
            addNote.setGlyphScale(0.9f);
            addNote.setPreferredSize(buttonSize);
            this.add(addNote);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addNote));
            addNote.addActionListener(new AddNote(tabManager, durationToolbar));
        
        //ADD REST
        GlyphButton addRest = new GlyphButton();
            addRest.setGlyph(new RestGlyph(Duration.Quarter));
            this.add(addRest);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addRest));
            addRest.setPreferredSize(new Dimension(40, 40));
            addRest.addActionListener(new AddRest(tabManager, durationToolbar));
        
        //ADD CLEF
        GlyphButton addClef = new GlyphButton();
            addClef.setGlyph(new ClefGlyph(Clef.G2));
            addClef.setGlyphScale(0.5f);
            addClef.setPreferredSize(new Dimension(40, 40));
            this.add(addClef);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addClef));
            addClef.addActionListener(new AddClef(tabManager));
            
        //MEASURE
         GlyphButton addMeasure = new GlyphButton();
         
            //rozbite
            //addMeasure.setGlyph(new BarSeparator(BarSeparatorType.SINGLE));
         
            //docasne cizi ikonka
            addMeasure.setGlyph(new ClefGlyph(Clef.G2));
            
            addMeasure.setGlyphScale(0.5f);
            addMeasure.setPreferredSize(new Dimension(40, 40));
            this.add(addMeasure);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(addMeasure));
            addMeasure.addActionListener(new AddMeasure(tabManager));   
        
        
        Component[] components = getComponents();
        for (Component component : components) {
            component.setFocusable(false);
        }
    }
    
}
