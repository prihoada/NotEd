package cz.cvut.fit.project.noted.model;

import java.io.FileNotFoundException;

/**
 *
 * @author Adam Příhoda
 */
public interface IlModelProvider
{
    /**
     * Parses a musicXML file on the given path.
     * @param filePath path to the file.
     * @return instance of Model containing parsed hierarchy.
     * @throws FileNotFoundException 
     */
    public Model getModel(String filePath) throws FileNotFoundException;
    

}
