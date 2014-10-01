
package cz.cvut.fit.project.noted.musicrenderer.svg;

/**
 * Paint command. Symbols consist of multiple of these.
 * @author Adam Příhoda
 */
public class SvgCommand {

    /**
     * Type of the command.
     */
    public enum TYPE
    {
    //command name                                //svg letter
        MOVE,                                           //M
        MOVE_RELATIVE,                                  //m
        CLOSE,                                          //Z,z
        LINE_ABSOLUTE,                                  //L
        LINE_RELATIVE,                                  //l
        HORIZONTAL_LINE_ABSOLUTE,                       //H
        HORIZONTAL_LINE_RELATIVE,                       //h
        VERTICAL_LINE_ABSOLUTE,                         //V
        VERTICAL_LINE_RELATIVE,                         //v
        CURVE_CUBIC_ABSOLUTE,                           //C
        CURVE_CUBIC_RELATIVE,                           //c
        CURVE_CUBIC_SHORTHAND_ABSOLUTE,                 //S
        CURVE_CUBIC_SHORTHAND_RELATIVE,                 //s
        CURVE_QUADRATIC_ABSOLUTE,                       //Q
        CURVE_QUADRATIC_RELATIVE,                       //q
        CURVE_QUADRATIC_SHORTHAND_ABSOLUTE,             //T
        CURVE_QUADRATIC_SHORTHAND_RELATIVE,             //t
        ;
    }
    
    private final TYPE type;

    public TYPE getType() {
        return type;
    }

    /**
     * Returns the array of parameters for this command.
     * @return array of integers.
     */
    public int[] getParams() {
        return params;
    }
    private final int [] params;

    
    /**
     * Constructs the command.
     * @param type TYPE of the command.
     * @param params array of integer parameters.
     */
    public SvgCommand(TYPE type, int[] params) {
        this.type = type;
        this.params = params;
    }

    
    
    
    @Override
    public String toString() {
    
        //used for debugging
        
        
        String string = "SvgCommand{" + "type=" + type + ", params=[";
        
        for (int i = 0; i < params.length; i++) {
            string += params[i] + ", ";
        }
        string += "]}";
        
        return string;
        
    }
}
