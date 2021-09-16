package pokerGameTest;

import java.util.logging.Logger;

import junit.framework.TestCase;
import pokerGameController.PokerGame;
import pokerGameModel.Hand;

public class PokerTest extends TestCase {
	static Logger logger = Logger.getLogger(PokerTest.class.getName());

	static String nameBlack = "Black";
	static String nameWhite = "White";
	static String tie = "Tie.";
	static String[] blackHandString = { "2H 3D 5S 9C KD", "2H 4S 4C 2D 4H", "2H 3D 5S 9C KD", "2H 3D 5S 9C KD" };
	static String[] whiteHandString = { "2C 3H 4S 8C AH", "2S 8S AS QS 3S", "2C 3H 4S 8C KH", "2D 3H 5C 9S KH" };

	public static void testGetWinner() throws Exception {

		assertEquals(nameWhite, new PokerGame().getWinner(new Hand(blackHandString[0], nameBlack),
				new Hand(whiteHandString[0], nameWhite)));
		assertEquals(nameBlack, new PokerGame().getWinner(new Hand(blackHandString[1], nameBlack),
				new Hand(whiteHandString[1], nameWhite)));

		assertEquals(nameBlack, new PokerGame().getWinner(new Hand(blackHandString[2], nameBlack),
				new Hand(whiteHandString[2], nameWhite)));

		assertEquals(tie, new PokerGame().getWinner(new Hand(blackHandString[3], nameBlack),
				new Hand(whiteHandString[3], nameWhite)));

	}

}
