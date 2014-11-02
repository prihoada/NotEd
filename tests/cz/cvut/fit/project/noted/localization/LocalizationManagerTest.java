package cz.cvut.fit.project.noted.localization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class LocalizationManagerTest {

    @Test
    public void managerTranslatesCorrectly () {

        LocalizationManager manager = new LocalizationManager();
        assertEquals("File", manager.getString("menu_file").getName());
    }

}
