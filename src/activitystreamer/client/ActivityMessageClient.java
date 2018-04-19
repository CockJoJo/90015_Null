package activitystreamer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import activitystreamer.*;
import activitystreamer.server.User;

//Interactive client that reads input from the command line and sends it to 
//a server 
//This method only can send message once

public class ActivityMessageClient {

	private static OutputStream os;
	
	private ActivityMessageClient(){
	
	}

	public static void sendFromClient(User user, String IP_address, int port) {

		Socket socket = null;
		try {
			// Create a stream socket bounded to any port and connect it to the
			// socket bound to localhost on port
			socket = new Socket(IP_address, port);

			// Get the input/output streams for reading/writing data from/to the socket
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			Scanner scanner = new Scanner(System.in);
			String inputStr = null;

			//While the user input differs from "exit"
			while (!(inputStr = scanner.nextLine()).equals("exit")) {
				
				// Send the input string to the server by writing to the socket output stream
				Activity_Message.sendMsg(os, inputStr);
				// Receive the reply from the server by reading from the socket input stream
				String received = in.readLine(); // This method blocks until there
													// is something to read from the
													// input stream
			}
			
			scanner.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the socket
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
