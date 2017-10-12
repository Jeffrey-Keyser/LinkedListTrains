/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will 
 * reference the first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIterator constructed by passing 
 * the first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without
 * calling the add and remove methods in this class.  So, the size()
 * method must be implemented by counting nodes.  This counting must
 * occur each time the size method is called.  DO NOT USE a numItems field.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedList<E> implements ListADT<E> {


	//	 TODO: YOU MUST IMPLEMENT THE LINKED LIST CLASS AS FOLLOWS:
	//	 
	//	 It must be a SINGLY-LINKED chain of ListNode<E> nodes
	//	 It must have a "header node" ("dummy node" without data)
	//	 It must NOT have a tail reference
	//	 It must NOT keep a number of data items
	//       NOTE: in this program, the chains of nodes in this program may be 
	//   	 changed outside of the LinkedList class, so the actual data count 
	//       must be determined each time size is called.
	//
	//	 It must return a LinkedListIterator<E> as its iterator.
	//	 
	//	 Note: The "header node"'s data reference is always null and 
	//	 its next references the node with the first data of the list.
	//	 
	//	 Be sure to implement this LinkedList<E> using Listnode
	//	       you must use LinkedListIterator<E> instead of Iterator<E>
	//	
	private Listnode<E> headerNode;
	
	public LinkedList()
	{
		headerNode = new Listnode<E>(null);
	}
	
	/** 
	 * Returns a reference to the header node for this linked list.
	 * The header node is the first node in the chain and it does not 
	 * contain a data reference.  It does contain a reference to the 
	 * first node with data (next node in the chain). That node will exist 
	 * and contain a data reference if any data has been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access
	 * to the headerNode in this way to classes that use the linked list.  
	 * We are providing direct access to support testing and to 
	 * allow multiple nodes to be moved as a chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
	public Listnode<E> getHeaderNode() {
		return headerNode;
	}

	/**
	 * Must return a reference to a LinkedListIterator for this list.
	 */
	public LinkedListIterator<E> iterator() {
		
		LinkedListIterator<E> itr = new LinkedListIterator<E>(headerNode);
		return itr;
	}

	@Override
	public void add(E item) {
		
		if (item == null)
			throw new IllegalArgumentException();
		
		// Create new node
		
		Listnode<E> newNode = new Listnode<E>(item);
		
		Listnode<E> curr = headerNode;
			
		// Traverse to last node
		while (curr.getNext() != null)
		{	curr = curr.getNext();	}
		
		curr.setNext(newNode);

		}
	

	@Override
	public void add(int pos, E item) {
		
		Listnode<E> curr = headerNode;
		int count = 0;
		
		while (count != pos)
		{
			curr.getNext();
			count++;
		}
		curr.setNext(new Listnode( item, curr.getNext()));	
	}

	@Override
	public boolean contains(E item) {
		
		LinkedListIterator itr = new LinkedListIterator(headerNode);
		
		while (itr.hasNext())
		{
			Listnode<E> checker = (Listnode<E>) itr.next();
			
			if (checker.getData() == item)
				return true;
		}
		
		return false;
	}

	@Override
	public E get(int pos) {
		if (pos < 0)	// NEED TO IMPLEMENT .SIZE()!!!
			throw new IndexOutOfBoundsException();
		
		// Traverse to node with data at index pos.
		Listnode<E> curr = headerNode;
		for (int p = 0; p < pos; p++)
			curr = curr.getNext();
		
		// Return the data	(not the node)
		return curr.getData();
	}

	@Override
	public boolean isEmpty() {
		if (headerNode.getNext() == null)
			return false;
		else 
			return true;
	}

	@Override
	public E remove(int pos) {
		Listnode<E> curr = headerNode;
		int count = 0;
		
		while (count != pos)
		{
			curr.getNext();
			count++;
		}
		
		Listnode<E> removeHelp = curr;
		
		Listnode<E> removed = removeHelp.getNext();
		
		curr.setNext(curr.getNext().getNext());
		
		return removed.getData();
	}

	@Override
	public int size() {
		
		LinkedListIterator itr = new LinkedListIterator(headerNode);
		int count = 0;
		
		while (headerNode.getNext() != null)
		{
			itr.next();
			count++;
		}	

		return count;
	}
	}

