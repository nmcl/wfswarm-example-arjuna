import java.util.*;

/**
 * A map of the tunnel system.
 */

public class Map
{
    public Map (Vector<String> input, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _debug = debug;

        createMap(input);
    }

    @Override
    public String toString ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        int index = 1;
        String str = "Map dimensions < "+_maxX+", "+_maxY+" >\n";

        while (iter.hasMoreElements())
        {
            str += iter.nextElement().getContents();

            if (index++ == _maxX)
            {
                str += "\n";

                index = 1;
            }
        }

        return str;
    }

    protected boolean valid (Coordinate coord)
    {
        if (coord != null)
        {
            if ((coord.getX() >= 0) && (coord.getY() >= 0))
            {
                if ((coord.getX() < _maxX) && (coord.getY() < _maxY))
                    return true;
            }
        }

        return false;
    }

    protected void createMapFromImage (String[] lines)
    {
        int lineLength = lines[0].length(); // all lines are the same length

        _maxY = lines.length -1;
        _maxX = lineLength;

        for (int y = 0; y < lines.length -1; y++)
        {
            for (int x = 0; x < lineLength; x++)
            {
                Cell theCell = new Cell(new Coordinate(x, y), String.valueOf(lines[y].charAt(x)), _debug);

                if (_debug)
                    System.out.println(theCell);

                _theMap.add(theCell);
            }
        }
    }

    protected Vector<Cell> _theMap;
    protected boolean _debug;
}