package cz.cvut.fit.project.noted.musicrenderer;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.AccidentalGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Adam Příhoda
 */
public class TestPaint {

    /**
     * Test the painting process by painting into an image and comparing the pixels before and after.
     * This is just a basic check.
     */
    @Test
    public void testPaintNote()
    {
        NoteGlyph note = new NoteGlyph(Duration.Quarter, 0);
        note.addAccidental(new AccidentalGlyph(AccidentalType.Flat));
        
        int size = 30;
        
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        //fill the image with white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size, size);
        
        //paint the symbol with black
        g2d.setColor(Color.BLACK);
        note.doLayout();
        note.paint(0, 0, g2d);
        
        int numDifferent = 0;
        
        //compare pixels
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                if(image.getRGB(j, i) != -1) numDifferent++; //-1 is white
            }
        }
        assertTrue(numDifferent > 0);
    }
    
}
