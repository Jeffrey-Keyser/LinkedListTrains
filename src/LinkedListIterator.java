import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The iterator implementation for LinkedList.  The constructor for this
 * class requires that a reference to a Listnode with the first data
 * item is passed in.
 * 
 * If the Listnode reference used to create the LinkedListIterator is null,
 * that implies there is no data in the LinkedList and this iterator
 * should handle that case correctly.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	// TODO determine what data members are needed for this iterator type
	private Listnode<T> curr;

	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) {
		if (head == null)
		{	}
		else
		curr = head;
		
		// TODO finish the constructor

	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	@Override
	public T next() {
		if (curr == null)
			throw new NoSuchElementException();
		T item = curr.getData();
		curr = curr.getNext();
		return item;
		// TODO implement this method
	}
	
	/**
	 * Returns true if their are no more data items to iterate through 
	 * for this list.
	 * 
	 * @return true if their are any remaining data items to iterator through
	 */
	@Override
	public boolean hasNext() {
		return curr != null;
		// TODO implement this method
	}
       
    /**
     * The remove operation is not supported by this iterator
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
    @Override
	public void remove() {
	  throw new UnsupportedOperationException();
	}

}