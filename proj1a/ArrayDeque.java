public class ArrayDeque<T>{
    public T[] items;
    public int size;
    private int nextFirst;
    private int nextLast;
    private static final int RFactor = 2;
    private static final int CAPACITY = 8;
    private static final double MIN_USAGE_RATIO = 0.25;

    public ArrayDeque(){
        items = (T [])new Object[CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T [])new Object[CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

        for(int i = 0;i<other.size();i++){
            addFirst((T) other.get(i));
        }
    }

    private int minusone(int index){
        return (index-1 + items.length) % items.length;
    }

    private int plusone(int index){
        return (index+1) % items.length;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int curr = plusone(nextFirst);
        for(int i =0;i<size;i++){
            a[i] = items[curr];
            curr = plusone(curr);
        }
        items = a;
        nextFirst = capacity-1;
        nextLast = size;
    }

    public void addFirst(T item){
        if (size == items.length) {
            resize(size * RFactor);
        }
        items[nextFirst] = item;
        nextFirst = minusone(nextFirst);
        size ++;
    }

    public void addLast(T item){
        if (size == items.length){
            resize(size * RFactor);
        }
        items[nextLast] = item;
        nextLast = plusone(nextLast);
        size ++;
    }

    public T removeFirst(){
        if (size == 0) return null;
        nextFirst = plusone(nextFirst);
        T item = items[nextFirst];
        size --;
        if(items.length > 16 && size/items.length < MIN_USAGE_RATIO){
            resize(items.length/RFactor);
        }
        return item;
    }

    public T removeLast(){
        if (size == 0) return null;
        nextLast = minusone(nextLast);
        T item = items[nextLast];
        size --;
        if (items.length > 16 && size/items.length < MIN_USAGE_RATIO){
            resize(items.length/RFactor);
        }
        return item;
    }

    public void printDeque(){
        int first = plusone(nextFirst);
        while (first != nextLast){
            T item = items[first];
            System.out.print(item + " ");
            first = plusone(first);
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T get(int index){
        if (index > size){
            return null;
        }
        index = (plusone(nextFirst)+index) % items.length;
        return items[index];
    }

    public int size(){
        return size;
    }

    private void printHelper(){
        System.out.println("nextFirst: " + nextFirst + " nextLast: "+nextLast +
                " size: " + size);
    }

    public static void main(String[] args){
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.addLast("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.printHelper();
    }
}
