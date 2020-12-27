import java.io.*;

public class Intcode
{
    /*
     * Could be enum values but ints are just so much easier!
     */

     /**
      * The Opcodes for the computer.
      */

    public static final int ADD = 1;
    public static final int MULTIPLY = 2;
    public static final int INPUT_AND_STORE = 3;
    public static final int OUTPUT = 4;
    public static final int HALT = 99;
   
    /**
     * The Parameter modes.
     */

    public static final int POSITION_MODE = 0;  // parameter is interpreted as a position
    public static final int IMMEDIATE_MODE = 1; // parameter is interpreted as a value

    public static final String DELIMITER = ",";

    /*
     * This implementation is stateless other than being placed
     * into debug mode where it will output whatever action it
     * takes.
     */

    public Intcode (boolean debug)
    {
        _debug = debug;
    }

    public String parseAndExecute (String[] values)
    {
        for (int i = 0; i < values.length; i++)
        {
            String str = getOpcode(values[i]);

            if (_debug)
                System.out.println("Working on entry "+i+" with "+str);

            switch (Integer.valueOf(str))
            {
                case Intcode.ADD:
                {
                    /*
                     * Opcode 1 adds together numbers read from two positions
                     * and stores the result in a third position. The three integers
                     * immediately after the opcode tell you these three positions - the
                     * first two indicate the positions from which you should read the
                     * input values, and the third indicates the position at which
                     * the output should be stored.
                     */

                     int position1 = Integer.valueOf(values[i+1]);
                     int position2 = Integer.valueOf(values[i+2]);
                     int store = Integer.valueOf(values[i+3]);

                     if (_debug)
                        System.out.println("Adding "+values[position1]+" and "+values[position2]);

                     int sum = Integer.valueOf(values[position1])+Integer.valueOf(values[position2]);

                     if (_debug)
                        System.out.println("Storing "+sum+" at position "+store);

                     values[store] = String.valueOf(sum);

                     i = i+3;  // move the pointer on.
                }
                break;
                case Intcode.MULTIPLY:
                {
                    /*
                     * Opcode 2 works exactly like opcode 1, except it multiplies the
                     * two inputs instead of adding them. Again, the three integers after
                     * the opcode indicate where the inputs and outputs are, not their values.
                     */

                    int position1 = Integer.valueOf(values[i+1]);
                    int position2 = Integer.valueOf(values[i+2]);
                    int store = Integer.valueOf(values[i+3]);

                    if (_debug)
                        System.out.println("Multiplying "+values[position1]+" and "+values[position2]);

                    int product = Integer.valueOf(values[position1])*Integer.valueOf(values[position2]);

                    if (_debug)
                        System.out.println("Storing "+product+" at position "+store);

                    values[store] = String.valueOf(product);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Intcode.HALT:
                {
                    /*
                     * Means that the program is finished and should immediately halt.
                     */

                     if (_debug)
                        System.out.println("Halting execution.");

                     return values[0];
                }
                default:
                {
                    System.out.println("Unknown opcocde "+str+" encountered");

                    return "NaN";
                }
            }
        }

        return values[0];
    }

    private String getOpcode (String digits)
    {
        if ((digits != null) && (digits.length > 2))
            return String.substring(digits.length-2);
        else
            return null;
    }

    private boolean _debug;
}