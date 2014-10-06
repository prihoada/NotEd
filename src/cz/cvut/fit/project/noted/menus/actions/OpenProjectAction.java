package cz.cvut.fit.project.noted.menus.actions;

import cz.cvut.fit.project.noted.localization.LocaleString;
import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.ParsingException;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import cz.cvut.fit.project.noted.rendering.Tab;
import cz.cvut.fit.project.noted.rendering.TabManager;
import cz.cvut.fit.project.noted.utils.FileUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Shows an open dialog and creates tabs for opened xml files,
 * @author Adam Příhoda
 */
public class OpenProjectAction implements ActionListener
{
    
    private final XMLFileChooser xmlFileChooser;
    private final ProxyMusicHandler proxyMusicHandler;
    
    public OpenProjectAction(XMLFileChooser xmlFileChooser,
                             ProxyMusicHandler proxyMusicHandler)
    {
        this.xmlFileChooser = xmlFileChooser;
        this.proxyMusicHandler = proxyMusicHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        xmlFileChooser.setSelectedFile(new File(""));
        int result = xmlFileChooser.showOpenDialog(null);
       
        switch(result)
        {
            case JFileChooser.APPROVE_OPTION:
                
                File [] files = xmlFileChooser.getSelectedFiles();
                for (File file : files)
                {
                    
                    try
                    {
                        Model model = proxyMusicHandler.getModel(file);
                        Tab newTab = new Tab(xmlFileChooser,proxyMusicHandler);
                        newTab.setSaved(true);
                        newTab.setModel(model);
                        TabManager.getInstance().addTab(FileUtils.removeExtension(file.getName()), newTab);
                    }
                    catch (FileNotFoundException ex)
                    {
                        LocaleString string = LocalizationManager.getInstance().getString("error_fileNotFound");
                        JOptionPane.showMessageDialog(null, string.getTooltip(), string.getName(), JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    catch (ParsingException ex)
                    {
                        LocaleString string = LocalizationManager.getInstance().getString("error_fileNotValid");
                        JOptionPane.showMessageDialog(null, string.getTooltip(), string.getName(), JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                break;
            case JFileChooser.CANCEL_OPTION:
                return;
            case JFileChooser.ERROR_OPTION:
                return;
        }
        
        
    }
    
}
