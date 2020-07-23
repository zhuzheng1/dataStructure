package 队列;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayQueue<T> {

	private T[] dataArray;
	private int count = 0 ;
	private static final int INITIALIZATION_VALUE = 3;
	private String dataArrayString = "";

	
	@SuppressWarnings("unchecked")
	public ArrayQueue(Class<T> type) {
		dataArray = (T[])Array.newInstance(type, INITIALIZATION_VALUE);
	}
	
	public void add(T data) {
		if(count >= INITIALIZATION_VALUE) {
			int count1 = count+1;
			dataArray = (T[])Arrays.copyOf(dataArray, count1);
			dataArray[count++] = data;
		}
		else {
			dataArray[count++] = data;
		}
	}
	
	public T peek() {
		T dataT = dataArray[0];
		return dataT;
	}
	
	public T poll() {
		if(dataArray[0] == null) {
			return null;
		}
		else {
			T dataT = dataArray[0];
			for(int i=1; i<dataArray.length; i++) {
				dataArray[i-1] = dataArray[i];
			}//
			count--;
			return dataT;
		}
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public String print() {
		if(count == 0) {
			return null;
		}
		else {
			for(int i=0; i<count; i++) {
			dataArrayString = dataArrayString+dataArray[i]+"<--";
			}
			return dataArrayString;
		}
	}
	
	public static void main(String[] args) {

		ArrayQueue<Integer> d1 = new ArrayQueue<Integer>(Integer.class);
		d1.add(1111);
		d1.add(2222);
		d1.add(3333);
		d1.add(4444);
		System.out.println("队列中元素个数："+d1.size());
		System.out.println("出队之前的队顶："+d1.peek());
		System.out.println("出队元素为："+d1.poll());
		System.out.println("出队之后的队顶："+d1.peek());
		System.out.println("从队首到队尾的元素为;"+d1.print());
	}

}
