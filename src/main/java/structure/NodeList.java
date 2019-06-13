package structure;

import java.util.ArrayList;
import java.util.Comparator;

public class NodeList<T> {

	private Node<T> head;
	private Comparator<T> comparator;

	public NodeList() {
	}

	public NodeList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public Node<T> createNode(T info) {
		return new Node<T>(info);
	}

	public void addLast(Node<T> node) {
		Node<T> actual = head;
		if (actual != null) {
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(node);
		} else {
			head = node;
		}
	}

	public Node<T> getFirst() {
		Node<T> first = head;
		head = head.getNext();
		first.setNext(null);
		return first;
	}

	public void addFirst(Node<T> node) {
		Node<T> actual = head;
		if (actual != null) {
			node.setNext(head);
			head = node;
		} else {
			head = node;
		}
	}

	public void addPriority(Node<T> node) {
		Node<T> actual = head;
		while (actual != null) {
			if (comparator.compare(node.getInfo(), actual.getInfo()) > 0) {
				addBefore(node, actual);
				break;
			}
			actual = actual.getNext();
		}
		if (!exist(node)) {
			addLast(node);
		}
	}

	public boolean exist(Node<T> node) {
		Node<T> actual = head;
		while (actual != null) {
			if (comparator.compare(node.getInfo(), actual.getInfo()) == 0) {
				return true;
			}
			actual = actual.getNext();
		}
		return false;
	}

	public void addAfter(Node<T> newNode, Node<T> nodeAfter) {
		Node<T> actual = head;
		while (actual != null) {
			if (comparator.compare(actual.getInfo(), nodeAfter.getInfo()) == 0) {
				newNode.setNext(actual.getNext());
				actual.setNext(newNode);
			}
			actual = actual.getNext();
		}
	}

	public void addBefore(Node<T> newNode, Node<T> nodeBefore) {
		Node<T> actual = head;
		if (comparator.compare(actual.getInfo(), nodeBefore.getInfo()) != 0) {
			while (actual.getNext() != null) {
				if (comparator.compare(actual.getNext().getInfo(), nodeBefore.getInfo()) == 0) {
					newNode.setNext(actual.getNext());
					actual.setNext(newNode);
					break;
				}
				actual = actual.getNext();
			}
		} else {
			newNode.setNext(head);
			head = newNode;
		}
	}

	public void remove(Node<T> nodeRemove) {
		Node<T> actual = head;
		if (comparator.compare(actual.getInfo(), nodeRemove.getInfo()) != 0) {
			while (actual != null) {
				if (comparator.compare(actual.getNext().getInfo(), nodeRemove.getInfo()) == 0) {
					removeNode(actual);
					break;
				}
				actual = actual.getNext();
			}
		} else {
			head = head.getNext();
			actual.setNext(null);
		}
	}
	
	private void removeNode(Node<T> node) {
		Node<T> nodeRemove = node.getNext();
		node.setNext(node.getNext().getNext());
		nodeRemove.setNext(null);
	}

	public int size() {
		int size = 0;
		Node<T> actual = head;
		while (actual != null) {
			size++;
			actual = actual.getNext();
		}
		return size;
	}

	public void print() {
		Node<T> actual = head;
		while (actual != null) {
			System.out.println(actual.getInfo());
			actual = actual.getNext();
		}
	}

	public ArrayList<T> getInfoList() {
		ArrayList<T> list = new ArrayList<>();
		Node<T> actual = head;
		while (actual != null) {
			list.add(actual.getInfo());
			actual = actual.getNext();

		}
		return list;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getHead() {
		return head;
	}
}