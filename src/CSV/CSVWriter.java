package CSV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import Data.GameData;


public class CSVWriter {
public static void WriteGameData(String filepath) throws IOException {
	 BufferedWriter writer = Files.newBufferedWriter(Paths.get(filepath));
	CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("TeamKey", "Match Number","Score","Auto Points","Teleop Points",
			"Bay1","Bay2","Bay3","Bay4","Bay5","Bay6","Bay7","Bay8",
			"Cargo Points","Rocket RP","End Position Robot 1","End Position Robot 2","End Position Robot 3","Fouls","Tech Fouls",
			"Hab Line Robot 1","Hab Line Robot 2","Hab Line Robot 3",
			"lowLeftRocketFar","lowLeftRocketNear","lowRightRocketFar","lowRightRocketNear",
			"midLeftRocketFar","midLeftRocketNear","midRightRocketFar","midRightRocketNear",
			"topLeftRocketFar","topLeftRocketNear","topRightRocketFar","topRightRocketNear",
			"Pre Match Level Robot 1","Pre Match Level Robot 2","Pre Match Level Robot 3"));
	
	for(int i = 0;i<GameData.matchdata.size();i++) {
		for(int ie = 0;ie<GameData.matchdata.get(GameData.teamkeys.get(i)).size();ie++) {
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).alliance.equals("blue")) {
			csv.printRecord(GameData.teamkeys.get(i).replaceAll("frc", ""),GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).matchNumber,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.score,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.autoScore,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.teleopPoints,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay4,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay5,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay6,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay7,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.bay8,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.cargoPoints,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.completeRocketRankingPoint,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.endgameRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.endgameRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.endgameRobot3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.foulCount,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.techFoulCount,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.habLineRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.habLineRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.habLineRobot3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.lowLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.lowLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.lowRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.lowRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.midLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.midLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.midRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.midRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.topLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.topLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.topRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.topRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.preMatchLevelRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.preMatchLevelRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).blueData.preMatchLevelRobot3);
			}
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).alliance.equals("red")) {
				csv.printRecord(GameData.teamkeys.get(i).replaceAll("frc", ""),GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).matchNumber,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.score,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.autoScore,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.teleopPoints,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay4,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay5,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay6,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay7,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.bay8,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.cargoPoints,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.completeRocketRankingPoint,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.endgameRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.endgameRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.endgameRobot3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.foulCount,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.techFoulCount,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.habLineRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.habLineRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.habLineRobot3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.lowLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.lowLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.lowRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.lowRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.midLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.midLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.midRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.midRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.topLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.topLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.topRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.topRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.preMatchLevelRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.preMatchLevelRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).redData.preMatchLevelRobot3);
			}
		}
	}
	csv.flush();
}
}
