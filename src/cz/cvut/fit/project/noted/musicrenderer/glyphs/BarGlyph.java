
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.GlyphGroup;

/**
 * Single bar. Contains musical symbols. Ends with a bar line.
 * @author Adam Příhoda
 */
public class BarGlyph extends GlyphGroup<Glyph>
{
    private static final int LINE_SPACING = 9;

    
    @Override
    public void paint(int x, int y, Graphics2D g) {
        
        //paint music lines
        double offsetY = y - 2 * LINE_SPACING;
        Path2D.Double lines = new Path2D.Double();
        lines.moveTo(x, y);
        //there are 5 lines in a staff
        for (int i = 0; i < 5; i++) {
            
            lines.moveTo(x, offsetY + i * LINE_SPACING);
            lines.lineTo(x + getGlyphWidth(), offsetY + i * LINE_SPACING);
            
        }
        g.setStroke(new BasicStroke(1));
        g.draw(lines);
    
        //paint the content of the bar
        super.paint(x, y, g); 
      
    }
    
}
