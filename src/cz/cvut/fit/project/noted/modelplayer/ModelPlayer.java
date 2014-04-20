

package cz.cvut.fit.project.noted.modelplayer;

import com.audiveris.proxymusic.*;
import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;
import cz.cvut.fit.project.noted.model.*;
import java.io.*;
/**
 * Important!!!
 */
import java.lang.String;
/**
 * Important!!!
 */
import java.math.BigDecimal;
import java.util.List;
import jm.music.data.*;
import jm.JMC;

/**
 *
 * @author Nguyen Viet Bach
 */
public class ModelPlayer
{
    private final Model model;
    private final ProxyMusicHandler proxyMusicHandler;
    private final String filePath;

    /* private Sequence sequence;
     private Sequencer sequencer;
     private Synthesizer synthesizer;
     private final float volume = 0.9f;*/
    private final Phrase phrase;
    private final double newTempo;

    public ModelPlayer(String filePath) throws FileNotFoundException, ParsingException
    {
        /**
         * temporary for testing purpose TestFile...
         */
        filePath = "assets/musixXMLTest_MORE.xml";
        newTempo = 130;

        this.filePath = filePath;
        proxyMusicHandler = ProxyMusicHandler.getInstance();
        model = proxyMusicHandler.getModel(this.filePath);
        phrase = new Phrase();
        phrase.setTempo(newTempo);
        phrase.setDynamic(JMC.FORTISSIMO);
    }

    public void play()
    {
        jm.util.Play.midi(phrase);
    }

    public String getReadyToPlay()
    {
        ScorePartwise score = model.getModelHierarchy();
        PartList partList = score.getPartList();
        /*List<Object> partListMembers = partList.getPartGroupOrScorePart();
         List<ScorePart> scoreParts = new ArrayList<>();
         List<PartGroup> partGroups = new ArrayList<>();

         for (int i = 0; i < partListMembers.size(); i++)
         {
         Object obj = partListMembers.get(i);
         if (obj instanceof ScorePart)
         {
         scoreParts.add((ScorePart) obj);
         }
         else if (obj instanceof PartGroup)
         {
         partGroups.add((PartGroup) obj);
         }
         }*/
        List<Part> parts = score.getPart();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.size(); i++)
        {
            result.append("Part: P").append(i + 1).append(printPart(parts.get(i)));
        }
        return result.toString();
    }

    private String printPart(Part part)
    {
        StringBuilder result = new StringBuilder();
        List<Measure> measures = part.getMeasure();
        for (int j = 0; j < measures.size(); j++)
        {
            result.append("\n   Measure number: ").append(measures.get(j).getNumber()).append("      ").append(printMeasure(measures.get(j)));
        }
        return result.toString();
    }

    private String printMeasure(Measure measure)
    {
        StringBuilder result = new StringBuilder();
        Attributes attributes = null;
        com.audiveris.proxymusic.Note note = null;
        List<Object> objs = measure.getNoteOrBackupOrForward();
        for (int k = 0; k < objs.size(); k++)
        {
            Object obj = objs.get(k);
            if (obj instanceof Attributes)
            {
                attributes = (Attributes) (obj);
            }
            else if (obj instanceof com.audiveris.proxymusic.Note)
            {
                note = (com.audiveris.proxymusic.Note) (obj);
                result.append("\n      Note: ").append(printNote(note));
            }
        }
        return result.toString();
    }

    private String printNote(com.audiveris.proxymusic.Note note)
    {
        StringBuilder result = new StringBuilder();
        Pitch pitch = note.getPitch();
        BigDecimal duration = note.getDuration();
        NoteType type = note.getType();
        result.append("[Pitch: ").append(pitch.getStep()).append(pitch.getOctave())
                .append(", Duration: ").append(duration).append(", Type: ").append(type.getValue()).append("]");

        jm.music.data.Note theNote = new jm.music.data.Note();
        theNote.setPitch(proxyPitchToJMC(pitch.getStep(), pitch.getOctave()));
        theNote.setDuration(proxyTypeToJMCDuration(type.getValue()));
        phrase.addNote(theNote);

        return result.toString();
    }

    private int proxyPitchToJMC(Step step, int octave)
    {
        int pitch = 0;
        switch (step.value())
        {
            case "C":
                pitch = JMC.C0;
                break;
            case "D":
                pitch = JMC.D0;
                break;
            case "E":
                pitch = JMC.E0;
                break;
            case "F":
                pitch = JMC.F0;
                break;
            case "G":
                pitch = JMC.G0;
                break;
            case "A":
                pitch = JMC.A0;
                break;
            case "B":
                pitch = JMC.B0;
                break;
        }

        pitch = pitch + (octave * 12);

        return pitch;
    }

    private double proxyTypeToJMCDuration(String type)
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
            case "sixteenth":
                return JMC.SIXTEENTH_NOTE;
            default:
                return JMC.QUARTER_NOTE;
        }
    }

    public Phrase getPhrase()
    {
        return phrase;
    }
}

/*else if (obj instanceof Backup)
 {
 backup = (Backup) (obj);
 }
 else if (obj instanceof Forward)
 {
 forward = (Forward) (obj);
 }
 else if (obj instanceof Direction)
 {
 direction = (Direction) (obj);
 }
 else if (obj instanceof Harmony)
 {
 harmony = (Harmony) (obj);
 }
 else if (obj instanceof FiguredBass)
 {
 figuredBass = (FiguredBass) (obj);
 }
 else if (obj instanceof Print)
 {
 print = (Print) (obj);
 }
 else if (obj instanceof com.audiveris.proxymusic.Sound)
 {
 proxySound = (com.audiveris.proxymusic.Sound) (obj);
 }
 else if (obj instanceof Barline)
 {
 barline = (Barline) (obj);
 }
 else if (obj instanceof Grouping)
 {
 grouping = (Grouping) (obj);
 }
 else if (obj instanceof Link)
 {
 link = (Link) (obj);
 }
 else if (obj instanceof Bookmark)
 {
 bookmark = (BookMark) (obj);
 }
 */
