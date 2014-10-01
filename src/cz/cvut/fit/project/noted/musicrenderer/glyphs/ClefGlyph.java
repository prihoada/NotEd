package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.Clef;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * 
 * @author Adam Příhoda
 */
public class ClefGlyph extends SvgGlyph
{
    private final Clef clef;

    public ClefGlyph(Clef clef) {
        
        this.clef = clef;
        
        setSymbol(getClefSymbol(clef));
        
    }

    @Override
    public void doLayout() {
        setSymbolWidth(27);
        
        switch (clef)
        {
            case Neutral:
                offsetY = -9;
                break;
            case C3:
                break;
            case C4:
                offsetY = -9;
                break;
            case G2:
                offsetX = 2;
                break;
            case F4:
                offsetY = -8;
                break;
        }
        
        setGlyphWidth(symbolWidth + 5);
        setPadding(3);
    }

    
    
    private SvgSymbol getClefSymbol(Clef clef) {
        
        switch (clef)
        {
            case C3:
            case C4:
                return SVGRepository.ClefC;
            case F4:
                return SVGRepository.ClefF;
            case G2:
                return SVGRepository.ClefG;
            case Neutral:
                return SVGRepository.ClefNeutral;
        }
        return SVGRepository.ClefNeutral;
    }
    
}
