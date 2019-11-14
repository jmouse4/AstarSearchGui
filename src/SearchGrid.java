/**
 * 
 * @author Jerry Moua
 * Search Grid contains code for interface for Astar search.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchGrid 
{
	//class instances
	private static final int N = 15;
	private static JButton buttons[] = new JButton[N*N];
	private static boolean pickStart = true;
	private static boolean pickGoal = true;
	private static boolean pathShown = false;
	protected static final String START = "S";
	protected static final String GOAL = "G";
	protected static final String BLOCK = "B";
	protected static final String PATH = "P";
	
	public static int blocks = 0;
	
	public static void main(String[] args)
	{
		gridPanel();
	}
	
	/**
	 * Creates a frame, panel, and buttons on the board.
	 */
	private static void gridPanel()
	{
		
		JFrame frame = new JFrame("Astar Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(15,15));
		panel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		panel.setBackground(Color.white);
		
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new MyButton();
			buttons[i].setBackground(Color.white);
			panel.add(buttons[i]);
		}
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		frame.setSize(1200,1200);
		
	}
	
	/**
	 * Customized button extending the JButton Class from Java Swing library
	 * @ Jerry Moua
	 */
	@SuppressWarnings("serial")
	private static class MyButton extends JButton implements ActionListener 
	{
		
		public MyButton()
		{
			super();
			setFont(new Font(Font.SERIF, Font.BOLD, 40));
			setText(" ");
			addActionListener(this);
		}
		
		/**
		 * User selects start and goal nodes.
		 */
		public void actionPerformed(ActionEvent e)
		{
			//Picks start position
			if (pickStart && getText().equals(" "))
			{
				setText(START);
				setBackground(Color.pink);
				pickStart = false;
				
				//Log.debug("Start is in button " + getX() + ", " + getY());
			}
			//remove start position
			else if(!pickStart && getText().equals(START))
			{
				setText(" ");
				setBackground(Color.white);
				pickStart = true;
				
				//Log.debug("Start removed from button" + getX() + ", " + getY());
			}
			//Pick a goal position
			else if (!pickStart && pickGoal && getText().equals(" "))
			{
				setText(GOAL);
				setBackground(Color.green);
				pickGoal = false;
				
				//Log.debug("Goal is in button " + getX() + ", " + getY());
			}
			//remove goal position
			else if (!pickGoal && getText().equals(GOAL))
			{
				setText(" ");
				setBackground(Color.white);
				pickGoal = true;
				
				//Log.debug("Goal removed from button " + getX() + ", " + getY());
			}
			/**
			 * TODO: Select as many blocks as later. For now select only 10
			 */
			else if(getText().equals(" ") && blocks < 10)
			{
				setText(BLOCK);
				setBackground(Color.gray);
				blocks++;
				
				//Log.debug("block is in button " + getX() + ", " + getY());
			} 
			else if (getText().equals(BLOCK))
			{
				setText(" ");
				setBackground(Color.white);
				blocks--;
				
				//Log.debug("Block removed from button " + getX() + "," + getY());
			}
			
			else if (blocks == 10 && !pickStart && !pickGoal)
			{
				GridWorld gw = new GridWorld();
				gw.genWorld(getGridState());
				showPath(gw.Asearch());
			}
			
		}
	}
	/**
	 * Clear all buttons on gridPanel
	 */
	public static void clearButtons()
	{
		for(int i = 0; i < N*N; i++)
		{
			buttons[i].setText(" ");
		}
	}
	/**
	 * Clear all buttons with "P" as its text
	 */
	public static void clearPath()
	{
		
	}
	/**
	 * records the current gridPanel state
	 * @return gridState
	 */
	public static String[] getGridState()
	{
		String[] gridState = new String[N*N];
			for(int i = 0; i < N*N; i++)
			{
				gridState[i] = buttons[i].getText();
			}
		return gridState;
	}
	/**
	 * shows path to from start to goal
	 * @param goal
	 */
	public static void showPath(Node goal)
	{
		
		pathShown = true;
	}
}
