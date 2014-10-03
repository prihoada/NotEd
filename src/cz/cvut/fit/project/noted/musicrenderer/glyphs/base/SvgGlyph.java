
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.logging.Logger;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgCommand;
import cz.cvut.fit.project.noted.musicrenderer.svg.SvgSymbol;

/**
 * A base class for an actual graphics symbol painted on screen.
 * @author Adam Příhoda
 */
public class SvgGlyph extends Glyph {

    Logger LOGGER = Logger.getLogger(SvgGlyph.class.getName());
    
    /**
     * All the SVG data is scaled up so it can be stored as integers.
     * This value scales it back.
     */
    private static final double SvgScaleFactor = 0.0099;

    /**
     * SVG data symbol.
     */
    private SvgSymbol symbol;

    protected double scaleX = 1 * SvgScaleFactor;
    protected double scaleY = 1 * SvgScaleFactor;
    
    /**
     * SVG symbol Y offset. Used to correct the alignment of the svg.
     */
    protected double offsetY = 0;
    /**
     * SVG symbol X offset. Used to correct the alignment of the svg.
     */
    protected double offsetX = 0;
    
    public SvgGlyph() {
    
        setPadding(0);
        
        //debug value
        setGlyphWidth(3);
    }
    
    

    
    
    
    /**
     * Sets the SVG data symbol.
     * @param symbol 
     */
    public void setSymbol(SvgSymbol symbol) {
        this.symbol = symbol;
    }


    @Override
    public void doLayout() {
        this.setSymbolWidth(10);
    }
    
    
    

