package com.linkedlist.demo;

public class DemoLinkedList<T> {
	
	class Node{
		T data;
		Node nextNode;
		
		public Node(T data){
			this.data = data;
		}
	}
	
	private Node node=null;
	private int count = 0;;
	private T[] nodeData;
	
	public void add(T data){
		
		Node newRoot = new Node(data);
		Node tmp = node;
		if(node == null) {
			node = newRoot;
			count++;
		}
		else {
			while(tmp.nextNode != null){
			tmp = tmp.nextNode;
			}
			tmp.nextNode = newRoot;
			count++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void printNode(){

		nodeData = (T[])new Object[count];
		
		if(node == null) {
			throw new NullPointerException("����Ϊ�գ���������ݣ�");
		}
		
		if(node != null) {
			nodeData[0] = node.data;
		}
		
		if(node.nextNode != null) {
			Node tmp = node;
			for(int i=1 ;i<nodeData.length; i++){
				nodeData[i] = tmp.nextNode.data;
				tmp = tmp.nextNode;
			}
		}
		
		
		System.out.println("������Ϊ��"+nodeData.length);
		
		System.out.println("�����׽ڵ�����Ϊ��"+nodeData[0]);
		if(nodeData.length > 2){
			for(int i=1; i<nodeData.length-1; i++){
				System.out.println("�����м�ڵ�����Ϊ��"+nodeData[i]);
			}
			System.out.println("����β�ڵ�����Ϊ��"+nodeData[nodeData.length-1]);
		}
		
		if(nodeData.length == 2){
			System.out.println("����β�ڵ�����Ϊ��"+nodeData[nodeData.length-1]);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		DemoLinkedList<String> d1 = new DemoLinkedList<>();
		d1.add("10");
		d1.add("13");
		d1.add("1");
		d1.add("42");
		d1.printNode();
		
		DemoLinkedList<Long> d2 = new DemoLinkedList<>();
		d2.add(1100000000000L);
		d2.add(2200000000000L);
		d2.add(3300000000000L);
		d2.add(4400000000000L);
		d2.printNode();
		
	}
}
