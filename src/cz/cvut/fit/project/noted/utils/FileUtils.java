package cz.cvut.fit.project.noted.utils;

/**
 * Class for file generic file utilities.
 *
 * @author Adam Příhoda
 */
public class FileUtils
{

    /**
     * Returns the extension of the filename supplied.
     * @param fileName fileName to extract the extension from.
     * @return extension (lower case) or an empty string.
     */
    public static String getExtension(String fileName)
    {
        String ext = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1)
        {
            ext = fileName.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Removes the extension from the supplied fileName
     * @param fileName fileName to remove extension from.
     * @return the new fileName without an extension.
     */
    public static String removeExtension(String fileName)
    {
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1)
        {
            return fileName.substring(0, i);
        }
        else return fileName;
    }

}
