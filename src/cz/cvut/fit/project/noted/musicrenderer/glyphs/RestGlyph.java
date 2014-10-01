package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 *
 * @author Adam Příhoda
 */
public class RestGlyph extends SvgGlyph
{
    
    private final Duration duration;

    public RestGlyph(Duration duration) {
        
        this.duration = duration;
        setSymbol(getRestSvg(duration));
    }

    @Override
    public void doLayout() {
        
        switch(duration)
        {
            case Whole:
                setSymbolWidth(13);
                offsetY = -8;
                offsetX = 2;
                break;
            case Half:
                setSymbolWidth(13);
                offsetY = -4;
                offsetX = 2;
                break;
            case Quarter:
                setSymbolWidth(12);
                offsetX = 1;
                offsetY = 2;
                break;
            case Eighth:
                setSymbolWidth(12);
                offsetY = 8;
                break;
            case Sixteenth:
                setSymbolWidth(14);
                offsetY = 14;
                break;
            case ThirtySecond:
                setSymbolWidth(16);
                offsetY = 18;
                break;
            case SixtyFourth:
                setSymbolWidth(18);
                offsetY = 23;
                break;
        }
        
        setGlyphWidth(symbolWidth + 5);
    }
    
    
    
    private SvgSymbol getRestSvg(Duration duration) 
    {
        switch(duration)
        {
            case Whole:
            case Half: 
                return SVGRepository.RestWhole;
            case Quarter:
                return SVGRepository.RestQuarter;
            case Eighth:
                return SVGRepository.RestEighth;
            case Sixteenth:
                return SVGRepository.RestSixteenth;
            case ThirtySecond:
                return SVGRepository.RestThirtySecond;
            case SixtyFourth:
                return SVGRepository.RestSixtyFourth;
        }
        return null;
    }
    
    
}
