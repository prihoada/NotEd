
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.PositionGlyph;
import java.awt.Graphics2D;

/**
 *
 * @author Adam Příhoda
 */
public class CursorGlyph extends PositionGlyph
{
    private final HelperLinesGlyph helperLines;

    public CursorGlyph(int positionY) {
        
        helperLines = new HelperLinesGlyph();
        
        setPositionY(positionY);
    }

    
    
    @Override
    public void doLayout() {
        
        setSymbolWidth(15);
        helperLines.setSymbolWidth(this.getSymbolWidth());
        setGlyphWidth(20);
        
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {
        
        
        helperLines.paint(x, y, g);
        
        g.fillRect(x, y, 15, (int) (2*positionSpacing)); 
    
    }

    @Override
    public void setPositionY(int positionY) {
        super.setPositionY(positionY);
        helperLines.setPositionY(positionY);
    }

    
    
  
    
    
    
    
    
}


