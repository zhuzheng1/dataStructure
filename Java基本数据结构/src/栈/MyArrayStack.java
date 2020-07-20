package ջ;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayStack<T> {

	private int count; //Ԫ�ظ���
	private int top; //ջ��ָ��
	private T[] arrayStack;
	private static final int STACK_DEFAULT_VALUE = 3;
	
	@SuppressWarnings("unchecked")
	//���÷����ʼ��ջ
	public MyArrayStack(Class<T> type){
		arrayStack = (T[]) Array.newInstance(type, STACK_DEFAULT_VALUE);
		count = 0;
		top = -1;	
	}
	
	public void push(T data){
		int addLength = 1+count;
		if(count >= STACK_DEFAULT_VALUE){
			arrayStack =  Arrays.copyOf(arrayStack, addLength);
			arrayStack[++top] = data;
			count++;
		}
		else {
			arrayStack[++top] = data;
			count++;
		}
		
	}
	
	public T peek(){
		return arrayStack[top];
	}
	
	public T pop(){
		if(top == -1) {
			throw new NullPointerException("ջΪ�գ�");
		}
		else {
			T data = arrayStack[top];
			int addLength = count-1;
			arrayStack =  Arrays.copyOf(arrayStack, addLength);
			count--;
			return data;
		}
	}
	
	public boolean isEmpty(){
		return top == -1;
	}
	
	public boolean isFull(){
		return count == STACK_DEFAULT_VALUE;
	}
	
	public int size(){
		return count;
	}
	
	public void print(){
		for(int i=0; i<arrayStack.length; i++){
			System.out.print(arrayStack[i]+"-->");
		}
	}
	
	public static void main(String[] args) {
		MyArrayStack<String> d1 = new MyArrayStack<>(String.class);
		d1.push("a");
		d1.push("b");
		d1.push("c");
		d1.push("d");
		d1.push("e");
		System.out.println("ջ��Ԫ�ظ�����"+d1.size());
		System.out.println("��ջ֮ǰ��ջ����"+d1.peek());
		System.out.println("������ջ��Ԫ��Ϊ��"+d1.pop());
		System.out.println("��ջ֮���ջ����"+d1.peek());
		System.out.println("ջ��Ԫ�ظ�����"+d1.size());
		System.out.print("��ջ�׵�ջ����Ԫ��Ϊ��");
		d1.print();
	}
}
