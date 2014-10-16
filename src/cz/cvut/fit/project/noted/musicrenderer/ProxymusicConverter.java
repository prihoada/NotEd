
package cz.cvut.fit.project.noted.musicrenderer;

import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.NoteType;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Step;
import cz.cvut.fit.project.noted.musicrenderer.model.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Provides methods for converting renderer model to proxymusic model.
 * @author Adam Příhoda
 */
public class ProxymusicConverter {
    
     private static Logger LOG = Logger.getLogger(ProxymusicConverter.class.getName());

    /**
     * Converts the supplied proxymusic note to the renderer y position.
     * @param note
     * @return 
     */
    public static int noteYtoRendererY(Note note)
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
                    convertedOffset = 5;
                    break;
                case B:
                    convertedOffset = 6;
                    break;
                case C:
                    convertedOffset = 0;
                    break;
                case D:
                    convertedOffset = 1;
                    break;
                case E:
                    convertedOffset = 2;
                    break;
                case F:
                    convertedOffset = 3;
                    break;
                case G:
                    convertedOffset = 4;
                    break;
            }
            convertedOffset *= -1;
            convertedOffset += octaveShift;
            return convertedOffset;
    }
    
    
    
    /**
     * Converts the supplied renderer y position to the proxymusic pitch.
     * @param rendererY
     * @return 
     */
    public static Pitch renderYtoNoteY(int rendererY)
    {
        Pitch pitch = new Pitch();
        
        int octaveShift = -(rendererY / 7) + 4;
        int offset = rendererY % 7;
        
        if(offset < 0){
            offset += 7;
            octaveShift++;
        }
        
        switch (offset)
        {
            case 2:
                pitch.setStep(Step.A);
                break;
            case 1:
                pitch.setStep(Step.B);
                break;
            case 0:
                pitch.setStep(Step.C);
                octaveShift++; //C is basically 7 in the current octave or 0 in the next
                break;
            case 6:
                pitch.setStep(Step.D);
                break;
            case 5:
                pitch.setStep(Step.E);
                break;
            case 4:
                pitch.setStep(Step.F);
                break;
            case 3:
                pitch.setStep(Step.G);
                break;
        }
        
        pitch.setOctave(octaveShift);
        return pitch;
    }

    
    
    

    /**
     * Mapping for duration types.
     */
    public static final Map<Duration, String> durationMap = new HashMap<>();
    static
    {
        durationMap.put(Duration.Whole, "whole");
        durationMap.put(Duration.Half, "half");
        durationMap.put(Duration.Quarter, "quarter");
        durationMap.put(Duration.Eighth, "eighth");
        durationMap.put(Duration.Sixteenth, "16th");
        durationMap.put(Duration.ThirtySecond, "32nd");
        durationMap.put(Duration.SixtyFourth, "64th");
    }

    /**
     * Converts the renderer model duration to the proxymusic duration type
     * @param duration
     * @return 
     */
    public static NoteType durationToType(Duration duration)
    {
        NoteType noteType = new NoteType();
        noteType.setValue(durationMap.get(duration));
        return noteType;
    }

    /**
     * Converts the proxymusic duration type to the renderer model.
     * @param type
     * @return 
     */
    public static Duration typeToDuration(String type)
    {
        Set<Map.Entry<Duration, String>> entrySet = durationMap.entrySet();
        for (Map.Entry<Duration, String> mapping : entrySet) {
             if(mapping.getValue().equals(type)) return mapping.getKey();
        }
        return Duration.Quarter;
    }
    
}
