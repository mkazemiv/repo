/**
 * This class represents a Town which has a name.
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.util.ArrayList;

public class Town implements Comparable<Town> {

	// name of Town
	private String name;
	// distance from an adjacent Town  
	private int distance;
	// TODO
	private boolean visited;
	// TODO
	private Town predecessor;
	// ArrayList of adjacent Towns
	private ArrayList<Town> adjTowns = new ArrayList<Town>();
	// ArrayList of adjacent Roads
	private ArrayList<Road> adjRoads = new ArrayList<Road>();
	
	/*	distance, visited, and pred are to be used for calculating the shortest
	*	path to the adjacent Town, in dijkstraShortestPath() of Graph.java
	*	source: java2blog.com/dijkstra-java/
	*/
	
	/**
	 * Basic constructor that creates a Town using the given name
	 * @param name the name for the new town
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * Copy-constructor that creates a new Town using the given Town's name 
	 * @param t a Town that already exists
	 */
	public Town(Town t) {
		name = t.name;
	}
	
	/**
	 * Returns 0 if the given Town and this Town are the same (have the same name)
	 * and a positive value otherwise.
	 * @param town 
	 * @return 0 if names are equal or 1 otherwise
	 */
	@Override
	public int compareTo(Town town) {
		if(name.equals(town.name))
			return 0;
		else
			return 1;
	}
	
	/**
	 * Returns true if the given Town and this Town are the same (have the same name)
	 * and a false otherwise.
	 * @param t a Town that already exists
	 * @return true if names are equal or false otherwise
	 */
	public boolean equals(Object t) {
		boolean result = false;
		try {
			if(name.equals(((Town) t).name))
				result = true;
			else
				result = false;
		} catch(NullPointerException e)
		{
			result = false;
		}
		return result;
	}
	
	/**
	 * Returns the name of the Town
	 * @return this Town's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method adds the given t to this Town's adjTowns
	 * @param t a Town adjacent to this Town
	 */
	public void addToTowns(Town t) {
		if(!adjTowns.contains(t))
			adjTowns.add(t);
	}
	
	/**
	 * This method removes the given t from this Town's adjTowns
	 * @param t a Town that is no longer adjacent to this Town
	 */
	public void removeFromTowns(Town t) {
		if(adjTowns.contains(t))
			adjTowns.remove(t);
	}
	
	/**
	 * TODO
	 * @return 
	 */
	public ArrayList<Town> getAdjacentTowns() {
		return adjTowns;
	}
	
	/** TODO
	 * This method adds the given t to this Town's adjTowns
	 * @param t a Town adjacent to this Town
	 */
	public void addToRoads(Road r) {
		if(!adjRoads.contains(r))
			adjRoads.add(r);
	}
	
	/**
	 * This method removes the given Road from this Town's adjRoads
	 * @param r a Road that is no longer connected to this Town
	 */
	public void removeFromRoads(Road r) {
		if(adjRoads.contains(r))
			adjRoads.remove(r);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public ArrayList<Road> getAdjacentRoads() {
		return adjRoads;
	}
	
	/**
	 * Returns the hash-code for the name of this Town
	 * @return hash-code for Town's name
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * TODO
	 * @param d
	 */
	public void setDistance(int d) {
		distance = d;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Returns the name of the Town
	 * @return this Town's name 
	 */
	public String toString() {
		return name;
	}

	/**
	 * TODO
	 * @param tf
	 */
	public void setVisited(boolean tf) {
		visited = tf;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * TODO
	 * @param actualTown
	 */
	public void setPredecessor(Town actualTown) {
		predecessor = actualTown;
	}

	/**
	 * TODO
	 * @return
	 */
	public Town getPredecessor() {
		return predecessor;
	}

}