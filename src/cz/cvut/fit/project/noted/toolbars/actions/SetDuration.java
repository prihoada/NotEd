
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.toolbars.Toolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author david
 */
public class SetDuration implements ActionListener
{
    private final Toolbar toolbar;
    
    public SetDuration(Toolbar toolbar)
    {
        this.toolbar = toolbar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JComboBox jcmbType = (JComboBox) e.getSource();
	String cmbType = (String) jcmbType.getSelectedItem();

         switch (cmbType) {
            case "Eighth":          toolbar.setDuration(Duration.Eighth);
                     break;
            case "Half":            toolbar.setDuration(Duration.Half);
                     break;
            case "Quarter":         toolbar.setDuration(Duration.Quarter);
                     break;
            case "Sixteenth":       toolbar.setDuration(Duration.Sixteenth);
                     break;
            case "SixtyFourth":     toolbar.setDuration(Duration.SixtyFourth);
                     break;
            case "ThirtySecond":    toolbar.setDuration(Duration.ThirtySecond);
                     break;
            case "Whole":           toolbar.setDuration(Duration.Whole);
                     break;
             
         }

    }
    
}
