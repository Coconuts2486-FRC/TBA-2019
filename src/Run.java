import java.io.File;
import java.util.ArrayList;
import Data.GameData;
import GUI.MainGUI;
import Internet.HTTP;
import Internet.PingTBA;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void main(String[] args) throws Exception {
		File baseFile = new File(basedir);
		System.out.println(baseFile.exists());
		baseFile.mkdir();
		System.out.println(baseFile.exists());
		MainGUI GUI = new MainGUI();
		PingTBA ping = new PingTBA();
		GUI.Ping=ping.ping();
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		GUI.run();
		System.out.println("Done");

		/*
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,6}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,6}));
		*/
	}

}