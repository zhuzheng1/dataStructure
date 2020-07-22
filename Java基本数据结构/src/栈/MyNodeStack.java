package 栈;

public class MyNodeStack<T> {

	class Node {
		T data;
		Node nextNode;
		Node backNode;
		public Node(T data) {
			this.data = data;
		}
	}
	
	Node node = null;
	private int count = 0;
	private String dataString = "";
	public void push(T data) {
		Node nextNode = new Node(data);
		if(node == null) {
			node = nextNode;
			node.backNode = null;
			count++;
		}
		else {
			Node tmp = node;
			while(tmp.nextNode != null) {
				tmp = tmp.nextNode;
			}
			tmp.nextNode = nextNode;
			tmp.nextNode.backNode = tmp;
			count++;
		}
		
	}
	
	public int size() {
		return count;
	}
	
	public T peek() {
		Node tmp = node;
		if(tmp != null) {
			while(tmp.nextNode != null) {
				tmp = tmp.nextNode;
			}
			return tmp.data;
		}
		else {
			return null;
		}
		
	}
	
	public T pop() {
		Node tmp = node;
		if(tmp == null) {
			return null;
		}
		else {
			while(tmp.nextNode != null) {
				tmp = tmp.nextNode;
			}
			T dataT = tmp.data;
			if(count > 1) {
				tmp.backNode.nextNode = null;
				tmp.backNode = null;
				tmp = null;
			}
			if(count == 1) {
				node = null;
			}	
			count--;
			return dataT;
		}
	}
	
	public String print() {
		Node tmp = node;
		if (tmp == null) {
			return null;
		}
		else {
				dataString = dataString+tmp.data;
				while (tmp.nextNode != null) {
					dataString = dataString+"-->"+tmp.nextNode.data;
					tmp = tmp.nextNode;
				}
				return dataString;
			}
	}
	
	public boolean isEmpty() {
		return count == 0;
	} 
	
	public static void main(String[] args) {

		MyNodeStack<Integer> d1 = new MyNodeStack<Integer>();
		d1.push(11);
		d1.push(22);
		d1.push(33);
		d1.push(44);
		System.out.println("栈中元素个数："+d1.size());
		System.out.println("弹栈之前的栈顶："+d1.peek());
		//System.out.println("弹出的栈顶元素为："+d1.pop());
		//System.out.println("弹出的栈顶元素为："+d1.pop());
		//System.out.println("弹出的栈顶元素为："+d1.pop());
		System.out.println("弹出的栈顶元素为："+d1.pop());
		System.out.println("弹栈之后的栈顶："+d1.peek());
		System.out.println("栈中元素个数："+d1.size());
		System.out.print("从栈底到栈顶的元素为："+d1.print()+"\n");
		System.out.println("栈是否为空："+d1.isEmpty());
		
		
		System.out.println("\n--------------------------------");
		
		MyNodeStack<String> d2 = new MyNodeStack<>();
		d2.push("aa");
		d2.push("bb");
		d2.push("cc");
		d2.push("dd");
		System.out.println("栈中元素个数："+d2.size());
		System.out.println("弹栈之前的栈顶："+d2.peek());
		System.out.println("弹出的栈顶元素为："+d2.pop());
		System.out.println("弹出的栈顶元素为："+d2.pop());
		System.out.println("弹出的栈顶元素为："+d2.pop());
		System.out.println("弹出的栈顶元素为："+d2.pop());
		System.out.println("弹栈之后的栈顶："+d2.peek());
		System.out.println("栈中元素个数："+d2.size());
		System.out.print("从栈底到栈顶的元素为："+d2.print()+"\n");
		
		System.out.println("栈是否为空："+d2.isEmpty());
		
	}

}
