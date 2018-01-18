package Module_6.Homework6;

public class MyArrayList<T> {
    private T[] elementData;
    private int size;

    public MyArrayList(){
        elementData =  (T[]) new Object[10];
        size = 0;
    }
    public MyArrayList(int size){
        elementData =  (T[]) new Object[size];
        this.size = 0;
    }

    public void add(T value){
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    public void remove(int index){
        int numMoved = size - index - 1;
        if (numMoved>0) System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }
    public T get(int index){
        return elementData[index];
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
