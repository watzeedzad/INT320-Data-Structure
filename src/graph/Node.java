/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author int320
 */
public class Node<T> {

    private T vertex;
    private List<Edge<T>> edges;

    public Node(T vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<>();
    }

    public T getVertex() {
        return vertex;
    }

    public boolean addEdge(Node<T> toNode, int weight) {
        if (hasEdge(toNode)) {
            return false;
        }
        Edge<T> newEdge = new Edge<>(this, toNode, weight);
        return edges.add(newEdge);
    }

    public boolean removeEdge(Node<T> node) {
        Optional<Edge<T>> optional = findEdge(node);
        if (optional.isPresent()) {
            return edges.remove(optional.get());
        }
        return false;
    }

    public boolean hasEdge(Node<T> node) {
        return findEdge(node).isPresent();
    }

    private Optional<Edge<T>> findEdge(Node<T> node) {
        return edges.stream().filter(edge -> edge.isBetween(this, node)).findFirst();   //java8 "lambdas"
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return vertex + "";
    }

}
