
package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.model.Model;
import javax.swing.JPanel;

/**
 * Panel containing rendered sheet music, associated midi player and model.
 * @author Adam Příhoda
 */
public class Tab extends JPanel
{
    private boolean saved;

    private Model model;
    
    public Tab()
    {
        saved = false;
    }

    public Model getModel()
    {
        return model;
    }

    public Tab setModel(Model model)
    {
        this.model = model;
        return this;
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }
}
