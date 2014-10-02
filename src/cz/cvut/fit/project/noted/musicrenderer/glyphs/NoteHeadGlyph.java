package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.handlers.BeamHandler;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

/**
 * Head of a note.
 * @author Adam Příhoda
 */
public class NoteHeadGlyph extends SvgGlyph
{
    private Duration duration;
    private BeamHandler beamHandler;

    

    public NoteHeadGlyph(Duration duration) {
        this.duration = duration;
        
        //debug BeamDirection value.
        this.beamHandler = new BeamHandler(BeamDirection.UP, this);
        
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
        
        //calculate the properties of the footer/beam
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
        
        //paint the noteHead
        super.paint(x, y, g);
        
        //paint the beam only once.
        if(beamHandler.getNoteIndex(this) == 0) beamHandler.paint(x, y, g);
        
    }
    
    
    
    
    public BeamHandler getBeamHandler() {
        return beamHandler;
    }

    public void setBeamHandler(BeamHandler beamHandler) {
        this.beamHandler = beamHandler;
    }
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
}
