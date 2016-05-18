import java.util.*;
import javax.swing.*;

public class Main {
	public static int ROW = 3;
	public static int COL = 3;
	public static JFrame MAINFRAME = new JFrame("TicTacToe (with AI)");
	public static Button BUTTONS[][] = new Button[ROW][COL];
	public static Game GAME;

	public static void main(String[] args) {
		LayoutManager layoutManager = new LayoutManager();

		// Initial board
		String[][] initialBoard = new String[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				initialBoard[i][j] = "-";
			}
		}

		Scanner scanner = new Scanner(System.in);

		/*System.out.println("[1] You");
		System.out.println("[2] AI");
		System.out.print("First turn? ");
		int firstTurn = scanner.nextInt();*/

		// State initialState = new State(initialBoard, null, "X", firstTurn == 1 ? 2 : 1);
		State initialState = new State(initialBoard, null, "X", 2);

		GAME = new Game(initialState);

		while (!GAME.getCurrentState().terminal()) {
		}
	}
}