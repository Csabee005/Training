package com.magyarcs.depthfirstsearch;

import java.util.EmptyStackException;
import java.util.Stack;

public class DepthFirstMain {

    private static Stack<Node> nodeStack = new Stack<>();
    private static boolean hasUnvisited = true;

    public static void main(String[] args) {
        Node c = new Node("c");
        Node f = new Node("f");
        Node b = new Node("b");
        Node e = new Node("e");
        Node g = new Node("g");
        Node d = new Node("d");
        Node a = new Node("a");
        Node s = new Node("s");

        s.addChildNodes(a, b, c);
        a.addChildNodes(s, d);
        d.addChildNodes(a, g);
        g.addChildNodes(e, f);
        e.addChildNodes(g, b);
        b.addChildNodes(s, e);
        f.addChildNodes(c, g);
        c.addChildNodes(s, f);


        nodeStack.push(s);
        s.setVisited(true);
        System.out.println("Searched: " + s.getName());
        checkNode();
    }

    private static void checkNode() {
        if (!nodeStack.isEmpty()) {
            for (Node connectedNode : nodeStack.peek().getChildNodes()) {
                if (!connectedNode.isVisited()) {
                    connectedNode.setVisited(true);
                    nodeStack.push(connectedNode);
                    System.out.println("Searched: " + connectedNode.getName());
                    checkNode();
                }
            }
            if (!nodeStack.isEmpty()) {
                nodeStack.pop();
                checkNode();
            }
        }
    }
}