    /**
     * Paints the glyph to the supplied graphics.
     * 
     * @param xOrig
     * @param yOrig
     * @param g 
     */
    @Override
    public void paint(int xOrig, int yOrig, Graphics2D g) {
        
        /**
         * previous control point. Used for shorthand curves.
         */
        Point2D.Double lastControl = new Point2D.Double(0, 0);
        /**
         * Current paint brush position.
         */
        Point2D.Double current = new Point2D.Double(0, 0);
        /**
         * Previous command painted.
         */
        SvgCommand.TYPE lastCommandType = null;
        
        float x = xOrig;
        float y = yOrig;
        //correct for svg symbol positon
        x += offsetX;
        y += offsetY;
        
        Path2D path = new Path2D.Double();
        
        for (SvgCommand command : symbol.getCommands())
        {
            int[] params = command.getParams();
            switch (command.getType())
            {
                //move to a location
                case MOVE:
                    current.x = x + params[0] * scaleX;
                    current.y = y + params[1] * scaleY;
                    path.moveTo(current.x, current.y);
                    break;

                //move by an amount
                case MOVE_RELATIVE:
                    current.x += params[0] * scaleX;
                    current.y += params[1] * scaleY;
                    path.moveTo(current.x, current.y);
                    break;
                    
                //draw a horizontal line to a location
                case HORIZONTAL_LINE_ABSOLUTE:
                    current.x = x + params[0] * scaleX;
                    path.lineTo(current.x, current.y);
                    
                    break;
                    
                //draw a horizontal line from the current paint point.
                case HORIZONTAL_LINE_RELATIVE:
                    current.x += (params[0] * scaleX);
                    path.lineTo(current.x, current.y);
                    break;
                case VERTICAL_LINE_ABSOLUTE:
                    current.y = y + params[0] * scaleY;
                    path.lineTo(current.x, current.y);
                    break;
                case VERTICAL_LINE_RELATIVE:
                    current.y += (params[0] * scaleY);
                    path.lineTo(current.x, current.y);
                    break;
                    
                //bezier curve
                case CURVE_CUBIC_ABSOLUTE:
                {
                    double x1 = (x + params[0] * scaleX);
                    double y1 = (y + params[1] * scaleY);
                    double x2 = (x + params[2] * scaleX);
                    double y2 = (y + params[3] * scaleY);
                    double x3 = (x + params[4] * scaleX);
                    double y3 = (y + params[5] * scaleY);
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x3;
                    current.y = y3;
                    
                    path.curveTo(x1, y1, x2, y2, x3, y3);
                    
                    break;
                    
                } 
                case CURVE_CUBIC_RELATIVE:
                {
                    double x1 = (current.x + params[0] * scaleX);
                    double y1 = (current.y + params[1] * scaleY);
                    double x2 = (current.x + params[2] * scaleX);
                    double y2 = (current.y + params[3] * scaleY);
                    double x3 = (current.x + params[4] * scaleX);
                    double y3 = (current.y + params[5] * scaleY);
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x3;
                    current.y = y3;
                    
                    path.curveTo(x1, y1, x2, y2, x3, y3);
                    
                    break;
                    
                } 
                //shorthand bezier. Uses previous curve as a reference.
                case CURVE_CUBIC_SHORTHAND_ABSOLUTE:
                {   
                    boolean canCurveContinue = (lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_ABSOLUTE || 
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_RELATIVE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_ABSOLUTE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_RELATIVE);
                    
                    double x1 = (x + params[0] * scaleX);
                    double y1 = (y + params[1] * scaleY);
                    double x2 = canCurveContinue ? current.x + (current.x - lastControl.x) : current.x;
                    double y2 = canCurveContinue ? current.y + (current.y - lastControl.y) : current.y;
                    double x3 = (x + params[2] * scaleX);
                    double y3 = (y + params[3] * scaleY);
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x3;
                    current.y = y3;
                    
                    path.curveTo(x1, y1, x2, y2, x3, y3);
                
                    break;    
                }
                case CURVE_CUBIC_SHORTHAND_RELATIVE:
                {   
                    boolean canCurveContinue = (lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_ABSOLUTE || 
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_RELATIVE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_ABSOLUTE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_CUBIC_SHORTHAND_RELATIVE);
                    
                    double x1 = (current.x + params[0] * scaleX);
                    double y1 = (current.y + params[1] * scaleY);
                    double x2 = canCurveContinue ? current.x + (current.x - lastControl.x) : current.x;
                    double y2 = canCurveContinue ? current.y + (current.y - lastControl.y) : current.y;
                    double x3 = (current.x + params[2] * scaleX);
                    double y3 = (current.y + params[3] * scaleY);
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x3;
                    current.y = y3;
                    
                    path.curveTo(x1, y1, x2, y2, x3, y3);
                
                    break;    
                }
                
                //quadratic curve
                case CURVE_QUADRATIC_ABSOLUTE:
                {
                    double x1 = (x + params[0] * scaleX);
                    double y1 = (y + params[1] * scaleY);
                    double x2 = (x + params[2] * scaleX);
                    double y2 = (y + params[3] * scaleY);
                    
                    lastControl.x = x1;
                    lastControl.y = y1;
                    current.x = x2;
                    current.y = y2;
                    
                    path.quadTo(x1, y1, x2, y2);
                    break;
                }
                case CURVE_QUADRATIC_RELATIVE:
                {
                    double x1 = (current.x + params[0] * scaleX);
                    double y1 = (current.y + params[1] * scaleY);
                    double x2 = (current.x + params[2] * scaleX);
                    double y2 = (current.y + params[3] * scaleY);
                    
                    lastControl.x = x1;
                    lastControl.y = y1;
                    current.x = x2;
                    current.y = y2;
                    
                    path.quadTo(x1, y1, x2, y2);
                    break;
                }
                case CURVE_QUADRATIC_SHORTHAND_ABSOLUTE:
                {   
                    boolean canCurveContinue = (lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_ABSOLUTE || 
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_RELATIVE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_ABSOLUTE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_RELATIVE);
                    
                    double x1 = (x + params[0] * scaleX);
                    double y1 = (y + params[1] * scaleY);
                    double x2 = canCurveContinue ? current.x + (current.x - lastControl.x) : current.x;
                    double y2 = canCurveContinue ? current.y + (current.y - lastControl.y) : current.y;
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x1;
                    current.y = y1;
                    
                    path.quadTo(x2, y2, x1, y1);
                
                    break;    
                }
                case CURVE_QUADRATIC_SHORTHAND_RELATIVE:
                {   
                    boolean canCurveContinue = (lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_ABSOLUTE || 
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_RELATIVE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_ABSOLUTE ||
                                        lastCommandType == SvgCommand.TYPE.CURVE_QUADRATIC_SHORTHAND_RELATIVE);
                    
                    double x1 = (current.x + params[0] * scaleX);
                    double y1 = (current.y + params[1] * scaleY);
                    double x2 = canCurveContinue ? current.x + (current.x - lastControl.x) : current.x;
                    double y2 = canCurveContinue ? current.y + (current.y - lastControl.y) : current.y;
                    
                    
                    lastControl.x = x2;
                    lastControl.y = y2;
                    current.x = x1;
                    current.y = y1;
                    
                    path.quadTo(x2, y2, x1, y1);
                
                    break;    
                }
                
                case LINE_RELATIVE:
                    current.x += (params[0] * scaleX);
                    current.y += (params[1] * scaleY);
                    path.lineTo(current.x, current.y);
                    break;
                
                case LINE_ABSOLUTE:
                    current.x = x + params[0] * scaleX;
                    current.y = y + params[1] * scaleY;
                    path.lineTo(current.x, current.y);
                    break;
                //close the path
                case CLOSE:
                    path.closePath();
                    break;
                    
                default:
                    LOGGER.warning("Unknown svg command type " + command.getType().name());
                    break;
            }
            
            lastCommandType = command.getType();
        }

        //finally draw the path 
        g.draw(path);
        g.fill(path);
        
        
        
        
        //debug padding and width
        //do NOT remove this
        
//        g.setColor(new Color(33, 33, 33, 33));
//        g.fillRect((int) (x - offsetX), (int) (y - offsetY + -25), getSymbolWidth(), 50);
//        g.setColor(Color.BLACK);
        
    }
    
}
