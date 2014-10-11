/*
 CO CHYBI:
 DEBUGNOUT TO
 upravit metody ve kterych je TODO
 */
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.ObjectFactory;
import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.model.Model;
import java.util.List;

/**
 *
 * @author david
 */
public class ModelEditor
{

    
    private final Cursor cursor;
    private final Model model;
    private final ScorePartwise modelHierarchy;
    
    private final List<ScorePartwise.Part> parts;
    private final List<ScorePartwise.Part.Measure> measures;
    private final List<Object> notes;
    
    private final int partPos;
    private final int measurePos;
    private final int x;
    private final int y;
    
    
    
    
    
    public ModelEditor(Model model)
    {
        cursor = new Cursor(model);
        
        this.model = model;
        this.modelHierarchy = model.getModelHierarchy();
       
        // TO WHERE PUT THE NOTE
        this.partPos = cursor.getPart();
        this.measurePos = cursor.getMeasure();
        this.x = cursor.getPosition_x();
        this.y = cursor.getPosition_y();
        
        this.parts = modelHierarchy.getPart();
        
        //TODO   ZDE JE CHYBA ARRAY INDEX OUT OF BOUND  - PartPos
        this.measures = parts.get(partPos).getMeasure();
        //TODO   ZDE JE CHYBA ARRAY INDEX OUT OF BOUND  - measurePos
        this.notes = measures.get(measurePos).getNoteOrBackupOrForward();
       
        
        
     
    }
    
    
    
    
    
    
    //TODO ???? predavat typ noty ve Stringu nebo jinak ?????
    private Note createNote(String noteType)
    {
        ObjectFactory factory = new ObjectFactory();
        Note note = factory.createNote();
        
        //TODO
        //Zde bude switch a podle typu noty se nastavy dana nota !!!!
        // !!!!!!!
        
        
        return note;
    }
    
    public void addNote(String noteType)
    {
        
        Note note = createNote(noteType);  
        this.notes.add(this.x, note);

    }
    
    //moves Note on current cursor right,
    //the cursor moves right also
    public void moveToRight()
    {
        //TODO move to right TODO
        
        
        //if done, move cursor to this position too
        cursor.moveToRight();
    }
    
    public void moveToLeft()
    {
        //TODO
    }
    
    
}
