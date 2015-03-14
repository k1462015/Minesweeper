package personalMinesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MinesweeperGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Minesweeper m;
	private JPanel board;
	private JToggleButton[][] squaresArray;
	private boolean gameStatus;

	public MinesweeperGUI(Minesweeper m) {
		super("Minesweeper");
		this.m = m;
		// try {
		// for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		// if ("Nimbus".equals(info.getName())) {
		// UIManager.setLookAndFeel(info.getClassName());
		// break;
		// }
		// }
		// } catch (Exception e) {
		// // If Nimbus is not available, you can set the GUI to another look
		// // and feel.
		// }
		initUi();
	}

	public void initUi() {
		// Basic GUI Layout
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenu settings = new JMenu("Settings");
		JMenu help = new JMenu("Help");

		// Settings menu
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");

		// Adds actionListeners to all difficulty settings
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(9, 9, 10);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(500, 500);
				gui.setLocationRelativeTo(null);
			}

		});
		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(16, 16, 40);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(1000, 1000);
				gui.setLocationRelativeTo(null);
			}

		});
		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Minesweeper min = new Minesweeper(30, 16, 99);
				MinesweeperGUI gui = new MinesweeperGUI(min);
				gui.setSize(1600, 900);
				gui.setLocationRelativeTo(null);

			}

		});

		// Adds to settings menu
		settings.add(easy);
		settings.add(medium);
		settings.add(hard);

		// Create Top Tool bar
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		JButton createNew = new JButton("New");
		JButton pause = new JButton("Pause");
		toolbar.add(createNew);
		toolbar.add(pause);

		createNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				if (getWidth() == 500) {
					Minesweeper min = new Minesweeper(9, 9, 10);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(500, 500);
					gui.setLocationRelativeTo(null);
				} else if (getWidth() == 1000) {
					Minesweeper min = new Minesweeper(16, 16, 40);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(1000, 1000);
					gui.setLocationRelativeTo(null);
				} else if (getWidth() == 1600) {
					Minesweeper min = new Minesweeper(30, 16, 99);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(1600, 900);
					gui.setLocationRelativeTo(null);
				} else {
					Minesweeper min = new Minesweeper(9, 9, 10);
					MinesweeperGUI gui = new MinesweeperGUI(min);
					gui.setSize(500, 500);
					gui.setLocationRelativeTo(null);
				}

			}

		});

		// Creates Bottom Tool bar
		JToolBar botbar = new JToolBar();
		botbar.setFloatable(false);
		JLabel statusBar = new JLabel("Status bar");
		botbar.add(statusBar);
		add(botbar, BorderLayout.SOUTH);

		menuBar.add(menu);
		menuBar.add(settings);
		menuBar.add(help);

		setJMenuBar(menuBar);
		add(toolbar, BorderLayout.NORTH);
		add(botbar, BorderLayout.SOUTH);

		// Constructs actual board
		constructBoard();

		// Sets up main JFrame
		getContentPane().setBackground(Color.BLACK);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

	}

	public void constructBoard() {
		// Game board
		board = new JPanel();
		board.setOpaque(false);
		board.setLayout(new GridLayout(m.getHeight(), m.getWidth()));
		gameStatus = true;

		// Loads button icon
		Image newimg = null;
		try {
			Image image = ImageIO
					.read(new URL(
							"https://cdn3.iconfinder.com/data/icons/softwaredemo/PNG/256x256/Box_Grey.png"));
			// Resizes icon
			newimg = image.getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);

		} catch (Exception e) {
			System.out.println("Failed to get button icon");
		}

		// Adds squares to board
		squaresArray = new JToggleButton[m.getHeight()][m.getWidth()];
		for (int i = 0; i < m.getHeight(); i++) {
			for (int j = 0; j < m.getWidth(); j++) {
				final JToggleButton temp = new JToggleButton();
				final int row = i;
				final int column = j;
				// If you want to view what number is in every square
				// temp.setText(m.squareStatus[row][column].ordinal() + "");
				temp.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						if (gameStatus == true) {
							temp.setIcon(null);
							if (e.getClickCount() == 1) {
								if (SwingUtilities.isRightMouseButton(e)) {
									if (temp.isEnabled()) {
										if (!temp.getText().equals("F")) {
											temp.setText("F");
										} else {
											temp.setText("");
										}
									}

								}

								if (SwingUtilities.isLeftMouseButton(e)) {

									if (!temp.getText().equals("F")) {
										if (temp.isEnabled()) {

											if (m.squareStatus[row][column] == SquareStatus.MINE) {
												System.out
														.println("Click on mine");
												gameOver();
											}

											else {
												if (m.squareStatus[row][column] == SquareStatus.ZERO) {
													System.out
															.println("Clicked on 0");
													disableButton(temp);
													revealAround(row, column,
															false);
													repaint();
												} else {
													System.out
															.println("Clicked on square 1-8");
													temp.setText(m.squareStatus[row][column]
															.ordinal() + "");
													disableButton(temp);
													repaint();
												}
											}

										}

									}
								}

							}

							if (e.getClickCount() == 2) {
								if (!temp.isEnabled()
										&& checkNumberOfFlags(row, column) == m.squareStatus[row][column]
												.ordinal()) {
									revealAround(row, column, true);
									repaint();
								} else {
									System.out.println("Not enough flags");
								}
							}
						}

					}

				});
				temp.setFont(new Font("Century Gothic", Font.BOLD, 15));
				temp.setBackground(Color.WHITE);
				temp.setForeground(Color.WHITE);
	
				temp.setContentAreaFilled(false);
				temp.setBorderPainted(false);
				temp.setIcon(new ImageIcon(newimg));
				squaresArray[i][j] = temp;
				board.add(temp);
			}
		}
		add(board, BorderLayout.CENTER);

	}

	public void squarePress(int row, int column) {
		squaresArray[row][column].setIcon(null);
		System.out.println("Checking before press");
		if (squaresArray[row][column].isEnabled()
				&& !squaresArray[row][column].getText().equals("F")) {
			if (m.squareStatus[row][column] == SquareStatus.ZERO) {
				System.out.println("Zero detected");
				disableButton(squaresArray[row][column]);
				revealAround(row, column, false);
			} else {
				System.out.println("Non-zero");
				squaresArray[row][column].setText(m.squareStatus[row][column]
						.ordinal() + "");
				disableButton(squaresArray[row][column]);
			}
		}

	}

	public void revealAround(int row, int column, boolean isDoubleClick) {
		int topx = row - 1;
		int topy = column;

		int bottomx = row + 1;
		int bottomy = column;

		int rightx = row;
		int righty = column + 1;

		int leftx = row;
		int lefty = column - 1;

		int topRightx = row - 1;
		int topRighty = column + 1;

		int topLeftx = row - 1;
		int topLefty = column - 1;

		int bottomRightx = row + 1;
		int bottomRighty = column + 1;

		int bottomLeftx = row + 1;
		int bottomLefty = column - 1;
		// Activates surrounding buttons
		if (row >= 0 && row < m.getHeight() && column >= 0
				&& column < getWidth()) {
			if (topx >= 0 && topx < m.getHeight() && topy >= 0
					&& topy < m.getWidth()) {
				System.out.println("Checking top");
				if (isDoubleClick) {
					DoublesquarePress(topx, topy);
				} else {
					squarePress(topx, topy);
				}
			}
			if (bottomx >= 0 && bottomx < m.getHeight() && bottomy >= 0
					&& bottomy < m.getWidth()) {
				System.out.println("Checking bottom");
				System.out.println("Row " + bottomx + " column " + bottomy);
				if (isDoubleClick) {
					DoublesquarePress(bottomx, bottomy);
				} else {
					squarePress(bottomx, bottomy);
				}

			}
			if (rightx >= 0 && rightx < m.getHeight() && righty >= 0
					&& righty < m.getWidth()) {
				System.out.println("Checking right");
				if (isDoubleClick) {
					DoublesquarePress(rightx, righty);
				} else {
					squarePress(rightx, righty);
				}

			}
			if (leftx >= 0 && leftx < m.getHeight() && lefty >= 0
					&& lefty < m.getWidth()) {
				System.out.println("Checking left");
				if (isDoubleClick) {
					DoublesquarePress(leftx, lefty);
				} else {
					squarePress(leftx, lefty);
				}

			}
			if (topRightx >= 0 && topRightx < m.getHeight() && topRighty >= 0
					&& topRighty < m.getWidth()) {
				System.out.println("Checking top Right");
				if (isDoubleClick) {
					DoublesquarePress(topRightx, topRighty);
				} else {
					squarePress(topRightx, topRighty);
				}

			}
			if (topLeftx >= 0 && topLeftx < m.getHeight() && topLefty >= 0
					&& topLefty < m.getWidth()) {
				System.out.println("Checking top left");
				if (isDoubleClick) {
					DoublesquarePress(topLeftx, topLefty);
				} else {
					squarePress(topLeftx, topLefty);
				}

			}
			if (bottomRightx >= 0 && bottomRightx < m.getHeight()
					&& bottomRighty >= 0 && bottomRighty < m.getWidth()) {
				System.out.println("Checking bottom right");
				if (isDoubleClick) {
					DoublesquarePress(bottomRightx, bottomRighty);
				} else {
					squarePress(bottomRightx, bottomRighty);
				}

			}
			if (bottomLeftx >= 0 && bottomLeftx < m.getHeight()
					&& bottomLefty >= 0 && bottomLefty < m.getWidth()) {
				System.out.println("Checking bottom left");
				if (isDoubleClick) {
					DoublesquarePress(bottomLeftx, bottomLefty);
				} else {
					squarePress(bottomLeftx, bottomLefty);
				}

			}
		}
	}

	public void DoublesquarePress(int row, int column) {
		System.out.println("Checking before press");
		squaresArray[row][column].setIcon(null);
		if (squaresArray[row][column].isEnabled()
				&& !squaresArray[row][column].getText().equals("F")) {
			if (m.squareStatus[row][column] == SquareStatus.MINE) {
				gameOver();
			} else {
				if (m.squareStatus[row][column] == SquareStatus.ZERO) {
					System.out.println("Zero detected");
					disableButton(squaresArray[row][column]);
					revealAround(row, column, true);
				} else {
					System.out.println("Non-zero");
					squaresArray[row][column]
							.setText(m.squareStatus[row][column].ordinal() + "");
					disableButton(squaresArray[row][column]);
				}
			}
		}

	}

	public int checkNumberOfFlags(int row, int column) {
		int numberOfFlags = 0;
		int topx = row - 1;
		int topy = column;

		int bottomx = row + 1;
		int bottomy = column;

		int rightx = row;
		int righty = column + 1;

		int leftx = row;
		int lefty = column - 1;

		int topRightx = row - 1;
		int topRighty = column + 1;

		int topLeftx = row - 1;
		int topLefty = column - 1;

		int bottomRightx = row + 1;
		int bottomRighty = column + 1;

		int bottomLeftx = row + 1;
		int bottomLefty = column - 1;
		// Activates surrounding buttons
		if (row >= 0 && row < m.getHeight() && column >= 0
				&& column < getWidth()) {
			if (topx >= 0 && topx < m.getHeight() && topy >= 0
					&& topy < m.getWidth()) {
				System.out.println("Checking top");
				if (squaresArray[topx][topy].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (bottomx >= 0 && bottomx < m.getHeight() && bottomy >= 0
					&& bottomy < m.getWidth()) {
				System.out.println("Checking bottom");
				System.out.println("Row " + bottomx + " column " + bottomy);
				if (squaresArray[bottomx][bottomy].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (rightx >= 0 && rightx < m.getHeight() && righty >= 0
					&& righty < m.getWidth()) {
				System.out.println("Checking right");
				if (squaresArray[rightx][righty].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (leftx >= 0 && leftx < m.getHeight() && lefty >= 0
					&& lefty < m.getWidth()) {
				System.out.println("Checking left");
				if (squaresArray[leftx][lefty].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (topRightx >= 0 && topRightx < m.getHeight() && topRighty >= 0
					&& topRighty < m.getWidth()) {
				System.out.println("Checking top Right");
				if (squaresArray[topRightx][topRighty].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (topLeftx >= 0 && topLeftx < m.getHeight() && topLefty >= 0
					&& topLefty < m.getWidth()) {
				System.out.println("Checking top left");
				if (squaresArray[topLeftx][topLefty].getText().equals("F")) {
					numberOfFlags++;
				}

			}
			if (bottomRightx >= 0 && bottomRightx < m.getHeight()
					&& bottomRighty >= 0 && bottomRighty < m.getWidth()) {
				System.out.println("Checking bottom right");
				if (squaresArray[bottomRightx][bottomRighty].getText().equals(
						"F")) {
					numberOfFlags++;
				}

			}
			if (bottomLeftx >= 0 && bottomLeftx < m.getHeight()
					&& bottomLefty >= 0 && bottomLefty < m.getWidth()) {
				System.out.println("Checking bottom left");
				if (squaresArray[bottomLeftx][bottomLefty].getText()
						.equals("F")) {
					numberOfFlags++;
				}

			}
		}
		System.out.println("Number of flags is " + numberOfFlags);
		return numberOfFlags;

	}

	public void disableButton(JToggleButton jb) {
		jb.setIcon(null);
		jb.setOpaque(false);
		jb.setEnabled(false);
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
	}

	public void gameOver() {
		System.out.println("Game over - Pressed on mine");
		gameStatus = false;
		// Gets Minesweeper logo
		Image newimg = null;
		try {
			Image image = ImageIO
					.read(new URL(
							"http://upload.wikimedia.org/wikipedia/en/5/5c/Minesweeper_Icon.png"));
			// Resizes all images
			newimg = image.getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);
		} catch (Exception e) {
			System.out.println("Couldn't load mine icon");
		}

		for (int row = 0; row < m.getHeight(); row++) {
			for (int column = 0; column < m.getWidth(); column++) {
				squaresArray[row][column].setEnabled(false);;
				if (m.squareStatus[row][column] == SquareStatus.MINE) {
					squaresArray[row][column].setText("");
					try {
						squaresArray[row][column]
								.setDisabledIcon(new ImageIcon(newimg));
						squaresArray[row][column]
								.setIcon(new ImageIcon(newimg));
					} catch (Exception e) {
						System.out.println("Error reading file");
						squaresArray[row][column].setText("M");
					}

				}
			}
		}
		JOptionPane.showMessageDialog(getParent(), "Game over");
	}

	public static void main(String[] args) {
		Minesweeper min = new Minesweeper(9, 9, 10);
		MinesweeperGUI gui = new MinesweeperGUI(min);
		gui.setSize(500, 500);
		gui.setLocationRelativeTo(null);

	}
}
