import java.util.*;

public class MovementLogic
{
    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;
    public static final int ROUTINE_D = 3;

    public static final String MOVE_LEFT = "L";
    public static final String MOVE_RIGHT = "R";

    public MovementLogic (Map theMap, boolean debug)
    {
        _theMap = theMap;
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

             --> start here!
             
             // only change facing when we start to move off again

            if (!tryMoveLeft(_currentPosition))
                tryMoveRight(_currentPosition);

            return true;
        }
        else
        {
            System.out.println("Robot not found!");

            return false;
        }
    }

    private boolean tryMoveLeft (Coordinate curr)
    {
        // go as far left as we can from current location

        Coordinate coord = leftCoordinate(curr);

        System.out.println("tryMoveLeft: "+coord);

        if (_theMap.isScaffold(coord))
        {
            changeFacing(MOVE_LEFT);

            _currentMoveDirection = MOVE_LEFT;
            _path.push(_currentMoveDirection);

            return tryMoveLeft(coord);
        }
        else
        {
            System.out.println("Left change facing! "+curr);

            return changeDirection(curr);
        }
    }

    private boolean tryMoveRight (Coordinate curr)
    {
        // go as far right as we can from current location

        Coordinate coord = rightCoordinate(curr);

        System.out.println("tryMoveRight: "+coord);

        if (_theMap.isScaffold(coord))
        {
            changeFacing(MOVE_RIGHT);

            _currentMoveDirection = MOVE_RIGHT;
            _path.push(_currentMoveDirection);

            return tryMoveRight(coord);
        }
        else
        {
            System.out.println("Right change facing! "+curr);
            
            return changeDirection(curr);
        }
    }

    private void changeFacing (String moveDirection)
    {
        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_UP:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
                else
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
            }
            break;
            case CellId.ROBOT_FACING_DOWN:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
                else
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
                else
                    _robotFacing = CellId.ROBOT_FACING_UP;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_UP;
                else
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
            }
            break;
        }
    }

    private final boolean changeDirection (Coordinate coord)
    {
        
    }

    private final Coordinate rightCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

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

    private final Coordinate leftCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

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

    private Map _theMap;
    private Stack<String> _path;
    private String _robotFacing;
    private String _currentMoveDirection;
    private Coordinate _currentPosition;
    private boolean _debug;
}