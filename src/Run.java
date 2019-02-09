public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	public static void main(String[] args) throws Exception {
		System.out.println(HTTP.APIRead("status"));
		JSON_Parsing.test(HTTP.APIRead("status"));
	}

}
