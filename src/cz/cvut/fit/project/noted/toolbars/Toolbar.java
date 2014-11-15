
package cz.cvut.fit.project.noted.toolbars;

import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.editor.Cursor;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.toolbars.actions.PlayAction;
import cz.cvut.fit.project.noted.toolbars.actions.StopAction;
import cz.cvut.fit.project.noted.toolbars.actions.ZoomAction;
import cz.cvut.fit.project.noted.utils.TabbedPaneDisableComponentChangeListener;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Adam Příhoda
 */
public class Toolbar extends JToolBar
{
    
    public Toolbar(final TabManager tabManager, String name)
    {   
        super(name);
        
        this.setRollover(true);
        this.setFloatable(false);
        this.setFocusable(false);
        
        
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
     
        
        JButton debugButton = new JButton("Debug trace");
        debugButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                
                Cursor cursor = tabManager.getActiveTab().getEditor().getCursor();
                System.out.println(cursor);
                
                Model model = tabManager.getActiveTab().getModel();
                System.out.println(model);
                
                ScorePartwise modelHierarchy = model.getModelHierarchy();
                List<ScorePartwise.Part> part = modelHierarchy.getPart();
                
                System.out.println("    Parts: " + part.size());
                System.out.println("    Measure: " + part.get(0).getMeasure().size());
                System.out.println("    Symbols: " + part.get(0).getMeasure().get(cursor.getMeasure()).getNoteOrBackupOrForward().size());        
            }
        });
        
        
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
        
            
        
        this.add(debugButton);
        
        
        Component[] components = getComponents();
        for (Component component : components) {
            component.setFocusable(false);
        }
    }
  
    
}
