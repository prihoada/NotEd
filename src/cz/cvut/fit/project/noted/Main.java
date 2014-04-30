
package cz.cvut.fit.project.noted;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Adam Příhoda
 * @author Ondřej Altman
 * @author Nguyen, Viet Bach
 * @author David Vavricka
 * @author Karel Smejkal
 */
public class Main {

   
    public static void main(String[] args)
    {
      
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new MainFrame());
    }
    
}
