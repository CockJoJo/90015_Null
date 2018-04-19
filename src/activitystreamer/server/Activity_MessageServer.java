package activity_Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Activity_MessageServer {

	public static void ActivityMessageServer(String username, String secret, int port, User user) {

		ServerSocket listeningSocket = null;
		Socket clientSocket = null;

		try {
			// Create a server socket listening on port 4444
			listeningSocket = new ServerSocket(port);

			// Listen for incoming connections for ever
			while (true) {
				// Accept an incoming client connection request
				clientSocket = listeningSocket.accept(); // This method will block until a connection request is
															// received

				// Get the input/output streams for reading/writing data from/to the socket
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

				// Read the message from the client and reply
				// Notice that no other connection can be accepted and processed until the last
				// line of
				// code of this loop is executed, incoming connections have to wait until the
				// current
				// one is processed unless...we use threads!
				String clientMsg = null;
				try {
					Login l1 = new Login();
					l1.logIn(username, secret);
					while ((clientMsg = in.readLine()) != null) {

						 if (!User.checkUser(username, secret)) {
							System.out.printf("Wrong information");
							clientSocket.close();
						} else if (l1.getState()!=2) {
							System.out.printf("Please login.");
							clientSocket.close();
						} else  {
							System.out.println(clientMsg);
							out.flush();
						} 
					}
				}
			catch (SocketException e) {
					System.out.println("closed...");
				}
				clientSocket.close();
			}

		} catch (SocketException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (listeningSocket != null) {
				try {
					listeningSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
