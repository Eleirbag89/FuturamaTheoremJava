package futurama;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MindSwitchingMachine msm = MindSwitchingMachine.getInstance();

		Character<String> fry = new Character<String>("Fry");
		Character<String> zoidberg = new Character<String>("Zoidberg");
		Character<String> professorFarnsworth = new Character<String>("Professor Farnsworth");
		Character<String> amy = new Character<String>("Amy");
		Character<String> bender = new Character<String>("Bender");
		Character<String> emperorNikolai = new Character<String>("Emperor Nikolai");
		Character<String> washBucket = new Character<String>("Wash Bucket");
		Character<String> leela = new Character<String>("Leela");
		Character<String> hermes = new Character<String>("Hermes");
		
		msm.swap(professorFarnsworth, amy);
		msm.swap(amy, bender);
		msm.swap(washBucket, emperorNikolai);
		msm.swap(amy, emperorNikolai);
		msm.swap(professorFarnsworth, leela);
		msm.swap(fry, zoidberg);
		msm.swap(hermes, leela);
		List<Character<String>> characters = new ArrayList<>();
		characters.add(fry);
		characters.add(zoidberg);
		characters.add(professorFarnsworth);
		characters.add(amy);
		characters.add(bender);
		characters.add(emperorNikolai);
		characters.add(washBucket);
		characters.add(leela);
		characters.add(hermes);
		
		Character<String> sweetClyde = new Character<String>("Sweet Clyde");
		Character<String> bubblegum = new Character<String>("Bubblegum");
		
		MindSwapSolver<String> solver = new MindSwapSolver<String>(characters, sweetClyde, bubblegum);
		List<Pair<Character<String>, Character<String>>> solution = solver.solve();
		printSolution(solution);
		System.out.println("Result after the swaps");
		for (Character<String> c : characters) {
			System.out.println(c);
		}
		
		System.out.println("Random:");
		randomTest();
	}

	public static void randomTest() {
		MindSwitchingMachine msm = MindSwitchingMachine.getInstance();
		List<Character<Integer>> characters = new ArrayList<>();
		for (int i=0; i < 100; i++) {
			characters.add(new Character<Integer>(i));
		}
		
		for (int i=0; i<100; i++) {
			Random r = new Random();
			int a = r.nextInt(100);
			int b = r.nextInt(100);
			if (a!=b)
				msm.swap(characters.get(a),characters.get(b));
		}
		Character<Integer> x = new Character<>(-1);
		Character<Integer> y = new Character<>(-2);
		
		MindSwapSolver<Integer> solver = new MindSwapSolver<>(characters, x, y);
		List<Pair<Character<Integer>, Character<Integer>>> solution = solver.solve();
		printSolution(solution);
		System.out.println("Result after the swaps");
		for (Character<Integer> c : characters) {
			System.out.println(c);
		}
	}
	
	public static <T> void printSolution(List<Pair<Character<T>, Character<T>>> solution) {
		System.out.println("Number of swaps: "+solution.size());
		for (Pair<Character<T>, Character<T>> step : solution) {
			System.out.println("Swap ("+ step.getKey() + ") with (" + step.getValue() + ")");
		}
	}
}


