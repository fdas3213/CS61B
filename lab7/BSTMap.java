import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private Node root;

    private class Node{
        private K k;
        private V v;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size){
            k = key;
            v = val;
            this.size = size;
        }
    }

    @Override
    public BSTIterator iterator(){
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K>{
        private Node n;

        public BSTIterator() {
            n = root;
        }

        @Override
        public boolean hasNext(){
            return n != null;
        }

        @Override
        public K next(){
            K item = n.k;
            n = n.left;
            return item;
        }
    }

    /* Initialize an Empty tree */
    public BSTMap(){}

    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (key == null) throw new IllegalArgumentException("null key");
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return get(root, key);
    }

    /* private helper get method */
    private V get(Node x, K key){
        if (key == null) throw new IllegalArgumentException("null key");
        if (x == null) return null;
        int cmp = x.k.compareTo(key);
        if (cmp < 0) return get(x.right, key);
        else if (cmp > 0) return get(x.left, key);
        else return x.v;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if (key == null) throw new IllegalArgumentException("null key");
        root = put(root, key, value);
    }

    /* private helper put method */
    private Node put(Node x, K key, V value){
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.k);
        if(cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.v = value;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* print the BSTMap in order of increasing Key */
    public void printInOrder(){
        printNode(root);
    }

    private void printNode(Node x){
        if (x == null) return;
        printNode(x.left);
        System.out.print(x.k + " ");
        printNode(x.right);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        Set<K> kset = new HashSet<>();
        keySet(kset, root);
        return kset;
    }

    private void keySet(Set<K> kSet, Node x){
        if (x == null) return;
        keySet(kSet, x.left);
        kSet.add(x.k);
        keySet(kSet, x.right);
    }

    /* Unit Test */
    public static void main(String[] args){
        BSTMap<Integer, Integer> m = new BSTMap<>();
        m.put(5,1);
        m.put(3,1);
        m.put(2,1);
        m.put(6,1);
        m.put(9,1);
        m.put(11,1);
        m.put(4,1);
        m.printInOrder();
        System.out.println();
        System.out.println(m.keySet().toString());
    }

}
