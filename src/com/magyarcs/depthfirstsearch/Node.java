package com.magyarcs.depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private String name;
    private boolean visited;
    private List<Node> childNodes = new ArrayList<>();
    private int currentNodeIndex = -1;

    public Node(String name, Node... childNodes) {
        this.name = name;
        this.childNodes.addAll(Arrays.asList(childNodes));
        if (childNodes.length > 0) {
            currentNodeIndex = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void addChildNodes(Node... nodes) {
        childNodes.addAll(Arrays.asList(nodes));
    }

    public Node getNextNode() {
        if (childNodes == null) {
            return new Node("", null);
        }
        if (currentNodeIndex < childNodes.size() - 1) {
            return childNodes.get(currentNodeIndex++);
        }
        return new Node("", null);
    }
}
