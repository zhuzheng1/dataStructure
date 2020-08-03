package 二叉树;

public class SortBinaryTree<T> {

	class Node {
		Comparable<T> data;
		Node leftNode = null;
		Node rightNode = null;
		Node parentNode = null;
		public Node(Comparable<T> data) {
			this.data = data;
		}
		
		public Node selectBinaryNode(T data) {
			if(this.data.compareTo(data) == 0) {
				return this;
			}
			else {  //判断大小
				if(this.data.compareTo(data) < 0) {  //比根节点大
					if(this.rightNode != null) {
						return this.rightNode.selectBinaryNode(data);
					}
					else {
						return null;
					}
				}
				else {
					if(this.leftNode != null) {
						return this.leftNode.selectBinaryNode(data);
					}
					else {
						return null;
					}
				}
			}
		}
	}
	
	private int count = 0;
	private Node root = null;
	
	/*
	 * 增加节点
	 */
	public void add(T data) {
		if(data == null) {
			throw new NullPointerException("排序二叉树不能存储空值");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("要保存的数据对象所在的类没有实现java.lang.Comparable接口");
		}
		Node newNode = new Node((Comparable<T>) data);
		if(root == null) {
			this.root = newNode;
			count++;
		}
		else {
			Node currentNode = root;
			int oldCount = count;
			while (oldCount == count) {
				if(currentNode.data.compareTo(data) <= 0) {  //比根节点大
					if(currentNode.rightNode == null) {
						currentNode.rightNode = newNode;
						newNode.parentNode = currentNode;
						count++;
					}
					else {
						currentNode = currentNode.rightNode;
					}
				}
				else {  //比根节点小
					if(currentNode.leftNode == null) {
						currentNode.leftNode = newNode;
						newNode.parentNode = currentNode;
						count++;
					}
					else {
						currentNode = currentNode.leftNode;
					}
				}
			}
		}
			
	}
	
	/*
	 * 先序遍历
	 */
	public void PreOrderTraversal(Node node) {
		if(node == null) {
			return;
		}
		System.out.print(node.data+" ");
		PreOrderTraversal(node.leftNode);
		PreOrderTraversal(node.rightNode);
	}
	
	public int size() {
		return count;
	}
	
	/*
	 * 查找节点
	 */
	public boolean selectBinary(T data) {
		if(data == null) {
			throw new NullPointerException("排序二叉树不能存储空值");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("要保存的数据对象所在的类没有实现java.lang.Comparable接口");
		}
		if(this.size() == 0) {
			return false;
		}
		return this.root.selectBinaryNode(data) != null;
	}
	
	/*
	 * 删除节点
	 */
	public void remove(T data) {
		if(data == null) {
			throw new NullPointerException("排序二叉树不能存储空值");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("要保存的数据对象所在的类没有实现java.lang.Comparable接口");
		}
		if(selectBinary(data)) {  //节点是否存在
			if(this.root.data.compareTo(data) == 0) {
				Node removeNode = this.removeNode(data);
				if(removeNode != null) {
					this.root = removeNode;
				}
			}
			else {
				this.removeNode(data);
				System.out.println("删除成功!");
			}
		}
		else {
			throw new NullPointerException("要删除的数值不存在！");
		}
		count--;
	}
	
