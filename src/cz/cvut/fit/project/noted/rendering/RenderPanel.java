/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.project.noted.rendering;

import abc.notation.KeySignature;
import abc.notation.Music;
import abc.notation.Note;
import abc.notation.TimeSignature;
import abc.notation.Tune;
import abc.ui.swing.JScoreComponent;
import com.audiveris.proxymusic.Attributes;
import com.audiveris.proxymusic.PartList;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.ScorePartwise;
import cz.cvut.fit.project.noted.model.Model;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import java.util.List;
import javax.swing.JPanel;
import jm.JMC;
import jm.music.data.Phrase;

/**
 *
 * @author david
 */
public class RenderPanel extends JPanel
{

    private final Model model;

    private Tune tune;
    private KeySignature key;
    private Music music;
    private JScoreComponent scoreUI;

    public RenderPanel(Model model)
    {
        this.model = model;
        scoreUI = new JScoreComponent();

        this.add(scoreUI);
        update();
    }

    void update()
    {
        tune = new Tune();
        key = new KeySignature(Note.D, KeySignature.MAJOR);
        tune.getMusic().addElement(key);
        music = tune.getMusic();
        music.addElement(TimeSignature.SIGNATURE_4_4);

        
        ScorePartwise score = model.getModelHierarchy();
        PartList partList = score.getPartList();

        List<ScorePartwise.Part> parts = score.getPart();

        for (int i = 0; i < parts.size(); i++)
        {
            ScorePartwise.Part part = parts.get(i);
            List<ScorePartwise.Part.Measure> measures = part.getMeasure();
            for (int j = 0; j < measures.size(); j++)
            {
                ScorePartwise.Part.Measure measure = measures.get(j);
                List<Object> objs = measure.getNoteOrBackupOrForward();
                for (int k = 0; k < objs.size(); k++)
                {
                    Attributes attributes = null;

                    Object obj = objs.get(k);
                    if (obj instanceof Attributes)
                    {
                        //...... 
                    } else if (obj instanceof com.audiveris.proxymusic.Note)
                    {
                        com.audiveris.proxymusic.Note note = (com.audiveris.proxymusic.Note) obj;
                        printOneNote(note.getPitch());
                    }
                }

            }
        }

        scoreUI.setTune(tune);
    }

    void printOneNote(Pitch pitch)
    {
        StringBuilder result = new StringBuilder();
        result = result.append(pitch.getStep());
        String b = result.toString();
        char note = b.charAt(0);

        switch (note)
        {
            case 'C':
                //abc.notation.Accidental a =  new abc.notation.Accidental();
                music.addElement(new Note(Note.C, pitch.getOctave() - 4));
                break;
            case 'D':
                music.addElement(new Note(Note.D, pitch.getOctave() - 4));
                break;
            case 'E':
                music.addElement(new Note(Note.E, pitch.getOctave() - 4));
                break;
            case 'F':
                music.addElement(new Note(Note.F, pitch.getOctave() - 4));
                break;
            case 'G':
                music.addElement(new Note(Note.G, pitch.getOctave() - 4));
                break;
            case 'A':
                music.addElement(new Note(Note.A, pitch.getOctave() - 4));
                break;
            case 'B':
                music.addElement(new Note(Note.B, pitch.getOctave() - 4));
                break;

        }

    }

}
