import java.util.*;

public class Tunnel
{
    public static final String TUNNEL_DATA = "tunnel.txt";

    public static final void main (String[] args)
    {
        String dataFile = TUNNEL_DATA;
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-data <file>] [-verify] [-debug] [-help]");
                System.exit(0);
            }
            
            if ("-data".equals(args[i]))
                dataFile = args[i+1];

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            theVerifier.verify();
        }
        else
        {
            Vector<String> input = Util.readMap(TUNNEL_DATA);
            Map theMap = new Map(input, debug);

            System.out.println(theMap);
        }
    }
}