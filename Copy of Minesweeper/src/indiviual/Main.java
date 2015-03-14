package indiviual;

public class Main {

	public static void main(String[] args) {
		Minesweeper min = new Minesweeper(9, 9, 10);
		MinesweeperGUI gui = new MinesweeperGUI(min);
		gui.setEasy();
		gui.setSize(650, 650);
		gui.setLocationRelativeTo(null);

	}

}
