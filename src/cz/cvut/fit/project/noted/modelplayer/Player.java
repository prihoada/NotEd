package cz.cvut.fit.project.noted.modelplayer;

import cz.cvut.fit.project.noted.model.Model;
import javax.swing.Action;
import jm.JMC;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

/**
 * Music player.
 * @author Adam Příhoda
 */
public class Player {
    
    private Score score;
    private Phrase phrase;
    private final JMCBuilder builder;
    
    private Action playAction;
    private Action stopAction;

    public Player() {
        
        builder = new JMCBuilder();
    }
    
    
    /**
     * Plays a model from the first measure.
     * @param model 
     */
    public void playModel(Model model)
    {
        playModel(model, 0);
    }
    
    /**
     * Plays the model from the specified measure index.
     * @param model
     * @param measureIndex 
     */
    public void playModel(Model model, int measureIndex)
    {
        stop();
        
        if(playAction != null) playAction.setEnabled(false);
        if(stopAction != null) stopAction.setEnabled(true);
        
        score = new Score();
        score.add(new Part());
        
        phrase = new Phrase();
        phrase.setTempo(120);
        phrase.setDynamic(JMC.MEZZO_FORTE);
        phrase = builder.buildFromModel(model, phrase, measureIndex);

        score.getPart(0).add(phrase);
        
        Play.midi(score, false, false, 1, 1);
    }
    
    /**
     * Stops the playback as soon as possible.
     */
    public void stop()
    {
        Play.stopMidi();
        
        if(playAction != null) playAction.setEnabled(true);
        if(stopAction != null) stopAction.setEnabled(false);
    }
    
    
    /**
     * Specifies the actions to contextually enable/disable.
     * @param playAction
     * @param stopAction 
     */
    public void setActions(Action playAction, Action stopAction)
    {
        this.playAction = playAction;
        this.stopAction = stopAction;
    }
}
