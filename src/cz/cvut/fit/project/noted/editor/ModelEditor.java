
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.Clef;
import com.audiveris.proxymusic.ClefSign;
import com.audiveris.proxymusic.ObjectFactory;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Rest;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;
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
    private ScorePartwise modelHierarchy;
    
    private List<ScorePartwise.Part> parts;
    private List<ScorePartwise.Part.Measure> measures;
    private List<Object> notes;
    
    private ObjectFactory factory;
    
    public ModelEditor(Model model)
    {
        cursor = new Cursor(model);
        factory = new ObjectFactory();
        
        this.model = model;
    }
    
    private void loadStuff()
    {
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
        this.loadStuff();
        Note note = createNote(this.getCursor().getPosition_y(), duration); 
        this.notes.add(this.cursor.getPosition_x(), note);  
    }
    

 
    public void addClef()
    {
        this.loadStuff();
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
        this.loadStuff();
        Note space = createRest(duration);  
        this.notes.add(this.cursor.getPosition_x(), space); 
    }
    
    public void addMeasure()
    {
        this.loadStuff();
 
        //new measure, on the left of the current one
        Measure measure = factory.createScorePartwisePartMeasure();
        this.measures.add(cursor.getMeasure(), measure);
        
        List<Object> newMeasure = measures.get(cursor.getMeasure()).getNoteOrBackupOrForward();
        
        for(int pos = 0; pos < cursor.getPosition_x(); pos++)
        {
            newMeasure.add(pos, this.notes.get(0));
            this.notes.remove(0);
        }
        
        
    }
    
    
    public Cursor getCursor() {
        return cursor;
    }
    
        
}
