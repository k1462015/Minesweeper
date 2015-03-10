import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

	public MinesweeperGUI(Minesweeper m) {
		super("Minesweeper");
		this.m = m;
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        		}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		initUi();
	}

	public void initUi() {

		// Basic GUI Layout
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenu settings = new JMenu("Settings");
		JMenu help = new JMenu("Help");

		// Create Top Tool bar
		JToolBar toolbar = new JToolBar();
		JButton createNew = new JButton("New");
		JButton pause = new JButton("Pause");
		toolbar.add(createNew);
		toolbar.add(pause);

		// Creates Bottom Tool bar
		JToolBar botbar = new JToolBar();
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
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void constructBoard() {
		// Game board
		board = new JPanel();
		board.setLayout(new GridLayout(m.getWidth(), m.getHeight()));
		add(board, BorderLayout.CENTER);

		// Adds squares to board
		squaresArray = new JToggleButton[m.getWidth()][m.getHeight()];
		for (int i = 0; i < m.getHeight(); i++) {
			for (int j = 0; j < m.getWidth(); j++) {
				final JToggleButton temp = new JToggleButton();
				final boolean status = m.minePos[i][j];
//				temp.setText(m.aroundMines[i][j]);
//				if(m.aroundMines[i][j].equals("0")){
//					//temp.setText("Mine");
//				}else{
//					temp.setText(m.aroundMines[i][j]);
//				}
				//temp.setText(m.aroundMines[i][j]);
				temp.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1){
							System.out.println("Right Clicked");
							if (temp.getText().equals("F")) {
								temp.setText("");
							} else {
								temp.setText("F");
							}
							
						}
						if(SwingUtilities.isLeftMouseButton(e) && status == true){
							gameOver();
						}
						
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				//final JButton temp = new JButton();
				
//				if(m.minePos[i][j]==true){
//					temp.setText("Mine");
//				}

				squaresArray[i][j] = temp;
				board.add(temp);
			}
		}

	}
	
	public void gameOver(){
		for(int i = 0;i < m.widthOfBoard;i++){
			for(int j = 0; j < m.heightOfBoard;j++){
				squaresArray[i][j].setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		Minesweeper min = new Minesweeper(4, 5, 10);
		MinesweeperGUI gui = new MinesweeperGUI(min);

	}

}
