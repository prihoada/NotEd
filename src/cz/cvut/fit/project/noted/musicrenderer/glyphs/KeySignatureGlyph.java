
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.GlyphGroup;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;

/**
 * Glyph displaying a key signature.
 * @author Adam Příhoda
 */
public class KeySignatureGlyph extends GlyphGroup<SvgGlyph>
{

    private final AccidentalType type;
    private int number;
    
    
    /**
     * Line offsets for minor keys.
     */
    private static final int [] minorOffsets = {5,2,6,3,7,4,8};
    /**
     * Line offsets for major keys
     */
    private static final int [] majorOffsets = {1,4,0,3,6,2,5};
    
    public KeySignatureGlyph(int numberOfSymbols, AccidentalType type) {
        this.number = numberOfSymbols;
        
        if(number < 0) number = 0;
        if(number > 7) number = 7;
        
        this.type = type;
        
        for (int i = 0; i < number; i++) this.addGlyph(new AccidentalGlyph(type));
        
    }
    
    
    
    
    @Override
    public void doLayout() {
        
        super.doLayout();
        
        //pick the right offsets.
        int [] offsets;
        switch (type)
        {
            case Flat:
                offsets = minorOffsets;
                break;
            case Sharp:
                offsets = majorOffsets;
                break;
            default:
                offsets = majorOffsets;
        }
        
        for (int i = 0; i < glyphs.size(); i++) {
            SvgGlyph glyph = glyphs.get(i);
         
            //position the glyphs on the proper line.
            glyph.setY((int) (offsets[i] * 4.5f) - 26);
        }
    }
}
