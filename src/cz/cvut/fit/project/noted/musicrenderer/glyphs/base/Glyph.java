
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import java.awt.Graphics2D;

/**
 * Base class for all visual elements.
 * @author Adam Příhoda
 */
public abstract class Glyph {
    
    
    private int x;
    private int y;
    protected int symbolWidth;
    protected int glyphWidth;
    protected int padding;

   
    
    public Glyph()
    {
        this(0, 0);
    }
    public Glyph(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * Calculates and sets the width of this glyph.
     */
    public abstract void doLayout();    

    /**
     * Paints the glyph.
     * @param x starting x coordinate.
     * @param y starting y coordinate
     * @param g graphics to paint to.
     */
    public abstract void paint(int x, int y, Graphics2D g);
    
    
    
    
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the symbol. Symbol is the graphical element itself.
     * @return width of the symbol.
     */
    public int getSymbolWidth() {
        return symbolWidth;
    }

    /**
     * /**
     * Sets the width of the symbol. Symbol is the graphical element itself.
     * @param symbolWidth 
     */
    protected void setSymbolWidth(int symbolWidth) {
        this.symbolWidth = symbolWidth;
    }
    
    /**
     * Returns the glyph padding. Padding is a space on the sides of the symbol.
     * @return the glyph padding.
     */
    public int getPadding() {
        return padding;
    }

    /**
     * Sets the glyph padding. Padding is a space on the sides of the symbol.
     * @param padding 
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }

    
    /**
     * Return the width of the glyph. Glyph is considered to be a symbol and its reserved width in the layout.
     * @return width of the glyph
     */
    public int getGlyphWidth() {
        return glyphWidth;
    }

    /**
     * Sets the width of the glyph. Glyph is considered to be a symbol and its reserved width in the layout.
     * @param glyphWidth 
     */
    public void setGlyphWidth(int glyphWidth) {
        this.glyphWidth = glyphWidth;
    }
    
    
    
    
    
}
