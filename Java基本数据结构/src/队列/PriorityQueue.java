package 队列;

import java.util.NoSuchElementException;

public class PriorityQueue<T> {
    
    class Node {
        T data;
        int priority;  //优先级
        Node nextNode;
        public Node(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }
    
    Node node = null;
    private int count = 0;
    
    public void add(T data, int priority) {
        
        Node newNode = new Node(data, priority);
        if(node == null) {
            node = newNode;
            count++;
        }
        
        else {
                Node tmp = node;
                if(tmp.priority > newNode.priority) { //新节点优先级高于原根节点
                    newNode.nextNode = node;
                    this.node = newNode;
                    count++;
                }
                else {  //新节点优先级低于原根节点
                    while(tmp.nextNode != null && tmp.nextNode.priority <= newNode.priority) {
                        tmp = tmp.nextNode;
                    }
                    newNode.nextNode = tmp.nextNode;
                    tmp.nextNode = newNode;
                    count++;
                }
        }
        
    }
    
    public T peek() {
        if(node == null) {
            return null;
        }
        return node.data;
    }
    
    public T poll() {
        
        if(node == null) {
            throw new NoSuchElementException("队列中没有元素存在！");
        }
        else {
            T dataT = node.data;
            node = node.nextNode;
            count--;
            return dataT;
        } 
    }
    
    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) throws Exception{

        PriorityQueue<Integer> d1 = new PriorityQueue<>();
        d1.add(1111, 3);
        d1.add(2222, 2);
        d1.add(3333, 4);
        d1.add(4444, 1);
        System.out.println("出队前的队顶："+d1.peek());
        System.out.println("出队元素为："+d1.poll());
//        System.out.println("出队元素为："+d1.poll());
//        System.out.println("出队元素为："+d1.poll());
//        System.out.println("出队元素为："+d1.poll());
//        System.out.println("出队元素为："+d1.poll());
        System.out.println("出队后的队顶："+d1.peek());
        System.out.println("队中是否有元素："+d1.isEmpty());
    }

}
