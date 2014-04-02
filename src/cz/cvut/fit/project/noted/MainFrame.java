

package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.menus.MainMenuBar;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Adam Příhoda
 */
class MainFrame extends JFrame implements Runnable
{

    public MainFrame()
    {
        super("NotEd");
        
    }

    @Override
    public void run()
    {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        
        this.setJMenuBar(new MainMenuBar());
        
        this.setVisible(true);
    }
    
}
