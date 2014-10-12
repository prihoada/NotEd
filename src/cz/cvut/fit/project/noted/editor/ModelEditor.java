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
    
    
    
    
    
    
    //TODO ???? predavat typ noty ve Stringu nebo jinak ?????
    private Note createNote(String noteType)
    {
        ObjectFactory factory = new ObjectFactory();
        Note note = factory.createNote();
        
        //debug params
        
        //IMPORTANT - every note must have a pitch (with a step and octave) and a type. Renderer needs these values.
        note.setPitch(new Pitch());
        note.getPitch().setStep(Step.A);
        note.getPitch().setOctave(4);
        NoteType type = new NoteType();
        type.setValue("quarter");
        note.setType(type);
        
        //TODO
        //Zde bude switch a podle typu noty se nastavy dana nota !!!!
        // !!!!!!!
        
        
        return note;
    }
    
    public void addNote(String noteType)
    {
        
        Note note = createNote(noteType);  
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
