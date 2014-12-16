package cz.cvut.fit.project.noted.rendering;


import cz.cvut.fit.project.noted.localization.LocalizationManager;
import cz.cvut.fit.project.noted.menus.XMLFileChooser;
import cz.cvut.fit.project.noted.model.ProxyMusicHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TabTest {

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
    public void  tabIsNotSavedByDefault() {
        Tab t1 = new Tab(xml,ph,tm);
        assertEquals( false, t1.isSaved());
    }
}
