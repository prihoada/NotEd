/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
   // private final Model model;
    //private final ScorePartwise modelHierarchy;
    //List<ScorePartwise.Part> parts;
    
    
    public ModelEditor()
    {
       cursor = new Cursor();
        
        //this.model = model;
        //this.modelHierarchy = model.getModelHierarchy();
        
        //parts = modelHierarchy.getPart();
        
        
        
    }
    
    
    
    
    
    
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