	private Node removeNode(T data) {
		Node deleteNode = this.root.selectBinaryNode(data);
		Node moveNode = null;  //移动节点
		
		//情况一：被删节点含有两个孩子节点
		if(deleteNode.leftNode != null && deleteNode.rightNode != null) {
			//找到被删除节点的右节点的最左节点
			moveNode = replaceDoubleChildNode(deleteNode.rightNode);
			
			if(deleteNode.rightNode != moveNode) {  //如果被删除节点的右节点存在最左节点
				if(moveNode.rightNode != null) {  //如果存在右节点
					moveNode.parentNode.leftNode = moveNode.rightNode;
					moveNode.rightNode.parentNode = moveNode.parentNode;
					
				}
				else {  //如果不存在右节点
					moveNode.parentNode.leftNode = null;
					moveNode.parentNode = null;
				}
				if(deleteNode.parentNode != null) {  //父节点不为空
					if(deleteNode.parentNode.data.compareTo(data) <= 0) {  //父节点小于等于被删除节点
						
						deleteNode.parentNode.rightNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
						moveNode.rightNode = deleteNode.rightNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						deleteNode.rightNode.parentNode = moveNode;
					}
					else {  //父节点大于被删除节点
						
						deleteNode.parentNode.leftNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
						moveNode.rightNode = deleteNode.rightNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						deleteNode.rightNode.parentNode = moveNode;
					}
				}
				else {  //父节点为空
					
					moveNode.leftNode = deleteNode.leftNode;
					moveNode.rightNode = deleteNode.rightNode;
					moveNode.parentNode = null;
					deleteNode.rightNode.parentNode = moveNode;
					deleteNode.leftNode.parentNode = moveNode;
				}
			}
			else { //如果被删除节点的右节点不存在最左节点
				if(moveNode.rightNode != null) {  //如果存在右节点
					
					moveNode.parentNode.rightNode = moveNode.rightNode;
					moveNode.rightNode.parentNode = moveNode.parentNode;
				}
				if(deleteNode.parentNode != null) {  //父节点不为空
					if(deleteNode.parentNode.data.compareTo(data) <= 0) {  //父节点小于等于被删除节点
						
						deleteNode.parentNode.rightNode = moveNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
					}
					else {  //父节点大于被删除节点
						
						deleteNode.parentNode.leftNode = moveNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
					}
				}
				else {  //父节点为空,此处为删除根节点
					
					moveNode.parentNode = null;
					deleteNode.leftNode.parentNode = moveNode;
					moveNode.leftNode = deleteNode.leftNode;
				}
			}
		}
		
		
		//情况二：被删除节点存在一个孩子节点
		if((deleteNode.leftNode != null && deleteNode.rightNode == null) || 
				(deleteNode.leftNode == null && deleteNode.rightNode != null)) {
				if(deleteNode.leftNode != null) {
					moveNode = deleteNode.leftNode;
				}
				else {
					moveNode = deleteNode.rightNode;
				}
				if(deleteNode.parentNode != null) {
					if(this.root.data.compareTo(data) <= 0) { //比根节点大
						deleteNode.parentNode.rightNode = moveNode;
					}
					else {  //比根节点小
						deleteNode.parentNode.leftNode = moveNode;
						
					}
				}
				moveNode.parentNode = deleteNode.parentNode;
			}
		
		
		//情况三：被删除节点没有左右孩子节点
		if(deleteNode.leftNode == null && deleteNode.rightNode == null) {
			if(deleteNode.parentNode != null) {
				if(this.root.data.compareTo(data) <= 0) {  //比根节点大
					deleteNode.parentNode.rightNode = null;
				}
				else {  //比根节点小
					deleteNode.parentNode.leftNode = null;
				}
			}
			else {
				this.root = null;
			}
		}
		
		return moveNode;
	}
	
	public Node replaceDoubleChildNode(Node node) {
		if(node.leftNode == null) {
			return node;
		}
		
		return replaceDoubleChildNode(node.leftNode);
	}
	

	
	
	public static void main(String[] args) throws Exception{
		SortBinaryTree<Integer> d1 = new SortBinaryTree<>();
		d1.add(11);
		d1.add(13);
		d1.add(1);
		d1.add(4);
		d1.add(0);
		d1.add(12);
		d1.add(14);
		d1.add(15);
		
		System.out.println("先序遍历：");
		d1.PreOrderTraversal(d1.root);
		System.out.println("\n------------------------------");
		
		System.out.println("二叉树中是否存在此数据："+d1.selectBinary(11));
		System.out.println("------------------------------");
		
		System.out.println("删除之前的节点个数："+d1.size());
		d1.remove(15);
		System.out.println("删除之后的节点个数："+d1.size()+"\n------------------------------");

		System.out.println("先序遍历：");
		d1.PreOrderTraversal(d1.root);
		System.out.println("\n------------------------------");
	}
	
}
