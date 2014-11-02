package cz.cvut.fit.project.noted.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void getExtensionWorksCorrectly() {

        String fileName = "foo.txt";
        assertEquals("txt", FileUtils.getExtension(fileName));
    }

    @Test
    public void removeExtensionWorksCorrectly() {

        String fileName = "foo.txt";
        assertEquals("foo", FileUtils.removeExtension(fileName));
    }
}
