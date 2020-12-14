/**
 * 
 * @author Mohammad A. Kazemivarnamkhasti
 */

public class Road implements Comparable<Road> {
	// the two vertices (Town objects) that this Road connects to each other
	private Town vertexA, vertexB;
	// the name of this Road
	private String name;
	// the weight (length) of this Road
	private int weight;

	/**
	 * This constructor creates a Road using the given parameters, with weight set to 1.
	 * @param source a source Town
	 * @param destination a destination Town
	 * @param name the name for the Road being created
	 */
	public Road(Town source, Town destination, String name) {
		vertexA = source;
		vertexB = destination;
		weight = 1;
		this.name = name;
	}

	/**
	 * This constructor creates a Road using the given parameters.
	 * @param source a source Town
	 * @param destination a destination Town
	 * @param distance the weight of the Road being created
	 * @param name the name for the Road being created
	 */
	public Road(Town source, Town destination, int distance, String name) {
		vertexA = source;
		vertexB = destination;
		if(distance <= 0)
			weight = 1;
		else
			weight = distance;
		this.name = name;
	}

	/**
	 * Returns the name of this Road
	 * @return the Road's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the name of this Road
	 * @return the Road's weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Returns the name of the "source" vertex of this Road
	 * @return the name of vertexA
	 */
	public String getSource() {
		return vertexA.getName();
	}

	/**
	 * Returns the name of the "destination" vertex of this Road
	 * @return the name of vertexB
	 */
	public String getDestination() {
		return vertexB.getName();
	}

	/**
	 * Returns the "source" vertex of this Road
	 * @return vertexA
	 */
	public Town getVertexA() {
		return vertexA;
	}

	/**
	 * Returns the "destination" vertex of this Road
	 * @return vertexB
	 */
	public Town getVertexB() {
		return vertexB;
	}

	/**
	 * Returns true if each of the ends of the Road r is the same as the ends of this Road
	 * @param road an Object
	 * @return true Returns true if each of the ends of the road r is the same as the ends of this road, false otherwise
	 */
	public boolean equals(Object r) {
		if(		(vertexA.equals(((Road) r).getVertexA()) || vertexA.equals(((Road) r).getVertexB())) &&
				(vertexB.equals(((Road) r).getVertexB()) || vertexB.equals(((Road) r).getVertexA())) &&
				name.equals(((Road) r).getName()) && weight == ((Road) r).getWeight())
			return true;
		else
			return false;
	}

	/**
	 * This method returns true if the given Town is part of this Road.
	 * @param town a Town
	 * @return true if town is one of the vertices of this Road, false otherwise
	 */
	public boolean contains(Town town) {
		if(vertexA.equals(town) || vertexB.equals(town))
			return true;
		else
			return false;
	}

	/**
	 * Returns 0 if the given Road and this Road have the same name
	 * and 1 otherwise.
	 * @param road
	 * @return 0 if names are equal or 1 otherwise
	 */
	@Override
	public int compareTo(Road road) {
		if(this.name.equals(road.name))
			return 0;
		else
			return 1;
	}

	/**
	 * Returns all the information relating to this Road
	 * @return the Road's name, source, destination, and weight
	 */
	public String toString() {
		//		return name +" gets you from "+ vertexA.getName() +" to "+
		//				vertexB.getName() +" and is "+ weight +" mi long.";
		return name + ": " +weight;
	}

}