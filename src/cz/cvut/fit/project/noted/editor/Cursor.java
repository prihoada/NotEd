/*
 * what to do next?
 * 1. exceptions?
 * 2.
 */
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.model.Model;
import java.util.List;

/**
 *
 * @author david
 */
public class Cursor
{
    int part;
    int measure;
    int position_x;
    int position_y;

    private final Model model;
    private final ScorePartwise modelHierarchy;
    List<ScorePartwise.Part> parts;
    
    
    
    public Cursor(Model model)
    {
        this.setCursor(0, 0, 0, 0);
        
        this.model = model;
        this.modelHierarchy = model.getModelHierarchy();
        parts = modelHierarchy.getPart();
        
        
    }
    
    
    
    
   
    
    
    public int getMeasure()
    {
        return measure;
    }

    public int getPart()
    {
        return part;
    }

    public int getPosition_x()
    {
        return position_x;
    }

    public int getPosition_y()
    {
        return position_y;
    }


    
    
    public void setCursor(int part, int measure,
            int position_x, int position_y)
    {
        this.part = part;
        this.measure = measure;
        this.position_x = position_x;
        this.position_y = position_y;
    }
    
    public void setMeasure(int measure)
    {
        this.measure = measure;
    }

    public void setPart(int part)
    {
        this.part = part;
    }

    public void setPosition_x(int position_x)
    {
        this.position_x = position_x;
    }

    public void setPosition_y(int position_y)
    {
        this.position_y = position_y;
    }
    
    
    
    
    
    public void moveToRight()
    {
        position_x++;
    }
    
    public void moveToLeft()
    {
        position_x++;
    }
    
    public void moveUp()
    {
        position_y++;
    }
    
    public void moveDown()
    {
        position_y--;
    }
    
    
    //return true/false if is on the position Partwise
    public boolean checkCursorPartwise(int part)
    {
        return true;
    }
    
    //return true/false if is on the position Part
    
    //return true/false if is on the position some Measure
    
    
    
    
}