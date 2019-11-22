package com.zy.B_tree;

public class BTree implements Tree {

    public static void main(String[] args) {
        BTree bTree = new BTree();
        BNode H = new BNode("h", null, null);
        BNode K = new BNode("k", null, null);
        BNode G = new BNode("g", H, K);
        BNode F = new BNode("f", G, null);
        BNode E = new BNode("e", null, F);
        BNode D = new BNode("d", null, null);
        BNode C = new BNode("c", D, null);
        BNode B = new BNode("b", null, C);
        BNode A = new BNode("a", B, E);

        bTree.pre(A);
        System.out.println();
        bTree.z(A);
        System.out.println();
        bTree.h(A);

    }

}
