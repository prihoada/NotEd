
package cz.cvut.fit.project.noted.musicrenderer.glyphs.layout;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.StaveGlyph;

/**
 * This class handles the staff layout calculations. Different subclasses can produce different layouts. For example a 1 page layout, 2 page layout or a one line layout.
 * @author Adam Příhoda
 */
public class BaseStaffLayoutEngine {
    
    private final StaveGlyph stave;
    
    
    /**
     * should be set to true if this engine finished calculating.
     */
    private boolean finished = false;
    
    
    
    public BaseStaffLayoutEngine(StaveGlyph stave) {
        this.stave = stave;
        
    }

    
    
    /**
     * Resets the layout calculation process.
     */
    public void reset()
    {
        finished = false;
    }
    
    
    
    
    /**
     * Returns a line of the stave.
     * Base implementation returns a single line.
     * @return StaveGlyph representing a single line of the underlying stave or null if all lines have been returned.
     */
    public StaveGlyph getStaveLine()
    {
        
        if(finished) return null;
        
        int finalWidth = 0;
        for (BarGlyph glyph : stave.getGlyphs()) {
            
            glyph.doLayout();
            
            finalWidth += glyph.getPadding();
            glyph.setX(finalWidth);
            
            finalWidth += (glyph.getGlyphWidth() + glyph.getPadding());
        }
        
        stave.setGlyphWidth(finalWidth);
        
        finished = true;
        return stave;
    }
    
    
}
