import java.util.*;

public class Game
{
    // hard coded that number of players is 2

    public Game (boolean debug)
    {
        _debug = debug;
    }

    public final int play (Deck[] decks)
    {
        int round = 1;
        int game = 1;
        Deck winningDeck = new Deck(0);

        recursiveCombat(round, game, decks, new int[] { decks[0].size(), decks[1].size() }, winningDeck);

        return winningDeck.score();
    }

    private Deck recursiveCombat (int round, int game, Deck[] decks, int[] offsets, Deck winningDeck)
    {
        Deck[] theDecks = new Deck[2];

        theDecks[0] = new Deck(decks[0], offsets[0]);
        theDecks[1] = new Deck(decks[1], offsets[1]);

        HashSet<String> rounds = new HashSet<String>();

        if (_debug)
            System.out.println("\n=== Game "+game+" ===");

        while (!theDecks[0].isEmpty() && !theDecks[1].isEmpty())
        {
            if (_debug)
            {
                System.out.println("\n-- Round "+round+" (Game "+game+") --");
                System.out.println(theDecks[0]);
                System.out.println(theDecks[1]);
            }

            if (!rounds.add(theDecks[0].stringForm() + theDecks[1].stringForm()))
                return decks[0];

            int playerOneCard = theDecks[0].draw();
            int playerTwoCard = theDecks[1].draw();

            if (_debug)
            {
                System.out.println("Player 1 plays: "+playerOneCard);
                System.out.println("Player 2 plays: "+playerTwoCard);
            }

            if ((theDecks[0].size() >= playerOneCard) && (theDecks[1].size() >= playerTwoCard))
            {
                if (_debug)
                    System.out.println("Playing a sub-game to determine the winner...");

                if (recursiveCombat(1, game +1, theDecks, new int[] { playerOneCard, playerTwoCard}, null) == theDecks[0])
                {
                    if (_debug)
                        System.out.println("Player 1 wins round "+round+" of game "+game+"!");

                    theDecks[0].add(playerOneCard);
                    theDecks[0].add(playerTwoCard);
                }
                else
                {
                    if (_debug)
                        System.out.println("Player 2 wins round "+round+" of game "+game+"!");

                    theDecks[1].add(playerOneCard);
                    theDecks[1].add(playerTwoCard);
                }
            }
            else
            {
                if (playerOneCard > playerTwoCard)
                {
                    if (_debug)
                        System.out.println("Player 1 wins round "+round+" of game "+game+"!");

                    theDecks[0].add(playerOneCard);
                    theDecks[0].add(playerTwoCard);
                }
                else
                {
                    if (_debug)
                        System.out.println("Player 2 wins round "+round+" of game "+game+"!");

                    theDecks[1].add(playerTwoCard);
                    theDecks[1].add(playerOneCard);
                }
            }

            round++;
        }

        if (_debug)
        {
            System.out.println("\n== Post-game results ==");
            System.out.println(theDecks[0]);
            System.out.println(theDecks[1]);
        }

        if (winningDeck != null)
            winningDeck = (theDecks[1].isEmpty() ? theDecks[0] : theDecks[1]);

        if (_debug)
            System.out.print("The winner of game "+game+" is player ");

        if (theDecks[1].isEmpty())
        {
            if (_debug)
                System.out.println("1!\n...anyway, back to game "+(game-1)+".");

            return decks[0];
        }
        else
        {
            if (_debug)
                System.out.println("2!\n...anyway, back to game "+(game-1)+".");

            return decks[1];
        }
    }

    private boolean _debug;
}