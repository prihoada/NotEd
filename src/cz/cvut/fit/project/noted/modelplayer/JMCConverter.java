package cz.cvut.fit.project.noted.modelplayer;

import com.audiveris.proxymusic.Accidental;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Step;
import com.sun.istack.internal.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import jm.JMC;

/**
 *
 * @author Adam Příhoda
 */
public class JMCConverter {

    
    
    private static final Map<Step, Integer> pitchMap = new HashMap<>();
    static
    {
        pitchMap.put(Step.C, JMC.C0);
        pitchMap.put(Step.D, JMC.D0);
        pitchMap.put(Step.E, JMC.E0);
        pitchMap.put(Step.F, JMC.F0);
        pitchMap.put(Step.G, JMC.G0);
        pitchMap.put(Step.A, JMC.A0);
        pitchMap.put(Step.B, JMC.B0);
    }
 
    
    /**
     * Converts the proxyMusic pitch to JMC pitch value.
     * @param pitch
     * @return 
     */
    public static int convertPitch(Pitch pitch)
    {
        return convertPitch(pitch, null);
    }
    
    /**
     * Converts the proxyMusic pitch to JMC pitch value.
     * @param pitch
     * @param accidental
     * @return 
     */
    static int convertPitch(Pitch pitch, Accidental accidental) {
        Integer tone = pitchMap.get(pitch.getStep());
        tone += (pitch.getOctave() * 12);
        
        if(accidental != null)
        {
            switch (accidental.getValue())
            {
                case FLAT:
                    tone--;
                    break;
                case SHARP:
                    tone++;
                    break;
                case DOUBLE_SHARP:
                    tone += 2;
                    break;
                case FLAT_FLAT:
                    tone -= 2;
                    break;
                default:
                    break;
            }
        
        }
        return tone;
        
    }
    
    
    
    public static double proxyTypeToJMCDuration(String type)
    {
        switch (type)
        {
            case "quarter":
                return JMC.QUARTER_NOTE;
            case "whole":
                return JMC.WHOLE_NOTE;
            case "half":
                return JMC.HALF_NOTE;
            case "eighth":
                return JMC.EIGHTH_NOTE;
            case "16th":
                return JMC.SIXTEENTH_NOTE;
            case "32nd":
                return JMC.THIRTYSECOND_NOTE;
            case "64th":
                return 1/64;
            default:
                Logger.getLogger(JMCBuilder.class).warning("Unknown duration type: " + type);
                return JMC.QUARTER_NOTE;
        }
    }

}
