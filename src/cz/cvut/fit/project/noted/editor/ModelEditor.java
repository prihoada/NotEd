
package cz.cvut.fit.project.noted.editor;

import com.audiveris.proxymusic.Accidental;
import com.audiveris.proxymusic.AccidentalValue;
import static com.audiveris.proxymusic.AccidentalValue.DOUBLE_SHARP;
import static com.audiveris.proxymusic.AccidentalValue.FLAT;
import static com.audiveris.proxymusic.AccidentalValue.FLAT_FLAT;
import static com.audiveris.proxymusic.AccidentalValue.NATURAL;
import static com.audiveris.proxymusic.AccidentalValue.SHARP;
import com.audiveris.proxymusic.Attributes;
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
import cz.cvut.fit.project.noted.musicrenderer.model.AccidentalType;
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
    
    private final ObjectFactory factory;
    
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
    
    
    
    private Note createNote(int cursorY, AccidentalType accType, Duration duration)
    {
        Note note = factory.createNote();
        
        Pitch pitch = ProxymusicConverter.renderYtoNoteY(cursorY);
        note.setPitch(pitch);
        
        note.setType(ProxymusicConverter.durationToType(duration));

        if(accType != null)
        {
            Accidental acc = new Accidental();

            switch(accType)
            {
                case Natural:       
                    acc.setValue(NATURAL);
                    break;
                case Sharp:         
                    acc.setValue(SHARP);
                    break;
                case DoubleSharp:
                    acc.setValue(DOUBLE_SHARP);
                    break;
                case Flat:          
                    acc.setValue(FLAT);
                    break;
                case DoubleFlat:
                    acc.setValue(FLAT_FLAT);
                    break;
                default:            
                    acc.setValue(NATURAL);
                    break; 
            }

            note.setAccidental(acc);
        }
        
        return note;
    }
    
    public void addNote(AccidentalType accType, Duration duration)
    {
        this.loadStuff();
        Note note = createNote(this.getCursor().getPosition_y(), accType, duration);
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
    

    /**
     * Remove the symbol to the left of the cursor.
     */
    public void removePrevious() {
        
        if(cursor.moveToLeft()) removeNext();
    }
    /**
     * Removes the measure to the right of the cursor.
     */
    private void removeNextMeasure()
    {
        int numMeasures = modelHierarchy.getPart().get(cursor.getPart()).getMeasure().size();
        if(cursor.getMeasure() >= numMeasures-1)
        {
            //do nothing, no measure to remove
        }
        else
        {
            Measure measure = modelHierarchy.getPart().get(cursor.getPart()).getMeasure().get(cursor.getMeasure());
            Measure nextMeasure = modelHierarchy.getPart().get(cursor.getPart()).getMeasure().get(cursor.getMeasure() + 1);

            //transfer objects from the next measure to the current measure.
            measure.getNoteOrBackupOrForward().addAll(nextMeasure.getNoteOrBackupOrForward());
            //remove the measure
            modelHierarchy.getPart().get(cursor.getPart()).getMeasure().remove(nextMeasure);
        }
    }

    /**
     * Removes the symbol to the right of the cursor.
     */
    public void removeNext() {
        
        Measure measure = modelHierarchy.getPart().get(cursor.getPart()).getMeasure().get(cursor.getMeasure());
        
        if(cursor.getPosition_x() >= measure.getNoteOrBackupOrForward().size())
        {
            removeNextMeasure();
        }
        else
        {
            measure.getNoteOrBackupOrForward().remove(cursor.getPosition_x());
        }
    
    }
    
    /**
     * Returns the cursor.
     * @return current cursor.
     */
    public Cursor getCursor() {
        return cursor;
    }

        
}
