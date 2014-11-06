
package cz.cvut.fit.project.noted.toolbars;

import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.PlayAction;
import cz.cvut.fit.project.noted.toolbars.actions.StopAction;
import cz.cvut.fit.project.noted.toolbars.actions.ZoomAction;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Dimension;
import java.awt.Image;
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

        
        addSeparator();
        
        
        Image zoomInIcon = new ImageIcon("assets/images/ic_zoom_in.png").getImage().getScaledInstance(32,32, Image.SCALE_SMOOTH);
        JButton zoomIn = new JButton();
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(zoomIn));
        zoomIn.setAction(new ZoomAction(tabManager, ZoomAction.TYPE.Zoom_in, new ImageIcon(zoomInIcon)));
        zoomIn.setText("");
        this.add(zoomIn);
        
        Image zoomOutIcon = new ImageIcon("assets/images/ic_zoom_out.png").getImage().getScaledInstance(32,32, Image.SCALE_SMOOTH);
        JButton zoomOut = new JButton();
        tabManager.addChangeListener(new TabbedPaneDisableComponentChangeListener(zoomOut));
        zoomOut.setAction(new ZoomAction(tabManager, ZoomAction.TYPE.Zoom_out, new ImageIcon(zoomOutIcon)));
        zoomOut.setText("");
        this.add(zoomOut);
        
    }
  
    
}
