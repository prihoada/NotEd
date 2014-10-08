

package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.PositionGlyph;
import java.awt.Graphics2D;

/**
 * Glyph for small lines behind notes and the cursor. Displayed when the symbol is above or below the staff
 * @author Adam Příhoda
 */
public class HelperLinesGlyph extends PositionGlyph {

    @Override
    public void doLayout() {
        //this symbol does not have any layout parameters. Set them from the parent.
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {

    
        //paint lines above, the first note that needs a line is 7 positions above a1, which has position 0
        if(positionY <= -7) //
        {
            int numLines = ((positionY + 7) / -2) + 1;
            int finalY = (int) (y + positionSpacing + ((-positionY+1) % 2) * positionSpacing);
            for (int i = 0; i < numLines; i++) {
                g.drawLine(x - 5, finalY, x + getSymbolWidth() + 5, finalY);
                finalY += (int)(2*positionSpacing);
            }
        }
        //paint lines below, the first note that needs a line is 5 positions below a1, which has position 0
        else if (positionY >= 5)
        {
            int numLines = ((positionY - 5) / 2) + 1;
            int finalY = (int) (y + (positionY % 2)*positionSpacing) + (positionY %2);
            for (int i = 0; i < numLines; i++) {
                g.drawLine(x - 5, finalY, x + getSymbolWidth() + 5, finalY);
                finalY -= (int)(2*positionSpacing);
            }
        }
    
    }
    
}
