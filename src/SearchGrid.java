/**
 * 
 * @author Jerry Moua
 * Search Grid contains code or interface for Astar search.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public class SearchGrid {
	//class instances
	private static JButton buttons[] = new JButton[15*15];
	private static boolean pickStart = true;
	private static final String START = "S";
	private static final String GOAL = "G";
	private static final String BLOCK = "B";
	private static final String PATH = "P";
	
	public static void main(String[] args){
		gridPanel();
	}
	
	/**
	 * Creates a frame, panel, and buttons on the board.
	 */
	private static void gridPanel(){
		
		JFrame frame = new JFrame("Astar Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(15,15));
		panel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		panel.setBackground(Color.white);
		
		for (int i = 0; i < buttons.length; i++){
			buttons[i] = new MyButton();
			panel.add(buttons[i]);
		}
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		frame.setSize(1200,1200);
	}
	
	/**
	 * Customized button extending the JButton Class from Java Swing library
	 * @author dajer
	 *
	 */
	@SuppressWarnings("serial")
	private static class MyButton extends JButton implements ActionListener {
		
		public MyButton(){
			super();
			setFont(new Font(Font.SERIF, Font.BOLD, 40));
			setText(" ");
			addActionListener(this);
		}
		
		/**
		 * User selects start and goal nodes.
		 */
		public void actionPerformed(ActionEvent e){
			if (pickStart && getText().equals(" ")){
				setText(START);
				pickStart = false;
			} 
			else {
				setText(GOAL);
			}
			
		}
	}
}
