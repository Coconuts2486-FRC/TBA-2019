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
	CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("TeamKey", "Match Number"));
	
	for(int i = 0;i<GameData.matchdata.size();i++) {
		for(int ie = 0;ie<GameData.matchdata.get(GameData.teamkeys.get(i)).size();ie++) {
			//GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie)
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).alliance.equals("blue")) {
			csv.printRecord(GameData.teamkeys.get(i),GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).matchNumber);
			}
			if(GameData.matchdata.get(GameData.teamkeys.get(i)).get(ie).alliance.equals("red")) {
				
			}
		}
	}
	csv.flush();
}
}
