/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.project.noted.musicrenderer.glyphs;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.Glyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.StaveGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.BarSeparatorType;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Glyph that separates one bar from another.
 * @author Adam Příhoda
 */
public class BarSeparator extends Glyph
{
    private final BarSeparatorType type;

    public BarSeparator(BarSeparatorType type) {
        this.type = type;
    }

    
    
    
    @Override
    public void doLayout() {

        switch (type)
        {
            case SINGLE:
                setGlyphWidth(1);
                break;
                
            //TODO other types
                
                
            default:
                setGlyphWidth(1);
                break;
        }
        
        
        //15 seems to look good
        setGlyphWidth(15);
    }

    @Override
    public void paint(int x, int y, Graphics2D g) {

        
        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(1.2f));
        
        StaveGlyph stave = getStave();
        
        switch (type)
        {
            //paint single line
            case SINGLE:
                
                g.drawLine(x, y - 17 - stave.getTopOverflow(), x, y + 17 + stave.getBottomOverflow());
                
                break;
            
            //TODO other types
                
                
            default:
                g.drawLine(x, y - 17 - stave.getTopOverflow(), x, y + 17 + stave.getBottomOverflow());
                break;
            
        }
        
    
        
        g.setStroke(originalStroke);
        
    }
    
}
