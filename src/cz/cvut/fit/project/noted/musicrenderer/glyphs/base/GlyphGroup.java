
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Group of glyphs. Can be a whole score or just a bar.
 * @author Adam Příhoda
 */
public class GlyphGroup extends Glyph
{
    
    protected ArrayList<Glyph> glyphs = new ArrayList<>();


    /**
     * Calculates the width of the glyph and position of all children.
     */
    @Override
    public void doLayout() {
        
        int finalWidth = 0;
        for (Glyph glyph : glyphs) {
            
            glyph.doLayout();
            
            finalWidth += glyph.getPadding();
            glyph.setX(finalWidth);
            
            finalWidth += (glyph.getGlyphWidth() + glyph.getPadding());
        }
        
        setGlyphWidth(finalWidth);
    }
    
    
    
    /**
     * Adds a glyph to the end of the bar.
     * @param glyph 
     */
    public void addGlyph(Glyph glyph)
    {
        this.glyphs.add(glyph);
        doLayout();
    }
    /**
     * Removes the given glyph if present.
     * @param glyph Glyph to remove.
     */
    public void removeGlyph(Glyph glyph)
    {
        boolean removed = this.glyphs.remove(glyph);
        if(removed) doLayout();
    }

    
    /**
     * Paints the group. Note that children are painted using their x and y coordinates.
     * @param x global x offset passed from a parent.
     * @param y global y offset passed from a parent.
     * @param g 
     */
    @Override
    public void paint(int x, int y, Graphics2D g) {
        
        for (Glyph glyph : glyphs) {
            glyph.paint(x + glyph.getX(), y + getY() + glyph.getY(), g);
        }
        
    }
    
}
