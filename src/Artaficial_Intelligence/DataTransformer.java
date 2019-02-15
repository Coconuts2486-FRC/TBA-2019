package Artaficial_Intelligence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

public class DataTransformer {
	public static DataSet ArrayListToDataSet(String filePath, int inputs, int outputs,boolean hasHeader) throws FileNotFoundException {
		ArrayList<ExampleStructure> data = CSVtoArrayList(filePath, inputs, outputs, hasHeader);
		INDArray input = Nd4j.zeros(data.size(), inputs);
		System.out.println(data.size()+"   "+inputs);
		INDArray labels = Nd4j.zeros(data.size(), outputs);
		
		for(int i = 0; i<data.size();i++) {
			for(int ie = 0;ie<inputs;ie++) {
				input.putScalar(new int[]{i, ie}, data.get(i).inputs.get(ie));
			}
			for(int ie = 0;ie<outputs;ie++) {
				labels.putScalar(new int[]{i, ie}, data.get(i).outputs.get(ie));
			}
		}
		DataSet ds = new DataSet(input,labels);
		return ds;
	}
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
				Double a = Double.parseDouble(items[ie]);
				exam.inputs.add(a);
			}
			for(int iee = items.length-outputs; iee<items.length;iee++) {
				Double b = Double.parseDouble(items[iee]);
				exam.outputs.add(b);
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
