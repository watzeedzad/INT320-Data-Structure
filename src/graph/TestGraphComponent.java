/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author int320
 */
public class TestGraphComponent {
    public static void main(String[] args) {
        Node<String> a = new Node("A");
        Node<String> b = new Node("B");
        Node<String> c = new Node("C");
        Node<String> d = new Node("D");
        
        a.addEdge(c, 5);
        c.addEdge(b, 2);
        c.addEdge(d, 3);
        
        b.addEdge(c, 7);
        b.addEdge(d, 1);
        
        System.out.println("Has egde from a to b ? "+ (a.hasEdge(b)?"Yes" : "No"));
        System.out.println("Has egde from c to d ? "+ (c.hasEdge(d)?"Yes" : "No"));
        
        for (Edge e : c.getEdges()) {
            System.out.println(e);
        }
    }
    
}
