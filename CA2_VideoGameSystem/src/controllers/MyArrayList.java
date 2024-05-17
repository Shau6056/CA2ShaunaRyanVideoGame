package controllers;

public class MyArrayList<T> {
    private T[] data;//generic t data good for reuse
    private int size;

    public MyArrayList() {
        this.data = (T[]) new Object[0]; //the type t array is a new object at 0
        this.size = 0;//size is 0
    }

    public void add(T element) {
        if (size == data.length) {//then we add and to the array
            resize();
        }
        data[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[index];//we get the object at the index in the array and return that
    }

    public int size() {//return the size of the array
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    private void resize() { //this method will resize the array doubling every time it grows.
        int newSize = (data.length == 0) ? 1 : data.length * 2;
        T[] newData = (T[]) new Object[newSize];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }


}
