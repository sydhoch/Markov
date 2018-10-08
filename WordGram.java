
public class WordGram implements Comparable<WordGram>{
	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		for (int i =0; i<size; i++) {
			myWords[i]= words[i+index];
		}
		hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash=0;
		for (int i = 0; i < myWords.length; i++) {
			hash += myWords[i].hashCode()*i;
			hash *= (i+1);
		}
		myHash = hash;
		return myHash;
	}
	
	@Override
	public String toString() {
		return String.join(" ", myWords);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other;
		if (wg.length() != this.length()) {
			return false;
		}
		for (int i=0;i<myWords.length;i++) {
			if(! wg.myWords[i].equals(myWords[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(WordGram wg) {
		int len1 = toString().length();
		int len2 = wg.toString().length();
		int lim = Math.min(len1,len2);
		char[] v1 = toString().toCharArray();
		char[] v2 = wg.toString().toCharArray();
		
		int i = 0;
		while (i<lim) {
			char c1= v1[i];
			char c2= v2[i];
			if (c1 !=c2) {
				return c1-c2;
			}
			i++;
			
		}
		return len1-len2;
	}
	
	public int length() {
		int size = myWords.length;
		return size;
	}
	
	public WordGram shiftAdd(String last) {
		for (int i=1;i<myWords.length;i++) {
			myWords[i-1]=myWords[i];
		}
		myWords[myWords.length-1]= last;
		return new WordGram(myWords,0,myWords.length);
	}
}
