public class Verifier
{
    public static final String EXAMPLE_WORLD = "example.txt";
    
    public Verifier (int iterations, boolean debug)
    {
        _iterations = iterations;
        _debug = debug;
    }

    public boolean verify ()
    {
        Dimension dim = new Dimension(EXAMPLE_WORLD, _iterations, _debug);

        System.out.println("Loaded:\n\n"+dim);

        dim.cycle();

        System.out.println("Now:\n\n"+dim);

        return false;
    }

    private int _iterations;
    private boolean _debug;
}