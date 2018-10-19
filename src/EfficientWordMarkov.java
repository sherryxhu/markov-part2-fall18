import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram,ArrayList<String>> myMap = new HashMap<>();
	
	/**
	*Constructor 1
	*/
	public EfficientWordMarkov(int order) {
		super(order);
	}
	
	/**
	*Constructor 2
	*/
	public EfficientWordMarkov() {
		super();
	}
	
	/**
	 * clears myMap
	 * iterates through words to obtain all k-order WordGrams
	 * adds WordGrams to myMap along with the following words in an ArrayList
	 */
	@Override
	public void setTraining(String text) {
		myMap.clear();
		String[] words = text.split("\\s+");
		myWords = words;
		for(int i=0; i<words.length-myOrder+1;i++) {
			WordGram wd = new WordGram(words, i, myOrder);
			if(!myMap.containsKey(wd)) {
				myMap.put(wd, new ArrayList<String>());
			}
			if(i==words.length-myOrder) {
				myMap.get(wd).add(PSEUDO_EOS);
			}
			else{
				myMap.get(wd).add(words[i+myOrder]);
			}
		}
	}
	
	/**
	 * looks up the WordGram in the map and returns its associated value,
	 * which is an ArrayList of Strings
	 */
	@Override
	public ArrayList<String> getFollows(WordGram kGram) {
		if(!myMap.containsKey(kGram)) {
			throw new NoSuchElementException(kGram+" not in map");
		}
		return myMap.get(kGram);
	}
}
