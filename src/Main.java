import java.util.*;
import javax.swing.*;

public class Main {
	public static int ROW = 3;
	public static int COL = 3;
	public static JFrame MAINFRAME;
	public static Button BUTTONS[][];
	public static Game GAME;

	public static void main(String[] args) {
		startGame();
	}

	public static void startGame() {
		MAINFRAME = new JFrame("TicTacToe (with AI)");
		BUTTONS = new Button[ROW][COL];
		LayoutManager layoutManager = new LayoutManager();

		// Initial board
		String[][] initialBoard = new String[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				initialBoard[i][j] = "-";
			}
		}

		// Let the player choose who is the first turn
		int firstTurn = chooseFirstTurn();

		State initialState = new State(initialBoard, null, "X", firstTurn == 1 ? 2 : 1);

		GAME = new Game(initialState);

		if (firstTurn == 2) {
			State state = GAME.getCurrentState().value(Integer.MIN_VALUE, Integer.MAX_VALUE);
			State bestState = null;

			for (State s : state.getChildren()) {
				if (s.getUtility() == 1) {
					bestState = s;
					break;
				}
			}

			if (bestState == null) {
				for (State s : state.getChildren()) {
					if (s.getUtility() == 0) {
						bestState = s;
						break;
					}
				}
			}

			State tempState = GAME.getCurrentState();
			State newState = GAME.getCurrentState().result(bestState.getMoveToBeExecuted());
			GAME.changeCurrentState(newState);
			GAME.getCurrentState().printBoard();
			BUTTONS[bestState.getMoveToBeExecuted().getX()][bestState.getMoveToBeExecuted().getY()].setValue(state.getMove());
		}
	}

	private static int chooseFirstTurn() {
		String[] choices = {"Human first", "AI first"};

		String choice;

		do {
			choice = (String) JOptionPane.showInputDialog(null, "Choose first turn", "First turn", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);	
		} while (choice == null);

		if (choice == "Human first") {
			return 1;
		}
		else {
			return 2;
		}
	}
}