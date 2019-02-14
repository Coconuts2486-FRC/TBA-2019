package Artaficial_Intelligence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataTransformer {
	public static ArrayList<ExampleStructure> CSVtoArrayList(String filePath, int inputs, int outputs,boolean hasHeader) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		ArrayList<String> layers = new ArrayList<String>();
		ArrayList<ExampleStructure> out = new ArrayList<ExampleStructure>();
		
		
		while(sc.hasNextLine()) {
			String data = sc.nextLine();
			layers.add(data);
		}
		if(hasHeader) {
			layers.remove(0);
		}else {
			
		}
		for(int i = 0;i<layers.size();i++) {
			ExampleStructure exam = new ExampleStructure();
			String[] items;
			items=layers.get(i).split(",");
			if(inputs+outputs == items.length) {
			for(int ie = 0; ie<inputs;ie++) {
				exam.inputs.add(Double.parseDouble(items[ie]));
			}
			for(int iee = items.length-outputs; iee<items.length;iee++) {
				exam.outputs.add(Double.parseDouble(items[iee]));
			}
			}else {
				System.out.println("Error: The inputs and outputs do not add up to total enteries");
				System.out.println("Number of enteries: "+items.length);
				System.out.println("You entered: "+(outputs+inputs));
				break;
			}
			out.add(exam);
		}
		return out;
	}
}
