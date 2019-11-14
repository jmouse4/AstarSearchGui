/**
 * 
 * @author Jerry Moua
 *
 */
import java.util.ArrayList;

public class GridWorld {
	//Class variables
		private Node[][] nodeGrid;
		private ArrayList<Node> frontier;
		private ArrayList<Node> visited;
		private Node start;
		private Node goal;
		
		//Constructor
		public GridWorld()
		{
			nodeGrid = new Node[15][15];
			frontier = new ArrayList<Node>();
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
			
			for(int k = 0; k < 15; k++)
			{
				for(int l = 0; l < 15; l++)
				{
					nodeGrid[k][l].setManhattan(goal);
				}
			}
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
		}
		
		/**
		 * A* Search Method
		 */
		public void Asearch()
		{

			//results = new ArrayList<Node>();
			Node picked;
			Node temp;
			frontier.add(start);
			
			//Begin Search
			boolean success = false;
			while(!success){
				//explore the frontier
				if(frontier.size() == 0 || start.isNavigable() == false || goal.isNavigable() == false){ //Failure, returns an empty array of results
					System.out.println("No Path could be found :(");
					break;
				} else { //Pick a node to explore
					int pickIndex = 0;
					//picks the node with the lowest d() + h()
					for(int i = 1; i < frontier.size(); i++){
						if(frontier.get(i).Astar() < frontier.get(pickIndex).Astar()){
							pickIndex = i;
						}
					}
					picked = frontier.remove(pickIndex);
					visited.add(picked); //Adds Picked to Visited
					System.out.println("Picked: " + "(" + picked.getX() + ", " + picked.getY() + ")" 
							+ " f(): " + picked.Astar() + " d(): " + picked.getStartDist() + " h(): " + picked.getManDist());
					//GOAL is found. Add path to results
					if(picked.equals(goal)){
						System.out.println("Found Goal!!");
						System.out.print("Path: ");
						temp = picked;
						
						while(temp != null){
							System.out.print(temp.toString());
							temp = temp.getParent();
						}
						
						
						success = true;
					}
					
					//Add the left node, top node, right node, bottom node of the picked node if the node is not null, not a block, or visited node
					if(picked.getX() - 1 >= 0) {
						if (nodeGrid[picked.getX() - 1][picked.getY()].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX() - 1][picked.getY()]) 
							&& !frontier.contains(nodeGrid[picked.getX() - 1][picked.getY()]))
						{
							temp = nodeGrid[picked.getX() - 1][picked.getY()];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
					if(picked.getY() + 1 < 15){
						if(nodeGrid[picked.getX()][picked.getY() + 1].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX()][picked.getY() + 1]) 
							&& !frontier.contains(nodeGrid[picked.getX()][picked.getY() + 1]))
						{
							temp = nodeGrid[picked.getX()][picked.getY() + 1];
							temp.setParent(picked);
							frontier.add(0, temp);
						}	
					}
					if(picked.getX() + 1 < 15){
						if(nodeGrid[picked.getX() + 1][picked.getY()].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX() + 1][picked.getY()])
							&& !frontier.contains(nodeGrid[picked.getX() + 1][picked.getY()]))
						{
							temp = nodeGrid[picked.getX() + 1][picked.getY()];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
					if(picked.getY() - 1 >= 0){
						if(nodeGrid[picked.getX()][picked.getY() - 1].isNavigable() 
							&& !visited.contains(nodeGrid[picked.getX()][picked.getY() - 1]) 
							&& !frontier.contains(nodeGrid[picked.getX()][picked.getY() - 1]))
						{
							temp = nodeGrid[picked.getX()][picked.getY() - 1];
							temp.setParent(picked);
							frontier.add(0, temp);
						}
					}
				}
			}//While(success)
		}
}
