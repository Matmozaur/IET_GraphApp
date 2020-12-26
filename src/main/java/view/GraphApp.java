package view;

import controller.Main;
import controller.mouse.Clicker;
import controller.mouse.MouseMover;
import model.graphs.representation.WeightGraph;
import model.settings.CurrentAction;
import model.settings.Settings;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.*;


public class GraphApp {
    private Settings settings=new Settings();
    private JFrame frame;
    private GraphPanel graphPanel;
    private Clicker clicker;

    public JFrame getFrame() {
        return frame;
    }
    public Settings getSettings() {
        return settings;
    }

    public GraphApp() {
        initialize();
    }
    public GraphApp(WeightGraph G) {
        initialize();
        G.setPanel(this.graphPanel);
        this.graphPanel.setG(G);
        if(!G.getVertexes().isEmpty()){
            this.settings=G.getVertexes().get(0).getSettings();
            this.graphPanel.setSettings(G.getVertexes().get(0).getSettings());
            this.graphPanel.setCounter(G.getVertexes().size());
            for(int i=0;i<graphPanel.getMouseListeners().length;i++){
                graphPanel.removeMouseListener(graphPanel.getMouseListeners()[i]);
            }
            clicker=new Clicker(graphPanel);
            graphPanel.addMouseListener(clicker);
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(50, 50, 1000, 900);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setTitle("Map manager");

        graphPanel = new GraphPanel(this);
        graphPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        graphPanel.setBackground(UIManager.getColor("info"));
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        clicker=new Clicker(graphPanel);
        graphPanel.addMouseListener(clicker);
        graphPanel.addMouseMotionListener(new MouseMover(graphPanel));
        graphPanel.setBackground(Color.GRAY);



        JMenuBar menuBar = new JMenuBar();
        menuBar.setAutoscrolls(true);
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.setAlignmentX(0.0f);
        frame.setJMenuBar(menuBar);
        menuBar.setBackground(Color.DARK_GRAY);



        JMenu mnFile = new JMenu("File");
        mnFile.setForeground(new Color(0, 200, 0));
        mnFile.setVerticalAlignment(SwingConstants.TOP);
        mnFile.setBorderPainted(true);
        mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnFile.setBackground(Color.pink);
        menuBar.add(mnFile);


        JButton btnSave = new JButton("Save");
        btnSave.setForeground(new Color(0, 200, 0));
        btnSave.setPreferredSize(new Dimension(140, 23));
        btnSave.setMinimumSize(new Dimension(140, 23));
        btnSave.setMaximumSize(new Dimension(140, 23));
        btnSave.setBackground(Color.DARK_GRAY);
        btnSave.addActionListener(arg0 -> Main.getFC().getFW().saveAs(graphPanel.getG(),this.frame));
        mnFile.add(btnSave);

        JButton btnSaveAs = new JButton("Save As");
        btnSaveAs.setForeground(new Color(0, 200, 0));
        btnSaveAs.setPreferredSize(new Dimension(140, 23));
        btnSaveAs.setMinimumSize(new Dimension(140, 23));
        btnSaveAs.setMaximumSize(new Dimension(140, 23));
        btnSaveAs.setBackground(Color.DARK_GRAY);
        btnSaveAs.addActionListener(arg0 -> Main.getFC().getFW().saveAs(graphPanel.getG(),this.frame));
        mnFile.add(btnSaveAs);

        JButton btnOpen = new JButton("Open");
        btnOpen.setForeground(new Color(0, 200, 0));
        btnOpen.setPreferredSize(new Dimension(140, 23));
        btnOpen.setMinimumSize(new Dimension(140, 23));
        btnOpen.setMaximumSize(new Dimension(140, 23));
        btnOpen.setBackground(Color.DARK_GRAY);
        btnOpen.addActionListener(actionEvent ->{
            WeightGraph G=Main.getFC().getFR().getGraph(this.getFrame());
            if(G!=null){
                Main.loadManager(G);
            }
        });
        mnFile.add(btnOpen);



        JMenu mnNewMenu = new JMenu("New");
        mnNewMenu.setForeground(new Color(0, 200, 0));
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnNewMenu.setBackground(Color.pink);
        menuBar.add(mnNewMenu);


        JButton btnVertex = new JButton("Vertex");
        btnVertex.setForeground(new Color(0, 200, 0));
        btnVertex.setPreferredSize(new Dimension(140, 23));
        btnVertex.setMinimumSize(new Dimension(140, 23));
        btnVertex.setMaximumSize(new Dimension(140, 23));
        btnVertex.setBackground(Color.DARK_GRAY);
        btnVertex.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEX);
        mnNewMenu.add(btnVertex);

        JButton btnEdge = new JButton("Edge");
        btnEdge.setForeground(new Color(0, 200, 0));
        btnEdge.setPreferredSize(new Dimension(140, 23));
        btnEdge.setMinimumSize(new Dimension(140, 23));
        btnEdge.setMaximumSize(new Dimension(140, 23));
        btnEdge.setBackground(Color.DARK_GRAY);
        btnEdge.addActionListener(e -> settings.currently = CurrentAction.EDGE);
        mnNewMenu.add(btnEdge);



        JMenu mnAlgorithms = new JMenu("Find path");
        mnAlgorithms.setForeground(new Color(0, 200, 0));
        mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnAlgorithms);

