package 堆;

import java.util.Arrays;

/*
 * 这是个小顶堆
 */

public class Heap {

    private int count = 0;
    private int[] array;
    private int capacity;
    
    public Heap(int capacity) {
        this.capacity = capacity;
        array = new int[this.capacity];
    }
    
    public int getLeftChildIndex(int currentParentIndex) {
        return (2*currentParentIndex)+1;
    }
    
    public int getRightChildIndex(int currentParentIndex) {
        return (2*currentParentIndex)+2;
    }
    
    public int getParentIndex(int currentChildIndex) {
        return (currentChildIndex-1)/2;
    }
    
    public boolean hasLeftChild(int currentIndex) {
        return getLeftChildIndex(currentIndex) < count;
    }
    
    public boolean hasRightChild(int currentIndex) {
        return getRightChildIndex(currentIndex) < count;
    }
    
    public boolean hasParent(int currentIndex) {
        return getParentIndex(currentIndex) >= 0;
    }
    
    public int leftChildData(int currentIndex) {
        
        return array[getLeftChildIndex(currentIndex)];
    }
    
    public int rightChildData(int currentIndex) {
        return array[getRightChildIndex(currentIndex)];
    }
    
    public int parentData(int currentIndex) {
        return array[getParentIndex(currentIndex)];
    }
    
    public void add(int data) {
        if(data == 0) {
            throw new NullPointerException("不能存储一个空值！");
        }
        
        if(count == array.length) {
            int addCapacity = array.length+1;
            array = Arrays.copyOf(array, addCapacity);
        }
        if(count == 0) {
            array[count] = data;
            count++;
        }
        else {
            array[count++] = data;
            compareSize();
        }
    }
    
    public void compareSize() {
        int index = count-1;
        while(array[index] < parentData(index) && hasParent(index)) {
            int dataReplace = array[getParentIndex(index)];
            array[getParentIndex(index)] = array[index];
            array[index] = dataReplace;
            index = getParentIndex(index);
        }
    }
    
    public int size() {
        return count;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public int peek() {
        return array[0];
    }
    
    public int poll() {
        int dataT = array[0];
        array[0] = array[count-1];
        count--;
        array = Arrays.copyOf(array, count);
        int index = 0;
        while (hasLeftChild(index) && (array[index] > array[getLeftChildIndex(index)])) {
            int dataReplace = array[index];
            array[index] = array[getLeftChildIndex(index)];
            array[getLeftChildIndex(index)] = dataReplace;
            index = getLeftChildIndex(index);
        }
        while(hasRightChild(index) && (array[index] > array[getRightChildIndex(index)])) {
            int dataRightReplace = array[index];
            array[index] = array[getRightChildIndex(index)];
            array[getRightChildIndex(index)] = dataRightReplace;
            index = getRightChildIndex(index);
                while (hasLeftChild(index) && (array[index] > array[getLeftChildIndex(index)])) {
                int dataLeftReplace = array[index];
                array[index] = array[getLeftChildIndex(index)];
                array[getLeftChildIndex(index)] = dataLeftReplace;
                index = getLeftChildIndex(index);
                }
        }
        return dataT;
    }
    
    
    public static void main(String[] args) {
        Heap heap = new Heap(4);
        heap.add(11);
        heap.add(22);
        heap.add(33);
        heap.add(10);
        heap.add(20);
        heap.add(50);
        heap.add(12);
        heap.add(3);
        heap.add(19);
        heap.add(6);
        for(int i=0; i<heap.array.length; i++) {
            System.out.println("堆中元素从树根到树顶："+heap.array[i]);
        }
        System.out.println("堆中弹出的树根为："+heap.poll());
        System.out.println("根节点元素为："+heap.peek());
        for(int i=0; i<heap.array.length; i++) {
            System.out.println("堆中元素从树根到树顶："+heap.array[i]);
        }
    }
}
