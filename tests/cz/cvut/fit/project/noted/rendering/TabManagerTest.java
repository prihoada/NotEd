package cz.cvut.fit.project.noted.rendering;

import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TabManagerTest {

    private LocalizationManager lm;
    private TabManager tm;
    private XMLFileChooser xml;
    private ProxyMusicHandler ph;

    @Before
    public void setup() {
        lm = new LocalizationManager();
        xml = new XMLFileChooser(lm);
        ph = new ProxyMusicHandler();
        tm = new TabManager(lm);
    }

    @Test
    public void  addTab() {
        assertEquals(0,tm.getTabCount());
        Tab t1 = new Tab(xml,ph, tm);
        tm.addTab("prvni",t1);
        assertEquals(1,tm.getTabCount());
    }

    @Test
    public void setTitle() {
        Tab t1 = new Tab(xml,ph, tm);
        tm.addTab("prvni",t1);

        tm.setTitleAt(0,"test");
        assertEquals("test", tm.getTitleAt(0));
    }
}
