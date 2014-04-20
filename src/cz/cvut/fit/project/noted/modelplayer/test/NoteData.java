

package cz.cvut.fit.project.noted.modelplayer.test;

import com.audiveris.proxymusic.Step;
import java.math.BigDecimal;

public class NoteData
{
    final Step pitchStep;

    final int pitchOctave;

    final BigDecimal duration;

    final String type;

    public NoteData(Step pitchStep,
            int pitchOctave,
            BigDecimal duration,
            String type)
    {
        this.pitchStep = pitchStep;
        this.pitchOctave = pitchOctave;
        this.duration = duration;
        this.type = type;
    }
}
