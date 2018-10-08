package futurama;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class MindSwapSolver<T> {

	private List<Character<T>> characters;
	private Character<T> x;
	private Character<T> y;

	public MindSwapSolver(List<Character<T>> characters, Character<T> x, Character<T> y) {
		this.characters = characters;
		this.x = x;
		this.y = y;
	}
	
	public List<Pair<Character<T>, Character<T>>> solve() {
		// X and Y must be untouched!
		
		if (!x.getBody().equals(x.getMind()) || !y.getBody().equals(y.getMind()))
			return null; // TODO Throw exception!
		List<Pair<Character<T>, Character<T>>> solution = findSolution(findLoops());
		return solution;
	}

	private List<Pair<Character<T>, Character<T>>> findSolution(List<List<Character<T>>> loops) {
		List<Pair<Character<T>, Character<T>>> solution = new ArrayList<>();
		MindSwitchingMachine msm = MindSwitchingMachine.getInstance();
		for (List<Character<T>> loop : loops) {
			for (int i = 0; i < loop.size(); i++) {
				Character<T> current = loop.get(i);
				if (i == loop.size()-1) {
					solution.add(new Pair<Character<T>, Character<T>>(y.clone(), current.clone()));
					msm.swap(y, current);
				}
				solution.add(new Pair<Character<T>, Character<T>>(x.clone(), current.clone()));
				msm.swap(x, current);
				if (i == loop.size()-1) {
					solution.add(new Pair<Character<T>, Character<T>>(y.clone(), loop.get(0).clone()));
					msm.swap(y, loop.get(0));
				}
			}	
		}
		
		if (!x.getMind().equals(x.getBody())) {
			solution.add(new Pair<Character<T>, Character<T>>(x.clone(), y.clone()));
			msm.swap(x, y);
		}
		
		return solution;
	}
	
	protected List<List<Character<T>>> findLoops(){
		List<Marker> values = new ArrayList<>();
		for (Character<T> c : characters) {
			values.add(new Marker(c));
		}
		List<List<Character<T>>> cycles = new ArrayList<>();
		for (int i=0; i < values.size(); i++) {
			if (!values.get(i).isMark()) {
				List<Character<T>> cycle = new ArrayList<>();
				cycle.add(values.get(i).getObj());
				values.get(i).setMark(true);
				T currentBrain = values.get(i).getObj().getMind();
				for (int j=0; j < values.size(); j++) {
					if (!values.get(j).isMark() && values.get(j).getObj().getBody().equals(currentBrain)) {
						values.get(j).setMark(true);
						cycle.add(values.get(j).getObj());
						currentBrain = values.get(j).getObj().getMind();
						// reset Loop
						j = 0;
					}
				}
				cycles.add(cycle);
			}
		}

		return cycles;
	}
	
	protected class Marker {
		protected boolean mark;
		protected Character<T> obj;
		public Marker(Character<T> obj) {
			this.mark = false;
			this.obj = obj;
		}
		public boolean isMark() {
			return mark;
		}
		public void setMark(boolean mark) {
			this.mark = mark;
		}
		public Character<T> getObj() {
			return obj;
		}
		public void setObj(Character<T> obj) {
			this.obj = obj;
		}
	}

}
