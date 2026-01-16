package Traversals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class L144_Binary_Tree_Preorder_Traversal {

    public void preorderByMorrisTraversal(TreeNode root){
        //aim ? -> for every current node attach currentleft's right most to current's right

        TreeNode current = root;

        while(current!=null){
            System.out.println(current.val);

            if(current.left == null){
                current = current.right;
            }else{
                //attach currentleft's right most to current's right
                TreeNode currentLeft = current.left;

                while(currentLeft.right!=null){
                    currentLeft = currentLeft.right;
                }
                currentLeft.right = current.right;
                current = current.left;
            }
        }
    }

    // PREORDER BY RECURRSION
     public void preorderByRecurrsion(TreeNode root, List<Integer> list)
     {
         if(root==null)
         {
             return;
         }
         list.add(root.val);
         preorderByRecurrsion(root.left,list);
         preorderByRecurrsion(root.right,list);
     }

    // PREORDER BY ITERATION
     public List<Integer> preorderbyIteration(TreeNode root ){

         List<Integer> list = new ArrayList<>();
          if (root == null) {
              return list;
          }

          Stack<TreeNode> st = new Stack<>();
          st.push(root);

          while (!st.isEmpty()) {
              TreeNode top = st.pop();

              // Add the current node's value to the result list
              list.add(top.val);

              // Push right and left children to stack if they are not null
              if (top.right != null) {
                  st.push(top.right);
              }
              if (top.left != null) {
                  st.push(top.left);
              }
          }

          return list;
     }
}
