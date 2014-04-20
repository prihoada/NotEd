

package cz.cvut.fit.project.noted.modelplayer;

import cz.cvut.fit.project.noted.model.ParsingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import jm.JMC;

/**
 *
 * @author Nguyen Viet Bach
 */
public class PlayTest implements JMC
{
    public static void main(String[] args) throws FileNotFoundException, ParsingException
    {
        final ModelPlayer modelPlayer = new ModelPlayer(null);
        System.out.println(modelPlayer.getReadyToPlay());
       
        JFrame jf = new JFrame();
        JButton jb = new JButton("Click to play");
        jb.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                modelPlayer.play();
            }
        });
        jf.add(jb);
        jf.pack();
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(200, 100);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        //View.sketch(modelPlayer.getPhrase());
    }
}
