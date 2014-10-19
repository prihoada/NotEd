
package cz.cvut.fit.project.noted.toolbars.actions;

import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import cz.cvut.fit.project.noted.toolbars.DurationToolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author david
 */
public class SetDuration implements ActionListener
{
    private final DurationToolbar toolbar;
    private final String duration;
    
    public SetDuration(DurationToolbar toolbar, String duration)
    {
        this.toolbar = toolbar;
        this.duration = duration;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
         switch (duration) {
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
