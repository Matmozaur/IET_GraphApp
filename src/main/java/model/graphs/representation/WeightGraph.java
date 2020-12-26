package model.graphs.representation;

import model.elements.Edge;
import model.elements.Vertex;
import model.graphs.logic.WeightGraphLogic;
import model.settings.Settings;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import view.GraphPanel;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("ALL")
public class WeightGraph extends SimpleWeightedGraph<Vertex, DefaultWeightedEdge>  implements Serializable {
    private static final long serialVersionUID = -1567413557378365221L;

    private final List<Vertex> vertexes=new LinkedList<>();
    private final List<Edge> edges=new LinkedList<>();
    private transient GraphPanel panel;
    private final WeightGraphLogic logic;

    public WeightGraph(GraphPanel panel){
        super(DefaultWeightedEdge.class);
        this.panel=panel;
        logic=new WeightGraphLogic(this,0,new boolean[Settings.n][Settings.n],new int[Settings.n][Settings.n]);
    }
    public WeightGraph(){
        super(DefaultWeightedEdge.class);
        logic=new WeightGraphLogic(this,0,new boolean[Settings.n][Settings.n],new int[Settings.n][Settings.n]);
    }


    public void setPanel(GraphPanel panel) {
        this.panel = panel;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public WeightGraphLogic getLogic() {
        return logic;
    }

    public void add(Vertex v){
        super.addVertex(v);
        vertexes.add(v);
        logic.addV();
    }

    public void add(Edge e){
        super.addEdge(e.getMySource(),e.getMyTarget());
        this.edges.add(e);
        logic.addE(e.getMySource().getNumb(),e.getMyTarget().getNumb(),1);
    }

    public void removeE(Edge e){
        int a;
        for (Edge f : edges) {
            if (f.equals(e)) {
                a = edges.indexOf(f);
                if(panel!=null) panel.unselect(e.getMySource(), e.getMyTarget());
                edges.remove(a);
                logic.remove(e.getMySource().getNumb(),e.getMyTarget().getNumb());
                break;
            }
        }
    }

    public void clear(){
        for (Vertex c : vertexes) {
            c.setColor(panel.getSettings().vcolor);
            c.setAdditionalLabel(null);
        }
        for (Edge e : edges) {
            e.setColor(panel.getSettings().ecolor);
        }
    }

    public void remove(Vertex v){
        ListIterator<Edge> iterE = edges.listIterator();
        while (iterE.hasNext()) {
            Edge e = iterE.next();
            if (e.getMySource().equals(v) || e.getMyTarget().equals(v)) {
                iterE.remove();
                removeE(e);
                if(panel!=null) panel.removeEdge(e);
            }
        }
        if(panel!=null) panel.setCounter(panel.getCounter()-1);
        ListIterator<Vertex> iterV = vertexes.listIterator();
        while (iterV.hasNext()) {
            Vertex u = iterV.next();
            if (u.equals(v)) {
                iterV.remove();
                logic.remove(v.getNumb());
            }
        }
        for (Vertex u : vertexes) {
            if (u.getNumb() > v.getNumb()) u.setNumb(u.getNumb() - 1);
        }

    }

    @Override
    public boolean containsVertex(Vertex v){
        return vertexes.contains(v);
    }

    public boolean connected(Vertex v, Vertex u){
        return logic.connected(v.getNumb(),u.getNumb());
    }

    public GraphPath getShortestPath(Vertex v, Vertex u){
        GraphPath shortest_path = DijkstraShortestPath.findPathBetween(this, v, u);
        return shortest_path;
    }



}
