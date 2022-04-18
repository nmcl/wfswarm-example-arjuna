import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long EXAMPLE_RESULT = 273;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Tile> tiles = Util.loadData(EXAMPLE_DATA);

        if (_debug)
        {
            Iterator<Tile> iter = tiles.iterator();

            System.out.println("Loaded:\n\n");

            while (iter.hasNext())
            {
                System.out.println(iter.next());
            }
        }

        Solver s = new Solver(_debug);
        Vector<Tile> corners = s.solve(tiles);

        return false;
    }

    private boolean _debug;
}