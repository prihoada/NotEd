package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Attributes;
import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.model.Model;
import java.util.List;

/**
 * Represents current position in the model hierarchy.
 * @author david, Adam Příhoda
 */
public final class Cursor
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


    public void setCursor(int part, int measure, int position_x, int position_y)
    {
        this.part = part;
        this.measure = measure;
        this.position_x = position_x;
        this.position_y = position_y;
    }
    
    
    
    public boolean moveToRight()
    {
        ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
        ScorePartwise.Part.Measure bar = staff.getMeasure().get(measure);
        List<Object> symbols = bar.getNoteOrBackupOrForward();
        
        //check if there is a space on the right
        if(position_x  >= symbols.size())
        {
            return moveToRightMeasure();
        }
        else
        {
            position_x++;
            return true;
        }
    }
    
    public boolean moveToLeft()
    {
        //check if there is a space on the left
        if(position_x <= 0)
        {
            return moveToLeftMeasure();
        }
        else
        {
            position_x--;
            return true;
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
    
    public boolean moveToRightMeasure()
    {
        ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
             
        if(measure + 1 >= staff.getMeasure().size())
        {
            //end of staff, do nothing
            return false;
        }
        else
        {
            measure++;
            position_x = 0;
            return true;
        }
    }
    
    public boolean moveToLeftMeasure()
    {
        if(measure <= 0)
        {
            //do nothing
            return false;
        }
        else
        {
            measure--;
            
            //move the position_x to the last element in the bar
            ScorePartwise.Part staff = model.getModelHierarchy().getPart().get(part);
            ScorePartwise.Part.Measure bar = staff.getMeasure().get(measure);
            position_x = bar.getNoteOrBackupOrForward().size();
            return true;
        }
    }

    @Override
    public String toString() {
        return "Cursor{" + "part=" + part + ", measure=" + measure + ", position_x=" + position_x + ", position_y=" + position_y + ", model=" + model + ", modelHierarchy=" + modelHierarchy + ", parts=" + parts + '}';
    }
}
