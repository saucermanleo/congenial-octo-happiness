package com.zy.pmk.tree;

/**
 * @author Administrator 树节点
 */
public class TreeNode {
	private int value;

	private TreeNode leftTreeNode;

	private TreeNode rightTreeNode;

	
	public TreeNode(int value, TreeNode leftTreeNode, TreeNode rightTreeNode) {
		this.value = value;
		this.leftTreeNode = leftTreeNode;
		this.rightTreeNode = rightTreeNode;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public TreeNode getLeftTreeNode() {
		return leftTreeNode;
	}

	public void setLeftTreeNode(TreeNode leftTreeNode) {
		this.leftTreeNode = leftTreeNode;
	}

	public TreeNode getRightTreeNode() {
		return rightTreeNode;
	}

	public void setRightTreeNode(TreeNode rightTreeNode) {
		this.rightTreeNode = rightTreeNode;
	}

	/**
	 * 先序遍历 （根 左 右）
	 * @param rootTreeNode
	 */
	public static void preTraverseBTree(TreeNode rootTreeNode) {
		if (rootTreeNode != null) {
			System.out.print(rootTreeNode.getValue()+" ");
			preTraverseBTree(rootTreeNode.getLeftTreeNode());
			preTraverseBTree(rootTreeNode.getRightTreeNode());
			
		}
	}
	
	/**
	 * 中序遍历 （左 根 右）
	 * @param rootTreeNode
	 */
	public static void inTraverseBTree(TreeNode rootTreeNode) {
		if (rootTreeNode != null) {
			inTraverseBTree(rootTreeNode.getLeftTreeNode());
			System.out.print(rootTreeNode.getValue()+" ");
			inTraverseBTree(rootTreeNode.getRightTreeNode());
			
		}
	}
	
	/**
	 * 后序遍历 （左  右 后）
	 * @param rootTreeNode
	 */
	public static void postTraverseBTree(TreeNode rootTreeNode) {
		if (rootTreeNode != null) {
			postTraverseBTree(rootTreeNode.getLeftTreeNode());
			postTraverseBTree(rootTreeNode.getRightTreeNode());
			System.out.print(rootTreeNode.getValue()+" ");
			
		}
	}
	
	
	/**
	 *动态生成二叉查找树
	 *生成的树按中序遍历会按序排列
	 * @param rootTreeNode
	 * @param value
	 * @return
	 */
	public static TreeNode createBTree(TreeNode rootTreeNode , int value) {
		TreeNode headNode = null;
		if(rootTreeNode == null) {
			TreeNode t = new TreeNode(value, null, null);
			return t;
		}
		headNode = rootTreeNode;
		while(headNode !=null) {
			if(  headNode.getValue()>value) {
				if(headNode.getLeftTreeNode()==null) {
					headNode.setLeftTreeNode(new TreeNode(value, null, null));
					break;
				}else {
					headNode = headNode.getLeftTreeNode();
				}
				
			}else if(headNode.getValue()<value) {
				if(headNode.getRightTreeNode()==null) {
					headNode.setRightTreeNode(new TreeNode(value, null, null));
					break;
				}else {
					headNode = headNode.getRightTreeNode();
				}
			}
		}
		return rootTreeNode;
	}
	
}
