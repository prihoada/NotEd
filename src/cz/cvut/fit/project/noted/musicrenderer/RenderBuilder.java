package cz.cvut.fit.project.noted.musicrenderer;

import com.audiveris.proxymusic.Attributes;
import com.audiveris.proxymusic.Clef;
import com.audiveris.proxymusic.Key;
import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Rest;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.Step;
import com.audiveris.proxymusic.Time;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarSeparator;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.ClefGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.NoteGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.RestGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.ScoreGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.base.StaveGlyph;
import cz.cvut.fit.project.noted.musicrenderer.model.BarSeparatorType;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

/**
 * Handles the conversion from a model to the renderer.
 * @author Adam Příhoda
 */
public class RenderBuilder {
    
    
    Logger LOG = Logger.getLogger(RenderBuilder.class.getName());
    
    /**
     * Score this builder is building for.
     */
    private ScoreGlyph score;
    
    /**
     * Number of divisions the builder is currently working with.
     */
    private int divisions;

    
    public RenderBuilder(ScoreGlyph score) {
        this.score = score;
    }
    
    
    
    
    
    /**
     * Rebuild the entire model.
     * @param model
     */
    public void buildFromModel(Model model)
    {
        ScorePartwise modelHierarchy = model.getModelHierarchy();
        
        //PartList partList = modelHierarchy.getPartList();
        List<ScorePartwise.Part> parts = modelHierarchy.getPart();
        
        //build the staves from top to bottom
        for (ScorePartwise.Part part : parts) {
    
            buildPart(part);
        }

        
        
    }

    /**
     * Build a single stave/part
     * @param part 
     */
    private void buildPart(ScorePartwise.Part part) {
        
        List<ScorePartwise.Part.Measure> measures = part.getMeasure();
        
        StaveGlyph stave = new StaveGlyph();
        
        for (ScorePartwise.Part.Measure measure : measures) {
            buildMeasure(stave, measure);
        }
        score.addGlyph(stave);
    }

    /**
     * Builds a single measure/bar.
     * @param stave parent stave.
     * @param measure model measure.
     */
    private void buildMeasure(StaveGlyph stave, ScorePartwise.Part.Measure measure) {

        List<Object> objects = measure.getNoteOrBackupOrForward();
        
        BarGlyph bar = new BarGlyph();
        
        for (Object object : objects) {
        
            //System.out.println("Creating object " + object.getClass().getName());
            
            //parse attributes, usually once per stave
            if(object instanceof Attributes)
            {
                Attributes attributes = (Attributes) object;
                divisions = attributes.getDivisions().intValue();
                       
                buildClefs(bar, attributes.getClef());
                buildKeySignature(bar, attributes.getKey());
                buildTimeSignature(bar, attributes.getTime());
            }
            //parse a note, contains rests as well
            if(object instanceof Note)
            {
                buildNote(bar, (Note) object);
            }
        }

        bar.addGlyph(new BarSeparator(BarSeparatorType.SINGLE));
        stave.addGlyph(bar);
    }

    /**
     * Builds a note or a rest.
     * @param bar parent bar
     * @param note model note.
     */
    private void buildNote(BarGlyph bar, Note note) {

        Rest rest = note.getRest();
        if(rest != null)
        {
            RestGlyph restGlyph = new RestGlyph(convertDurationType(note.getType().getValue()));
            bar.addGlyph(restGlyph);
        }
        else
        {
          
            Pitch pitch = note.getPitch();
            Step step = pitch.getStep();

            //convert model octave to renderer octave
            int octave = pitch.getOctave() - 5; //model has c1 as octave 4
            int octaveShift = -octave * 7; //7 positions in an octave

            //convert model pitch to renderer pitch
            int convertedOffset = 0;
            switch (step)
            {
                case A:
                    convertedOffset = -7;
                    break;
                case B:
                    convertedOffset = -8;
                    break;
                case C:
                    convertedOffset = -2;
                    break;
                case D:
                    convertedOffset = -3;
                    break;
                case E:
                    convertedOffset = -4;
                    break;
                case F:
                    convertedOffset = -5;
                    break;
                case G:
                    convertedOffset = -6;
                    break;
            }

            convertedOffset += octaveShift;
            NoteGlyph noteGlyph = new NoteGlyph(convertDurationType(note.getType().getValue()), convertedOffset);
            bar.addGlyph(noteGlyph);
        }
    }
    
    /**
     * Converts the duration of the model to the renderer duration. 
     * Model duration works as described in http://www.musicxml.com/tutorial/the-midi-compatible-part/duration/
     * Deprecated in favor of convertDurationType()
     * @param duration duration of the model. 
     * @return the renderer duration.
     */
    @Deprecated
    private Duration convertDuration(BigDecimal modelDuration)
    {
        Duration rendererDuration = Duration.Quarter;
        int duration = modelDuration.intValue();
        
        System.out.println("Converting duration: " + duration);
        
        if(duration == divisions)
        {
            rendererDuration = Duration.Quarter;
        }
        else if(duration == divisions*2)
        {
            rendererDuration = Duration.Half;
        }
        else if(duration == divisions/2)
        {
            rendererDuration = Duration.Eighth;
        }
        else if(duration == divisions/4)
        {
            rendererDuration = Duration.Sixteenth;
        }
        
        else
        {
            System.out.println("Unknown duration " + duration);
        }
        
        return rendererDuration;
    }
    
    public Duration convertDurationType(String type)
    {
        Duration rendererDuration = Duration.Quarter;
        
        switch (type)
        {
            case "quarter":
                rendererDuration = Duration.Quarter;
                break;
            case "half":
                rendererDuration = Duration.Half;
                break;
            case "eighth":
                rendererDuration = Duration.Eighth;
                break;
            case "whole":
                rendererDuration = Duration.Whole;
                break;
            case "16th":
                rendererDuration = Duration.Sixteenth;
                break;
            case "32nd":
                rendererDuration = Duration.ThirtySecond;
                break;
            case "64th":
                rendererDuration = Duration.SixtyFourth;
                break;
                
            default:
                LOG.warning("Unknown note duration type: " + type);
                break;
        }
        
        return rendererDuration;
    }

    /**
     * Builds the clefs.
     * @param clefs list of clefs of a measure
     */
    private void buildClefs(BarGlyph bar, List<Clef> clefs) {
        
        if(clefs == null) return;
        
        for (Clef clef : clefs)
        {
            switch (clef.getSign())
            {
                case G:
                    bar.addGlyph(new ClefGlyph(cz.cvut.fit.project.noted.musicrenderer.model.Clef.G2));
                    break;
                    
                //TODO other clefs
                    
                default:
                    LOG.warning("Unknown clef type: " + clef.getSign());
                    break;
                    
            }
        }
        
    }

    /**
     * Build the key signature. This is the set of accidentals determining overall half-tone modifiers.
     * @param bar bar to put the signature in
     * @param keys list of keys to build
     */
    private void buildKeySignature(BarGlyph bar, List<Key> keys) {
        
        if(keys == null) return;
        
        for (Key key : keys) {
            
        //TODO
            
        }
    }

    /**
     * Build the time signatures. Bottom number represents the duration of the note. Top number represents the number of notes in the bar.
     * @param bar
     * @param times 
     */
    private void buildTimeSignature(BarGlyph bar, List<Time> times) {

        if(times == null) return;
        
        for (Time time : times) {
            
            //TODO
            
        }
        
    }
    
}
