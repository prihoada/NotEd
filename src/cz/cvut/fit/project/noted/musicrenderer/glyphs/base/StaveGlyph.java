
package cz.cvut.fit.project.noted.musicrenderer.glyphs.base;

import cz.cvut.fit.project.noted.musicrenderer.glyphs.BarGlyph;
import cz.cvut.fit.project.noted.musicrenderer.glyphs.layout.BaseStaffLayoutEngine;


/**
 * Container for a single track (Like a single instrument or a voice). Staves consist of bars.
 * @author Adam Příhoda
 */
public class StaveGlyph extends GlyphGroup<BarGlyph>
{

    private BaseStaffLayoutEngine layoutEngine;

    private int topOverflow;
    private int bottomOverflow;
    
    
    public StaveGlyph()
    {
        this.layoutEngine = new BaseStaffLayoutEngine(this);
        
        setPadding(10);
    }

    
    @Override
    public void doLayout() {

        //staves use layout engines to do the layouting.
    }
    
    /**
     * Returns the layout engine for this staff.
     * @return 
     */
    public BaseStaffLayoutEngine getLayoutEngine() {
        return layoutEngine;
    }

    public void setLayoutEngine(BaseStaffLayoutEngine layoutEngine) {
        this.layoutEngine = layoutEngine;
    }

    @Override
    public StaveGlyph getStave() {
        return this;
    }

    
    
    
    /**
     * Return the amount of reserved height above this stave. Non zero when the staff is part of a staff group.
     * @return amount of reserved height above this stave.
     */
    public int getTopOverflow() {
        return topOverflow;
    }

    public void setTopOverflow(int topOverflow) {
        this.topOverflow = topOverflow;
    }

    /**
     * Return the amount of reserved height below this stave. Non zero when the staff is part of a staff group.
     * @return amount of reserved height below this stave.
     */
    public int getBottomOverflow() {
        return bottomOverflow;
    }

    public void setBottomOverflow(int bottomOverflow) {
        this.bottomOverflow = bottomOverflow;
    }
    
    
    
    
    
    
    
    
}
