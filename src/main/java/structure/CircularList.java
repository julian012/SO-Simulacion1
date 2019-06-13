package structure;

import java.util.ArrayList;

public class CircularList<T>{

	private Node<T> head;
	
	public CircularList() {
	}
	
	public Node<T> createNode(T t) {
		return new Node<T>(t);
	}
	
	public void addNode(Node<T> node) {
		Node<T> actual = head;
		if (actual != null) {
			while (actual.getNext() != head) {
				actual = actual.getNext();
			}
			actual.setNext(node);
		} else {
			head = node;
		}
		node.setNext(head);
	}
	
	public Node<T> getFirst() {
		return head;
	}
	
	/*public ArrayList<T> getList() {
		ArrayList<T> infoList = new ArrayList<>();
		Node<T> actual = list.getHead();
		while (actual != null && actual.getNext() != list.getHead()) {
			infoList.add(actual.getInfo());
			actual = actual.getNext();

		}
		infoList.add(actual.getInfo());
		return infoList;
	}*/
	
	public void printList() {
		Node<T> actual = head;
		while (actual != null && actual.getNext() != head) {
			System.out.println(actual.getInfo());
			actual = actual.getNext();
		}
		System.out.println(actual.getInfo().toString());
	}
}