        JButton btnDfs = new JButton("DFS");
        btnDfs.setForeground(new Color(0, 200, 0));
        btnDfs.setPreferredSize(new Dimension(140, 23));
        btnDfs.setMinimumSize(new Dimension(140, 23));
        btnDfs.setMaximumSize(new Dimension(140, 23));
        btnDfs.setBackground(Color.DARK_GRAY);
        btnDfs.addActionListener(arg0 -> settings.currently = CurrentAction.DFS);
        mnAlgorithms.add(btnDfs);

        JButton btnBfs = new JButton("BFS");
        btnBfs.setForeground(new Color(0, 200, 0));
        btnBfs.setPreferredSize(new Dimension(140, 23));
        btnBfs.setMinimumSize(new Dimension(140, 23));
        btnBfs.setMaximumSize(new Dimension(140, 23));
        btnBfs.setBackground(Color.DARK_GRAY);
        btnBfs.addActionListener(arg0 -> settings.currently = CurrentAction.BFS);
        mnAlgorithms.add(btnBfs);

        JButton btnDijkstra = new JButton("Dijkstra");
        btnDijkstra.setForeground(new Color(0, 200, 0));
        btnDijkstra.setPreferredSize(new Dimension(140, 23));
        btnDijkstra.setMinimumSize(new Dimension(140, 23));
        btnDijkstra.setMaximumSize(new Dimension(140, 23));
        btnDijkstra.setBackground(Color.DARK_GRAY);
        btnDijkstra.addActionListener(arg0 -> settings.currently = CurrentAction.DIJKSTRA);
        mnAlgorithms.add(btnDijkstra);



        JMenu mnRemove = new JMenu("Change");
        mnRemove.setForeground(new Color(0, 200, 0));
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);


        JButton btnVertex_1 = new JButton("Remove vertex");
        btnVertex_1.setForeground(new Color(0, 200, 0));
        btnVertex_1.setPreferredSize(new Dimension(140, 23));
        btnVertex_1.setMinimumSize(new Dimension(140, 23));
        btnVertex_1.setMaximumSize(new Dimension(140, 23));
        btnVertex_1.setBackground(Color.DARK_GRAY);
        btnVertex_1.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEXREMOVE);

        JButton btnRefresh = new JButton("Delete all");
        btnRefresh.setForeground(new Color(0, 200, 0));
        btnRefresh.setMinimumSize(new Dimension(140, 23));
        btnRefresh.setMaximumSize(new Dimension(140, 23));
        btnRefresh.setPreferredSize(new Dimension(140, 23));
        btnRefresh.setBackground(Color.DARK_GRAY);
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(arg0 -> graphPanel.refresh());

        JButton btnClear = new JButton("Clear");
        btnClear.setForeground(new Color(0, 200, 0));
        btnClear.setPreferredSize(new Dimension(140, 23));
        btnClear.setMinimumSize(new Dimension(140, 23));
        btnClear.setMaximumSize(new Dimension(140, 23));
        btnClear.setBackground(Color.DARK_GRAY);
        btnClear.addActionListener(arg0 -> graphPanel.clear());
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

        JButton btnEdge_1 = new JButton("Remove \n edge");
        btnEdge_1.setForeground(new Color(0, 200, 0));
        btnEdge_1.setMaximumSize(new Dimension(140, 23));
        btnEdge_1.setMinimumSize(new Dimension(140, 23));
        btnEdge_1.setPreferredSize(new Dimension(140, 23));
        btnEdge_1.setBackground(Color.DARK_GRAY);
        btnEdge_1.addActionListener(e -> settings.currently = CurrentAction.EDGEREMOVE);
        mnRemove.add(btnEdge_1);


        JMenu mnSettings = new JMenu("Settings");
        mnSettings.setDoubleBuffered(true);
        mnSettings.setFocusCycleRoot(true);
        mnSettings.setFocusPainted(true);
        mnSettings.setFocusTraversalPolicyProvider(true);
        mnSettings.setForeground(new Color(0, 200, 0));
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);
        mnSettings.setBackground(Color.DARK_GRAY);


        JLabel lblNewLabel = new JLabel("Animations wait time");
        lblNewLabel.setForeground(new Color(0, 200, 0));
        mnSettings.add(lblNewLabel);

        JSpinner spinner = new JSpinner();
        spinner.setForeground(new Color(0, 200, 0));
        spinner.setBackground(new Color(105, 105, 105));
        spinner.addChangeListener(arg0 -> {

        });
        spinner.setModel(new SpinnerNumberModel(500, 0, 1000000, 10));
        mnSettings.add(spinner);

        JButton btnApplay = new JButton("Apply");
        btnApplay.addActionListener(e -> settings.t = (Integer) spinner.getValue());
        mnSettings.add(btnApplay);


    }

}
