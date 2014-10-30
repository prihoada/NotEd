
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.svg.SVGRepository;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * Glyph displaying a number for the time signature
 * @author Adam Příhoda
 */
public class NumberGlyph extends SvgGlyph
{
    private int number;

    public NumberGlyph(int number) {
        this.number = number;
        
       this.setSymbol(getNumberSymbol(number));
        
    }

    @Override
    public void doLayout() {
        super.doLayout();
        
        setSymbolWidth(12);
        setGlyphWidth(20);
        
    }
    
    private SvgSymbol getNumberSymbol(int number) {
        
        if(number < 0 ||  number > 9) return SVGRepository.Num0;
        return SVGRepository.numbers[number];
    }
}
