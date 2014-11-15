package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import java.awt.Graphics2D;

/**
 * An empty glyph used as a graphics or index fill.
 * @author Adam Příhoda
 */
public class EmptyGlyph extends Glyph
{

    public EmptyGlyph() {
        this(0, 0);
    }

    public EmptyGlyph(int symbolWidth, int glyphWidth) {
        
        setSymbolWidth(symbolWidth);
        setGlyphWidth(glyphWidth);
    }
    

    @Override
    public void doLayout()
    {
        /** empty **/
    }

    @Override
    public void paint(int x, int y, Graphics2D g)
    {
        /** empty **/
    }
    
}
