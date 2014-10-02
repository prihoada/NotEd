
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


        switch (beamDirection)
        {
            case UP:
                
                switch (duration)
                {
                    case Eighth:
                        offsetY = 0;
                        offsetX = -0.2f;
                        break;
                    case Sixteenth:
                        offsetY = 0;
                        offsetX = -0.2f;
                        break;
                    case ThirtySecond:
                        offsetY = -3;
                        offsetX = -0.5;
                        break;
                    case SixtyFourth:
                        offsetY = -7;
                        offsetX = -0.5;
                        break;
                }
            break;
                
            case DOWN:
                
                switch (duration)
                {
                    case Eighth:
                        offsetY = 0;
                        offsetX = -0.2f;
                        break;
                    case Sixteenth:
                        offsetY = 3;
                        offsetX = -0.2;
                        break;
                    case ThirtySecond:
                        offsetY = 7;
                        offsetX = -0.4;
                        break;
                    case SixtyFourth:
                        offsetY = 15;
                        offsetX = -0.5;
                        break;
                }
            
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
