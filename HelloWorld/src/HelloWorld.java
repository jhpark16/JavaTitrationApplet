import edu.duke.*;
import java.io.File;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FileResource res = new FileResource("hello_unicode.txt");
/*		URLResource res = new URLResource("https://www.nytimes.com");
		for (String line: res.lines()){
			System.out.println(line);
		}
		//System.out.println("Hello World");
		*/
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			System.out.println(f);
		}
	}
}
