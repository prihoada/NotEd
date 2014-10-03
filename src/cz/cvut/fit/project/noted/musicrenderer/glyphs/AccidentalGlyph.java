
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * Accidental is a note height modifier. Sharp, flat, natural and so on.
 * @author Adam Příhoda
 */
public class AccidentalGlyph extends SvgGlyph
{
    
    private AccidentalType type;

    public AccidentalGlyph(AccidentalType type) {
        this.type = type;
        setSymbol(getAccidentalSymbol(type));
    }

    @Override
    public void doLayout() {

        //set the offsets of the symbol. This is done to align the svg to its bounding box.
        switch (type)
        {
            case None:
                break;
            case Natural:
                setSymbolWidth(8);
                offsetY = 5;
                break;
            case Sharp:
            case Flat:
                setSymbolWidth(8);
                break;
            case DoubleFlat:
            case DoubleSharp:
                setSymbolWidth(13);
                offsetX = 1;
                break;

        }
        
        setGlyphWidth(symbolWidth + 3);
                
    }
    
    
    

    private SvgSymbol getAccidentalSymbol(AccidentalType type) {

        switch (type)
        {
            case None:
                 return null;
            case Natural:
                return SVGRepository.AccidentalNatural;
            case Sharp:
                return SVGRepository.AccidentalSharp;
            case Flat:
                return SVGRepository.AccidentalFlat;
            case DoubleFlat:
                return SVGRepository.AccidentalDoubleFlat;
            case DoubleSharp:
                return SVGRepository.AccidentalDoubleSharp;
        }
        return null;
    }
    
}
