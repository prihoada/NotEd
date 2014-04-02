package cz.cvut.fit.project.noted.localization;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**
 *
 * @author Adam Příhoda
 */
public class LocaleString
{
    
    private String id_flag = "";
    private String name = "";
    private String tooltip = "";
    private String mnemonic = "";

    
    public String getId_flag()
    {
        return id_flag;
    }

    public LocaleString setId_flag(String id_flag)
    {
        this.id_flag = id_flag;
        return this;
    }
    
    public String getName()
    {
        return name;
    }

    public LocaleString setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getTooltip()
    {
        return tooltip;
    }

    public LocaleString setTooltip(String tooltip)
    {
        this.tooltip = tooltip;
        return this;
    }

    public String getMnemonic()
    {
        return mnemonic;
    }
    public int getMnemonicKeyCode()
    {
        if(this.mnemonic.isEmpty()) return 0;
        return KeyStroke.getKeyStroke(mnemonic).getKeyCode();
    }

    public LocaleString setMnemonic(String mnemonic)
    {
        this.mnemonic = mnemonic;
        return this;
    }
    
    
}
