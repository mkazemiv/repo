/**
 * 
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {

	// ArrayLists of the respective type used for storing the towns and roads of this Graph
	private ArrayList<Town> towns = new ArrayList<Town>();
	private ArrayList<Road> roads = new ArrayList<Road>();

	/**
	 * Returns an edge that connects the given Towns, or null such an edge or either of the Towns don't exist 
	 * @param sourceVertex the source Town of the Road being searched for
	 * @param destinationVertex the destination Town of the Road being searched for
	 * @return a Road that connects sourceVertex to destinationVertex,
	 * or null if such a Rod doesn't exist
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex.equals(null) || destinationVertex.equals(null))
			return null;
		else
		{
			Road result = null;
			for(Road r : roads)
				if(		(r.getVertexA().equals(sourceVertex) || r.getVertexA().equals(destinationVertex)) && 
						(r.getVertexB().equals(sourceVertex) || r.getVertexB().equals(destinationVertex)))
					result = r;
			return result;
		}
	}
	
	/**
	 * 
	 * @param v
	 * @return
	 */
	public Town getVertex(Town v) {
		Town t = null;
		if(towns.contains(v))
		{
			for(Town a : towns)
				if(a.equals(v))
					t = a;
		}
		return t;
		
	}

	/**
	 *  
	 * @param sourceVertex 
	 * @param destinationVertex 
	 * @param weight 
	 * @param description 
	 * @return 
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road newRoad = null;
		if(sourceVertex.equals(null) || destinationVertex.equals(null))
			throw new NullPointerException("One or both of the Towns are null.");
		else if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			throw new IllegalArgumentException("One or both of the Towns are not part of this graph.");
		else
		{
			newRoad = new Road(sourceVertex, destinationVertex, weight, description);
			roads.add(newRoad);
			sourceVertex.addToTowns(destinationVertex);
			sourceVertex.addToRoads(newRoad);
			destinationVertex.addToTowns(sourceVertex);
			destinationVertex.addToRoads(newRoad);
			return newRoad;
		}
	}

	/**
	 * TODO
	 * @param v 
	 * @return 
	 */
	@Override
	public boolean addVertex(Town v) {
		if(v.equals(null))
			throw new NullPointerException("This Town is null.");
		else if(towns.contains(v))
			return false;
		else
		{
			towns.add(v);
			return true;
		}
	}

	/**
	 * TODO
	 * @param sourceVertex 
	 * @param destinationVertex 
	 * @return 
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		boolean result = false;
		for(int k = 0; k < roads.size(); k++)
			if(		(roads.get(k).getSource().equals(sourceVertex.getName()) || roads.get(k).getSource().equals(destinationVertex.getName())) &&
					(roads.get(k).getDestination().equals(sourceVertex.getName()) || roads.get(k).getDestination().equals(destinationVertex.getName())))
				result = true;
		return result;
	}

	/**
	 *  
	 */
	@Override
	public boolean containsVertex(Town v) {
		if(towns.contains(v))
			return true;
		else
			return false;
	}

	/**
	 * TODO
	 * @return 
	 */
	@Override
	public Set<Road> edgeSet() {
		Set<Road> r = new HashSet<Road>();
		for(Road a: roads)
			r.add(a);
		return r;
	}

	/**
	 * TODO
	 * @param vertex 
	 * @return 
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> e = new HashSet<Road>();
		for(Road a: roads)
			if(a.contains(vertex))
				e.add(a);
		return e;
	}

	/**
	 * TODO
	 * @param sourceVertex 
	 * @param destinationVertex 
	 * @param weight 
	 * @param description 
	 * @return 
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road r = new Road(sourceVertex, destinationVertex, weight, description);
		if(weight < 1 || description.equals(null) || !towns.contains(sourceVertex) || !towns.contains(destinationVertex))
			return null;
		else if(!roads.contains(r))
			return null;
		else
		{
			for(int k = 0; k < roads.size(); k++)
				if(roads.get(k).equals(r))
				{
					if(roads.get(k).getVertexA().equals(sourceVertex))
					{
						roads.get(k).getVertexA().removeFromTowns(destinationVertex);
						roads.get(k).getVertexB().removeFromTowns(sourceVertex);
					}
					else if(roads.get(k).getVertexB().equals(sourceVertex))
					{
						roads.get(k).getVertexA().removeFromTowns(sourceVertex);
						roads.get(k).getVertexB().removeFromTowns(destinationVertex);
					}
					roads.get(k).getVertexA().removeFromRoads(r);
					roads.get(k).getVertexB().removeFromRoads(r);
					roads.remove(r);
				}
			return r;
		}
	}

	/**
	 * TODO
	 * @param v 
	 * @return 
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(Town v) {
		if(!containsVertex(v) || v.equals(null))
			return false;
		else
		{
			ArrayList<Integer> roadsToRemove = new ArrayList<Integer>();
			Set<Road> vEdges = edgesOf(v);
			if(!vEdges.isEmpty())
				for(int k = 0; k < roads.size(); k++)
				{
					if(roads.get(k).contains(v))
					{
						if(roads.get(k).getVertexA().equals(v))
						{
							roads.get(k).getVertexB().removeFromTowns(roads.get(k).getVertexA());
							roads.get(k).getVertexB().removeFromRoads(roads.get(k));
						}
						else if(roads.get(k).getVertexB().equals(v))
						{
							roads.get(k).getVertexA().removeFromTowns(roads.get(k).getVertexB());
							roads.get(k).getVertexA().removeFromRoads(roads.get(k));
						}
						roadsToRemove.add(k);
					}
				}
			for(int k = 0; k < roadsToRemove.size(); k++)
				roads.remove(roadsToRemove.get(k));
			towns.remove(v);
			return true;
		}
	}

	/**
	 * This method returns 
	 * @return 
	 */
	@Override
	public Set<Town> vertexSet() {
		Set<Town> v = new HashSet<Town>();
		for(Town a: towns)
			v.add(a);
		return v;
	}

	/**
	 * TODO
	 * @param sourceVertex 
	 * @param destinationVertex 
	 * @return 
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<Town> path = new ArrayList<Town>();
		for(Town t = destinationVertex; t != null; t = t.getPredecessor())
			path.add(t);
		Collections.reverse(path);
		ArrayList<String> actualPath = new ArrayList<String>();
		for(int k = 0; k < path.size()-1; k++)
			actualPath.add(path.get(k).getName() +" via "+ getEdge(path.get(k), path.get(k+1)).getName() +" to "
				+ path.get(k+1).getName() +" "+ getEdge(path.get(k), path.get(k+1)).getWeight() + " mi");
		return actualPath;
	}

	/**
	 * TODO
	 * The code from this method is based on code from java2blog.com/dijkstra-java/
	 * @param sourceVertex 
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// this loop resets the necessary fields of each Town
		for(Town temp : vertexSet())
		{
			temp.setDistance(Integer.MAX_VALUE);
			temp.setVisited(false);
			temp.setPredecessor(null);
		}
		// the distance from the sourceVertex to itself is 0
		sourceVertex.setDistance(0);
		PriorityQueue<Town> priorityQueue = new PriorityQueue<>();
		ArrayList<Town> visitedVertices = new ArrayList<Town>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
		while(!priorityQueue.isEmpty()) {
			Town actualVertex = priorityQueue.poll();
			for(Road edge : actualVertex.getAdjacentRoads())
			{	// Gtown via 335-N to Gburg 2 mi
				Town v = edge.getVertexB();
				if(v.equals(sourceVertex))
					v = edge.getVertexA();
				if(!visitedVertices.contains(edge.getVertexB()))
					;
				else if(!visitedVertices.contains(edge.getVertexA()))
					v = edge.getVertexA();
				visitedVertices.add(v);
				
				if(!v.isVisited())
				{
					int newDistance = actualVertex.getDistance() + edge.getWeight();
					if(newDistance < v.getDistance()) {
						priorityQueue.remove(v);
						v.setDistance(newDistance);
						v.setPredecessor(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
		}
	}

}