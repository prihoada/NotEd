
package cz.cvut.fit.project.noted.musicrenderer.glyphs.handlers;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.FooterGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteHeadGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Handler for note beaming. Beams are a method to visually connect notes into groups.
 * On its own it just paints the stem and the footer for a note. If attached to multiple notes, it connects them.
 * @author Adam Příhoda
 */
public class BeamHandler extends Glyph
{

    
    private ArrayList<NoteHeadGlyph> notes = new ArrayList<>();
    private BeamDirection direction;

    

    public BeamHandler(BeamDirection direction, NoteHeadGlyph note) {
        notes.add(note);
        this.direction = direction;
    }
    
    public BeamHandler()
    {
        
    }
    
    
    /**
     * Returns the position of the given note in this beam group.
     * @param note 
     */
    public int getNoteIndex(NoteHeadGlyph note)
    {
        return notes.indexOf(note);
    }
    
    /**
     * Checks whether if the given note is the last one in this beam group.
     * @param note
     * @return true if the given note is the last in this group.
     */
    public boolean isNoteLast(NoteHeadGlyph note)
    {
        return notes.get(notes.size() - 1) == note;
    }
            
    
    
    @Override
    public void doLayout() {
    
        
    
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {

        //paint a simple footer
        if(notes.size() == 1)
        {
            NoteHeadGlyph note = notes.get(0);
            
            //a whole note does not have a stem or a footer
            if(note.getDuration() != Duration.Whole)
            {
                
                //paint the stem
                g.drawLine(x + note.getSymbolWidth() - 1,
                           y + note.getY(),
                           x + note.getSymbolWidth(),
                           y + note.getY() -30);
                
                
                //half and quarter notes don't have a footer
                if(note.getDuration() != Duration.Half && note.getDuration() != Duration.Quarter)
                {
                    FooterGlyph footer = new FooterGlyph(note.getDuration(), this.direction);
                    footer.setY(note.getY() - 30);
                    footer.doLayout();
                    footer.paint(x + note.getSymbolWidth(), y - 30, g);
                }
                
                
            }
            
            
            
        }
        else
        {
            //TODO
        }
    
    
    }
    
    
    
    
    
    
    public BeamDirection getDirection() {
        return direction;
    }

    public void setDirection(BeamDirection direction) {
        this.direction = direction;
    }
    
}
