/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.project.noted.editor;

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

  
    
    public Cursor()
    {
        
    }
    
    public Cursor(int part, int measure,
            int position_x, int position_y)
    {
        this.setCursor(part, measure, position_x, position_y);
    }
    
    void setCursor(int part, int measure,
            int position_x, int position_y)
    {
        this.part = part;
        this.measure = measure;
        this.position_x = position_x;
        this.position_y = position_y;
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
    
    
}
