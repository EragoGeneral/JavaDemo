package csvfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVUtil {
	
	public static void main(String[] args) throws IOException {
		File f = new File("F://test.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));  //附加
		bw.write("\"`张三\",\"`00091111\",\"`12345412343\"");
//		bw.write("\"李四\"" + "," + "\"1988\"" + "," + "\"1992\"");  
		bw.newLine();
		bw.write("\"`刘永福\""+","+"\"`00220311\""+","+"\"`983311102\"");
		bw.close();
	}
}
