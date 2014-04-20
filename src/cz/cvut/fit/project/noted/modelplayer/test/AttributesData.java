

package cz.cvut.fit.project.noted.modelplayer.test;

import com.audiveris.proxymusic.ClefSign;
import java.math.BigDecimal;
import java.math.BigInteger;

public class AttributesData
{
    final BigDecimal divisions;

    final BigInteger fifths;

    final String beats;

    final String beatType;

    final ClefSign clefSign;

    final BigInteger clefLine;

    public AttributesData(BigDecimal divisions,
            BigInteger fifths,
            String beats,
            String beatType,
            ClefSign clefSign,
            BigInteger clefLine)
    {
        this.divisions = divisions;
        this.fifths = fifths;
        this.beats = beats;
        this.beatType = beatType;
        this.clefSign = clefSign;
        this.clefLine = clefLine;
    }
}
