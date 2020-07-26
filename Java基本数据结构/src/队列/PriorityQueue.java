package ����;

import java.util.NoSuchElementException;

public class PriorityQueue<T> {
    
    class Node {
        T data;
        int priority;  //���ȼ�
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
                if(tmp.priority > newNode.priority) { //�½ڵ����ȼ�����ԭ���ڵ�
                    newNode.nextNode = node;
                    this.node = newNode;
                    count++;
                }
                else {  //�½ڵ����ȼ�����ԭ���ڵ�
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
            throw new NoSuchElementException("������û��Ԫ�ش��ڣ�");
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
        System.out.println("����ǰ�ĶӶ���"+d1.peek());
        System.out.println("����Ԫ��Ϊ��"+d1.poll());
//        System.out.println("����Ԫ��Ϊ��"+d1.poll());
//        System.out.println("����Ԫ��Ϊ��"+d1.poll());
//        System.out.println("����Ԫ��Ϊ��"+d1.poll());
//        System.out.println("����Ԫ��Ϊ��"+d1.poll());
        System.out.println("���Ӻ�ĶӶ���"+d1.peek());
        System.out.println("�����Ƿ���Ԫ�أ�"+d1.isEmpty());
    }

}
