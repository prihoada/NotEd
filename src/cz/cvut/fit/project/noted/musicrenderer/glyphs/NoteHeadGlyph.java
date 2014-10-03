package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.handlers.BeamHandler;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;
import java.awt.Graphics2D;

/**
 * Head of a note.
 * @author Adam Příhoda
 */
public class NoteHeadGlyph extends SvgGlyph
{
    private Duration duration;
    private BeamHandler beamHandler;
    /**
     * Line offset of the note. 
     */
    protected int positionY = 0;

    /**
     * Y offset from one note position to the next.
     */
    private final float positionSpacing = 4.5f;
    

    public NoteHeadGlyph(Duration duration) {
        this.duration = duration;
        construct();
    }
    
    public NoteHeadGlyph(Duration duration, int positionY) {
        this.duration = duration;
        setPositionY(positionY);
        construct();
    }
    
    private void construct()
    {
        
        //set the beam direction. Direction depends on the note position.
        this.beamHandler = new BeamHandler(getPositionY() >= 0 ? BeamDirection.UP : BeamDirection.DOWN, this);
        
        setSymbol(getNoteSvg(duration));
    }
    

    @Override
    public void doLayout() {

        switch(duration)
        {
            case Whole: 
                setSymbolWidth(15);
                break;
            default: 
                setSymbolWidth(10);
                break;
        }
        
        
        setGlyphWidth(symbolWidth + 15);
        
        //calculate the properties of the footer/beam, but only for the last note in the beam group. This prevents unnecessary calculations.
        if(beamHandler.isNoteLast(this)) beamHandler.doLayout();
        
    }
    
    
    
    
    private SvgSymbol getNoteSvg(Duration duration) 
    {
        switch(duration)
        {
            case Whole: 
                return SVGRepository.NoteWhole;
            case Half: 
                return SVGRepository.NoteHalf;
            default: 
                return SVGRepository.NoteQuarter;
        }
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {
        
        //paint the line helpers if the note is abowe or below the staff
        if(positionY <= -7 || positionY >= 5) paintLineHelpers(x, y, g);
        
        //paint the noteHead
        super.paint(x, y, g);
        
        //paint the beam only once.
        if(beamHandler.getNoteIndex(this) == 0) beamHandler.paint(x, y, g);
        
    }
    
    
    /**
     * Paints the helper lines if the note is above or below the staff
     * @param x
     * @param y
     * @param g 
     */
    private void paintLineHelpers(int x, int y, Graphics2D g) {
        
        //paint lines above, the first note that needs a line is 7 positions above a1, which has position 0
        if(positionY <= -7) //
        {
            int numLines = ((positionY + 7) / -2) + 1;
            int finalY = (int) (y + positionSpacing + ((-positionY+1) % 2) * positionSpacing);
            for (int i = 0; i < numLines; i++) {
                g.drawLine(x - 5, finalY, x + getSymbolWidth() + 5, finalY);
                finalY += (int)(2*positionSpacing);
            }
        }
        //paint lines below, the first note that needs a line is 5 positions below a1, which has position 0
        else if (positionY >= 5)
        {
            int numLines = ((positionY - 5) / 2) + 1;
            int finalY = (int) (y + (positionY % 2)*positionSpacing) + (positionY %2);
            for (int i = 0; i < numLines; i++) {
                g.drawLine(x - 5, finalY, x + getSymbolWidth() + 5, finalY);
                finalY -= (int)(2*positionSpacing);
            }
        }
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
    
    /**
     * Returns the position of the note. a1 has position 0. h1 has position 1, c2 has position 2 and so on. This position does not change with key or clef.
     * @return position of the note.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Sets the position of the note. a1 has position 0. h1 has position 1, c2 has position 2 and so on. This position does not change with key or clef.
     * @param positionY 
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
        
        this.setY((int) (positionY * positionSpacing));
        
    }


    
}
