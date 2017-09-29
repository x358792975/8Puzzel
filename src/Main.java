
public class Main {
	
		final static private String EASY = "134862705";
		final static private String MEDIUM = "281043765";
		final static private String HARD = "281463075";
		final static private String WORST = "567408321";
		final static private String GOAL = "123804765";
	public static void main(String[] args) {

		
		String rootState = EASY;
		long startTime = System.currentTimeMillis();
		
		searchTree search = new searchTree(new Node(rootState), GOAL);	
		//search.aStar(Heuristic.H_THREE);
		//search.bestFirstSearch();
		search.DepthFirstSearch();
		
		long finishTime = System.currentTimeMillis();
		long totalTime = finishTime - startTime;
		System.out.println("Total Time used : "+ totalTime + " ms.");
	}

}
