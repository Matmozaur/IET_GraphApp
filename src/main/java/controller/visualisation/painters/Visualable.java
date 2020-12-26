package controller.visualisation.painters;

public interface Visualable {
    void visualVertex(int v,String ... arg) throws InterruptedException;
    void visualEdge(int a,int b) throws InterruptedException;
    void visualMainVertex(int v, String ... arg) throws InterruptedException;
    void visualMainEdge(int a,int b) throws InterruptedException;
    void clearEdge(int a,int b);

}
