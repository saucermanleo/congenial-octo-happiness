package com.zy.B_tree;

public class BNode {
    private String value;
    private BNode right;
    private BNode left;

    public BNode(String value, BNode left, BNode right) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BNode getRight() {
        return right;
    }

    public void setRight(BNode right) {
        this.right = right;
    }

    public BNode getLeft() {
        return left;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }
}
