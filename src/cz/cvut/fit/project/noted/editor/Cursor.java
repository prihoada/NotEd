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
    private int part;
    private int measure;
    private int position_x;
    private int position_y;

    private final Model model;
    private final ScorePartwise modelHierarchy;
    private final List<ScorePartwise.Part> parts;
    
    
    
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

        ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
        ScorePartwise.Part.Measure bar = staff.getMeasure().get(measure);
        List<Object> symbols = bar.getNoteOrBackupOrForward();
        
        //check if there is a space on the right
        if(position_x + 1 > symbols.size())
        {
            moveToRightMeasure();
        }
        else
        {
            position_x++;
        }
        
    }
    
    public void moveToLeft()
    {
        
        //check if there is a space on the left
        if(position_x <= 0)
        {
            moveToLeftMeasure();
        }
        else
        {
            position_x--;
        }
        
    }
 
    
    public void moveUp()
    {
        position_y--;
    }
    
    public void moveDown()
    {
        position_y++;
    }
    
    
    public void moveToRightMeasure()
    {
        ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
             
        if(measure + 1 >= staff.getMeasure().size())
        {
            //end of staff, do nothing
        }
        else
        {
            measure++;
            position_x = 0;
        }

    }
    
    public void moveToLeftMeasure()
    {
    
        if(measure <= 0)
        {
            //do nothing
        }
        else
        {
            measure--;
            
            //move the position_x to the last element in the bar
            ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
            ScorePartwise.Part.Measure bar = staff.getMeasure().get(measure);
            position_x = bar.getNoteOrBackupOrForward().size();
        }
            
    }
   
    
    
    
}
