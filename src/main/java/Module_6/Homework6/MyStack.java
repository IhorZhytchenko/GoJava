package Module_6.Homework6;

public class MyStack<T> {
    private T[] elementData;
    private int size;

    public MyStack(){
        elementData =  (T[]) new Object[10];
        size = 0;
    }


    public void push(T value){
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    public void remove(int index){
        int numMoved = size - index - 1;
        if (numMoved>0) System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }
    public  T peek() {

        return elementData[size-1];
    }
    public T pop() {
        T obj= peek();
        remove(size - 1);
        return obj;
    }

    private void ensureCapacity(int length) {
        if (length == elementData.length){
            T[] copy = (T[]) new Object[size];
            System.arraycopy(elementData, 0, copy, 0, size);
            elementData = (T[]) new Object[(length * 3) / 2 + 1];
            System.arraycopy(copy, 0, elementData, 0, size);
        }

    }
    public void clear(){
        elementData =  (T[]) new Object[10];
        size = 0;
    }

    public int size(){
        return this.size;
    }
}


