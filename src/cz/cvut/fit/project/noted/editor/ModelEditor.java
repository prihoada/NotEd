/*
 CO CHYBI:
 DEBUGNOUT TO
 upravit metody ve kterych je TODO
 */
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.NoteType;
import com.audiveris.proxymusic.ObjectFactory;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.Step;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.musicrenderer.ProxymusicConverter;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
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
    
    
    public ModelEditor(Model model)
    {
        cursor = new Cursor(model);
        
        this.model = model;
        this.modelHierarchy = model.getModelHierarchy();
       
        this.parts = modelHierarchy.getPart();
        
        //TODO   ZDE JE CHYBA ARRAY INDEX OUT OF BOUND  - PartPos
        this.measures = parts.get(cursor.getPart()).getMeasure();
        //TODO   ZDE JE CHYBA ARRAY INDEX OUT OF BOUND  - measurePos
        this.notes = measures.get(cursor.getMeasure()).getNoteOrBackupOrForward();
       
        
        
     
    }
    
    
    
    
    
    
    private Note createNote(int cursorY)
    {
        ObjectFactory factory = new ObjectFactory();
        Note note = factory.createNote();
        
        //take the pitch from the cursor
        //IMPORTANT - every note must have a pitch (with a step and octave) and a type. Renderer needs these values.
        Pitch pitch = ProxymusicConverter.renderYtoNoteY(cursorY);
        note.setPitch(pitch);

        //set the duration
        
        //this should be taken from the toolbar
        Duration currentDuration = Duration.Quarter;
        
        
        note.setType(ProxymusicConverter.durationToType(currentDuration));
        
        return note;
    }
    
    public void addNote()
    {
        
        Note note = createNote(this.getCursor().getPosition_y());  
        this.notes.add(this.cursor.getPosition_x(), note);
        
    }
    
    
    //THIS IS NOT NEEDED - we can call getCursor().moveX
//    //moves Note on current cursor right,
//    //the cursor moves right also
//    public void moveToRight()
//    {
//        //TODO move to right TODO
//        
//        
//        //if done, move cursor to this position too
//        cursor.moveToRight();
//    }
//    
//    public void moveToLeft()
//    {
//        //TODO
//    }

    public Cursor getCursor() {
        return cursor;
    }
    
    
    
    
}
