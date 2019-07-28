package com.magyarcs.breadthfirstsearch;

import com.magyarcs.depthfirstsearch.Node;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class BreadthFirstMain {
    private static Queue<Node> nodeQueue = new LinkedTransferQueue<>();

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

        System.out.println("Checked node: " + s.getName());
        s.setVisited(true);
        checkNode(s);
    }

    private static void checkNode(Node... node) {
        List<Node> childNodes = null;
        if (!nodeQueue.isEmpty()) {
            childNodes = nodeQueue.peek().getChildNodes();
        }
        if (node != null) {
            childNodes = node[0].getChildNodes();
        }
        if (childNodes != null) {
            for (Node connectedNode : childNodes) {
                if (!connectedNode.isVisited()) {
                    connectedNode.setVisited(true);
                    nodeQueue.add(connectedNode);
                    System.out.println("Checked node: " + connectedNode.getName());
                }
            }
            if (!nodeQueue.isEmpty()) {
                Node nextNode = nodeQueue.peek();
                nodeQueue.remove();
                checkNode(nextNode);
            }
        }
    }

}
