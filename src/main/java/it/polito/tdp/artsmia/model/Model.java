package it.polito.tdp.artsmia.model;

import java.util.*;
import it.polito.tdp.artsmia.db.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.traverse.*;
import org.jgrapht.graph.*;

public class Model {

	private Graph<ArtObject,DefaultWeightedEdge> graph;
	private List<ArtObject> allNodes;
	private ArtsmiaDAO dao = new ArtsmiaDAO();
	private Map<Integer,ArtObject> idMap;
	
	public Model() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.allNodes = new ArrayList<>();
		this.idMap = new HashMap<>();
	}
	
	private void loadNodes(){
		if(this.allNodes.isEmpty())
			this.allNodes = dao.listObjects();
		if(this.idMap.isEmpty()) {
			for(ArtObject ao: allNodes) {
				idMap.put(ao.getId(), ao);
			}
		}
			
	}
	
	public void buildGraph() {
		this.loadNodes();
		Graphs.addAllVertices(graph, allNodes);
		
		/* metodo lentissimo
		for(ArtObject a1: this.allNodes) {
			for(ArtObject a2: this.allNodes) {
				int peso = dao.getWeight(a1.getId(), a2.getId());
				if(peso!=0)
					Graphs.addEdgeWithVertices(graph, a1, a2, peso);
					
			}
		}*/
		for(EdgeModel edge: dao.getAllWeights(idMap)) {
			Graphs.addEdgeWithVertices(graph, edge.getSource(), edge.getTarget(),edge.getPeso());
		}
		
		System.out.println(graph.vertexSet().size());
		System.out.println(graph.edgeSet().size());
	}
	
	public boolean isIDInGraph(Integer objID) {
		if(this.idMap.get(objID) != null)
			return true;
		else
			return false;
	}
	
	public Integer calcolaConnessa(Integer ObjID) {
		DepthFirstIterator<ArtObject,DefaultWeightedEdge> iterator = new DepthFirstIterator<>(this.graph, this.idMap.get(ObjID));
		List<ArtObject> compConnessa = new ArrayList<>();
		while(iterator.hasNext())
			compConnessa.add(iterator.next());
		return compConnessa.size();
		
	}
	
}
