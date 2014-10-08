
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.PositionGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.handlers.BeamHandler;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A complete note with accidentals, effects, stem and beaming.
 * @author Adam Příhoda
 */
public class NoteGlyph extends PositionGlyph
{

    private NoteHeadGlyph noteHead;
    private BeamHandler beamHandler;
    private HelperLinesGlyph helperLines;
    private final ArrayList<AccidentalGlyph> accidentals = new ArrayList<>();
    private Duration duration;
    
    public NoteGlyph(Duration duration, int positionY)
    {
        this.duration = duration;
        this.helperLines = new HelperLinesGlyph();
        setPositionY(positionY);
        
        this.noteHead = new NoteHeadGlyph(duration);
        this.beamHandler = new BeamHandler(getPositionY() >= 0 ? BeamDirection.UP : BeamDirection.DOWN, noteHead);
        
    }

    
    
    
    @Override
    public void doLayout() {
    
        noteHead.doLayout();
        
        int accidentalsWidth = 0;
        for (AccidentalGlyph accidental : accidentals) {
            accidental.doLayout();
            accidental.setX(accidentalsWidth);
            accidentalsWidth += accidental.getGlyphWidth();
        }

        noteHead.setX(accidentalsWidth);
        helperLines.setSymbolWidth(noteHead.getSymbolWidth());
        
        setGlyphWidth(noteHead.getGlyphWidth() + accidentalsWidth);

        //calculate the properties of the footer/beam, but only for the last note in the beam group. This prevents unnecessary calculations.
        if(beamHandler.isNoteLast(noteHead)) beamHandler.doLayout();
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {

        //paint the line helpers
        helperLines.paint(x + noteHead.getX(), y, g);    
        
        for (AccidentalGlyph accidental : accidentals) {
            accidental.paint(x + accidental.getX(), y, g);
        }
        
        //paint the note head
        noteHead.paint(x + noteHead.getX(), y, g);
        
        //paint the beam only once.
        if(beamHandler.getNoteIndex(noteHead) == 0) beamHandler.paint(x + noteHead.getX(), y, g);
    }
    
    
        
    /**
     * Returns the beam handler. Beam handler is responsible for painting and layouting beams and stems.
     * @return BeamHandler attached to the note.
     */
    public BeamHandler getBeamHandler() {
        return beamHandler;
    }

    public void setBeamHandler(BeamHandler beamHandler) {
        this.beamHandler = beamHandler;
    }
    

    
    
    
    /**
     * Adds an accidental to the note. Multiple accidentals stack from left to right.
     * @param accidental accidental to add.
     */
    public void addAccidental(AccidentalGlyph accidental)
    {
        this.accidentals.add(accidental);
    }
    
    /**
     * Removes all accidentals from the note.
     */
    public void clearAccidentals()
    {
        this.accidentals.clear();
    }
    
    
    
      /**
     * Returns the duration of the note. @see Duration for values.
     * @return duration of the note.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the note. @see Duration for values.
     * @param duration new wduration of the note.
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void setPositionY(int positionY) {
        super.setPositionY(positionY);
        helperLines.setPositionY(positionY);
    }
    
    
    
    
}
