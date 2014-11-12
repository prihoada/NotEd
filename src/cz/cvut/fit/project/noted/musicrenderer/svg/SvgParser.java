
package cz.cvut.fit.project.noted.musicrenderer.svg;

/**
 * SVG string parser. Provides and easy method of parsing the string, while checking for syntax errors.
 * @author Adam Příhoda
 */
public class SvgParser {

    /**
     * SVG string
     */
    private final String svg;
    /**
     * Current caret position.
     */
    private int currentPosition;
    /**
     * Length of the svg string.
     */
    private int svgLength;
    
    
    public SvgParser(String svg) {
        this.svg = svg;
        this.svgLength = svg.length();
    }
    
    /**
     * Returns true if the SVG string contains another symbol.
     * @return true if the SVG string contains another symbol.
     */
    public boolean hasNext()
    {
        return currentPosition < svgLength;
    }
    
    
    /**
     * Returns the next letter in the string. Letters symbolize type of commands.
     * Moves to the next position.
     * @return char symbolizing the command. @see SVGRepository for command letter types.
     * @throws SvgParsingException if the current character is not a letter or the parser has reached the end of the string.
     */
    public char getNextType() throws SvgParsingException
    {
        if(!hasNext()) throw new SvgParsingException("Index out of bounds");
        
        skipWhitespaces();
        
        char c = svg.charAt(currentPosition);
        if(!isLetter(c))
        {
            throw new SvgParsingException("Next token is not a type");
        }
        currentPosition++;
        return c;
    }
    
    /**
     * Returns the next integer in the string.
     * Moves the caret after the number.
     * @return the integer value read.
     * @throws SvgParsingException if the current character is not a number or the parser has reached the end of the string. 
     */
    public int getNextInt() throws SvgParsingException
    {
        if(!hasNext()) throw new SvgParsingException("Index out of bounds");
        
        skipWhitespaces();
        
        StringBuilder builder = new StringBuilder();
        while(hasNext() && isNumber(svg.charAt(currentPosition)))
        {
            builder.append(svg.charAt(currentPosition));
            currentPosition++;
        }
        return (int) Double.parseDouble(builder.toString());
    }
    
    
    /**
     * Skips all spaces and new lines.
     */
    private void skipWhitespaces()
    {
        while(hasNext() && isWhiteSpace(svg.charAt(currentPosition)))
        {
            currentPosition++;
        }
    }
    
    private boolean isNumber(char c)
    {
        return Character.isDigit(c) || c == '-' || c == '.';
    }
    private boolean isWhiteSpace(char c)
    {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == ',';
    }
    private boolean isLetter(char c)
    {
        return Character.isLetter(c);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
