
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * Symbol representing the note duration.
 * @author Adam Příhoda
 */
public class FooterGlyph extends SvgGlyph
{
    
    private Duration duration;
    private BeamDirection beamDirection;

    public FooterGlyph(Duration duration, BeamDirection beamDirection) {
        this.duration = duration;
        this.beamDirection = beamDirection;
        
        setSymbol(getFooterSvg());
        
        switch (beamDirection)
        {
            case DOWN:
                scaleY *= -1;
                break;
        }
    }

    @Override
    public void doLayout() {

        
        switch (duration)
        {
            case Eighth:
                offsetY = 0;
                break;
            case Sixteenth:
                offsetY = 0;
                break;
            case ThirtySecond:
                offsetY = -3;
                break;
            case SixtyFourth:
                offsetY = -7;
                break;
            
            
            default:
                
                break;
        }
        
        setSymbolWidth(11);
        setGlyphWidth(symbolWidth);
        
    }

    
    
    
    
    private SvgSymbol getFooterSvg() {

        switch (duration)
        {
            case Eighth:
                return SVGRepository.FooterUpEighth;
            case Sixteenth:
                return SVGRepository.FooterUpSixteenth;
            case ThirtySecond:
                return SVGRepository.FooterUpThirtySecond;
            case SixtyFourth:
                return SVGRepository.FooterUpSixtyFourth;
        }
        return null;
    }
    
    
    
}
