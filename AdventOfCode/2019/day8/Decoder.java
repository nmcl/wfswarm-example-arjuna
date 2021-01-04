public class Decoder
{
    public static final int DEFAULT_WIDTH = 25;
    public static final int DEFAULT_HEIGHT = 6;

    public static final String IMAGE_FILE = "image.sif";
    public static void main (String[] args)
    {
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-helps".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug] [-verify] [-width <size>] [-height <size>]");
                System.exit(0);
            }

            if ("-width".equals(args[i]))
                width = Integer.parseInt(args[i+1]);

            if ("-height".equals(args[i]))
                height = Integer.parseInt(args[i+1]);

            if ("-verify".equals(args[i]))
                verify = true;

            if ("-debug".equals(args[i]))
                debug = true;
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");
        }
    }
}