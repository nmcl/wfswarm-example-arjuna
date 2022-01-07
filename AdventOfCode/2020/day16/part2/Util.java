import java.util.*;
import java.io.*;

public class Util
{
    public static final String YOUR_TICKET = "your ticket:";
    public static final String NEARBY_TICKETS = "nearby tickets:";

    public static final String OR = " or ";

    public static final int CHECKED_NUMBER = -1;

    public static final Vector<Category> order (Vector<Ticket> validTickets, Vector<Category> cats)
    {
        permute(cats, 0, validTickets);

        return cats;
    }

    public static boolean permute (Vector<Category> arr, int k, Vector<Ticket> validTickets)
    {
        for (int i = k; i < arr.size(); i++)
        {
            Collections.swap(arr, i, k);

            if (permute(arr, k +1, validTickets))
                return true;

            Collections.swap(arr, k, i);
        }

        if (k == arr.size() -1)
        {
            /*
             * We have a combination of categories so let's run the
             * tickets through them and see if they are valid.
             */

            //System.out.println(Arrays.toString(arr.toArray()));

            boolean valid = true;

            for (int l = 0; l < validTickets.size(); l++)
            {
                int[] values = validTickets.elementAt(l).values();

                /*
                 * Each value in a ticket must match the category in
                 * the same order.
                 */

                for (int j = 0; j < values.length; j++)
                {
                    if (arr.elementAt(j).valid(values[j]))
                        valid = false;
                }
            }

            if (valid)
                System.out.println("Valid combination: "+Arrays.toString(arr.toArray()));
            else
                System.out.println("Invalid combination: "+Arrays.toString(arr.toArray()));
        }

        return false;
    }

    public static final Vector<Ticket> validTickets (Vector<Category> cats, Vector<Ticket> ticks)
    {
        Vector<Ticket> tickets = new Vector<Ticket>();

        for (int k = 0; k < ticks.size(); k++)
        {
            int[] values = Util.checkAlCategories(cats, ticks.elementAt(k));
            boolean invalid = false;

            for (int index = 0; index < values.length; index++)
            {
                if (values[index] != Util.CHECKED_NUMBER)
                    invalid = true;
            }

            if (!invalid)
                tickets.add(ticks.elementAt(k));
        }

        return tickets;
    }

    public static final int[] checkAlCategories (Vector<Category> cats, Ticket t)
    {
        int[] values = new int[t.values().length];

        for (int k = 0; k < values.length; k++)
            values[k] = t.values()[k];

        for (int i = 0; i < cats.size(); i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                if (values[j] != CHECKED_NUMBER)
                {
                    if (cats.elementAt(i).valid(values[j]))
                        values[j] = CHECKED_NUMBER;
                }
            }
        }

        return values;
    }

    // read the category data and ignore ticket information.

    public static final Vector<Category> loadCategories (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Category> values = new Vector<Category>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = null;
            boolean finished = false;

            while (((line = reader.readLine()) != null) && !finished)
            {
                if (YOUR_TICKET.equals(line) || (NEARBY_TICKETS.equals(line))) // end
                    finished = true;
                else
                {
                    int colonIndex = line.indexOf(":");

                    if (colonIndex != -1)
                    {
                        String type = line.substring(0, colonIndex);
                        int orIndex = line.indexOf(OR);
                        String firstRange = line.substring(colonIndex +1, orIndex).trim();
                        String secondRange = line.substring(orIndex + OR.length()).trim();
                        int r1 = Integer.parseInt(firstRange.substring(0, firstRange.indexOf("-")));
                        int r2 = Integer.parseInt(firstRange.substring(firstRange.indexOf("-") +1));
                        int r3 = Integer.parseInt(secondRange.substring(0, secondRange.indexOf("-")));
                        int r4 = Integer.parseInt(secondRange.substring(secondRange.indexOf("-") +1));

                        Category cat = new Category(type, r1, r2, r3, r4, debug);

                        if (debug)
                            System.out.println("Loaded "+cat);

                        values.add(cat);
                    }
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return values;
    }

    // read the ticket data and ignore categories.

    public static final Vector<Ticket> loadTickets (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Ticket> values = new Vector<Ticket>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = null;
            boolean ticketData = false;

            while ((line = reader.readLine()) != null)
            {
                if (YOUR_TICKET.equals(line) || NEARBY_TICKETS.equals(line))
                {
                    ticketData = true;
                }
                else
                {
                    if (ticketData && (line.indexOf(",") != -1))
                    {
                        String[] range = line.split(",");
                        Ticket t = new Ticket(range);

                        values.add(t);
                    }
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return values;
    }

    private Util ()
    {
    }
}