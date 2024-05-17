package controllers;
import java.util.Objects;

//This is our linkedList class that is our interconnected connection of Nodes that we have defined in the Node class
public class MyLinkedList<T>{
    public int size; //storing the size of the linked list or how many nodes are on the linked list
    private Node<T> head; //this is private and stored the head of our LinkedList or first Node

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return Objects.equals(head, that.head);
    }

    public boolean remove(T data) {
        if (head == null) {
            return false; //the list is empty so we cant remove anything from it
        }
        if (head.getData().equals(data)) {
            head = head.getNext(); //if the head has the node we are looking for now the head is the next node deleting it.
            return true;
        }
        Node<T> current = head; //instastizing the node and called it current
        while (current.getNext() != null) {//while the node isnt null then keep looping
            if (current.getNext().getData().equals(data)) {//if the next node had the date
                current.setNext(current.getNext().getNext()); //we will set the current node to the one after next so the next next. - the obj address is now gone is removed
                return true; // Element removed successfully
            }
            current = current.getNext();
        }
        return false; // Element not found in the list
    }

    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode; // If the list is empty, set the new node as the head
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode); // Set the next pointer of the last node to the new node
        }
        size++; // Increase the size of the list
        return true; // Return true to indicate successful addition
    }


    public Node<T> getHead() {
        return head;
    }



}