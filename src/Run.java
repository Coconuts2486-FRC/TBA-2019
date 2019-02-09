public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	public static void main(String[] args) throws Exception {
		String command = "team/frc2486/matches/2018/simple";
		System.out.println(HTTP.APIRead(command));
		JSON_Parsing.AdverageScore(HTTP.APIRead(command));
	}

}
