package com.zy.pmk.tree;

public class TestBinaryTree {

	public static void main(String[] args) {
		
		TreeNode t15 = new TreeNode(15, null, null);
		TreeNode t35 = new TreeNode(35, null, null);
		TreeNode t20 = new TreeNode(20, t15, t35);
		TreeNode t9 = new TreeNode(9, null, null);
		TreeNode t10 = new TreeNode(10, t9, t20);
		
		int[] arrays = {4,2,6,1,3,5,7};
		//int[] arrays = {2,3,1,4,5};
		
		
		TreeNode root = null;
		for(int i : arrays) {
			root = TreeNode.createBTree(root, i);
		}
		TreeNode.preTraverseBTree(root);
		System.out.println();
		TreeNode.inTraverseBTree(root);
		System.out.println();
		TreeNode.postTraverseBTree(root);
		System.out.println();
		System.out.println(TreeNode.getHeight(root));
		System.out.println(TreeNode.getMax(root));
	}

}
