
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Group of glyphs. Can be a whole score or just a bar.
 * @author Adam Příhoda
 * @param <T> Class that this group can contain
 */
public class GlyphGroup<T extends Glyph> extends Glyph
{
    
    protected ArrayList<T> glyphs = new ArrayList<>();

    
    /**
     * Calculates the width of the glyph and position of all children.
     */
    @Override
    public void doLayout() {
        
        int finalWidth = 0;
        for (T glyph : glyphs) {
            
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
    public void addGlyph(T glyph)
    {
        this.glyphs.add(glyph);
        glyph.setParent(this);
        doLayout();
    }
    /**
     * Removes the given glyph if present.
     * @param glyph Glyph to remove.
     */
    public void removeGlyph(T glyph)
    {
        boolean removed = this.glyphs.remove(glyph);
        glyph.setParent(null);
        if(removed) doLayout();
    }
    
    public ArrayList<T> getGlyphs() {
        return glyphs;
    }

    /**
     * Paints the group. Note that children are painted using their x and y coordinates.
     * @param x global x offset passed from a parent.
     * @param y global y offset passed from a parent.
     * @param g 
     */
    @Override
    public void paint(int x, int y, Graphics2D g) {
        
        for (T glyph : glyphs) {
            glyph.paint(x + glyph.getX(), y + getY() + glyph.getY(), g);
        }
        
    }
    
}
