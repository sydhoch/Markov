import java.util.*;
public class EfficientMarkov extends MarkovModel {
	Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for(int i=0;i<myText.length()-myOrder+1;i+=1) {
			String key;
			if (i==myText.length()-myOrder) {
				key = myText.substring(i);
			}
			else {
				key = myText.substring(i, i+myOrder);
			}
			if (!myMap.containsKey(key)) {
				myMap.put(key,new ArrayList<String>());
			}
			if(i<myText.length()-myOrder) {
				myMap.get(key).add(Character.toString(myText.charAt(i+myOrder)));
			}
			if(i==myText.length()-myOrder) {
				myMap.get(key).add(PSEUDO_EOS);
			}
			
		}
		//stores the parameter, clears the map, and then initializes the map
	}
	@Override
	public ArrayList<String> getFollows(String key){
		//make it a constant time operation that uses myMap to return an ArrayList using the parameter to getFollows as a key.
		//If the key isn't present in the map throw a new NoSuchElementException with an appropriate String
		ArrayList<String> follows = myMap.get(key);
		if (follows == null) {
			throw new NoSuchElementException("Following element not found after: " + key);
		}
		return follows;		
	}
}
