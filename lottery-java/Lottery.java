
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Lottery {

	public static void main(String args[]){
	   Lottery l = new Lottery();
	   String[] rules = new String[]{"INDIGO: 93 8 T F", "ORANGE: 29 8 F T", "VIOLET: 76 6 F F", "BLUE: 100 8 T T", "RED: 99 8 T T", "GREEN: 78 6 F T", "YELLOW: 75 6 F F"};
	   String gamesWithProbability[] = l.sortByOdds(rules);
	   for(String games: gamesWithProbability){
		   System.out.println(games);
	   }
	}
	
	public String[] sortByOdds(String[] rules) {
		Map<Long, ArrayList<String>> gameAndProbabilityOfLosing = new TreeMap<>();
		List<String> allGames = new ArrayList<>();
		
		for(String rule : rules){
			String[] game = rule.split(":");
			String name_of_the_game = game[0];
			String attributes = game[1].trim();
			String split_attributes[] = attributes.split(" ");
			int choices = Integer.parseInt(split_attributes[0]);
			int blanks = Integer.parseInt(split_attributes[1]);
			String isSorted = split_attributes[2];
			String isUnique = split_attributes[3];
			long probability_of_losing = 0;
			
			if(isSorted.equals("F") && isUnique.equals("F")){
				probability_of_losing = (long)Math.pow(choices, blanks);
			}else if(isSorted.equals("T") && isUnique.equals("F")){
				// (choices + blanks -1)Cr = (n + r -1)! / r!(n-1)!
				probability_of_losing = nCr(choices + blanks -1, blanks);
			}else if(isSorted.equals("F") && isUnique.equals("T")){
				//basically order does matter
				//choices P blanks = choices! / (choices - blanks)!
				probability_of_losing = nCr(choices, blanks) * factorial(blanks);
			}
			else{//both are true
				//choices C blanks = choices! / (choices - blanks)!blanks!
				probability_of_losing = nCr(choices, blanks);
			}
			
			if(gameAndProbabilityOfLosing.containsKey(probability_of_losing)){
				ArrayList<String> gamesWithSameProbability = gameAndProbabilityOfLosing.get(probability_of_losing);
				gamesWithSameProbability.add(name_of_the_game);
				gameAndProbabilityOfLosing.put(probability_of_losing, gamesWithSameProbability);
			}else{
				ArrayList<String> games = new ArrayList<>();
				games.add(name_of_the_game);
				gameAndProbabilityOfLosing.put(probability_of_losing, games);
			}
		}
		for(java.util.Map.Entry<Long, ArrayList<String>> entry : gameAndProbabilityOfLosing.entrySet()){
			ArrayList<String> games = entry.getValue();
			Collections.sort(games);
			allGames.addAll(games);
		}
	    String games_string[] = new String[allGames.size()];
	    games_string = allGames.toArray(games_string);
		return games_string;
	}
	
	//calulate it using equation : nCr = n-1Cr-1 + n-1Cr
	//nCn = 1; nC0 =1 ; nC1 = n
	public long nCr(int n, int r){
		long array[][] = new long[n+1][r+1];
		for(int i = 0 ; i <= n; i++){
			for(int j = 0 ;j <= Math.min(i,r); j++){
				if(j==i || j==0){
					array[i][j] = 1;
				}else{
					array[i][j] = array[i-1][j-1] + array[i-1][j];
				}
			}
		}
		return array[n][r];
	}
	
	public int factorial(int r){
		if(r==1 || r==0){
			return 1;
		}else{
			return r * factorial(r-1);
		}
	}

}
