public class Verifier
{
    public static final String EXAMPLE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid theWorld = new Grid(EXAMPLE);

        System.out.println(theWorld);
        
        return false;
    }

    private boolean _debug;
}