package Data;

public class Formulas {
	public static double averageScore(String teamkey) {
		double average = 0.0;
		for(int i=0;i<GameData.matchdata.get(teamkey).matches.size();i++) {
			if(GameData.matchdata.get(teamkey).matches.get(i).alliance.equals("red")) {
				average+=GameData.matchdata.get(teamkey).matches.get(i).redData.score;
			}else {
				average+=GameData.matchdata.get(teamkey).matches.get(i).blueData.score;
			}
		}
		return average/GameData.matchdata.get(teamkey).matches.size();
	}

}
