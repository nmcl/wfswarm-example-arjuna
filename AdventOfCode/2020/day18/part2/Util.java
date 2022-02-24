import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<String> loadData (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<String> data = new Vector<String>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;
            Tile t = null;

            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith(TileData.TILE_ID))
                {
                    
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return data;
    }

    private Util ()
    {
    }
}