import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Queues class for the project
 *
 * @param <Item>
 */
public class Queues<Item> implements Iterable<Item>{
	
	//first element of queue
	private Node<Item> start;
	
	//last element of queue
	private Node<Item> end;
	
	//total elements of queue
	private int num;
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public Queues(){
		//make an empty queue
		start = null;
		end = null;
		num = 0;
	}
	//make the queue empty
	public void clear(){
		start = null;
		end = null;
		num =0;
	}
	
	//implementation of enqueu
	public void enqueue(Item item){
		Node<Item> temp = end;
		end = new Node<Item>();
		end.item = item;
		end.next = null;
		if(isEmpty()) {
			start = end;
		}
		else temp.next = end;
		num++;
	}
	
	//implementation of dequeu
	// return the first element
	public Item dequeue(){
		if(isEmpty()) throw new NoSuchElementException("Queue is empty.");
		Item item = start.item;
		start = start.next;
		num--;
		return item;
	}
	
	//return the size of the queue
	public int getSize(){
		return num;
	}
	
	//check if the queue is empty
	public boolean isEmpty(){
		return start ==null;
	}
	
	//return the first element in the queue
	public Item getFirst(){
		return start.item;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(start);
	}
	
	public class ListIterator<Item> implements Iterator<Item>{
		public Node<Item> current;
		
		public ListIterator(Node<Item> first){
			current = first;
		}
	    public boolean hasNext(){
	    	return current !=null;
	    }
	    public Item next(){
	    	if(!hasNext()) throw new NoSuchElementException();
	    	Item item = current.item;
	    	current = current.next;
	    	return item;
	    }
	}
	
	public void add(Queues<Item> queue){
		if(!queue.isEmpty()){
			Node<Item> temp = start;
			
			if(isEmpty()){
				start = queue.start;
				end = queue.end;
			}
			else {
				start = queue.start;
				queue.end.next = temp;
			}
			num += queue.getSize();
		}
	}

	
}
