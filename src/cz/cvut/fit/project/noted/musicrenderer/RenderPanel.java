
package cz.cvut.fit.project.noted.musicrenderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.AccidentalGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.ClefGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.RestGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.FooterGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteHeadGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.ScoreGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.StaveGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Clef;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.awt.BasicStroke;

/**
 * Main panel for the renderer.
 * @author Adam Příhoda
 */
public class RenderPanel extends JPanel{

    /**
     * Top level container for the music.
     */
    private ScoreGlyph score = new ScoreGlyph();
    
    public RenderPanel() {
        
        score.setY(50);
        
        
        //debug test notes
        
        StaveGlyph stave = new StaveGlyph();
        
        
        
        
        
        BarGlyph bar = new BarGlyph();
        
        
        bar.addGlyph(new NoteHeadGlyph(Duration.Whole));
        bar.addGlyph(new NoteHeadGlyph(Duration.Half));
        bar.addGlyph(new NoteHeadGlyph(Duration.Quarter));
        bar.addGlyph(new NoteHeadGlyph(Duration.Eighth));
        bar.addGlyph(new NoteHeadGlyph(Duration.Sixteenth));
        bar.addGlyph(new NoteHeadGlyph(Duration.ThirtySecond));
        bar.addGlyph(new NoteHeadGlyph(Duration.SixtyFourth));
        
        
        
        
        
        bar.addGlyph(new ClefGlyph(Clef.G2));
        bar.addGlyph(new ClefGlyph(Clef.F4));
        bar.addGlyph(new ClefGlyph(Clef.Neutral));
        bar.addGlyph(new ClefGlyph(Clef.C3));
        bar.addGlyph(new ClefGlyph(Clef.C4));
        bar.addGlyph(new RestGlyph(Duration.Quarter));
        bar.addGlyph(new RestGlyph(Duration.Whole));
        bar.addGlyph(new RestGlyph(Duration.Half));
        bar.addGlyph(new RestGlyph(Duration.Eighth));
        bar.addGlyph(new RestGlyph(Duration.Sixteenth));
        bar.addGlyph(new RestGlyph(Duration.ThirtySecond));
        bar.addGlyph(new RestGlyph(Duration.SixtyFourth));
        
        
        bar.addGlyph(new AccidentalGlyph(AccidentalType.Natural));
        bar.addGlyph(new AccidentalGlyph(AccidentalType.Flat));
        bar.addGlyph(new AccidentalGlyph(AccidentalType.Sharp));
        bar.addGlyph(new AccidentalGlyph(AccidentalType.DoubleFlat));
        bar.addGlyph(new AccidentalGlyph(AccidentalType.DoubleSharp));

        
        
        
        bar.addGlyph(new FooterGlyph(Duration.Eighth, BeamDirection.DOWN));
        bar.addGlyph(new FooterGlyph(Duration.Sixteenth, BeamDirection.DOWN));
        bar.addGlyph(new FooterGlyph(Duration.ThirtySecond, BeamDirection.DOWN));
        bar.addGlyph(new FooterGlyph(Duration.SixtyFourth, BeamDirection.DOWN));
        
        bar.addGlyph(new FooterGlyph(Duration.Eighth, BeamDirection.UP));
        bar.addGlyph(new FooterGlyph(Duration.Sixteenth, BeamDirection.UP));
        bar.addGlyph(new FooterGlyph(Duration.ThirtySecond, BeamDirection.UP));
        bar.addGlyph(new FooterGlyph(Duration.SixtyFourth, BeamDirection.UP));
        
        
        score.addGlyph(stave);
        stave.addGlyph(bar);
        
        score.doLayout();
        
    }
    
    
    
    
    
    public double scale = 1;

    //override this, so for example a scroll pane knows how big this panel is.
    @Override
    public Dimension getPreferredSize() {

        int width = score.getGlyphWidth()+ 2 * score.getPadding();
        return new Dimension((int) (width * scale), 500);
    }
    
    
    @Override
    public void paintComponent(Graphics graphics) {

        //this clears the previous graphics
        super.paintComponent(graphics);
        
        Graphics2D g = (Graphics2D) graphics;
        
        //make the lines pretty
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        
        g.scale(scale, scale);
        
        score.paint(score.getX(), score.getY(), g);
    }


    
    
    
    
}
