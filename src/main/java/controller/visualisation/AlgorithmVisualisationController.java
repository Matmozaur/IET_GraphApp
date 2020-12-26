package controller.visualisation;
import controller.visualisation.painters.LabelPainter;
import controller.visualisation.painters.SlowPainter;
import model.settings.CurrentAction;
import model.elements.Vertex;
import view.GraphPanel;

import java.awt.event.MouseEvent;

public class AlgorithmVisualisationController {

    private final GraphPanel panel;
    private final SlowPainter slowpainter;
    private final LabelPainter labelPainter;
    private int flag=0;

    public AlgorithmVisualisationController(GraphPanel panel) {
        this.panel = panel;
        slowpainter=new SlowPainter(panel.getG(),panel);
        labelPainter=new LabelPainter(panel.getG(),panel);
    }


    public void action(MouseEvent e){
       if(panel.getSettings().currently == CurrentAction.DFS) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if(a!=null && !a.equals(panel.getCurrentVertex())) {
                if(flag==0){
                    flag++;
                    panel.setCurrentVertex(a);
                }
                else {
                    panel.select(a);
                    Thread t=new Thread(()->{
                        try {
                            panel.getG().getLogic().vDFS(panel.getCurrentVertex().getNumb(),a.getNumb(),slowpainter);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        finally {
                            panel.getSettings().currently=CurrentAction.VERTEX;
                        }
                    });
                    t.start();
                }
            }
       }

        if(panel.getSettings().currently == CurrentAction.BFS) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if (a!=null && !a.equals(panel.getCurrentVertex())) {
                if(flag==0){
                    flag++;
                    panel.setCurrentVertex(a);
                }
                else {
                    panel.select(a);
                    flag = 0;
                    Thread t=new Thread(()-> {
                        try {
                            panel.getG().getLogic().vBFS(panel.getCurrentVertex().getNumb(),a.getNumb(),slowpainter);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        } finally {
                            panel.getSettings().currently = CurrentAction.VERTEX;
                        }
                    });
                    t.start();
                }
            }
        }

        if(panel.getSettings().currently == CurrentAction.DIJKSTRA) {
            Vertex a = panel.getVertex(e.getX() - panel.getSettings().vdiam/ 2, e.getY() - panel.getSettings().vdiam/ 2);
            if(a!=null && !a.equals(panel.getCurrentVertex())) {
                if(flag==0){
                    flag++;
                    panel.setCurrentVertex(a);
                }
                else{
                    panel.select(a);
                    flag = 0;
                    Thread t=new Thread(()-> {
                        try {
                            panel.getG().getLogic().vdijkstra(panel.getCurrentVertex().getNumb(),a.getNumb(),labelPainter);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    });
                    t.start();
                }
            }
        }


    }
}
