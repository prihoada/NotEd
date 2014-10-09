
package cz.cvut.fit.project.noted.musicrenderer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Testing window for the renderer.
 * @author Adam Příhoda
 */
public class MainFrame extends JFrame
{

    public MainFrame() {
    
        this.setTitle("Renderer test window");
        
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLocationRelativeTo(null);
           
        
        this.setLayout(new BorderLayout());
        
        
        RenderPanel panel = new RenderPanel();
        this.add(new ToolsPanel(panel), BorderLayout.NORTH);
        
        panel.createDummyScore();
        
        JScrollPane pane = new JScrollPane(panel);
        pane.getHorizontalScrollBar().setUnitIncrement(30);
        this.add(pane, BorderLayout.CENTER);
        
        this.setVisible(true);
    
    }
    
    
    
    
}
