package com.zy.B_tree;

public interface Tree {
    default void pre(BNode root){
        System.out.print(root.getValue());
        if(root.getLeft()!=null){
            pre(root.getLeft());
        }
        if(root.getRight()!=null){
            pre(root.getRight());
        }
    }

    default void z(BNode root){
        if(root.getLeft()!=null){
            z(root.getLeft());
        }

        System.out.print(root.getValue());

        if(root.getRight()!=null){
            z(root.getRight());
        }
    }

    default  void  h(BNode root){
        if(root.getLeft()!=null){
            h(root.getLeft());
        }
        if(root.getRight()!=null){
            h(root.getRight());
        }
        System.out.print(root.getValue());
    }

}
