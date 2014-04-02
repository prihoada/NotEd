package cz.cvut.fit.project.noted.localization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Handles localization of all strings. Every string visible to the user should
 * be obtained through this class.
 * Localizations are stored in the /lang folder.
 *
 * @author Adam Příhoda
 */
public class LocalizationManager
{
    ///////////////////////
    //SINGLETON
    ///////////////////////
    private static LocalizationManager instance;
    public static LocalizationManager getInstance()
    {
        if (instance == null)
            instance = new LocalizationManager();
        return instance;
    }

    
    private HashMap<String, LocaleString> stringMap;

    private LocalizationManager()
    {
        stringMap = new HashMap<String, LocaleString>();
        
        //default locale
        loadXML("en-gb.xml");
    }

    
    /**
     * Loads an external xml file from the /lang/ folder and fills the stringMap with data.
     * @param xmlFileName 
     */
    @SuppressWarnings("unchecked")
    private void loadXML(String xmlFileName)
    {
        stringMap = new HashMap<String, LocaleString>();
        InputStream in = null;
        try
        {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            try
            {
                in = new FileInputStream("lang/" + xmlFileName);
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            XMLEventReader reader = inputFactory.createXMLEventReader(in);

            LocaleString readString = null;
            while(reader.hasNext())
            {
                XMLEvent event = reader.nextEvent();

                    if(event.isStartElement())
                    {
                        StartElement startElement = event.asStartElement();
                        if(startElement.getName().getLocalPart().equals("item"))
                        {
                            event = reader.nextEvent();
                            readString = new LocaleString();
                            readString.setName(event.asCharacters().getData());
                            
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if(attribute.getName().getLocalPart().equals("id_flag"))
                                {
                                    readString.setId_flag(attribute.getValue());
                                }
                                else if(attribute.getName().getLocalPart().equals("tooltip"))
                                {
                                    readString.setTooltip(attribute.getValue());
                                }
                                else if(attribute.getName().getLocalPart().equals("mnemonic"))
                                {
                                    readString.setMnemonic(attribute.getValue());
                                }
                            }
                        }
                    }
                    else if(event.isEndElement())
                    {
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equals("item"))
                        {
                            stringMap.put(readString.getId_flag(), readString);
                        }
                    }
            }
        }
        catch (XMLStreamException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Returns the LocaleString associated with given key.
     * @param idFlag the key.
     * @return LocaleSring from current locale or LocaleString with name "unknown".
     */
    public LocaleString getString(String idFlag)
    {
        if(stringMap.containsKey(idFlag))
        {
            return stringMap.get(idFlag);
        }
        return new LocaleString().setName("Unknown");
    }
    
    
    
    
    
    
}
