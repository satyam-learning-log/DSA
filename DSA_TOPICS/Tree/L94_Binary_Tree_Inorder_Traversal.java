

import com.sun.source.tree.Tree;

import java.util.*;

public class L94_Binary_Tree_Inorder_Traversal {
    public List<Integer> MorrisInorderTraverSal(TreeNode root)
    {
        TreeNode current=root;
        List<Integer> list = new ArrayList<>();

        //left -> root -> right

        while(current!=null){

            // if node doesn't contain any left node print it and move to right
            if(current.left==null){
                list.add(current.val);
                current = current.right;
            }else{

                //create a link between current.left's right most node
                //to current so we can traverse back to print node after visiting its left part

                TreeNode currentleft = current.left;

                while(currentleft.right!=null && currentleft.right!=current){
                    currentleft = currentleft.right;
                }

                // (currentleft.right == current) meaning we returned to already visited node after visiting its left part
                // now print it and traverse its right
                if(currentleft.right==null){
                    currentleft.right=current;
                    current=current.left;
                }else{
                    list.add(current.val);
                    current=current.right;
                    currentleft.right = null;
                }
            }
        }

        return list;
    }





    // public void inorderTraversal(TreeNode node,List<Integer>result,Stack<Integer> stack)
    // {
    //     if(node==null)
    //     {
    //        return;
    //     }
    //     stack.push(node);
    //     inorderTraversal(node.left,result,stack);
    //     list.add(stack.pop());
    //     inorderTraversal(node.right,result,stack);

    // }




    public List<Integer> inorderTraversal(TreeNode root) {
        //BY RECURRSIVE WAY
        // List<Integer> list = new ArrayList<>();
        // in(root,list);
        // return list;


        //BY TRAVERSING WAY
         Stack<TreeNode> stack = new Stack<>();
         List<Integer> output = new ArrayList<>();
         TreeNode node = root;

         while(!stack.isEmpty() || node!=null){
             if(node!=null){
                 stack.push(node);
                 node = node.left;
             }else{
                 TreeNode current = stack.pop();
                 output.add(current.val);
                 node= current.right;
             }
         }
         return output;

         //MORRIS INORDER TRAVERSAL
        //return MorrisInorderTraverSal(root);

    }
}
