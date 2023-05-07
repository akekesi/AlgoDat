import java.util.Stack;
import org.junit.jupiter.api.Test;

class BettelmannTest {

    @Test
    void testWorksheet1() {
        // game with a complete, shuffled deck
        Bettelmann game = new Bettelmann();
        game.distributeCards();

        System.out.println("ONLY PRINT, NO ASSERT:");
        System.out.println("Initial situation (top card first):\n" + game);
        int round = 0;
        while (round < 1000000 && game.getWinner() < 0) {
            round++;
            game.playRound();
            System.out.println("State after round " + round + ":\n" + game);
        }
    }

    @Test
    void testWorksheet2() {
        // game with specific distributed deck
        int[] deckArray = {28, 30, 6, 23, 17, 14};
        Stack<Card> deck = new Stack<>();
        for (int id : deckArray) {
            deck.push(new Card(id));
        }
        Bettelmann game = new Bettelmann();
        game.distributeCards(deck);

        System.out.println("ONLY PRINT, NO ASSERT:");
        System.out.println("Initial situation (top card first):\n" + game);
        int round = 0;
        while (round < 1000000 && game.getWinner()<0) {
            round++;
            game.playRound();
            System.out.println("State after round " + round + ":\n" + game);
        }
    }
}
