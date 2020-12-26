package model.settings;

import java.util.EnumSet;

public enum CurrentAction {
	VERTEX, EDGE,DFS,BFS,VERTEXREMOVE,
	EDGEREMOVE, DIJKSTRA;

	public static EnumSet<CurrentAction> getGraphElements() {
		return EnumSet.of(VERTEX, EDGE, VERTEXREMOVE, EDGEREMOVE);
	}

	public static EnumSet<CurrentAction> getAlgorithms() {
		return  EnumSet.of(DIJKSTRA,DFS,BFS);
	}

}
