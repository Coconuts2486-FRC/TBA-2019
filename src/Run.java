import java.io.File;
import Data.Secretkeys;
import GUI.MainGUI;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void main(String[] args) throws Exception {
		// Startup
		
		File baseFile = new File(basedir);
		baseFile.mkdir();
		Secretkeys.startup();
		
		MainGUI.run();
		
	}

}