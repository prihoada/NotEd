

package cz.cvut.fit.project.noted.model;

import com.audiveris.proxymusic.ScorePartwise;

/**
 * 
 * @author Adam Příhoda
 */
public class Model
{
    private ScorePartwise modelHierarchy;
    private String filePath;

    public Model()
    {
        this.modelHierarchy = new ScorePartwise();
        this.filePath = "";
    }

    public ScorePartwise getModelHierarchy()
    {
        return modelHierarchy;
    }

    public void setModelHierarchy(ScorePartwise modelHierarchy)
    {
        this.modelHierarchy = modelHierarchy;
    }
    
    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    
    
}
