/**
 * 
 * @author Jerry Moua
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class GridWorld {
	//Class variables
		private Node[][] nodeGrid;
		private LinkedList<Node> frontier;
		private ArrayList<Node> visited;
		private Node start;
		private Node goal;
		
		//Constructor
		public GridWorld()
		{
			nodeGrid = new Node[15][15];
			frontier = new LinkedList<Node>();
			visited = new ArrayList<Node>();

		}
		
		//Methods
		/**
		 * Sets the starting node position
		 * @param x
		 * @param y
		 */
		private void setStart(int x, int y)
		{
			start = nodeGrid[x][y];
			start.setStart();
			Log.debug("Start in GridWorld is at x: " + start.getX() + ", y: " + start.getY()); 
		}
		/**
		 * Sets the goal for the world and calculates the Manhattan distance for all nodes
		 * @param x
		 * @param y
		 */
		private void setGoal(int x, int y)
		{
			goal = nodeGrid[x][y];
			goal.setGoal();
			Log.debug("goal is now at x: " + goal.getX() + ", y: " + goal.getY());
			
		}
		
		/**
		 * Initialize the Nodes in grid world
		 */
		public void genWorld(String[] gridState)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 15; y++)
				{
					nodeGrid[x][y] = new Node(x, y);
					
					if(gridState[x*15+y] == SearchGrid.START)
					{
						setStart(x, y);
					}
					else if(gridState[x*15+y] == SearchGrid.GOAL)
					{
						setGoal(x, y);
					}
					else if(gridState[x*15+y] == SearchGrid.BLOCK)
					{
						nodeGrid[x][y].setNavigable(false);
					}
					
				}
			}
			
			setManhattans();
		}
		
		/**
		 * Set Manhattan distances for all nodes after all nodes instantiated
		 */
		private void setManhattans(){
			
			for(int k = 0; k < 15; k++)
			{
				for(int l = 0; l < 15; l++)
				{
					nodeGrid[k][l].setManhattan(goal);
				}
			}
		}
		
		/**
		 * A* Search Method
		 */
		public Node Asearch()
		{
			//results = new ArrayList<Node>();
			Node picked = null;
			Node temp;
			frontier.add(start);
			
			//Begin Search
			boolean success = false;
			while(!success){
				//explore the frontier
				if(frontier.size() == 0 || start.isNavigable() == false || goal.isNavigable() == false)
				{ //Failure, returns an empty array of results
					Log.debug("No Path could be found :(");
					return null;
				} else 
				{ //Pick a node to explore
					int pickIndex = 0;
					//picks the node with the lowest d() + h()
					for(int i = 1; i < frontier.size(); i++)
					{
						if(frontier.get(i).Astar() < frontier.get(pickIndex).Astar())
						{
							pickIndex = i;
						}
					}
					picked = frontier.remove(pickIndex);
					visited.add(picked); //Adds Picked to Visited
					Log.debug("Picked: " + "(" + picked.getX() + ", " + picked.getY() + ")" 
							+ " f(): " + picked.Astar() + " d(): " + picked.getStartDist() + " h(): " + picked.getManDist());
					//GOAL is found. Add path to results
					if(picked.equals(goal))
					{
						Log.debug("Found Goal!");
						success = true;
					}
					
					//Add the left node, right node, top node, bottom node of the picked node if the node is not null, not a block, or visited node
					if(picked.getX() - 1 >= 0) 
					{
						if (nodeGrid[picked.getX() - 1][picked.getY()].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX() - 1][picked.getY()]) 
							&& !frontier.contains(nodeGrid[picked.getX() - 1][picked.getY()]))
						{
							temp = nodeGrid[picked.getX() - 1][picked.getY()];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
					if(picked.getX() + 1 < 15)
					{
						if(nodeGrid[picked.getX() + 1][picked.getY()].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX() + 1][picked.getY()])
							&& !frontier.contains(nodeGrid[picked.getX() + 1][picked.getY()]))
						{
							temp = nodeGrid[picked.getX() + 1][picked.getY()];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
					if(picked.getY() + 1 < 15)
					{
						if(nodeGrid[picked.getX()][picked.getY() + 1].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX()][picked.getY() + 1]) 
							&& !frontier.contains(nodeGrid[picked.getX()][picked.getY() + 1]))
						{
							temp = nodeGrid[picked.getX()][picked.getY() + 1];
							temp.setParent(picked);
							frontier.add(0, temp);
						}	
					}
					
					if(picked.getY() - 1 >= 0)
					{
						if(nodeGrid[picked.getX()][picked.getY() - 1].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX()][picked.getY() - 1]) 
							&& !frontier.contains(nodeGrid[picked.getX()][picked.getY() - 1]))
						{
							temp = nodeGrid[picked.getX()][picked.getY() - 1];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
					
					StringBuilder sb = new StringBuilder();
					sb.append("[Frontier] ");
					for(int i = 0; i < frontier.size(); i++){
						sb.append(frontier.get(i).toString() + "f(): " + frontier.get(i).Astar());
					}
					Log.debug(sb.toString());
				}
			}//While(success)
			
			return picked;
		}
}
