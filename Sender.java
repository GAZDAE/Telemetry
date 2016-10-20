import java.net.*;
import java.io.*;
public class Sender {
	public static void main (String [] args ) throws IOException {

        Socket socket = new Socket("155.31.71.146",9010);
		InputStream is = socket.getInputStream();
		// TODO change this part
		File transferFile = new File ("C:\\Users\\ngieminv\\Downloads\\Whatheckyoudoing.gif");
		 byte [] bytearray1  = new byte [(int)transferFile.length()];
		 FileInputStream fin = new FileInputStream(transferFile);
		 BufferedInputStream bin = new BufferedInputStream(fin);
		 bin.read(bytearray1,0,bytearray1.length);
		 OutputStream os = socket.getOutputStream();
		 System.out.println("Sending Files...");
		 os.write(bytearray1,0,bytearray1.length);
		 os.flush();
		 socket.close();
		 System.out.println("File transfer complete");
	}
}