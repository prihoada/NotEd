
package cz.cvut.fit.project.noted.model;

import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.util.Marshalling;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Adam Příhoda
 */
public class ProxyMusicHandler implements IModelProvider
{
    //Singleton
    private static ProxyMusicHandler instance;
    public static ProxyMusicHandler getInstance()
    {
        if(instance == null) instance = new ProxyMusicHandler();
        return instance;
    }
    
    
    private ProxyMusicHandler()
    {
        
        //Create the context for marshalling.
        try
        {
            Marshalling.getContext();
        }
        catch (JAXBException ex)
        {
            Logger.getLogger(ProxyMusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
   
    @Override
    public Model getModel(String filePath) throws FileNotFoundException, ParsingException
    {

        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        ScorePartwise part = null;
        try
        {
            part = Marshalling.unmarshal(in);
        }
        catch (JAXBException | SAXException | ParserConfigurationException ex)
        {
            throw new ParsingException("Parsing error");
        }
        
        Model model = new Model();
        model.setModelHierarchy(part);
        model.setFilePath(filePath);
        
        return model;
    }
    public Model getModel(File file) throws FileNotFoundException, ParsingException
    {
        return getModel(file.getAbsolutePath());
    }
    
    
    /**
     * Saves the supplied model to a file. Creates the file if necessary.
     * @param model instance of Model to save.
     * @param filePath path to the destination file.
     * @throws IOException 
     */
    public void saveModel(Model model, String filePath) throws IOException
    {
        
        File file = new File(filePath);
        if(!file.exists())
        {
            file.createNewFile();
        }
        
        OutputStream out = new FileOutputStream(file);
        try
        {
            Marshalling.marshal(model.getModelHierarchy(), out, false);
            out.close();
        }
        catch (JAXBException ex)
        {
            Logger.getLogger(ProxyMusicHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
