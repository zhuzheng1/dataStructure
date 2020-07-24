package 队列;

public class NodeQueue<T> {

    class Node {
        T data;
        Node nextNode;
        public Node(T data) {
            this.data = data;
        }
    }
    
    private Node node = null;
    private int count = 0;
    private String nodeData = "";
    
    public void add(T data) {
        Node nextNode = new Node(data);
        if(node == null) {
            node = nextNode;
            count++;
        }
        else {
            Node tmp = node;
            while(tmp.nextNode != null) {
                tmp = tmp.nextNode;
            }
            tmp.nextNode = nextNode;
            count++;
        }
    }
    
    public T peek() {
        if(node == null) {
            return null;
        }
        else {
            return node.data;
        }
    }
    
    public int size() {
        return count;
    }
    
    public T poll() {
        if(node == null) {
            return null;
        }
        else {
            T dataT = node.data;
            Node newNode = node.nextNode;
            node.nextNode = null;
            node = null;
            node = newNode;
            newNode = null;
            return dataT;
        }
    }
    
    public String print() {
        if(node == null) {
            return null;
        }
        else {
            nodeData = nodeData + node.data;
            Node tmp = node;
            while(tmp.nextNode != null) {
                tmp = tmp.nextNode;
                nodeData = nodeData + " <-- " + tmp.data;
            }
            return nodeData;
        }
        
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public static void main(String[] args) {
        NodeQueue<Integer> d1 = new NodeQueue<Integer>();
        d1.add(1111);
        d1.add(2222);
        d1.add(3333);
        d1.add(4444);
        System.out.println("队列中元素个数："+d1.size());
        System.out.println("出队之前的队顶："+d1.peek());
        System.out.println("出队元素为："+d1.poll());
        System.out.println("出队元素为："+d1.poll());
        System.out.println("出队之后的队顶："+d1.peek());
        System.out.println("从队首到队尾的元素为:  "+d1.print());
        System.out.println("队列是否为空：  "+d1.isEmpty());
    }

}
