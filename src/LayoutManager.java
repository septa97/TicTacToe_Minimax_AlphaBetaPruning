import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LayoutManager {
	public LayoutManager() {
		/* Setting the layout */
		JPanel mainPanel = new JPanel();
		JPanel boardPanel = new JPanel();

		// Instantiate the buttons
		for (int i = 0; i < Main.ROW; i++) {
			for (int j = 0; j < Main.COL; j++) {
				Main.BUTTONS[i][j] = new Button("", i, j);
				Main.BUTTONS[i][j].addActionListener(Main.BUTTONS[i][j]);
				Main.BUTTONS[i][j].setForeground(Color.WHITE);
				Main.BUTTONS[i][j].setBackground(Color.BLACK);
				Main.BUTTONS[i][j].setFocusPainted(false);
				Main.BUTTONS[i][j].setFont(new Font("Arial", Font.BOLD, 40));
				boardPanel.add(Main.BUTTONS[i][j]);
			}
		}

		// Adding panels
 		mainPanel.setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(Main.ROW, Main.COL));

		mainPanel.add(boardPanel, BorderLayout.CENTER);
		Main.MAINFRAME.add(mainPanel);
		Main.MAINFRAME.setSize(new Dimension(400, 500));
		Main.MAINFRAME.setLocationRelativeTo(null);
		Main.MAINFRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.MAINFRAME.setVisible(true);
	}
}