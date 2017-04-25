/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author int320
 */
public class Graph<T> {

    private Map<T, Node<T>> adjacencyList;
    private boolean directed;

    public Graph() {
        this(false);
    }

    public Graph(boolean isDirected) {
        adjacencyList = new HashMap<>();
        this.directed = isDirected;
    }

    public boolean addVertex(T vertex) {
        if (!containsVertex(vertex)) {
            adjacencyList.put(vertex, new Node<>(vertex));
            return true;
        }
        return false;
    }

    public boolean addEdge(T vertex1, T vertex2) {
        return addEdge(vertex1, vertex2, 0);
    }

    public boolean addEdge(T vertex1, T vertex2, int weight) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            throw new RuntimeException("Vertex does not exist " + vertex1 + ", " + vertex2);
        }

        // add the edge
        Node<T> node1 = getNode(vertex1);
        Node<T> node2 = getNode(vertex2);
        if (!directed) {
            return node1.addEdge(node2, weight) && node2.addEdge(node1, weight);
        } else {
            return node1.addEdge(node2, weight);
        }
    }

    public int vertexCount() {
        return adjacencyList.keySet().size();
    }

    public int edgeCount() {
        return adjacencyList.values().stream().mapToInt(Node::getEdgeCount).sum();
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean containsEdge(T vertex1, T vertex2) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            return false;
        }
        return getNode(vertex1).hasEdge(getNode(vertex2));
    }

    public Node<T> getNode(T value) {
        return adjacencyList.get(value);
    }

    public List<Node<T>> getNodes() {
        return new ArrayList(adjacencyList.values());
    }

    public void deptFirst(T selectNode) {
        Set x = new HashSet((int) (this.vertexCount() / 0.75) + 1);
        deptFirst(selectNode, x);
        System.out.println("\b\b");
    }

    private void deptFirst(T node, Set<T> visited) {
        System.out.print(node + ", ");
        visited.add(node);
        for (Edge<T> e : this.getNode(node).getEdges()) {
            T n = e.getToNode().getVertex();
            if (!visited.contains(n)) {
                deptFirst(n, visited);
            }
        }
        return;
    }

    Graph.ShortestPath getShortestPath(T fromNode) {
        Graph.ShortestPath path = new ShortestPath(fromNode);
        PriorityQueue<Graph.PathInfo> q = new PriorityQueue<>(this.edgeCount());
        Node<T> node = this.getNode(fromNode);

        q.add(new PathInfo(node, node, 0));
        while (!q.isEmpty()) {
            PathInfo pinfo = q.remove();
            if (path.containsKey(pinfo.toNode.getVertex())) {
                continue;
            }
            path.put(pinfo.toNode.getVertex(), pinfo);
            node = pinfo.toNode;
            for (Edge<T> e : node.getEdges()) {
                if (!path.containsKey(e.getToNode().getVertex())) {
                    q.add(new PathInfo(node, e.getToNode(), pinfo.totalWeight + e.getWeight()));
                }
            }
        }
        return path;
    }

    public class PathInfo implements Comparable<PathInfo> {

        Node<T> fromNode, toNode;
        int totalWeight;

        public PathInfo(Node<T> fromNode, Node<T> toNode, int totalWeight) {
            this.fromNode = fromNode;
            this.toNode = toNode;
            this.totalWeight = totalWeight;
        }

        public Node<T> getFromNode() {
            return fromNode;
        }

        public Node<T> getToNode() {
            return toNode;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        @Override
        public String toString() {
            return fromNode + ": " + totalWeight;
        }

        @Override
        public int compareTo(PathInfo o) {
            return totalWeight - o.totalWeight;
        }
    }

    public class ShortestPath {

        private Map<T, Graph.PathInfo> path;
        private T start;

        private ShortestPath(T start) {
            this.start = start;
            path = new HashMap(vertexCount() * 2 + 1, 0.5f);
        }

        private boolean containsKey(T vertex) {
            return path.containsKey(vertex);
        }

        private void put(T vertex, Graph.PathInfo pinfo) {
            path.put(vertex, pinfo);
        }

        public List<T> getShortestPath(T toNode) throws Exception {
            if (!path.containsKey(toNode)) {
                throw new Exception("Vertex: " + toNode + " does not exist !!!");
            }
            Graph.PathInfo p = path.get(toNode);
            LinkedList<T> stack = new LinkedList();
            while (true) {
                stack.push((T) (p.getToNode().getVertex() + ":" + p.getTotalWeight()));
                p = path.get((T) p.getFromNode().getVertex());
                if (p.getFromNode().equals(p.getToNode())) {
                    stack.push((T) p.getToNode().getVertex());
                    break;
                }
            }
            return stack;
        }

    }

    public Graph<T> getSpanningTree(T fromNode) {
        Graph<T> g = new Graph();
        PriorityQueue<Graph.PathInfo> q = new PriorityQueue<>();
        Node<T> node = getNode(fromNode);

        q.add(new PathInfo(node, node, 0));
        while (!q.isEmpty()) {
            PathInfo pinfo = q.remove();
            if (g.containsVertex(pinfo.toNode.getVertex())) {
                continue;
            }
            if (pinfo.getFromNode().getVertex().equals(pinfo.getToNode().getVertex())) {
                g.addVertex(pinfo.toNode.getVertex());
            } else {
                g.addVertex(pinfo.toNode.getVertex());
                g.addEdge(pinfo.fromNode.getVertex(), pinfo.toNode.getVertex(), pinfo.totalWeight);
            }
            node = pinfo.toNode;
            for (Edge<T> e : node.getEdges()) {
                if (!(g.containsVertex(e.getFromNode().getVertex()) && g.containsVertex(e.getToNode().getVertex()))) {
                    q.add(new PathInfo(node, e.getToNode(), e.getWeight()));
                }
            }
        }
        return g;
    }

}
