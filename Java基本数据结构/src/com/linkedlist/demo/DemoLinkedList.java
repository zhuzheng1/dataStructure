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
			throw new NullPointerException("链表为空，请添加数据！");
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
		
		
		System.out.println("链表长度为："+nodeData.length);
		
		System.out.println("链表首节点数据为："+nodeData[0]);
		if(nodeData.length > 2){
			for(int i=1; i<nodeData.length-1; i++){
				System.out.println("链表中间节点数据为："+nodeData[i]);
			}
			System.out.println("链表尾节点数据为："+nodeData[nodeData.length-1]);
		}
		
		if(nodeData.length == 2){
			System.out.println("链表尾节点数据为："+nodeData[nodeData.length-1]);
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
