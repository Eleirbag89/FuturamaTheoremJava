package futurama;

import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;

public class MindSwitchingMachine {
	private static MindSwitchingMachine instance;
	private Set<Pair<?, ?>> swapHistory;
	private MindSwitchingMachine() {
		this.swapHistory = new HashSet<Pair<?,?>>();
	}
	
	public <T> boolean swap(Character<T> chara1, Character<T> chara2) {
		T body1 = chara1.getBody();
		T body2 = chara2.getBody();
		
		if (swapHistory.contains(new Pair<T,T>(body1, body2)) || swapHistory.contains(new Pair<T,T>(body2, body1))) {
				return false;
		}
		
		T brain1 = chara1.getMind();
		T brain2 = chara2.getMind();
		
		chara1.setMind(brain2);
		chara2.setMind(brain1);
		
		swapHistory.add(new Pair<T,T>(body1, body2));
		swapHistory.add(new Pair<T,T>(body2, body1));
		return true;
	}
	
	public static MindSwitchingMachine getInstance() {
		if  (instance == null) 
			instance = new MindSwitchingMachine();
		return instance;
	}

}
