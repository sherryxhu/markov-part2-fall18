import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram,ArrayList<String>> myMap = new HashMap<>();
	
	@Override
	public void setTraining(String text) {
		String[] words = text.split("\\s+");
		for(int i=0; i<words.length-myOrder+1;i++) {
			int start = i;
			int size = myOrder;
			WordGram wd = new WordGram(words, start, size);
			if(!myMap.containsKey(wd)) {
				myMap.put(wd, new ArrayList<String>());
			}
			if(i==text.length()-myOrder) {
				myMap.get(wd).add(PSEUDO_EOS);
			}
			else{
				myMap.get(wd).add(words[i+myOrder]);
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram kGram) {
		if(!myMap.containsKey(kGram)) {
			throw new NoSuchElementException(kGram+" not in map");
		}
		return myMap.get(kGram);
	}
}
