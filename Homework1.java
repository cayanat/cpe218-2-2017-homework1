
import java.util.Stack;
class Node {
    Node left;
    Node right;
    Node parent;
    char data;
    int key;

    public Node(char d) {
        data=d;
    }
    public Node() {
    }
    public Node(int k) {
        key=k;
    }
}
/**
 *
 * @author User
 */
public class Homework1 {
    
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length > 0) {
            String input = args[0];
            Homework1 h1=new Homework1();
            Node root=h1.constructTree(input);
            h1.inorder(root);
            h1.calculator(input);
	}
    }
    
    Node constructTree(String input){
        char post[]=input.toCharArray();
        Node tree,tree1,tree2;
        Stack<Node> s = new Stack<>();
        for(int i=0;i<input.length();i++){
            if(isOperator(post[i])){
                tree=new Node(post[i]);
                tree1=s.pop();
                tree.right=tree1;
                tree2=s.pop();
                tree.left=tree2;
                tree2.parent=tree;
                tree1.parent=tree;
                s.push(tree);
                
            }else{
                tree=new Node(post[i]);
                s.push(tree);
            }
        }      
        tree=s.pop();
        return tree;
    }
    
    boolean isOperator(char ch){
        if(ch == '+' || ch == '-' ||ch == '*' ||ch == '/' ) return true;
        else return false;
    }
    void calculator(String input){
        char post[]=input.toCharArray();
        Node tree,tree1,tree2;
        Stack<Node> s= new Stack<>();
        int sum=0;
        
        for(int i=0;i<input.length();i++){
            if(isOperator(post[i])){
                tree2=s.pop();
                tree1=s.pop();
                
                if(post[i]=='+') sum=tree1.key+tree2.key;
                else if(post[i]=='-') sum=tree1.key-tree2.key;
                else if(post[i]=='*') sum=tree1.key*tree2.key;
                else if(post[i]=='/') sum=tree1.key/tree2.key;
                
                
                //System.out.println(tree1.key+"  "+tree2.key+" = "+sum);
                tree=new Node(sum);
                
                s.push(tree);
            }else{
                tree=new Node(Character.getNumericValue(post[i]));
                s.push(tree);
            }
        }      
        tree=s.pop();
        System.out.print(" = "+tree.key);
    }
    void inorder(Node r){
        if(r!=null){
            if(isOperator(r.data)) a++;
            //System.out.println(a);
            inorder(r.left);
            infix(r);
            inorder(r.right);
        }
    }
    int a=0;
    int b=0;
    void infix(Node r){
        
        if(isOperator(r.data)){ 
            
            if(a==0){
                if(b>0) {
                    System.out.print(")");
                    b--;
                }
                System.out.print(r.data);
            }
            else System.out.print(r.data);
            
        }else{
            if(isOperator(r.parent.right.data)){  
                if(a>1){
                    System.out.print("("+r.data);
                    a--;
                    b++;
                }else {
                    System.out.print(r.data);
                    a--;
                }
            }else{
                if(a>1){
                    System.out.print("("+r.data);
                    a--;
                    b++;
                }else{   
                    if(b>0) {  
                        System.out.print(r.data);
                        System.out.print(")");
                        b--;
                    }else {
                        if(r.parent.left == r){
                            System.out.print("(");
                            System.out.print(r.data);
                        }else{
                            System.out.print(r.data);
                            System.out.print(")");
                        }
                        
                    }
                    a--;
                }
            }
        }
    }
}
