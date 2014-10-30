
package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.ClefGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.Clef;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.PlayAction;
import cz.cvut.fit.project.noted.toolbars.actions.StopAction;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Adam Příhoda
 */
public class Toolbar extends JToolBar
{
    
    public Toolbar(TabManager tabManager, String name)
    {   
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
        
        
        //PLAY
        JButton playButton = new JButton(new ImageIcon("assets/images/playButton.png"));
        playButton.setFocusPainted(false);
        this.add(playButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(playButton));
        playButton.addActionListener(new PlayAction(tabManager));
        
        
        //STOP
        JButton stopButton = new JButton(new ImageIcon("assets/images/stopButton.png"));
        stopButton.setFocusPainted(false);
        this.add(stopButton);
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(stopButton));
        stopButton.addActionListener(new StopAction(tabManager));

        
        GlyphButton button = new GlyphButton();
        button.setGlyph(new ClefGlyph(Clef.G2));
        button.setGlyphScale(0.5f);
        button.setPreferredSize(new Dimension(40, 40));
        
        this.add(button);
        
        GlyphButton button1 = new GlyphButton();
        NoteGlyph noteGlyph = new NoteGlyph(Duration.Quarter, 2);
        noteGlyph.setY(9);
        noteGlyph.setX(-4);
        button1.setGlyph(noteGlyph);
        button1.setPreferredSize(new Dimension(40, 40));
        button1.setGlyphScale(0.8f);
        
        this.add(button1);
        
        
    }
  
    
}
