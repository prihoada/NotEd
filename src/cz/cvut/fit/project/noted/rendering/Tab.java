
package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.editor.ModelEditor;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.musicrenderer.RenderPanel;
import cz.cvut.fit.project.noted.utils.FileUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Panel containing rendered sheet music, associated midi player and model.
 * @author Adam Příhoda
 */
public class Tab extends JPanel
{
    private boolean saved;
    private File saveFile;

    private Model model;
    private RenderPanel renderPanel;
    private ModelEditor editor;
    
    private JScrollPane pane;

    private final XMLFileChooser xmlFileChooser;
    private final ProxyMusicHandler proxyMusicHandler;
    private final TabManager tabManager;

    public Tab(XMLFileChooser xmlFileChooser,
               ProxyMusicHandler proxyMusicHandler,
               TabManager tabManager)
    {
        this.xmlFileChooser = xmlFileChooser;
        this.proxyMusicHandler = proxyMusicHandler;
        this.tabManager = tabManager;

        saved = false;
        saveFile = null;
        
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        
        renderPanel = new RenderPanel();
        pane = new JScrollPane(renderPanel);
        pane.getHorizontalScrollBar().setUnitIncrement(30);
        pane.getVerticalScrollBar().setUnitIncrement(25);
        this.add(pane, BorderLayout.CENTER);

        this.setModel(proxyMusicHandler.createEmptyModel());
    }

    public Model getModel()
    {
        return model;
    }
    public RenderPanel getRenderPanel() {
        return renderPanel;
    }
    public ModelEditor getEditor() {
        return editor;
    }
    
    public void scrollToRight() {
          pane.getHorizontalScrollBar().setValue(pane.getHorizontalScrollBar().getMaximum());
    }
    
    
    public Tab setModel(Model model)
    {
        this.model = model;
        this.editor = new ModelEditor(model);
        renderPanel.buildFromModel(model, editor.getCursor());
        
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
                proxyMusicHandler.saveModel(model, saveFile.getAbsolutePath());
                this.setSaved(true);
            }
            catch (IOException ex)
            {
                Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        //SAVE AS
        if(saveFile != null) xmlFileChooser.setSelectedFile(saveFile);
        else xmlFileChooser.setSelectedFile(new File(tabManager.getTitleAt(tabManager.getTabIndex(this)) + ".xml"));
        int result = xmlFileChooser.showSaveDialog(null);
        
        switch(result)
        {
            case XMLFileChooser.APPROVE_OPTION:
                
                File file = xmlFileChooser.getSelectedFile();
                try
                {
                    String fileName = file.getAbsolutePath();
                    if(!FileUtils.getExtension(fileName).equals("xml")) fileName = fileName.concat(".xml");
                    proxyMusicHandler.saveModel(model, fileName);
                    
                    String newTitle = file.getName();
                    if(FileUtils.getExtension(newTitle).equals("xml"))
                    {
                        newTitle = FileUtils.removeExtension(newTitle); 
                    }
                    this.setSaved(true);
                    this.saveFile = new File(fileName);
                    tabManager.setTitleAt(tabManager.getTabIndex(this), newTitle);
                    
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
