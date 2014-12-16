package cz.cvut.fit.project.noted.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProxyMusicHandlerTest {

    @Test
    public void loadModel() {
        ProxyMusicHandler ph = new ProxyMusicHandler();
        String filePath = "assets/unnamed.xml";
        try {
            Model m = ph.getModel(filePath);
            assertEquals(filePath, m.getFilePath());
        } catch (Exception e) {
            assertEquals("no exception", "failed to load model");
        }
    }
}
