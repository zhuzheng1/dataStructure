package ������;

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
			else {  //�жϴ�С
				if(this.data.compareTo(data) < 0) {  //�ȸ��ڵ��
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
	 * ���ӽڵ�
	 */
	public void add(T data) {
		if(data == null) {
			throw new NullPointerException("������������ܴ洢��ֵ");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("Ҫ��������ݶ������ڵ���û��ʵ��java.lang.Comparable�ӿ�");
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
				if(currentNode.data.compareTo(data) <= 0) {  //�ȸ��ڵ��
					if(currentNode.rightNode == null) {
						currentNode.rightNode = newNode;
						newNode.parentNode = currentNode;
						count++;
					}
					else {
						currentNode = currentNode.rightNode;
					}
				}
				else {  //�ȸ��ڵ�С
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
	 * �������
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
	 * ���ҽڵ�
	 */
	public boolean selectBinary(T data) {
		if(data == null) {
			throw new NullPointerException("������������ܴ洢��ֵ");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("Ҫ��������ݶ������ڵ���û��ʵ��java.lang.Comparable�ӿ�");
		}
		if(this.size() == 0) {
			return false;
		}
		return this.root.selectBinaryNode(data) != null;
	}
	
	/*
	 * ɾ���ڵ�
	 */
	public void remove(T data) {
		if(data == null) {
			throw new NullPointerException("������������ܴ洢��ֵ");
		}
		if(!(data instanceof Comparable)) {
			throw new ClassCastException("Ҫ��������ݶ������ڵ���û��ʵ��java.lang.Comparable�ӿ�");
		}
		if(selectBinary(data)) {  //�ڵ��Ƿ����
			if(this.root.data.compareTo(data) == 0) {
				Node removeNode = this.removeNode(data);
				if(removeNode != null) {
					this.root = removeNode;
				}
			}
			else {
				this.removeNode(data);
				System.out.println("ɾ���ɹ�!");
			}
		}
		else {
			throw new NullPointerException("Ҫɾ������ֵ�����ڣ�");
		}
		count--;
	}
	
	private Node removeNode(T data) {
		Node deleteNode = this.root.selectBinaryNode(data);
		Node moveNode = null;  //�ƶ��ڵ�
		
		//���һ����ɾ�ڵ㺬���������ӽڵ�
		if(deleteNode.leftNode != null && deleteNode.rightNode != null) {
			//�ҵ���ɾ���ڵ���ҽڵ������ڵ�
			moveNode = replaceDoubleChildNode(deleteNode.rightNode);
			
			if(deleteNode.rightNode != moveNode) {  //�����ɾ���ڵ���ҽڵ��������ڵ�
				if(moveNode.rightNode != null) {  //��������ҽڵ�
					moveNode.parentNode.leftNode = moveNode.rightNode;
					moveNode.rightNode.parentNode = moveNode.parentNode;
					
				}
				else {  //����������ҽڵ�
					moveNode.parentNode.leftNode = null;
					moveNode.parentNode = null;
				}
				if(deleteNode.parentNode != null) {  //���ڵ㲻Ϊ��
					if(deleteNode.parentNode.data.compareTo(data) <= 0) {  //���ڵ�С�ڵ��ڱ�ɾ���ڵ�
						
						deleteNode.parentNode.rightNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
						moveNode.rightNode = deleteNode.rightNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						deleteNode.rightNode.parentNode = moveNode;
					}
					else {  //���ڵ���ڱ�ɾ���ڵ�
						
						deleteNode.parentNode.leftNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
						moveNode.rightNode = deleteNode.rightNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						deleteNode.rightNode.parentNode = moveNode;
					}
				}
				else {  //���ڵ�Ϊ��
					
					moveNode.leftNode = deleteNode.leftNode;
					moveNode.rightNode = deleteNode.rightNode;
					moveNode.parentNode = null;
					deleteNode.rightNode.parentNode = moveNode;
					deleteNode.leftNode.parentNode = moveNode;
				}
			}
			else { //�����ɾ���ڵ���ҽڵ㲻��������ڵ�
				if(moveNode.rightNode != null) {  //��������ҽڵ�
					
					moveNode.parentNode.rightNode = moveNode.rightNode;
					moveNode.rightNode.parentNode = moveNode.parentNode;
				}
				if(deleteNode.parentNode != null) {  //���ڵ㲻Ϊ��
					if(deleteNode.parentNode.data.compareTo(data) <= 0) {  //���ڵ�С�ڵ��ڱ�ɾ���ڵ�
						
						deleteNode.parentNode.rightNode = moveNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
					}
					else {  //���ڵ���ڱ�ɾ���ڵ�
						
						deleteNode.parentNode.leftNode = moveNode;
						moveNode.parentNode = deleteNode.parentNode;
						deleteNode.leftNode.parentNode = moveNode;
						moveNode.leftNode = deleteNode.leftNode;
					}
				}
				else {  //���ڵ�Ϊ��,�˴�Ϊɾ�����ڵ�
					
					moveNode.parentNode = null;
					deleteNode.leftNode.parentNode = moveNode;
					moveNode.leftNode = deleteNode.leftNode;
				}
			}
		}
		
		
		//���������ɾ���ڵ����һ�����ӽڵ�
		if((deleteNode.leftNode != null && deleteNode.rightNode == null) || 
				(deleteNode.leftNode == null && deleteNode.rightNode != null)) {
				if(deleteNode.leftNode != null) {
					moveNode = deleteNode.leftNode;
				}
				else {
					moveNode = deleteNode.rightNode;
				}
				if(deleteNode.parentNode != null) {
					if(this.root.data.compareTo(data) <= 0) { //�ȸ��ڵ��
						deleteNode.parentNode.rightNode = moveNode;
					}
					else {  //�ȸ��ڵ�С
						deleteNode.parentNode.leftNode = moveNode;
						
					}
				}
				moveNode.parentNode = deleteNode.parentNode;
			}
		
		
		//���������ɾ���ڵ�û�����Һ��ӽڵ�
		if(deleteNode.leftNode == null && deleteNode.rightNode == null) {
			if(deleteNode.parentNode != null) {
				if(this.root.data.compareTo(data) <= 0) {  //�ȸ��ڵ��
					deleteNode.parentNode.rightNode = null;
				}
				else {  //�ȸ��ڵ�С
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
		
		System.out.println("���������");
		d1.PreOrderTraversal(d1.root);
		System.out.println("\n------------------------------");
		
		System.out.println("���������Ƿ���ڴ����ݣ�"+d1.selectBinary(11));
		System.out.println("------------------------------");
		
		System.out.println("ɾ��֮ǰ�Ľڵ������"+d1.size());
		d1.remove(15);
		System.out.println("ɾ��֮��Ľڵ������"+d1.size()+"\n------------------------------");

		System.out.println("���������");
		d1.PreOrderTraversal(d1.root);
		System.out.println("\n------------------------------");
	}
	
}
