import java.util.*;

class Node{
    int data,height;
    Node left,right;
    Node(int d)
    {
        data = d;
        height = 1;
        left = null;
        right = null;
    }
}

public class AVLIMP{
    public static Node root = null;
    

    public static Node append(Node root,int val){
        if(root==null)
            return new Node(val);
        if(val < root.data)
            root.left = append(root.left,val);
        if(val > root.data)
            root.right = append(root.right,val);

        updateHeight(root);
        int bh = isBalanced(root);
        if(bh > 1 ){
            // LL
            if(val < root.left.data){
                return rotateRight(root);
            }
            // LR 
            else{
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

            
        if(bh < -1){
            // RR
            if(val > root.right.data){
                return rotateLeft(root);
            }
            // RL
            else{
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        
        return root;
    }

    public static Node delete(Node root,int val){
        if(root==null)
            return null;
        if(val < root.data)
            root.left = delete(root.left,val);
        else if(val > root.data)
            root.right = delete(root.right,val);
        else{
            if(root.left==null || root.right==null){
                Node temp = null;
                if(temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if(temp==null){
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else{
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(root.right,temp.data);
            }
        }
        if(root==null)
            return null;

        updateHeight(root);
        int bh = isBalanced(root);
        if(bh > 1 ){
            // LL
            if(val < root.left.data){
                return rotateRight(root);
            }
            // LR 
            else{
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

            
        if(bh < -1){
            // RR
            if(val > root.right.data){
                return rotateLeft(root);
            }
            // RL
            else{
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }
        
        return root;
    }

    public static Node rotateLeft(Node a){
        Node b = a.right;
        Node c = b.left;
        b.left = a;
        a.right = c;
        updateHeight(a);
        updateHeight(b);
        return b;
    }

    public static Node rotateRight(Node a){
        Node b = a.left;
        Node c = b.right;
        b.right = a;
        a.left = c;
        updateHeight(a);
        updateHeight(b);
        return b;
    }
    public static int isBalanced(Node root){
        if(root==null)
            return 0;
        return (height(root.left)-height(root.right));
    }
    public static int height(Node root){
        if(root==null)
            return 0;
        return root.height;
    }
    public static void updateHeight(Node root){
        root.height = 1 + Math.max(height(root.left),height(root.right));
    }
    public static void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i=0;i<n;i++) root = append(root,scan.nextInt());
		inorder(root);
	}
}