/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.project.noted.model;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.ObjectFactory;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;
import com.audiveris.proxymusic.Work;
import cz.cvut.fit.project.noted.musicrenderer.RenderBuilder;
import cz.cvut.fit.project.noted.model.Model;
import java.util.List;

/**
 *
 * @author david
 */
public class ModelEdit
{
    private final Model model;
    private final ScorePartwise modelHierarchy;
    List<ScorePartwise.Part> parts;
    
   
    public ModelEdit(Model model)
    {
        this.model = model;
        this.modelHierarchy = model.getModelHierarchy();
        
        parts = modelHierarchy.getPart();
    }
    
    //v parametrech bude jakej chci
    public void addNote()
    {
        // vytvor notu
        Note note = createNote();
    
    
       //PRIDEJ ji nekam do toho PARTs??? snad
        parts.add(note)
    
    }
    
    // v parametrech bude jakej typ chci vytvorit
    private Note createNote()
    {
        ObjectFactory factory = new ObjectFactory();
        
        Note note = factory.createNote();
        
        //nastaveni noty....
        //note.setPitch(blalala);
        //set type,,,..
        
        
        return note;
    }
    
    
}
