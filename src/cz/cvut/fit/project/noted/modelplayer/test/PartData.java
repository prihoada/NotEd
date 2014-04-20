

package cz.cvut.fit.project.noted.modelplayer.test;

import java.util.List;

public class PartData
{
    final String id;
    final String name;
    final List<MeasuresData> measures;

    public PartData(String id,
            String name,
            List<MeasuresData> measures)
    {
        this.id = id;
        this.name = name;
        this.measures = measures;
    }
}
