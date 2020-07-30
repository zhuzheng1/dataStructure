package ������;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BinaryTreeNode {

    private int[] array;
    private int count = 0;  //�ڵ�ĸ���
    private static List<Node> nodeList = new LinkedList<Node>();  //������ʵ��
    private static final int DEFAULT_ARRAY_CAPACITY_VALUE = 3; //����Ĭ�Ͽռ�
    private int index = 0;  //nodeList�Ĳ����±�
    private boolean existence = false;  //ɾ���ڵ���ж�����
    
    
    class Node {  //�����ڵ���
        Node lNode;
        Node rNode;
        Node pNode = null;
        int data;
        public Node(int data) {
            this.data = data;
        }
    }
    
    public BinaryTreeNode() {
        this.array = new int[DEFAULT_ARRAY_CAPACITY_VALUE];
        for(int i=0; i<array.length; i++) {  //Ϊ�����Ĭ�Ͽռ����ó�ʼֵΪ����ֵ
            array[i] = Integer.MAX_VALUE;
        }
    }
    
    public void add(int data) {
        if(count >= DEFAULT_ARRAY_CAPACITY_VALUE) {  //Ϊ��������
            int newCount = count+1;
            array = Arrays.copyOf(array, newCount);
        }
          
          array[count++] = data;
          nodeList.add(new Node(array[count-1]));
          creatBinaryConnection(index,count);  //�˴�����ֻ���ó�Ա����index
    }
    
    public void creatBinaryConnection(int index, int arrayLength) { //������������ϵ
        
        if(nodeList.get(index).lNode != null && nodeList.get(index).rNode != null) {
            ++index;
            this.index = index;  //�˴��޸ĳ�Ա����index
        }
        if(array[1] == Integer.MAX_VALUE) {  //��ʱֻ�и��ڵ�
            return;
        }
        if(nodeList.get(index).lNode == null && (index*2)+1 <= count-1) {
            nodeList.get(index).lNode = nodeList.get((index * 2)+1);
        }
        if(nodeList.get(index).rNode == null && (index*2)+2 <= count-1) {
            nodeList.get(index).rNode = nodeList.get((index*2)+2);
        }
        if(index != 0) {
            nodeList.get(index).pNode = nodeList.get((index-1)/2);
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
        PreOrderTraversal(node.lNode);
        PreOrderTraversal(node.rNode);
    }
    
    /*
     * �������
     */
    public void inOrderTraversal(Node node) {
        if(node == null) {
            return;
        }
        inOrderTraversal(node.lNode);
        System.out.print(node.data+" ");
        inOrderTraversal(node.rNode);
    }
    
    /*
     * �������
     */
    public void postOrderTraversal(Node node) {
        if(node == null) {
            return;
        }
        postOrderTraversal(node.lNode);
        postOrderTraversal(node.rNode);
        System.out.print(node.data+" ");
    }
    
    public int size() {
        return count;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    /*
     * ɾ���ڵ�
     * �ڵ����ݲ������򱨴��޷�ɾ��
     */
    public void remove(int data) {
        for (int i = 0; i < nodeList.size(); i++) {
            if(data == nodeList.get(i).data) {
                existence = true;
                nodeList.remove(i);
                break;
            }
        }
        if(existence) {
                for(int index1=0; index1<(nodeList.size()/2)-1; index1++) {
                    nodeList.get(index1).lNode = nodeList.get(index1*2+1);
                    nodeList.get(index1).rNode = nodeList.get(index1*2+2);
                }
                for(int index1=1; index1<nodeList.size(); index1++) {
                    nodeList.get(index1).pNode = nodeList.get((index1-1)/2);
                }
                //�������һ�����ڵ�治�����Һ��ӽڵ�
                int lastParentNodeIndex = nodeList.size()/2-1;
                nodeList.get(lastParentNodeIndex).lNode = 
                                                    nodeList.get(lastParentNodeIndex*2+1);
                if(nodeList.size()%2 != 0) {
                    nodeList.get(lastParentNodeIndex).rNode = 
                                                    nodeList.get(lastParentNodeIndex*2+2);
                }
        }
        else {
            throw new NullPointerException("�������в����ڴ���ֵ����������ȷֵ");
        }
    }
    
    public static void main(String[] args) {
        BinaryTreeNode demo = new BinaryTreeNode();
        demo.add(12);
        demo.add(2);
        demo.add(1);
        demo.add(23);
        demo.add(5);
        demo.add(8);
        demo.add(66);
        
          demo.remove(12);
//        demo.remove(23);
//        demo.remove(99);
        
        //��ȡ���ڵ�
        Node root = nodeList.get(0);
        
        System.out.println("���������");
        demo.PreOrderTraversal(root);
        System.out.println();
        
        System.out.println("���������");
        demo.inOrderTraversal(root);
        System.out.println();
        
        System.out.println("���������");
        demo.postOrderTraversal(root);
        System.out.println();
    }
}
