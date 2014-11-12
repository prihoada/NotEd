package cz.cvut.fit.project.noted.musicrenderer;

import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.ScoreGlyph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam Příhoda
 */
public class RenderBuilderTest {
    
    
    public RenderBuilderTest() {
    }
    
    /**
     * Test of buildFromModel method, of class RenderBuilder.
     */
    @Test
    public void testBuildFromEmptyModel() {
        
        Model model = new ProxyMusicHandler().createEmptyModel();
        ScoreGlyph score = new ScoreGlyph();
        RenderBuilder instance = new RenderBuilder(score);
        instance.buildFromModel(model);
        
        assertNull(score.getStave());   //score does not belong to a stave
        assertNotNull(score.getGlyphs().get(0));    //score contains at least 1 stave
        assertNotNull(score.getGlyphs().get(0).getGlyphs());    //stave has 1 bar
        assertEquals(1, score.getGlyphs().get(0).getGlyphs().size());
        assertEquals(1, score.getGlyphs().get(0).getGlyphs().get(0).getGlyphs().size()); //empty bar contains only ending line
        
    }
}
