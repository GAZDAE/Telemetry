import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class Manager{

    static public Sender SimClient;
	
	public static void main(String[] args) throws IOException {
		Path dir = Paths.get("C:\\Users\\parkere2\\Documents\\Tester");//args[dirArg]);
		SimClient = new Sender("155.31.132.70", 7010);
		new WatchDir(dir, true).processEvents();
	}
}