package cz.cvut.fit.project.noted.editor;

import static org.junit.Assert.assertEquals;

import cz.cvut.fit.project.noted.model.Model;
import org.junit.Test;
import org.junit.Before;

public class CursorTest {

    protected Model model;
    protected Cursor cursor;

    @Before
    public void setUp() {

        model = new Model();
        cursor = new Cursor(model);
    }

    @Test
    public void setCursorWorksCorrectly () {

        cursor.setCursor(1,2,3,4);

        assertEquals(1, cursor.getPart());
        assertEquals(2, cursor.getMeasure());
        assertEquals(3, cursor.getPosition_x());
        assertEquals(4, cursor.getPosition_y());
    }

    @Test
    public void cursorMovesUp () {

        int lastPositionY = cursor.getPosition_y();
        cursor.moveUp();
        assertEquals(lastPositionY - 1, cursor.getPosition_y());
    }

    @Test
    public void cursorMovesDown () {

        int lastPositionY = cursor.getPosition_y();
        cursor.moveDown();
        assertEquals(lastPositionY + 1, cursor.getPosition_y());
    }

}