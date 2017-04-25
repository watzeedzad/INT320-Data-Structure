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
public class Edge<T> {

    private Node<T> fromNode;
    private Node<T> toNode;
    private int weight;

    public Edge(Node<T> node1, Node<T> node2, int weight) {
        this.fromNode = node1;
        this.toNode = node2;
        this.weight = weight;
    }

    public Node<T> getFromNode() {
        return fromNode;
    }

    public Node<T> getToNode() {
        return toNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isBetween(Node<T> fromNode, Node<T> toNode) {
        return (this.fromNode == fromNode && this.toNode == toNode);
    }

    @Override
    public String toString() {
        return fromNode + " --> " + toNode;
    }

}
