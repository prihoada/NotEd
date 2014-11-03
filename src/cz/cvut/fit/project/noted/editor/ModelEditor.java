/*
 CO CHYBI:
 DEBUGNOUT TO
 upravit metody ve kterych je TODO
 */
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.Clef;
import com.audiveris.proxymusic.ClefSign;
import com.audiveris.proxymusic.NoteType;
import com.audiveris.proxymusic.ObjectFactory;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Rest;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.Step;
import com.audiveris.proxymusic.YesNo;
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
    
    
    private ObjectFactory factory;
    
    public ModelEditor(Model model)
    {
        cursor = new Cursor(model);
        factory = new ObjectFactory();
        
        this.model = model;
        this.modelHierarchy = model.getModelHierarchy();
        this.parts = modelHierarchy.getPart();   
        this.measures = parts.get(cursor.getPart()).getMeasure();
        this.notes = measures.get(cursor.getMeasure()).getNoteOrBackupOrForward();
     
    }
    
    
    private Note createNote(int cursorY, Duration duration)
    {
        Note note = factory.createNote();
        
        //take the pitch from the cursor
        //IMPORTANT - every note must have a pitch (with a step and octave) and a type. Renderer needs these values.
        Pitch pitch = ProxymusicConverter.renderYtoNoteY(cursorY);
        note.setPitch(pitch);
        
        note.setType(ProxymusicConverter.durationToType(duration));
        
        return note;
    }
    
    public void addNote(Duration duration)
    {
        Note note = createNote(this.getCursor().getPosition_y(), duration);  
        this.notes.add(this.cursor.getPosition_x(), note);  
    }
    

 
    public void addClef()
    {
            Clef clef = factory.createClef();
            
            //add a standard violin G clef for now
            clef.setSign(ClefSign.G);
            this.notes.add(this.cursor.getPosition_x(), clef); 
    }
        
        
    private Note createRest(Duration duration)
    {
        Note note = factory.createNote();
        
        note.setRest(new Rest());
        note.setType(ProxymusicConverter.durationToType(duration));
        
        return note;
    }
    
    public void addRest(Duration duration)
    {
        Note space = createRest(duration);  
        this.notes.add(this.cursor.getPosition_x(), space); 
    }
    
    
    
    
    
    
    public Cursor getCursor() {
        return cursor;
    }
    
        
}
