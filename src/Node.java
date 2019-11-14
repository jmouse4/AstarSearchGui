/**
 * 
 * @author Jerry Moua
 * Node Class. 
 * Contains information about nodes position 
 * is navigable
 * is the starting node or goal node 
 * 
 */
public class Node {
	//Class variables for node
	private int x; // Row position on grid
	private int y; // Column position on grid
	private int startDist; //
	private int manDist;
	private boolean navigable;
	private boolean start;
	private boolean goal;
	
	//node that explored this node
	private Node parent;
	
	/**
	 * Constructor for Node Class
	 * @param x
	 * @param y
	 */
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		this.navigable = true;
		startDist = 0;
		start = false;
		goal = false;
	}
	/**
	 * Constructor for creating a new copy of another Node Object
	 * @param dup
	 */
	public Node(Node dup){
		this.x = dup.x;
		this.y = dup.y;
		this.navigable = dup.navigable;
		this.start = dup.start;
		this.manDist = dup.manDist;
		this.start = dup.start;
		this.goal = dup.goal;
	}
	
	//Getters and Setters for Node Class
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	public boolean isNavigable(){
		return navigable;
	}
	public void setNavigable(boolean navigable){
		this.navigable = navigable;
	}
	public int getStartDist(){
		return startDist;
	}
	public void setStartDist(int dist){
		this.startDist = dist;
	}
	public int getManDist(){
		return manDist;
	}
	//Sets parent of current Node, increments distance from start from parents distance.
	public void setParent(Node P){
		this.parent = P;
		this.startDist = parent.startDist + 1;
	}
	public Node getParent(){
		return parent;
	}
	
	public boolean isStart(){
		return start;
	}
	public void setStart(){
		this.start = true;
		this.parent = null;
	}
	public boolean isGoal(){
		return goal;
	}
	public void setGoal(){
		this.goal = true;
	}
	
	//Methods
	/**
	 * Sets the Manhattan Distance from Node to Goal
	 * @param goal
	 */
	public void setManhattan(Node goal){
		int xValue = Math.abs(goal.getX() - this.x);
		int yValue = Math.abs(goal.getY() - this.y);		
		
		this.manDist = xValue + yValue;
	}
	/**
	 * Returns the value distance d() + Manhattan h() 
	 * @return Astar value
	 */
	public int Astar(){
		return startDist + manDist;
	}
	
	/**
	 * Compares nodes if they are equal to each other
	 * @param dupe
	 * @return boolean
	 */
	public boolean equals(Node dupe){
		if(dupe == null){
			return false;
		} else if(this.x == dupe.getX() && this.y == dupe.getY() && this.navigable == dupe.isNavigable()){
			return true;
		}
		return false;
	}
	/**
	 * Prints out node information in String
	 */
	@Override
	public String toString(){
		return "(" + this.x + ", " + this.y + ") ";
	}
}
