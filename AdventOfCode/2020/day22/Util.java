import java.util.*;
import java.io.*;

public class Util
{
    public static final String PLAYER_DESIGNATOR = "Player ";

    public static final Deck[] loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Deck[] decks = new Deck[2];
        int player = 0;

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));

            line = reader.readLine();

            if (line.startsWith(PLAYER_DESIGNATOR))
            {
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

        return decks;
    }

    // prevent instantiation

    private Util ()
    {
    }
}