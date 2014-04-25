
package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.modelplayer.ModelPlayer;
import cz.cvut.fit.project.noted.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * Panel containing rendered sheet music, associated midi player and model.
 * @author Adam Příhoda
 */
public class Tab extends JPanel
{
    private boolean saved;
    private File saveFile;

    private Model model;
    private ModelPlayer player;
    
    
    public Tab()
    {
        saved = false;
        saveFile = null;
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
    

    /**
     * Marshals the model to a file. Asks for file destination if saveAs is true or if the tab has not yet been saved.
     * @param saveAs true if the tab should prompt for a new location.
     */
    public void save(boolean saveAs)
    {
        //SAVE
        if(this.isSaved() && !saveAs) return;
        if(saveFile != null && !saveAs)
        {
            try
            {
                ProxyMusicHandler.getInstance().saveModel(model, saveFile.getAbsolutePath());
                this.setSaved(true);
            }
            catch (IOException ex)
            {
                Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        //SAVE AS
        XMLFileChooser chooser = XMLFileChooser.getInstance();
        if(saveFile != null) chooser.setSelectedFile(saveFile);
        else chooser.setSelectedFile(new File(TabManager.getInstance().getTitleAt(TabManager.getInstance().getTabIndex(this)) + ".xml"));
        int result = chooser.showSaveDialog(null);
        
        switch(result)
        {
            case XMLFileChooser.APPROVE_OPTION:
                
                File file = chooser.getSelectedFile();
                try
                {
                    String fileName = file.getAbsolutePath();
                    if(!FileUtils.getExtension(fileName).equals("xml")) fileName = fileName.concat(".xml");
                    ProxyMusicHandler.getInstance().saveModel(model, fileName);
                    
                    String newTitle = file.getName();
                    if(FileUtils.getExtension(newTitle).equals("xml"))
                    {
                        newTitle = FileUtils.removeExtension(newTitle); 
                    }
                    this.setSaved(true);
                    this.saveFile = new File(fileName);
                    TabManager.getInstance().setTitleAt(TabManager.getInstance().getTabIndex(this), newTitle);
                    
                }
                catch (IOException ex)
                {
                    Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                break;
            case XMLFileChooser.CANCEL_OPTION:
                return;
            case XMLFileChooser.ERROR_OPTION:
                return;
        }
    }
}
