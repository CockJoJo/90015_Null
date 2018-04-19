package activitystreamer;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Activity_Message {

    public InputStream ins;  
    public OutputStream ous;  
  
    
	    static public void sendMsg(OutputStream os, String s) throws IOException {  
	        // send message to client  
	        byte[] bytes = s.getBytes();  
	        os.write(bytes);  
	        os.write(13);  
	        os.write(10);  
	        os.flush();  
	    }  
	  
	    // read the messages from the client 
	    static public String readMsg(InputStream ins) throws Exception {  

	        int value = ins.read();  
	        // read entire line, and when read the return"/r"(ASCII is 13) or the newline"/n"(ASCII value is 10), it stop read
	        String str = "";  
	        while (value != 10) {  
	            // when close the client, the value will return -1 
	            if (value == -1) {  
	                throw new Exception();  
	            }  
	            str = str + ((char) value);  
	            value = ins.read();  
	        }  
	        str = str.trim();  
	        return str;  
	    }  
}
