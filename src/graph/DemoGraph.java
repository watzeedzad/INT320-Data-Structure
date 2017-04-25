/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.List;
import java.util.Map;

/**
 *
 * @author pichet
 */
public class DemoGraph {

    public static void main(String[] args) throws Exception {
        Graph<String> g = new Graph(false);

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addVertex("G");
        g.addVertex("H");
        g.addVertex("I");

        g.addEdge("C", "B", 2);
        g.addEdge("C", "A", 4);

        g.addEdge("B", "E", 3);
        g.addEdge("B", "F", 2);
        g.addEdge("B", "A", 1);

        g.addEdge("A", "D", 8);

        g.addEdge("D", "H", 2);

        g.addEdge("F", "E", 5);
        g.addEdge("F", "H", 12);
        g.addEdge("F", "I", 3);

        g.addEdge("H", "G", 3);

        g.addEdge("I", "D", 5);
        g.addEdge("I", "G", 18);

        System.out.println(g.containsEdge("B", "C"));
        System.out.println(g.containsEdge("C", "B"));

        System.out.println("node count: " + g.vertexCount());

        for (Node<String> node : g.getNodes()) {
            System.out.print(node.getVertex());
            int x = 1;
            for (Edge<String> e : node.getEdges()) {
                System.out.println((x == 1 ? " --> " : "  --> ") + e.getToNode().getVertex() + " : (" + e.getWeight() + ")");
                x++;
            }
            System.out.println((x == 1 ? "\n" : "") + "---------------------");
        }

        g.deptFirst("C");

        Graph.ShortestPath path = g.getShortestPath("C");

        List<String> lst = null;
        for (Node<String> node : g.getNodes()) {
            lst = path.getShortestPath(node.getVertex());
            for (String v : lst) {
                System.out.print(v + " --> ");
            }
            System.out.println("\b\b\b\b\n");
        }

        Graph<String> spt = g.getSpanningTree("A");
        for (Node<String> n : spt.getNodes()) {
            for (Edge<String> e : n.getEdges()) {
                System.out.println(e);
            }
        }
    }
}
