package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.utils.FileUtils;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * Custom file chooser. Singleton instance allows the file chooser to remember the last location used.
 * @author Adam Příhoda
 */
public class XMLFileChooser extends JFileChooser
{
    
    private int state;
    private static final int STATE_SAVE = 0;
    private static final int STATE_OPEN = 1;

    private final LocalizationManager localizationManager;

    public XMLFileChooser(LocalizationManager localizationManager)
    {
        this.localizationManager = localizationManager;
        this.setAcceptAllFileFilterUsed(false);
        this.setFileFilter(new XMLFileFilter(localizationManager));
    }

    @Override
    public int showSaveDialog(Component parent) throws HeadlessException
    {
        this.state = STATE_SAVE;
        this.setMultiSelectionEnabled(false);
        return super.showSaveDialog(parent);
    }

    @Override
    public int showOpenDialog(Component parent) throws HeadlessException
    {
        this.state = STATE_OPEN;
        this.setMultiSelectionEnabled(true);
        return super.showOpenDialog(parent);
    }

    
    
    
    /**
     * Approves the file selection
     *  if saving a file - Shows an overwrite confirm dialog if necessary.
     */
    @Override
    public void approveSelection()
    {
        if(this.state == STATE_OPEN)
        {
            super.approveSelection();
        }
        else
        {
            File f = getSelectedFile();

            if (f.exists())
            {
                int option = JOptionPane.showConfirmDialog(null, localizationManager.getString("file_overwrite_confirm").getTooltip(), localizationManager.getString("file_overwrite_confirm").getName(), JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION)
                {
                    return;
                }
            }
            super.approveSelection();
        }
        
        
    }
    
    private static class XMLFileFilter extends FileFilter
    {

        private final LocalizationManager localizationManager;

        public XMLFileFilter(LocalizationManager localizationManager) {
            this.localizationManager = localizationManager;
        }

        @Override
        public boolean accept(File f)
        {
            if(f.isDirectory()) return true;
            
            String extension = FileUtils.getExtension(f.getName());
            if(extension.equals("xml"))
            {
                return true;
            }
            return false;
        }

        @Override
        public String getDescription()
        {
            return localizationManager.getString("xml_file_description").getName();
        }
    }
}
