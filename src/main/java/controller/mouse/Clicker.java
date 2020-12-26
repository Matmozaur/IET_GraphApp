package controller.mouse;

import controller.model_manipulation.ConstructionController;
import controller.visualisation.AlgorithmVisualisationController;
import model.settings.CurrentAction;
import view.GraphPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clicker extends MouseAdapter {
    private final GraphPanel panel;
    private final ConstructionController CC;
    private final AlgorithmVisualisationController AC;

    public Clicker(GraphPanel panel) {
        super();
        this.panel = panel;
        CC=new ConstructionController(panel);
        AC=new AlgorithmVisualisationController(panel);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (CurrentAction.getGraphElements().contains(panel.getSettings().currently)) {
            CC.action(e);
        }
        if(CurrentAction.getAlgorithms().contains(panel.getSettings().currently)) {
            AC.action(e);
        }
    }
}

