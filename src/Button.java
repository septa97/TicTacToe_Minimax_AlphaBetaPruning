import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton implements ActionListener {
	// Attributes
	private String value;
	private int x, y;

	// Constructor
	public Button(String value, int x, int y) {
		super(value);
		this.value = value;
		this.x = x;
		this.y = y;
	}

	// Methods
	public void actionPerformed(ActionEvent e) {
		if (Main.BUTTONS[x][y].value == "") {

			Action action = new Action(this.x, this.y);
			State newState = Main.GAME.getCurrentState().result(action);
			Main.BUTTONS[this.x][this.y].setValue(Main.GAME.getCurrentState().getMove());
			Main.GAME.changeCurrentState(newState);

			Main.GAME.getCurrentState().printBoard();

			if (isTerminal(newState.getMove())) {
				System.exit(0);				
			}

			/**** --AI's TURN-- ****/

			State state = Main.GAME.getCurrentState().value();
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

			State tempState = Main.GAME.getCurrentState();
			newState = Main.GAME.getCurrentState().result(bestState.getMoveToBeExecuted());
			Main.GAME.changeCurrentState(newState);
			Main.GAME.getCurrentState().printBoard();
			Main.BUTTONS[bestState.getMoveToBeExecuted().getX()][bestState.getMoveToBeExecuted().getY()].setValue(state.getMove());

			if (isTerminal(tempState.getMove())) {
				System.exit(0);				
			}
		}
	}

	private boolean isTerminal(String move) {
		if (hasWinner()) {
			JOptionPane.showMessageDialog(null, "Player " + move + " has won!", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);

			return true;
		}
		else if (isDraw()) {
			JOptionPane.showMessageDialog(null, "DRAW!", "DRAW!", JOptionPane.INFORMATION_MESSAGE);
		
			return true;	
		}

		return false;
	}


	public void setValue(String value) {
		this.setText(value);
		this.value = value;
	}


	private boolean hasWinner() {
		// check the rows
		for (int i = 0; i < Main.ROW; i++) {
			if (Main.BUTTONS[i][0].getText() == Main.BUTTONS[i][1].getText() &&
				Main.BUTTONS[i][0].getText() == Main.BUTTONS[i][2].getText() &&
				Main.BUTTONS[i][0].getText() != "") {

				return true;
			}
		}

		// check the columns
		for (int j = 0; j < Main.COL; j++) {
			if (Main.BUTTONS[0][j].getText() == Main.BUTTONS[1][j].getText() &&
				Main.BUTTONS[0][j].getText() == Main.BUTTONS[2][j].getText() &&
				Main.BUTTONS[0][j].getText() != "") {

				return true;
			}	
		}

		// special cases (diagonal)
		if (Main.BUTTONS[0][0].getText() == Main.BUTTONS[1][1].getText() &&
			Main.BUTTONS[0][0].getText() == Main.BUTTONS[2][2].getText() &&
			Main.BUTTONS[0][0].getText() != "") {

			return true;
		}

		if (Main.BUTTONS[0][2].getText() == Main.BUTTONS[1][1].getText() &&
			Main.BUTTONS[0][2].getText() == Main.BUTTONS[2][0].getText() &&
			Main.BUTTONS[0][2].getText() != "") {

			return true;
		}

		return false;
	}


	private boolean isDraw() {
		for (int i = 0; i < Main.ROW; i++) {
			for (int j = 0; j < Main.COL; j++) {
				if (Main.BUTTONS[i][j].getText() == "") {
					return false;
				}
			}	
		}

		return true;
	}
}