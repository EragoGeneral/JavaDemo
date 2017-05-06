package basic.file;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	public static void main(String[] args) throws IOException {
		
		String dirPath = "F:\\document\\ediary(平安时期)\\ediary\\日记";
		File dir = new File(dirPath);
		File[] fs = dir.listFiles();
		for(File f : fs){
			String name = f.getName();
			File tmpFile = new File("F:"+File.separator+"tmp"+File.separator+name+".rtf");
			if(f.isDirectory()){
				File[] fs1 = f.listFiles();
				System.out.println(fs1[0]);
				File ff = new File(f.getAbsoluteFile()+File.separator+name+".rtf");
				org.apache.commons.io.FileUtils.copyFile(fs1[0], tmpFile);
				//tmpFile.renameTo(ff);
			}
		}
	}
}
