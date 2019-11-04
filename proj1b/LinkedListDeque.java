public class LinkedListDeque<T> implements Deque<T>{
   public class Node{
       T item;
       Node next;
       Node prev;

       public Node(T i, Node p, Node n){
           item = i;
           next = n;
           prev = p;
       }
   }

   public Node sentinel;
   public int size;

   /*Empty linked constructor
   * */
   public LinkedListDeque(){
       sentinel = new Node(null, null, null);
       sentinel.prev = sentinel;
       sentinel.next = sentinel;
       size = 0;
   }

   /*creates a deep copy of other
    */

   public LinkedListDeque(LinkedListDeque other){
       sentinel = new Node(null, null, null);
       sentinel.prev = sentinel;
       sentinel.next = sentinel;
       size = 0;

       for(int i = 0; i< other.size(); i++){
           addLast(((T) other.get(i)));
       }
   }

   @Override
   public void addFirst(T item){
       Node first = new Node(item, sentinel, sentinel.next);
       sentinel.next.prev = first;
       sentinel.next = first;
       size ++;
   }

   @Override
   public void addLast(T item){
       Node last = new Node(item, sentinel.prev, sentinel);
       sentinel.prev.next = last;
       sentinel.prev = last;
       size ++;
   }

   @Override
   public int size(){
       return size;
   }

   @Override
   public void printDeque(){
       Node p = sentinel;
       while (p.next != sentinel){
           p = p.next;
           System.out.print(p.item + " ");
       }
       System.out.println();
   }

   @Override
   public T removeFirst(){
       if (sentinel.next == sentinel && sentinel.prev == sentinel){
           return null;
       }
       Node first = sentinel.next;
       sentinel.next = first.next;
       sentinel.next.prev = sentinel;
       size --;
       return first.item;
   }

   @Override
   public T removeLast(){
       if (sentinel.next == sentinel && sentinel.prev == sentinel){
           return null;
       }
       Node last = sentinel.prev;
       sentinel.prev = last.prev;
       sentinel.prev.next = sentinel;
       size --;
       return last.item;
   }

   @Override
   public T get(int index){
       if (index > size){
           return null;
       }
       Node p = sentinel.next;
       for(int i=0; i<index;i++){
           p = p.next;
       }
       return p.item;
   }

   public T getRecursive(int index){
       return getRecursiveHelper(sentinel.next, index);
   }

   private T getRecursiveHelper(Node curr, int index){
       if (index == 0) {
           return curr.item;
       } else{
           curr = curr.next;
           return getRecursiveHelper(curr, index-1);
       }
   }

   public static void main(String[] args){
       LinkedListDeque<Integer> L = new LinkedListDeque<>();
       L.addLast(5);
       L.addFirst(3);
       System.out.println("size is: " + L.size);
       L.printDeque();
       System.out.println("first item: " + L.get(0) + " second item: " +  L.get(1));
       /*
       System.out.println(L.removeFirst());
       System.out.println("After removing first item, size is: " + L.size);
       System.out.println(L.removeLast());
       System.out.println("After removing last item, size is: " + L.size);
       */
   }
}
