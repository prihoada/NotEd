package cz.cvut.fit.project.noted.menus;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Adam Příhoda
 */
public class XMLFileChooser extends JFileChooser
{

    public XMLFileChooser()
    {
        
        this.setAcceptAllFileFilterUsed(false);
        this.setFileFilter(new XMLFileFilter());
        this.setMultiSelectionEnabled(true);
    }
    
    
    
    
    
    

    
    
    private static class XMLFileFilter extends FileFilter
    {

        public XMLFileFilter()
        {
        }

        @Override
        public boolean accept(File f)
        {
            if(f.isDirectory()) return true;
            
            String extension = getExtension(f);
            if(extension != null && extension.equals("xml"))
            {
                return true;
            }
            
            return false;
        }

        @Override
        public String getDescription()
        {
            return LocalizationManager.getInstance().getString("xml_file_description").getName();
        }
        
        private String getExtension(File file)
        {
            String ext = null;
            String name = file.getName();
            int i = name.lastIndexOf('.');
            if(i > 0 && i < name.length() - 1)
            {
                ext = name.substring(i+1).toLowerCase();
            }

            return ext;
            }
        
        
    }
    
    
    
}
