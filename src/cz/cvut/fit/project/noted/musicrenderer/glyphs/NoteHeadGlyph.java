package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * Head of a note.
 * @author Adam Příhoda
 */
public class NoteHeadGlyph extends SvgGlyph
{
    private Duration duration;

    public NoteHeadGlyph(Duration duration) {
        this.duration = duration;
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

    public Duration getDuration() {
        return duration;
    }
    
    
    
    
    
}
