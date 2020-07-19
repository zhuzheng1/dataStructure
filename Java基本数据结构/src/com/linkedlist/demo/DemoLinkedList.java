package com.linkedlist.demo;
public class DemoLinkedList {
	
	class Node{
		String data;
		Node nextNode;
		
		public Node(String data){
			this.data = data;
		}
	}
	
	private Node node=null;
	private int count = 0;;
	private String[] nodeData;
	
	public void add(String data){
		Node newRoot = new Node(data);
		Node tmp = node;
		if(node == null) {
			node = newRoot;
			count++;
		}
		else {
			while(tmp.nextNode != null){
			tmp = tmp.nextNode;
			count++;
			}
			tmp.nextNode = newRoot;
		}
	}
	
	public void printNode(){
		
		nodeData = new String[count+1];
		
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
		DemoLinkedList d1 = new DemoLinkedList();
		d1.add("10");
		d1.add("13");
		d1.add("1");
//		d1.add("42");
		d1.printNode();
		
	}
}
