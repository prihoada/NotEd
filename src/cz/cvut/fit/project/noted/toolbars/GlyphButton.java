
package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author Adam Příhoda
 */
public class GlyphButton extends JButton
{

    private Glyph glyph;
    private float glyphScale = 1;


    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(glyph != null)
        {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 

            g2d.scale(glyphScale, glyphScale);

            int tWidth = (int) ((this.getWidth()/2) * (1/glyphScale) - glyph.getSymbolWidth()/2) + glyph.getX();
            int tHeight = (int) (this.getHeight()/2 * (1/glyphScale)) + glyph.getY();

            glyph.paint(tWidth, tHeight, g2d);
            g2d.dispose();
        }
    }
    
    
    public Glyph getGlyph() {
        return glyph;
    }

    public void setGlyph(Glyph glyph) {
        this.glyph = glyph;
        glyph.doLayout();
    }

    public float getGlyphScale() {
        return glyphScale;
    }

    public void setGlyphScale(float glyphScale) {
        this.glyphScale = glyphScale;
    }
}
