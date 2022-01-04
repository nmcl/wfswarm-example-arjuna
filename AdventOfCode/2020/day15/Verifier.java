public class Verifier
{
    public final int[] EXAMPLE_1 = { 0, 3, 6 };
    public final int EXAMPLE_1_RESULT = 436;
    public final int[] EXAMPLE_2 = { 1, 3, 2 };
    public final int EXAMPLE_2_RESULT = 1;
    public final int[] EXAMPLE_3 = { 2, 1, 3 };
    public final int EXAMPLE_3_RESULT = 10;
    public final int[] EXAMPLE_4 = { 1, 2, 3 };
    public final int EXAMPLE_4_RESULT = 27;
    public final int[] EXAMPLE_5 = { 2, 3, 1 };
    public final int EXAMPLE_5_RESULT = 78;
    public final int[] EXAMPLE_6 = { 3, 2, 1 };
    public final int EXAMPLE_6_RESULT = 438;
    public final int[] EXAMPLE_7 = { 3, 1, 2 };
    public final int EXAMPLE_7_RESULT = 1836;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Memory mem = new Memory(_debug);
        Integer lastNumberSpoken = speakInitial(EXAMPLE_1, mem);

        for (int i = 3; i < 2020; i++)
        {
            System.out.println("Turn "+i+" and "+lastNumberSpoken);

            if (mem.firstTimeSpoken(lastNumberSpoken))
                lastNumberSpoken = 0;
            else
                lastNumberSpoken = mem.getTurnDifference(lastNumberSpoken);

            System.out.println("**got "+lastNumberSpoken);

            mem.speakNumber(lastNumberSpoken);
        }

        return false;
    }

    private Integer speakInitial (int[] initial, Memory mem)
    {
        for (int i = 0; i < initial.length; i++)
        {
            mem.speakNumber(initial[i]);
        }

        return initial[2];
    }

    private boolean _debug;
}