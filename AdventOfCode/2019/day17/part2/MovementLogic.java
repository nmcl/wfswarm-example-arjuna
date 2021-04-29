import java.util.*;

public class MovementLogic
{
    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;
    public static final int ROUTINE_D = 3;

    public MovementLogic (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _robotTrack = new Trail(_theMap);
        _path = new Stack<String>();
        _robotFacing = CellId.ROBOT_FACING_UP;
        _currentMoveDirection = "";
        _currentPosition = new Coordinate(_theMap.findStartingPoint());
        _debug = debug;
    }

    public void createMovementFunctions ()
    {
        createPath();

        String pathElement = _path.pop();

        System.out.println("Path:");

        while (pathElement != null)
        {
            System.out.println(pathElement);

            try
            {
                _path.pop();
            }
            catch (Exception ex)
            {
                pathElement = null;
            }
        }
    }

    public void createMovementRoutine ()
    {
        
    }

    /*
     * Create path using only L and R.
     */

    private boolean createPath ()
    {
        System.out.println("createPath from: "+_currentPosition);

        if (_currentPosition != null)
        {
            /*
             * Try L then R.
             * 
             * Robot always starts facing up. Change facing when we
             * start to move but remember initial facing so we can
             * refer movement directions as L or R.
             */

            if (!tryToMove(CellId.MOVE_LEFT, leftCoordinate(_currentPosition)))
            {
                return tryToMove(CellId.MOVE_RIGHT, rightCoordinate(_currentPosition));
            }
            else
                return true;
        }
        else
        {
            System.out.println("Robot not found!");

            return false;
        }
    }

    private boolean tryToMove (String direction, Coordinate coord)
    {
        System.out.println("tryMove: "+coord+" with direction: "+direction);
        System.out.println("and current position: "+_currentPosition);

        /*
         * Already visited? Might be sufficient to just check .path
         */

        if (_robotTrack.visited(coord) && !_robotTrack.path(coord))
        {
            System.out.println("Robot already visited this location.");

            return false;
        }

        System.out.println("Location not visited ... yet.");

        if (_theMap.isScaffold(coord))
        {
            System.out.println("Is scaffolding!");

            _currentMoveDirection = direction;
            _path.push(_currentMoveDirection);
            
            _robotTrack.changeElement(coord, _currentMoveDirection);
            _currentPosition = coord;

            System.out.println("\n"+_robotTrack);
        }
        else
        {
            System.out.println("Not scaffolding!");

            System.out.println("Robot was facing "+_robotFacing+" and moving "+direction);

            if (_theMap.theEnd(_currentPosition))
                return false;

            changeFacing();

            System.out.println("Robot now facing "+_robotFacing);

            String nextDirection = getNextDirection();

            System.out.println("Next direction to try with new facing: "+nextDirection);

            direction = nextDirection;
        }

        if (CellId.MOVE_LEFT.equals(direction))
            coord = leftCoordinate(_currentPosition);
        else
            coord = rightCoordinate(_currentPosition);

        return tryToMove(direction, coord);
    }

    private String getNextDirection ()
    {
        System.out.println("Getting next direction to move from: "+_currentPosition);

        Coordinate coord = leftCoordinate(_currentPosition);

        System.out.println("Left coordinate would be: "+coord);

        if (_robotTrack.visited(coord) || !_robotTrack.isScaffold(coord))
        {
            System.out.println("Visited so try right ...");

            coord = rightCoordinate(_currentPosition);

            System.out.println("Right coordinate would be: "+coord);

            if (_robotTrack.visited(coord))
                return null;
            else
                return CellId.MOVE_RIGHT;
        }
        else
        {
            System.out.println("Not visited.");

            return CellId.MOVE_LEFT;
        }
    }

    private void changeFacing ()
    {
        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_UP:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
                else
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
            }
            break;
            case CellId.ROBOT_FACING_DOWN:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
                else
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
                else
                    _robotFacing = CellId.ROBOT_FACING_UP;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_UP;
                else
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
            }
            break;
        }
    }

    /*
     * Map/Trail can deal with invalid Coordinates.
     */

    private final Coordinate rightCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        System.out.println("rightCoordinate facing: "+_robotFacing+" and position: "+coord);

        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_DOWN:
            {
                x--;
            }
            break;
            case CellId.ROBOT_FACING_UP:
            {
                x++;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                y--;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                y++;
            }
            break;
        }

        return new Coordinate(x, y);
    }

    private final Coordinate leftCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        System.out.println("leftCoordinate facing: "+_robotFacing+" and position: "+coord);

        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_DOWN:
            {
                x++;
            }
            break;
            case CellId.ROBOT_FACING_UP:
            {
                x--;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                y++;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                y--;
            }
            break;
        }

        return new Coordinate(x, y);
    }

    private Map _theMap;
    private Trail _robotTrack;
    private Stack<String> _path;
    private String _robotFacing;
    private String _currentMoveDirection;
    private Coordinate _currentPosition;
    private boolean _debug;
}