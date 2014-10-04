
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
import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarSeparator;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.FooterGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteHeadGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.ScoreGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.StaveGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
import cz.cvut.fit.project.noted.musicrenderer.model.BarSeparatorType;
import cz.cvut.fit.project.noted.musicrenderer.model.BeamDirection;
import cz.cvut.fit.project.noted.musicrenderer.model.Clef;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;

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
        
        score.setY(150);
        
        StaveGlyph stave = new StaveGlyph();
        

        createDummyBar(stave);
        
        
        score.addGlyph(stave);
        score.doLayout();
        
    }
    
    
    /**
     * Creates a set of test bars.
     */
    private void createDummyBar(StaveGlyph stave)
    {
        BarGlyph bar = new BarGlyph();
        bar.addGlyph(new ClefGlyph(Clef.G2));
        
        for (int i = -15; i < 15; i++) {
            
            bar.addGlyph(new NoteHeadGlyph(Duration.Quarter, i));
            
            if(i % 4 == 0) bar.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        }
        
        bar.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        
        stave.addGlyph(bar);
        
        BarGlyph bar1 = new BarGlyph();
        
        bar1.addGlyph(new NoteHeadGlyph(Duration.Whole));
        bar1.addGlyph(new NoteHeadGlyph(Duration.Half));
        bar1.addGlyph(new NoteHeadGlyph(Duration.Quarter, -1));
        bar1.addGlyph(new NoteHeadGlyph(Duration.Eighth, -2));
        bar1.addGlyph(new NoteHeadGlyph(Duration.Sixteenth, -3));
        bar1.addGlyph(new NoteHeadGlyph(Duration.ThirtySecond, -4));
        bar1.addGlyph(new NoteHeadGlyph(Duration.SixtyFourth, -5));
        bar1.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        
        stave.addGlyph(bar1);
        
        BarGlyph bar2 = new BarGlyph();
        
        bar2.addGlyph(new ClefGlyph(Clef.F4));
        bar2.addGlyph(new ClefGlyph(Clef.Neutral));
        bar2.addGlyph(new ClefGlyph(Clef.C3));
        bar2.addGlyph(new ClefGlyph(Clef.C4));
        bar2.addGlyph(new RestGlyph(Duration.Quarter));
        bar2.addGlyph(new RestGlyph(Duration.Whole));
        bar2.addGlyph(new RestGlyph(Duration.Half));
        bar2.addGlyph(new RestGlyph(Duration.Eighth));
        bar2.addGlyph(new RestGlyph(Duration.Sixteenth));
        bar2.addGlyph(new RestGlyph(Duration.ThirtySecond));
        bar2.addGlyph(new RestGlyph(Duration.SixtyFourth));
        bar2.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        
        stave.addGlyph(bar2);
        
        BarGlyph bar3 = new BarGlyph();
        
        bar3.addGlyph(new FooterGlyph(Duration.Eighth, BeamDirection.DOWN));
        bar3.addGlyph(new FooterGlyph(Duration.Sixteenth, BeamDirection.DOWN));
        bar3.addGlyph(new FooterGlyph(Duration.ThirtySecond, BeamDirection.DOWN));
        bar3.addGlyph(new FooterGlyph(Duration.SixtyFourth, BeamDirection.DOWN));
        
        bar3.addGlyph(new FooterGlyph(Duration.Eighth, BeamDirection.UP));
        bar3.addGlyph(new FooterGlyph(Duration.Sixteenth, BeamDirection.UP));
        bar3.addGlyph(new FooterGlyph(Duration.ThirtySecond, BeamDirection.UP));
        bar3.addGlyph(new FooterGlyph(Duration.SixtyFourth, BeamDirection.UP));
        bar3.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        
        stave.addGlyph(bar3);
        
        BarGlyph bar4= new BarGlyph();
        
        bar4.addGlyph(new AccidentalGlyph(AccidentalType.Natural));
        bar4.addGlyph(new AccidentalGlyph(AccidentalType.Flat));
        bar4.addGlyph(new AccidentalGlyph(AccidentalType.Sharp));
        bar4.addGlyph(new AccidentalGlyph(AccidentalType.DoubleFlat));
        bar4.addGlyph(new AccidentalGlyph(AccidentalType.DoubleSharp));
        bar4.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));;
        stave.addGlyph(bar4);

    }
    
    
    
    /**
     * Overall scale of the display
     */
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
