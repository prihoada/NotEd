
package cz.cvut.fit.project.noted.modelplayer;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.model.Model;
import jm.music.data.Phrase;
import jm.music.data.Rest;

/**
 * Converter from the proxyMusic model to the JMusic midi phrase.
 * @author Adam Příhoda
 */
public class JMCBuilder {

    
    private Phrase phrase;
    
    /**
     * Converts the model into the supplied phrase.
     * @param model model to convert.
     * @param phrase phrase to populate.
     * @param measureIndex first measure to start building from
     * @return populated phrase.
     */
    public Phrase buildFromModel(Model model, Phrase phrase, int measureIndex)
    {
        this.phrase = phrase;
        
        ScorePartwise.Part part = model.getModelHierarchy().getPart().get(0);
        
        for (int i = measureIndex; i < part.getMeasure().size(); i++) {
            buildMeasure(part.getMeasure().get(i));
        }
        
        return phrase;
    }

    /**
     * Builds a single measure.
     * @param measure 
     */
    private void buildMeasure(ScorePartwise.Part.Measure measure) {

        for (Object object : measure.getNoteOrBackupOrForward()) {
            
            if(object instanceof Note)
            {
                buildNote((Note) object);
            }
        }
    }

    /**
     * Builds a single note or a rest.
     * @param note 
     */
    private void buildNote(Note note) {

        if(note.getRest() != null)
        {
            Rest rest = new Rest();
            double duration = JMCConverter.proxyTypeToJMCDuration(note.getType().getValue());
            rest.setLength(duration);
            
            phrase.add(rest);
        }
        else
        {
            jm.music.data.Note jmcNote = new jm.music.data.Note();

            int convertPitch = JMCConverter.convertPitch(note.getPitch(), note.getAccidental());
            jmcNote.setPitch(convertPitch);
            double duration = JMCConverter.proxyTypeToJMCDuration(note.getType().getValue());
            jmcNote.setLength(duration);
            
            

            phrase.add(jmcNote);
        }
    }
}
