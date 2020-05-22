package homewok4;
public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	private Node temp=root;
	public Node findNode(E val) {
		/* IMPLEMENT THIS METHOD! */
		if(temp==null)
			return null;
		if(temp.value==val)
			return root;
		else if(temp.value.compareTo(val)<0)
		{
			temp=temp.rightChild;
			return findNode(val);
		}
		else
		{
			temp=temp.leftChild;
			return findNode(val);
		}
	
	}
	
	
	protected int depth(E val) {

		if(val==null)
			return -1;
		return depth(root,val,0);		
		

	}
	
	private int depth(Node root,E val, int level) {
		// TODO Auto-generated method stub
		if(root==null)
			return -1;
		if(root.value==val)
			return level;
		else if(root.value.compareTo(val)>0)
			return depth(root.leftChild,val,level+1);
		else if(root.value.compareTo(val)<0)
			return depth(root.rightChild,val,level+1);
		return -1;
	}

	// Method #3.
	protected int height(E val) {

		Node curr=findNode(val);
		return height(curr,val,0);
	}


	private int height(BinarySearchTree<E>.Node curr, E val,int level) {
		// TODO Auto-generated method stub
		if(curr.leftChild==null && curr.rightChild==null)
			return level;
		if(curr.leftChild==null)
			return height(curr.rightChild,val,level+1);
		else if(curr.rightChild==null)
			return height(curr.leftChild,val,level+1);
		return height(curr.leftChild,val,level+1);
	}

	// Method #4.
	protected boolean isBalanced(Node n) {

		/* IMPLEMENT THIS METHOD! */
		if(Math.abs(height(n.leftChild.value)-height(n.rightChild.value))==0||Math.abs(height(n.leftChild.value)-height(n.rightChild.value))==1)
			return true;
		
		return false; 
	}
	Node temp1=root;
	// Method #5. .
	public boolean isBalanced() {

		return dfs(root);

	}
	boolean v1=true;
	boolean v2=true;
	boolean v3=true;
	private boolean dfs(BinarySearchTree<E>.Node root2) {
		// TODO Auto-generated method stub
		if(!(isBalanced(root2)))
			return false;
		v1=dfs(root2.leftChild);
		if(!v1)
			return v1;	
		v2=dfs(root2);
		if(!(v2))
			return v2;
		v3=dfs(root2.rightChild);
		if(!v3)
			return v3;
		return true;
	}

}

