package view;


import controller.Main;
import controller.model_manipulation.GenerationController;
import model.graphs.representation.WeightGraph;

import javax.swing.*;


class GraphGenerator extends JFrame{
    private JList list;
    private JButton generateButton;
    private JLabel Vnumb;
    private JPanel panel;
    private JSpinner numberV;
    private JLabel Wnumb;
    private JSpinner numberW;

    GraphGenerator(){
        initialize();
    }

    private void initialize(){
        setTitle("Generator");
        setBounds(300, 300, 400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        numberV.setModel(new SpinnerNumberModel(1, 1, 40, 1));
        numberW.setModel(new SpinnerNumberModel(1, 1, 40, 1));
        generateButton.addActionListener(actionEvent -> {
            if(list.getSelectedValue()!=null){
                generate(list.getSelectedValue().toString(),(Integer) numberV.getValue(),(Integer) numberW.getValue());
            }
        });
    }

    private void generate(String type,int v, int w){
        WeightGraph G;
        switch (type){
            case "Grid": G=GenerationController.generateGrid(v, w); break;
            case "Path": G=GenerationController.generatePath(v); break;
            case "Cycle": G=GenerationController.generateCycle(v); break;
            case "Binary Tree": G=GenerationController.generateBT(v); break;
            default: return;
        }
        if(G!=null){
            Main.loadManager(G);
            this.setVisible(false);
        }

    }



}
