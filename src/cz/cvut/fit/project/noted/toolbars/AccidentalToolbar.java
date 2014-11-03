package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.AccidentalGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

/**
 * Toolbar providing accidental selection.
 * @author Adam Příhoda
 */
public class AccidentalToolbar extends JToolBar
{

    private AccidentalType selectedType = null;
    private final ButtonGroup group;
    
    public AccidentalToolbar(TabManager tabManager, String name) {
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
        
        Dimension buttonSize = new Dimension(40, 40);
        group = new ButtonGroup();
        
        this.addSeparator();
        
        GlyphToggleButton sharp = new GlyphToggleButton();
            sharp.setGlyph(new AccidentalGlyph(AccidentalType.Sharp));
            sharp.setPreferredSize(buttonSize);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(sharp));
            sharp.addActionListener(new SelectAccidentalListener(AccidentalType.Sharp, this));
            this.add(sharp);
            group.add(sharp);
            
        GlyphToggleButton flat = new GlyphToggleButton();
            flat.setGlyph(new AccidentalGlyph(AccidentalType.Flat));
            flat.setPreferredSize(buttonSize);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(flat));
            flat.addActionListener(new SelectAccidentalListener(AccidentalType.Flat, this));
            this.add(flat);
            group.add(flat);
            
        GlyphToggleButton natural = new GlyphToggleButton();
            natural.setGlyph(new AccidentalGlyph(AccidentalType.Natural));
            natural.setPreferredSize(buttonSize);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(natural));
            natural.addActionListener(new SelectAccidentalListener(AccidentalType.Natural, this));
            this.add(natural);
            group.add(natural);
            
        GlyphToggleButton doubleSharp = new GlyphToggleButton();
            doubleSharp.setGlyph(new AccidentalGlyph(AccidentalType.DoubleSharp));
            doubleSharp.setPreferredSize(buttonSize);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(doubleSharp));
            doubleSharp.addActionListener(new SelectAccidentalListener(AccidentalType.DoubleSharp, this));
            this.add(doubleSharp);
            group.add(doubleSharp);
            
        GlyphToggleButton doubleFlat = new GlyphToggleButton();
            doubleFlat.setGlyph(new AccidentalGlyph(AccidentalType.DoubleFlat));
            doubleFlat.setPreferredSize(buttonSize);
            tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(doubleFlat));
            doubleFlat.addActionListener(new SelectAccidentalListener(AccidentalType.DoubleFlat, this));
            this.add(doubleFlat);
            group.add(doubleFlat);
        

        Component[] components = getComponents();
        for (Component component : components) {
            component.setFocusable(false);
        }
    }

    /**
     * Unselects the currently selected type.
     */
    public void unselect()
    {
        this.selectedType = null;
        group.clearSelection();
    }
    
    
    private static class SelectAccidentalListener implements ActionListener {
        private final AccidentalType accidentalType;
        private final AccidentalToolbar toolbar;

        public SelectAccidentalListener(AccidentalType accidentalType, AccidentalToolbar toolbar) {
            this.accidentalType = accidentalType;
            this.toolbar = toolbar;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(toolbar.getSelectedType() == accidentalType)
            {
                toolbar.unselect();
            }
            else
            {
                toolbar.setSelectedType(accidentalType);
            }
        }
    }
    
    
    public AccidentalType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(AccidentalType selectedType) {
        this.selectedType = selectedType;
    }
    
    
}
