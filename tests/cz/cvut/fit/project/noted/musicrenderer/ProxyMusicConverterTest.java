package cz.cvut.fit.project.noted.musicrenderer;

import cz.cvut.fit.project.noted.musicrenderer.ProxymusicConverter;
import org.junit.Test;
import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.NoteType;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.Step;
import static org.junit.Assert.*;

public class ProxyMusicConverterTest {

    @Test
    public void  testNoteYtoRenderer() {
        ProxymusicConverter p = new ProxymusicConverter();
        Note note = new Note();
        note.setPitch(new Pitch());
        note.getPitch().setOctave(5);
        note.getPitch().setStep(Step.A);
        int i = ProxymusicConverter.noteYtoRendererY(note);
        assertEquals( -5, i);
    }

    @Test
    public void  testRenderYtoNoteY() {
        ProxymusicConverter pr = new ProxymusicConverter();
        Pitch p = ProxymusicConverter.renderYtoNoteY(-5);
        assertEquals(Step.A, p.getStep());
    }


}
