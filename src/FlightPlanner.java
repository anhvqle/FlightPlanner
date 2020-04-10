import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class FlightPlanner {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		FileReader f = new FileReader("SampleData");
		Scanner scan = new Scanner(f);
		ArrayList<String> airport1 = new ArrayList<>();
		ArrayList<String> airport2 = new ArrayList<>();
		
		while(scan.hasNext()) {
			String[] airports = scan.next().split("\\|");
			airport1.add(airports[0]);
			airport2.add(airports[1]);
		}
		scan.close();
		LinkedList <String> Dallas = new LinkedList<>();
		Dallas.addAll(Arrays.asList("Dallas", "Austin", "Houston"));
		
		LinkedList <String> Austin = new LinkedList<>();
		Austin.addAll(Arrays.asList("Austin", "Dallas", "Houston", "Chicago"));
		
		LinkedList <String> Houston = new LinkedList<>();
		Houston.addAll(Arrays.asList( "Houston","Austin","Dallas"));
		
		LinkedList <String> Chicago = new LinkedList<>();
		Chicago.addAll(Arrays.asList("Chicago", "Austin"));
		
		List<LinkedList <String>> adjacencyList = new ArrayList<>(Arrays.asList(Dallas, Austin, Houston, Chicago));
		System.out.println(adjacencyList);
		System.out.println(route(adjacencyList, "Austin", "Dallas"));
	}
	public static List<LinkedList <String>> route (List<LinkedList <String>> adjacencyList, String source, String destination){
		LinkedList <String> stack = new LinkedList<>();
		stack.add(source);
		List<LinkedList <String>> ans = new ArrayList<>();
		List<Iterator<String>> itrList = new ArrayList<>();
		
		for(LinkedList <String> list: adjacencyList) {
			itrList.add(list.iterator());
		}
		while(!stack.isEmpty()) {
//			System.out.println(ans);
//			System.out.println(stack);
			if(stack.getLast().equals(destination)) {
				ans.add(new LinkedList<String>(stack));
				stack.removeLast();
			}
			else {
				Iterator <String> itr = null;
				int index = -1;
				for(int i = 0; i < adjacencyList.size(); i++) {
					if(adjacencyList.get(i).getFirst().equals(stack.getLast())) {
						itr = itrList.get(i);
						index = i;
						break;
					}
				}
				if(!itr.hasNext()) {
					stack.removeLast();
					itrList.set(index, adjacencyList.get(index).iterator()); //Reset iterator
					itrList.get(index).next();
				}
				else {
					String s = itr.next();
					if(stack.contains(s)) 
						continue;
					else 
						stack.addLast(s);
				}
			}
		}
		return ans;
	}

}
