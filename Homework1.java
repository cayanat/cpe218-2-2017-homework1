
import java.util.Stack;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

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
        //if (args.length > 0) {
            String input = "578+-5*";
            Homework1 h1=new Homework1();
            Node root=h1.constructTree(input);
            h1.inorder(root);
            h1.calculator(input);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MyForm frame = new MyForm(h1,root,input);
                    frame.setVisible(true);
                }
            });
            
            
	//}
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
    DefaultMutableTreeNode inorderII(Node r){
        DefaultMutableTreeNode item = new DefaultMutableTreeNode(r.data);
        if(r!=null && r.left!=null && r.right!=null){
            item.add(inorderII(r.left));
            item.add(inorderII(r.right));
        }
        return item;
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

class MyForm extends JFrame {

	public MyForm( Homework1 h1,Node root,String input) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 249);
		setTitle("ThaiCreate.Com Java GUI Tutorial");
		getContentPane().setLayout(null);
		
		// Tree
		final JTree tree = new JTree(h1.inorderII(root));
		tree.setBounds(28, 11, 209, 131);

		// Scroll Pane
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(28, 11, 209, 169);

		// Image Icon
		/*ImageIcon imageIcon = new ImageIcon(getClass().getResource("open.gif"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(imageIcon);

		tree.setCellRenderer(renderer);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
*/
		// Add Scroll and Tree
		scroll.setViewportView(tree);
		getContentPane().add(scroll);

		// Selected
		tree.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();

						JOptionPane.showMessageDialog(null, selectedNode
								.getUserObject().toString());
					}
				});

	}
}