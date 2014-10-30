/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.GlyphGroup;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.SvgGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.TimeSignature;

/**
 *
 * @author Adam Příhoda
 */
public class TimeSignatureGlyph extends GlyphGroup<SvgGlyph>
{
    
    private TimeSignature type;
    
    
    public TimeSignatureGlyph(TimeSignature type) {
        
        this.type = type;
        throw new UnsupportedOperationException("Other time signature types not yet supported.");
    }
    public TimeSignatureGlyph(int topNumber, int bottomNumber)
    {
        this.type = TimeSignature.CUSTOM;
        
        this.addGlyph(new NumberGlyph(topNumber));
        this.addGlyph(new NumberGlyph(bottomNumber));
        
    }
    
    @Override
    public void doLayout() {
        for (SvgGlyph glyph : glyphs) {
            
            glyph.doLayout();
            int width = 0;
            width += glyph.getPadding();
            glyph.setX(width);
            width += (glyph.getGlyphWidth() + glyph.getPadding());
            
            if(this.getGlyphWidth() < width) this.setGlyphWidth(width);
        }
        
        switch (type)
        {
            case CUSTOM:
            
                SvgGlyph topNumber = getGlyphs().get(0);
                topNumber.setY(-19);
                
                break;
        }
    }

    
}
