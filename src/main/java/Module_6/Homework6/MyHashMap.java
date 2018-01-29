package Module_6.Homework6;

public class MyHashMap<T,K> {
    private int size = 0;
    private Node<T,K> first;

    public void put(T key, K value){
        if (size == 0){
            first = new Node<T,K>(key,value,null);
            size++;
        } else {
            int k = checkKey(key);
            if (k==0){
                final Node<T,K> f = first;
                final Node<T,K> newNode = new Node<>(key,value,f);
                first = newNode;
                size++;

            } else {
                node(k).value=value;
            }
        }
    }
    public void remove(T key){
        Node<T,K> x = node(checkKey(key));
        final Node<T,K> next = x.next;
        final Node<T,K> prev = node(checkKey(key)-1);

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        x.next= null;
        x.key= null;
        x.value= null;
        size--;
    }

    public K get(T key){
        return node(checkKey(key)).value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (Node<T,K> x = first; x != null; ) {
            Node<T,K> next = x.next;
            x.key = null;
            x.next = null;
            x.value = null;
            x = next;
        }
        first = null;
        size = 0;
    }

    private int checkKey(T key) {
        Node<T,K> x = first;
        for (int i = 1; i<=size;i++){
            if((x.key==key)||(x.key.equals(key))) return i;
            x=x.next;
        }
        return 0;
    }

    private   Node<T,K> node(int k) {
            if(k==0) return null;
            Node<T,K> x = first;
            for (int i = 1; i < k; i++)
                x = x.next;
            return x;

    }


    private static class Node<T,K> {
        T key;
        K value;
        Node<T,K> next;


        Node(T key,K value, Node<T,K> next) {
            this.key = key;
            this.next = next;
            this.value = value;
        }
    }
}
