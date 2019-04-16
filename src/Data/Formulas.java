package Data;

import java.util.ArrayList;

public class Formulas {
	public static String averageScore(String teamkey) {
		double average = 0.0;
		double max = 0.0;
		double min = 10000.0;
		for(int i=0;i<GameData.matchdata.get(teamkey).matches.size();i++) {
			if(GameData.matchdata.get(teamkey).matches.get(i).alliance.equals("red")) {
				average+=GameData.matchdata.get(teamkey).matches.get(i).redData.score;
				if(GameData.matchdata.get(teamkey).matches.get(i).redData.score>max) {
					max=GameData.matchdata.get(teamkey).matches.get(i).redData.score;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).redData.score<min) {
					min=GameData.matchdata.get(teamkey).matches.get(i).redData.score;
				}
			}else {
				average+=GameData.matchdata.get(teamkey).matches.get(i).blueData.score;
				if(GameData.matchdata.get(teamkey).matches.get(i).blueData.score>max) {
					max=GameData.matchdata.get(teamkey).matches.get(i).blueData.score;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).blueData.score<min) {
					min=GameData.matchdata.get(teamkey).matches.get(i).blueData.score;
				}
			}
		}
		String out = "Scoring Data Matrix"
				+ "\n    Average:   "+average/GameData.matchdata.get(teamkey).matches.size()
				+ "\n    Max Score: "+max
				+ "\n    Min Score: "+min;
		return out;
	}
	public static void scoringInfo(String teamkey) {
		
	}
	public static String climbInfo(String teamkey) {
		ArrayList<Integer> levelInfo = new ArrayList<Integer>();
		int numberOfMatches = GameData.matchdata.get(teamkey).matches.size();
		int levelOne = 0;
		int levelTwo = 0;
		int levelThree = 0;
		for(int i=0;i<GameData.matchdata.get(teamkey).matches.size();i++) {
			if(GameData.matchdata.get(teamkey).matches.get(i).alliance.equals("red")) {
				if(GameData.matchdata.get(teamkey).matches.get(i).redData.teamkey1.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).redData.endgameRobot1));
					levelOne += 1;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).redData.teamkey2.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).redData.endgameRobot2));
					levelTwo += 1;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).redData.teamkey3.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).redData.endgameRobot3));
					levelThree += 1;
				}
			}else {
				if(GameData.matchdata.get(teamkey).matches.get(i).blueData.teamkey1.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).blueData.endgameRobot1));
					levelOne += 1;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).blueData.teamkey2.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).blueData.endgameRobot2));
					levelTwo += 1;
				}
				if(GameData.matchdata.get(teamkey).matches.get(i).blueData.teamkey3.equals(teamkey)) {
					levelInfo.add(transformer(GameData.matchdata.get(teamkey).matches.get(i).blueData.endgameRobot3));
					levelThree += 1;
				}
			}
		}
		double onePer = ((double)levelOne/(double)numberOfMatches)*100;
		double twoPer = ((double)levelTwo/(double)numberOfMatches)*100;
		double threePer = ((double)levelThree/(double)numberOfMatches)*100;
		String out = "Hab Climb Data Matrix"
				+ "\n    Level One:    "+levelOne
				+ "\n    Level One:   %"+onePer
				+ "\n    Level Two:    "+levelTwo
				+ "\n    Level Two:   %"+twoPer
				+ "\n    Level Three:  "+levelThree
				+ "\n    Level Three: %"+threePer;
		return out;
	}
	public static int transformer(String data) {
		switch(data) {
		case "HabLevel1": return 1;
		case "HabLevel2": return 2;
		case "HabLevel3": return 3;
		default: return 0;
		}
		
	}
}
