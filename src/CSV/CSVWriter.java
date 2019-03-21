package CSV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import Artificial_Intelligence.DeepNetworkAbilities;
import Data.GameData;


public class CSVWriter {
	public void WritePredictedData(String filepath) throws IOException {
		 BufferedWriter writer = Files.newBufferedWriter(Paths.get(filepath));
		CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
				"TeamKey","RocketHatch Low","RocketHatch Mid","RocketHatch High","RocketCargo Low",
				"RocketCargo Mid","RocketCargo High","CargoShip Hatches","CargoShip Cargo"));
		Object[] keys = GameData.matchdata.keySet().toArray();
		for(int i = 0; i< keys.length;i++) {
			if(GameData.matchdata.get(keys[i]).matches.size()>4) {
				csv.printRecord(keys[i]
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(0)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(1)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(2)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(3)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(4)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(5)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(6)
						,DeepNetworkAbilities.calculate((String) keys[i]).getDouble(7));
				
			}else {
				csv.printRecord(keys[i],"Not Enough Matches Played");
			}
			csv.flush();
		}
	}
	public void WriteGameData(String filepath) throws IOException {
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
		for(int ie = 0;ie<GameData.matchdata.get(GameData.teamkeys.get(i)).matches.size();ie++) {
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).alliance.equals("blue")) {
			csv.printRecord(GameData.teamkeys.get(i).replaceAll("frc", ""),GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).matchNumber,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.score,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.autoScore,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.teleopPoints,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay4,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay5,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay6,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay7,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.bay8,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.cargoPoints,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.completeRocketRankingPoint,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.endgameRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.endgameRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.endgameRobot3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.foulCount,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.techFoulCount,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.habLineRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.habLineRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.habLineRobot3,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.lowLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.lowLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.lowRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.lowRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.midLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.midLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.midRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.midRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.topLeftRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.topLeftRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.topRightRocketFar,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.topRightRocketNear,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.preMatchLevelRobot1,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.preMatchLevelRobot2,
					GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).blueData.preMatchLevelRobot3);
			}
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).alliance.equals("red")) {
				csv.printRecord(GameData.teamkeys.get(i).replaceAll("frc", ""),GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).matchNumber,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.score,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.autoScore,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.teleopPoints,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay4,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay5,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay6,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay7,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.bay8,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.cargoPoints,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.completeRocketRankingPoint,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.endgameRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.endgameRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.endgameRobot3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.foulCount,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.techFoulCount,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.habLineRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.habLineRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.habLineRobot3,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.lowLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.lowLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.lowRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.lowRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.midLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.midLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.midRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.midRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.topLeftRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.topLeftRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.topRightRocketFar,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.topRightRocketNear,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.preMatchLevelRobot1,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.preMatchLevelRobot2,
						GameData.matchdata.get(GameData.teamkeys.get(i)).matches.get(ie).redData.preMatchLevelRobot3);
			}
		}
	}
	csv.flush();
}
}
