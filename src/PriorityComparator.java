import java.util.Comparator;

/**
 * A comparator class for the priority queue that stores nodes based on its cost
 * @author Sean-Veah
 *
 */
public class PriorityComparator implements Comparator<Node> {

	public int compare(Node a, Node b){
		if(a.getTotalCost() < b.getTotalCost()) return -1;
		
		if(a.getTotalCost() > b.getTotalCost()) return 1;
		
		else {
			return 0;
		}
	}
	
}
