
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Top level container. Represents a complete music piece with multiple staves, title, author, copyright etc.
 * @author Adam Příhoda
 */
public class ScoreGlyph extends GlyphGroup<StaveGlyph>
{
    
    /**
     * Lines of musical text.
     */
    private final ArrayList<StaveGlyph> staveLines = new ArrayList<>();
    
    
    @Override
    public void doLayout()
    {
        for (StaveGlyph stave : glyphs) {
            stave.getLayoutEngine().reset();
        }
        
        staveLines.clear();
        
        
        //keep calculating until all staves returned all of their lines
        while(true)
        {
            boolean finished = true;
            
            for (StaveGlyph stave : glyphs) {
               
                StaveGlyph staveLine = stave.getLayoutEngine().getStaveLine();
               
                if(staveLine != null)
                {
                    int finalWidth = 0;
                    finished = false;
                    staveLines.add(staveLine);
                    
                    //TODO calculate final width only once. Maybe from the layouting engine?
                    
                    finalWidth += staveLine.getPadding();
                    staveLine.setX(finalWidth);
                    finalWidth += (staveLine.getGlyphWidth() + staveLine.getPadding());
                    
                    this.setGlyphWidth(finalWidth);
                }
               
            }
            
            if(finished) break;
        }
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {

        //TODO height calculation
        for (StaveGlyph line : staveLines) {
            
            line.paint(x + line.getX(), y + line.getY(), g);
        }
    }
    
}
