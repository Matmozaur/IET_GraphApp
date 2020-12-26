package controller.model_manipulation;

import model.settings.CurrentAction;
import model.elements.Edge;
import model.elements.Vertex;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class ConstructionController {
    private final GraphPanel panel;
    private  int j = 0;
    private  int r = 0;

    public ConstructionController(GraphPanel panel) {
        this.panel = panel;
    }

    public void action(MouseEvent e){
        if (panel.getSettings().currently == CurrentAction.VERTEX) {
            panel.addVertex(new Vertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2, panel.getCounter(), null,panel.getSettings()));
        }
        //EDGE
        if (panel.getSettings().currently == CurrentAction.EDGE) {
            if (j == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2,
                        e.getY() - panel.getSettings().vdiam/ 2));
                if (panel.getCurrentVertex() != null) j = 1;
            }
            else {
                Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
                if (a != null) {
                    if(a != panel.getCurrentVertex()) {
                        Edge c = new Edge(a, panel.getCurrentVertex(), panel.getSettings());
                        panel.addEdge(c);
                        panel.unselect(a);
                        panel.setCurrentVertex(null);
                        j--;
                    }
                    else {
                        j--;
                        panel.unselect(a);
                    }
                }
            }
        }

        if (panel.getSettings().currently == CurrentAction.EDGEREMOVE) {
            if (r == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2));
                if (panel.getCurrentVertex() != null) r = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
                if (a != null) {
                    Edge c = panel.getEdge(a, panel.getCurrentVertex());
                    if (c != null) {
                        panel.removeEdge(c);
                        r--;
                    }
                    Edge w = panel.getEdge(a, panel.getCurrentVertex());
                    if (w != null) {
                        panel.removeEdge(w);
                        r--;
                    }
                }
            }
        }
        if (panel.getSettings().currently != CurrentAction.EDGEREMOVE) r = 0;

        if (panel.getSettings().currently == CurrentAction.VERTEXREMOVE) {
            Vertex v = panel.getVertex(e.getX() - panel.getSettings().vdiam / 2, e.getY() - panel.getSettings().vdiam / 2);
            if (v != null) {
                panel.removeVertex(v);
            }
        }

    }
}
