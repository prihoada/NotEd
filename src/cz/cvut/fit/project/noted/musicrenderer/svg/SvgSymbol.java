
package cz.cvut.fit.project.noted.musicrenderer.svg;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SVG base symbol. Contains command needed to paint the symbol on the screen.
 * Each symbol is instantiated only once and kept in the SVGRepository.
 * @author Adam Příhoda
 */
public class SvgSymbol {

    
    private final Logger LOGGER = Logger.getLogger(SvgSymbol.class.getName());
    
    /**
     * List of paint commands.
     */
    private final ArrayList<SvgCommand> commands;

    /**
     * Returns the list of paint commands.
     * @return ArrayList of SvgCommand.
     */
    public ArrayList<SvgCommand> getCommands() {
        return commands;
    }
    
    public SvgSymbol(String svgPathDataString)
    {
        commands = new ArrayList<>();
        parseData(svgPathDataString);
    }

    /**
     * Parses the string and makes the commands.
     * @param svgPathDataString 
     */
    private void parseData(String svgPathDataString) {

        SvgParser parser = new SvgParser(svgPathDataString);
        
        while(parser.hasNext())
        {
            try
            {
                SvgCommand command = null;

                char type = parser.getNextType();
                
                switch (type)
                {
                    case 'M':
                        command = new SvgCommand(SvgCommand.TYPE.MOVE, getIntegers(parser, 2));
                        break;
                    case 'm':
                        command = new SvgCommand(SvgCommand.TYPE.MOVE_RELATIVE, getIntegers(parser, 2));
                        break;
                    case 'z':
                    case 'Z':
                        command = new SvgCommand(SvgCommand.TYPE.CLOSE, new int[0]);
                        break;
                    case 'H':
                        command = new SvgCommand(SvgCommand.TYPE.HORIZONTAL_LINE_ABSOLUTE, getIntegers(parser, 1));
                        break;
                    case 'h':
                        command = new SvgCommand(SvgCommand.TYPE.HORIZONTAL_LINE_RELATIVE, getIntegers(parser, 1));
                        break;
                    case 'V':
                        command = new SvgCommand(SvgCommand.TYPE.VERTICAL_LINE_ABSOLUTE, getIntegers(parser, 1));
                        break;
                    case 'v':
                        command = new SvgCommand(SvgCommand.TYPE.VERTICAL_LINE_RELATIVE, getIntegers(parser, 1));
                        break;
                    case 'L':
                        command = new SvgCommand(SvgCommand.TYPE.LINE_ABSOLUTE, getIntegers(parser, 2));
                        break;
                    case 'l':
                        command = new SvgCommand(SvgCommand.TYPE.LINE_RELATIVE, getIntegers(parser, 2));
                        break;
                    case 'C':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_CUBIC_ABSOLUTE, getIntegers(parser, 6));
                        break;
                    case 'c':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_CUBIC_RELATIVE, getIntegers(parser, 6));
                        break;
                    case 'S':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_ABSOLUTE, getIntegers(parser, 4));
                        break;
                    case 's':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_RELATIVE, getIntegers(parser, 4));
                        break;
                    case 'Q':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_QUADRATIC_ABSOLUTE, getIntegers(parser, 4));
                        break;
                    case 'q':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_QUADRATIC_RELATIVE, getIntegers(parser, 4));
                        break;
                    case 'T':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_ABSOLUTE, getIntegers(parser, 2));
                        break;
                    case 't':
                        command = new SvgCommand(SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_RELATIVE, getIntegers(parser, 2));
                        break;
                    
                    default:
                        LOGGER.log(Level.WARNING, "Unknown svg command type");
                        break;

                }

                if(command != null) this.commands.add(command);
                
            }
            catch (SvgParsingException ex)
            {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Convenience method for retrieving multiple integers from the parser.
     * @param parser parser that is ready to read integers.
     * @param numberOfIntegers number of values to read.
     * @return array of integers with numberOfIntegers elements.
     * @throws SvgParsingException on parsing error.
     */
    private int[] getIntegers(SvgParser parser, int numberOfIntegers) throws SvgParsingException
    {
        int [] array = new int[numberOfIntegers];
        
        for (int i = 0; i < numberOfIntegers; i++) {
            array[i] = parser.getNextInt();
        }
        return array;
    }
    
  
    
}

