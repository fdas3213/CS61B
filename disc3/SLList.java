public class SLList{
	private class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int item, IntNode next){
			this.item = item;
			this.next = next;
		}
	}	
	private IntNode first;
	
	public void addFirst(int x){
		first = new IntNode(x,first);
	}

	public void insert(int x, int pos){
		if (first == null || pos == 0) {
			addFirst(x);
			return;
		}
		int length = 0;
		IntNode p = first;
		while(p.next != null && length < pos-1) {
			length++;
			p = p.next;
		}
		IntNode n = new IntNode(x,p.next);
		p.next = n;
	}

	public void reverse(){
		if (first ==null || first.next == null) return;
		IntNode s = null;
		while (first != null){
			IntNode next = first.next;
			first.next = s;
			s = first;
			first = next;
		}
		first = s;
	}

	private void printHelper(){
		IntNode p = first;
		while(p != null){
			System.out.print(p.item + " -> ");
			p = p.next;
		}
		System.out.println();
	}

	public static void main(String[] args){
		SLList l = new SLList();
		l.addFirst(2);
		l.addFirst(6);
		l.addFirst(5);
		l.printHelper();
		l.insert(10,1);
		l.printHelper();
		l.reverse();
		l.printHelper();
	}
}
