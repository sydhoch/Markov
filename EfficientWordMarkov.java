import java.util.*;

public class EfficientWordMarkov  extends WordMarkovModel{
	HashMap<WordGram,ArrayList<String>> myMap;
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();
		for(int i=0;i<myWords.length-myOrder+1;i+=1) {
			WordGram key;
			key = new WordGram(myWords,i,myOrder);
			if (!myMap.containsKey(key)) {
				myMap.put(key,new ArrayList<String>());
			}
			if(i<myWords.length-myOrder) {
				myMap.get(key).add(myWords[i+myOrder]);
			}
			if(i==myWords.length-myOrder) {
				myMap.get(key).add(PSEUDO_EOS);
			}
			
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = myMap.get(key);
		if (follows==null) {
			throw new NoSuchElementException("Following element not found after: " + key);
		}
		return follows;
	}
}
