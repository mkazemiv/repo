/**
 * 
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph = new Graph();

	/**
	 * 
	 * Adds a road and returns true if the addition was successful,
	 * and false otherwise
	 * @param name of source Town
	 * @param name of destination Town
	 * @param weight of Road
	 * @param name of Road
	 * @return true if the addition of the Road was successful, false otherwise
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if(!containsRoadConnection(town1, town2))
		{
			graph.addEdge(getTown(town1), getTown(town2), weight, roadName);
			return true;
		}
		else
			return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		if(containsRoadConnection(town1, town2))
			return graph.getEdge(getTown(town1), getTown(town2)).getName();
		else
			return null;
	}

	@Override
	public boolean addTown(String v) {
		if(!graph.containsVertex(getTown(v)))
			return graph.addVertex(new Town(v));
		else
			return false;
	}

	@Override
	public Town getTown(String name) {
		Town t = null;
		for(Town temp : graph.vertexSet())
			if(temp.getName().equals(name))
				t = temp;
		return t;
	}

	@Override
	public boolean containsTown(String v) {
		boolean result = false;
		for(Town temp : graph.vertexSet())
			if(temp.getName().equals(v))
				result = true;
		return result;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		if(graph.containsEdge(getTown(town1), getTown(town2)))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roadNames = new ArrayList<String>();
		for(Road r : graph.edgeSet())
			roadNames.add(r.getName());
		Collections.sort(roadNames);
		return roadNames;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(containsRoadConnection(town1, town2))
		{
			Road r = graph.getEdge(getTown(town1), getTown(town2));
			graph.removeEdge(getTown(town1), getTown(town2), r.getWeight(), road);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean deleteTown(String v) {
		if(containsTown(v))
		{
			graph.removeVertex(getTown(v));
			return true;
		}
		else
			return false;
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> townsNames = new ArrayList<String>();
		for(Town t : graph.vertexSet())
			townsNames.add(t.getName());
		Collections.sort(townsNames);
		return townsNames;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		if(!containsTown(town1) || !containsTown(town2))
			return null;
		else
		{
//			System.out.println(graph.shortestPath(getTown(town1), getTown(town2)));
			return graph.shortestPath(getTown(town1), getTown(town2));
		}
	}

	/**
	 * TODO
	 * @param selectedFile
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		// example: I270-N,14;Frederick;Clarksburg
		Scanner reader = new Scanner(selectedFile);
		String[] towns = null;
		String[] roadInfo = null;
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			
			towns = line.split(";");
			// towns: {"[roadName],[weight]", "[sourceTown]", "[destTown]"}
			
			roadInfo = towns[0].split(",");
			// roadInfo: {"[roadName]", "[weight]"}

			addTown(towns[1]);
			addTown(towns[2]);
			addRoad(getTown(towns[1]).getName(), getTown(towns[2]).getName(), Integer.parseInt(roadInfo[1]), roadInfo[0]);
		}
		reader.close();
	}

}