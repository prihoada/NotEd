
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

/**
 * Glyph that has a Y position in the staff.
 * @author Adam Příhoda
 */
public abstract class PositionGlyph extends Glyph {
    
    
    /**
     * Y offset from one note position to the next.
     */
    protected final float positionSpacing = 4.5f;
    protected int positionY;
    
    
     
    /**
     * Returns the y position of the glyph. This position is referring to the sound height of the symbol.
     * a1 has position 0. h1 has position 1, c2 has position 2 and so on. This position does not change with key or clef.
     * @return y position of the glyph.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Sets the y position of the glyph.  This position is referring to the sound height of the symbol.
     * a1 has position 0. h1 has position 1, c2 has position 2 and so on. This position does not change with key or clef.
     * @param positionY 
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
        this.setY((int) (positionY * positionSpacing));
    }
    
}
