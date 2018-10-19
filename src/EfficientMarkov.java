import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
	private Map<String,ArrayList<String>> myMap = new HashMap<>();
	
	/**
	*Constructor 1
	*/
	public EfficientMarkov(int order) {
		super(order);
	}
	
	/**
	*Constructor 2
	*/
	public EfficientMarkov() {
		super();
	}
	
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myText = text;
		for(int i=0; i<text.length()-myOrder+1;i++) {
			String sub = myText.substring(i,i+myOrder);
			if(!myMap.containsKey(sub)) {
				myMap.put(sub, new ArrayList<String>());
			}
			if(i==text.length()-myOrder) {
				myMap.get(sub).add(PSEUDO_EOS);
			}
			else{
				myMap.get(sub).add(String.valueOf(myText.charAt(i+myOrder)));
			}
		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key) {
		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}
