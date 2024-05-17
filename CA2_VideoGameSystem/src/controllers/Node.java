package controllers; //Importing the library package

// This is going to be used with the hash table to stop collision
public class Node<T> {
    private T data;
    public Node<T> next; //this is the address of the next Node

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public void setNext(Node<T> newNode) {//setter for next node with data type that we will send in
        this.next = newNode;
    }

    public Node<T> getNext() //getting the next node
    {
        return next;
    }

    public T getData() {
        return data;
    }//this is return the data such as if we have getNext.getData then this will get the data
}
