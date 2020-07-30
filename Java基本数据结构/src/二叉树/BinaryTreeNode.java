package 二叉树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BinaryTreeNode {

    private int[] array;
    private int count = 0;  //节点的个数
    private static List<Node> nodeList = new LinkedList<Node>();  //用链表实现
    private static final int DEFAULT_ARRAY_CAPACITY_VALUE = 3; //数组默认空间
    private int index = 0;  //nodeList的查找下标
    private boolean existence = false;  //删除节点的判断条件
    
    
    class Node {  //创建节点类
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
        for(int i=0; i<array.length; i++) {  //为数组的默认空间设置初始值为极大值
            array[i] = Integer.MAX_VALUE;
        }
    }
    
    public void add(int data) {
        if(count >= DEFAULT_ARRAY_CAPACITY_VALUE) {  //为数组扩容
            int newCount = count+1;
            array = Arrays.copyOf(array, newCount);
        }
          
          array[count++] = data;
          nodeList.add(new Node(array[count-1]));
          creatBinaryConnection(index,count);  //此处函数只调用成员变量index
    }
    
    public void creatBinaryConnection(int index, int arrayLength) { //创建二叉树关系
        
        if(nodeList.get(index).lNode != null && nodeList.get(index).rNode != null) {
            ++index;
            this.index = index;  //此处修改成员变量index
        }
        if(array[1] == Integer.MAX_VALUE) {  //此时只有根节点
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
     * 先序遍历
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
     * 中序遍历
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
     * 后序遍历
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
     * 删除节点
     * 节点数据不存在则报错，无法删除
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
                //考虑最后一个父节点存不存在右孩子节点
                int lastParentNodeIndex = nodeList.size()/2-1;
                nodeList.get(lastParentNodeIndex).lNode = 
                                                    nodeList.get(lastParentNodeIndex*2+1);
                if(nodeList.size()%2 != 0) {
                    nodeList.get(lastParentNodeIndex).rNode = 
                                                    nodeList.get(lastParentNodeIndex*2+2);
                }
        }
        else {
            throw new NullPointerException("二叉树中不存在此数值，请输入正确值");
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
        
        //获取根节点
        Node root = nodeList.get(0);
        
        System.out.println("先序遍历：");
        demo.PreOrderTraversal(root);
        System.out.println();
        
        System.out.println("中序遍历：");
        demo.inOrderTraversal(root);
        System.out.println();
        
        System.out.println("后序遍历：");
        demo.postOrderTraversal(root);
        System.out.println();
    }
}
