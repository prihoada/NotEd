
package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.modelplayer.ModelPlayer;
import javax.swing.JPanel;

/**
 * Panel containing rendered sheet music, associated midi player and model.
 * @author Adam Příhoda
 */
public class Tab extends JPanel
{
    private boolean saved;

    private Model model;
    private ModelPlayer player;
    
    
    public Tab()
    {
        saved = false;
        model = new Model();
        player = new ModelPlayer(model);
    }

    public Model getModel()
    {
        return model;
    }
    public ModelPlayer getPlayer()
    {
        return player;
    }
    public Tab setModel(Model model)
    {
        this.model = model;
        this.player = new ModelPlayer(model);
        return this;
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    public Tab setSaved(boolean saved)
    {
        this.saved = saved;
        return this;
    }
}
