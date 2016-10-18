import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{ 
	
	
	private static int portNum;		// Port numbers
	private static int fileSize;	// Size of the file in bytes
	
	private int bytesRead;
	private int currentTot;
	
	public static String dataType = " ";	// Type of file
	public static String fileName = " ";	// Name of the file
	
	// Create a socket object that will be used for communications
	public Socket socket = new Socket();
	
	
	// No-argument constructor
	public Client(){
	}
	
	
	/**
	 * Constructor to make an object with specified port number
	 * Sets incoming data type to .txt by default
	 * Sets file name to newFile.txt
	 * Sets max file size to be about 5 MB
	 * @param port
	 */
	public Client(int port){
		
		setPortNum(port);
		currentTot = 0;
		fileSize = 1048576 *5;
		setDataType(".txt");
		setFileName("newFile");
	}
	
	
	
	/**
	 * Method that starts communications by creating server socket object
	 * Allow to accept incoming connections
	 * @throws IOException
	 */
	public void startComm() throws IOException{
		
		// Create a server socket with specified port number
		ServerSocket serverSocket = new ServerSocket(portNum); 
		// Allow socket to accept connections
		socket = serverSocket.accept(); 
		System.out.println("Accepted connection : " + socket);
	}
	
	
	/**
	 * Method that receives data in terms of bytes from a ground station
	 * Writes received data to a new file and saves it locally
	 * @throws IOException
	 */
	public void writeToFile() throws IOException{
		
		// Create new byte array based on a file type
		byte [] bytearray = new byte [fileSize];
		
		// Get input stream of data
		InputStream is = socket.getInputStream();
		
		// TODO
		FileOutputStream fos = new FileOutputStream(fileName);
		// TODO
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		// TODO
		bytesRead = is.read(bytearray,0,bytearray.length); 
		currentTot = bytesRead; 
		
		// Write received streak of bytes to a new file and save it locally
		System.out.println("Writing to a new file");
		do { 
			bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot)); 
			if(bytesRead >= 0) currentTot += bytesRead; 
		} while(bytesRead > -1); 
		
		// TODO
		bos.write(bytearray, 0 , currentTot); 
		bos.flush(); 
		bos.close(); 
		System.out.println("Done");
	}
	
	
	/**
	 * Method to close the socket
	 * @throws IOException
	 */
	public void closeSocket() throws IOException{
		socket.close(); 
	}
	
	
	/**
	 * Method to set the port number
	 * @param newPort
	 */
	public void setPortNum(int newPort){
		portNum = newPort;
	}
	
	
	/**
	 * Method to set the maximum size of the incoming file
	 * File size is set in bits
	 * @param newSize
	 */
	public void setFileSize(int newSize){
		fileSize = newSize;
	}
	
	
	/**
	 * Method to set the type of the incoming data
	 * Replaces old string with a new string
	 * @param newType - a string, .txt for example
	 */
	public void setDataType(String newType){
		dataType = dataType.replace(dataType, newType);
	}
	
	
	/**
	 * Method to set the name of the file
	 * Whole file name is set to have new name + data type
	 * Replaces old string with a new string
	 * Ex. FileName.txt
	 * @param newName
	 */
	public void setFileName(String newName){
		fileName = fileName.replace(fileName, newName + dataType);
	}
	
	
	// RUN THE MAIN MOTHERFUCKA
	public static void main (String [] args ) throws IOException { 
		
		Client cl = new Client(9010);
		cl.startComm();
		cl.writeToFile();
		cl.closeSocket();
		
	} 
}

 