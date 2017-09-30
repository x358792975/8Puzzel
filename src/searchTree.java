import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * searching class for different methods of searching
 * @author Xiang Cui
 *
 */
public class searchTree {
    private Node root;
    private String goalState;
    
    //getter & setter for root
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	
	//getter & setter for goalState
	public String getGoalState() {
		return goalState;
	}
	public void setGoalSate(String goalState) {
		this.goalState = goalState;
	}
    
	//constructor with two parameters(root & goalState)
	public searchTree(Node root, String goalState){
		this.root = root;
		this.goalState = goalState;
	}
	
	public void DepthFirstSearch(){
		int cost = 0;
		int time = 0;
		//list for nodes that have been visited
		Set<String> state = new HashSet<String>();
		
		Node myNode = new Node(root.getState());
		
		//a queue for nodes that need to be expanded
		Queues<Node> myQueue = new Queues<>();
		
		//a queue for a expanded node's successors
		Queues<Node> succQueue = new Queues<>();
		
		Node current = myNode;
		
		// looping until the goal state is reached
		while(!current.getState().equals(goalState)){
			state.add(current.getState());
			List<String> nodeSucc = Utility.getSuccessors(current.getState());
			
			for(String s : nodeSucc){
				if(state.contains(s)){
					continue;
				}
				state.add(s);
				Node children = new Node(s);
				current.addChildren(children);
				children.setParent(current);
				succQueue.enqueue(children);
			}
			myQueue.add(succQueue);
			
			succQueue.clear();
			current = myQueue.dequeue();
			time++;
			nodeSucc.clear();
		
		}
		Utility.printSolution(current, state, root, time);
		
		}


	public void a_Star(Heuristic h){
		int cost = 0;
		int time = 0;
		//list for nodes that have been visited
		Set<String> state = new HashSet<String>();
		
		Node myNode = new Node(root.getState());
		
		//comparator compares the cost, the priority queue is based on cost 
		PriorityComparator compare = new PriorityComparator();
		
		PriorityQueue<Node> myQueue = new PriorityQueue<Node>(10, compare);
		
		Node current = myNode;
		
		while(!current.getState().equals(goalState)){
			state.add(current.getState());
			List<String> nodeSucc = Utility.getSuccessors(current.getState());
			for(String s : nodeSucc){
				if(state.contains(s)) continue;
				state.add(s);
				Node children = new Node(s);
				current.addChildren(children);
				children.setParent(current);
				
				if(h == Heuristic._ONE){
					children.setTotalCost(current.getTotalCost() + Character.getNumericValue(children.getState().charAt(children.getParent().getState().indexOf('0'))),h_One(children.getState(),goalState));
				}
				else if (h == Heuristic._TWO){
					children.setTotalCost(current.getTotalCost() + Character.getNumericValue(children.getState().charAt(children.getParent().getState().indexOf('0'))),h_Two(children.getState(),goalState));
				}
				else if (h == Heuristic._THREE){
					children.setTotalCost(current.getTotalCost() + Character.getNumericValue(children.getState().charAt(children.getParent().getState().indexOf('0'))),h_Three(children.getState(),goalState));
				}
				myQueue.add(children);
			}
			
			current = myQueue.poll();
			time +=1;
		}
		Utility.printSolution(current, state, root, time);
	}
	
    public int h_One(String currentState, String goalSate) {
        int diff = 0;
        for (int i = 0; i < currentState.length(); i++)
            if (currentState.charAt(i) != goalSate.charAt(i))
                diff += 1;
        return diff;
    }	
    
    public int h_Two(String currentState, String goalSate) {
        int diff = 0;
        for (int i = 0; i < currentState.length(); i++)
            for (int j = 0; j < goalSate.length(); j++)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    diff  += ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
        return diff;
    }
    
    public int h_Three(String currentState, String goalSate) {
        int diff = 0;
        int manhattanDistance = 0;
        for (int i = 0; i < currentState.length(); i++)
            for (int j = 0; j < goalSate.length(); j++)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    manhattanDistance = (Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3);
        diff = diff + 2 * manhattanDistance - 1;
        return diff;
    }
    
    public void iterativeDeepning(int depthLimit){
        Node currentNode = new Node(root.getState());
        boolean solutionFound = false;
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        Set<String> totalVisitedStates = new HashSet<>();
        int time = 0;
        for (int maxDepth = 1; maxDepth < depthLimit; maxDepth++) {
            //we should clear the visited list in each iteration
            stateSets.clear();
            //the queue that store nodes that we should expand
            Queues<Node> mainQueue = new Queues<>();
            //the queue that stores the successors of the expanded node
            Queues<Node> successorsQueue = new Queues<>();
            Node node = new Node(root.getState());
            mainQueue.enqueue(node);
            currentNode = node;
            List<String> nodeSuccessors = null;
            stateSets.add(currentNode.getState());
            while (!mainQueue.isEmpty()) {
                currentNode = mainQueue.dequeue();
                time += 1;
                if (currentNode.getState().equals(goalState)) {
                    solutionFound = true;
                    break;
                }
                if (currentNode.getDepth() < maxDepth) {
                    nodeSuccessors = Utility.getSuccessors(currentNode.getState());
                    for (String n : nodeSuccessors) {
                        if (stateSets.contains(n))
                            continue;

                        stateSets.add(n);
                        Node child = new Node(n);
                        currentNode.addChildren(child);
                        child.setParent(currentNode);
                        child.setVisited(true);
                        child.setDepth(currentNode.getDepth() + 1);
                        successorsQueue.enqueue(child);

                    }
                    //we add the queue that contains the successors of the visted node to the beginning of the main queue
                    mainQueue.add(successorsQueue);
                    //successors queue should be cleared in order to store the next expaneded's successors
                    successorsQueue.clear();
                }
            }

            if (solutionFound)
                break;
            totalVisitedStates.addAll(stateSets);
        }
        if (!solutionFound)
            System.out.println("No solution Found!");
        else {
            Utility.printSolution(currentNode, totalVisitedStates, root, time);
        }   	
    	
    }
    

}
	

