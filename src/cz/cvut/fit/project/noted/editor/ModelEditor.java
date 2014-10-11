/*
 * what to do next?
 * 1. presun posunX/Y -> modelEditor z Cursor
 * 2. addNote
   3. MAYBE: measures-> udelat pole a ulozit measures pro vsechny parts
    4. addNote pridelat pro vsechny znaky all..
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
        this.measures = parts.get(partPos).getMeasure();
        this.notes = measures.get(measurePos).getNoteOrBackupOrForward();
        
        
     
    }
    
    
    
    
    
    
    //TODO mozna ne string ale primo nota?
    private Note createNote(String noteType)
    {
        ObjectFactory factory = new ObjectFactory();
        
        Note note = factory.createNote();
        
        //nastaveni noty. - switch podle STRING NOTETYPE... TODO !!!!
        //note.setPitch(blalala);
        //set type,,,..
        
        
        return note;
    }
    
    //TODO pridat parametry jakou notu atd....
    public void addNote(String noteType)
    {
        
        //CREATE NOTE 
        Note note = createNote(noteType);
        

        //FINALLY PUT IT THERE  TODO
        
        
        this.notes.add(this.x, note);
        
        //this.notes.add(0, note);

//nota bude z measures(measure) .... x,y
        //measures.get(measurePos).adddnota(,.mdfdfsd)
    }
    
    //moves Note on current cursor right,
    //the cursor moves right also
    public void moveToRight()
    {
        //move to right TODO
        
        
        //if done, move cursor to this position too
        cursor.moveToRight();
    }
    
    public void moveToLeft()
    {
        //TODO
    }
    
    
}
