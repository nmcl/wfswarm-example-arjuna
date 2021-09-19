public class Level
{
    public static final int CENTRE_HEIGHT = 3;  // TODO only makes sense for 5x5 so need to fix this!!
    public static final int CENTRE_WIDTH = 3;

    public Level (int height, int width, int level, boolean debug)
    {
        _level = level;
        _height = height;
        _width = width;
        _debug = debug;

        _theWorld = new Tile[_height][_width];

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                _theWorld[i][j] = new Tile(TileId.EMPTY_SPACE);
            }
        }

        _theWorld[CENTRE_HEIGHT][CENTRE_WIDTH] = new Tile(TileId.NESTED_GRID);
    }

    public Level (Tile[][] theWorld, int level, boolean debug)
    {
        _theWorld = theWorld;
        _height = _theWorld.length;
        _width = _theWorld[0].length;
        _level = level;
        _debug = debug;
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if ((i == CENTRE_HEIGHT) && (j == CENTRE_WIDTH))
                    str += TileId.NESTED_GRID;
                else
                    str += _theWorld[i][j];
            }
            
            str += "\n";
        }

        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Level temp = (Level) obj;

            if ((temp._height == _height) && (temp._width == _width))
            {
                for (int i = 0; i < _height; i++)
                {
                    for (int j = 0; j < _width; j++)
                    {
                        if (temp._theWorld[i][j].type() != _theWorld[i][j].type())
                            return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    protected Level (Level theGrid)
    {
        _height = theGrid._height;
        _width = theGrid._width;
        _debug = theGrid._debug;

        _theWorld = new Tile[_height][_width];

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                _theWorld[i][j] = new Tile(theGrid._theWorld[i][j].type());
            }
        }
    }

    private Tile[][] _theWorld;
    private int _level;
    private int _height;
    private int _width;
    private boolean _debug;
}