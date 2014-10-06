

package cz.cvut.fit.project.noted;

import cz.cvut.fit.project.noted.menus.MainMenuBar;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.menus.actions.ExitAction;
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
        XMLFileChooser xmlFileChooser = new XMLFileChooser();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.addWindowListener(new ExitAction());
        
        this.setJMenuBar(new MainMenuBar(xmlFileChooser));
        this.add(new MainPanel(xmlFileChooser));
        
        this.setVisible(true);
    }
    
}
