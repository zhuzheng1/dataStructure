package 栈;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayStack<T> {

	private int count; //元素个数
	private int top; //栈顶指针
	private T[] arrayStack;
	private static final int STACK_DEFAULT_VALUE = 3;
	
	@SuppressWarnings("unchecked")
	//利用反射初始化栈
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
			throw new NullPointerException("栈为空！");
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
		System.out.println("栈中元素个数："+d1.size());
		System.out.println("弹栈之前的栈顶："+d1.peek());
		System.out.println("弹出的栈顶元素为："+d1.pop());
		System.out.println("弹栈之后的栈顶："+d1.peek());
		System.out.println("栈中元素个数："+d1.size());
		System.out.print("从栈底到栈顶的元素为：");
		d1.print();
	}
}
