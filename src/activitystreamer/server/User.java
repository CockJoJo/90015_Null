package activitystreamer.server;

import java.util.HashMap;

public class User {
	
	private static HashMap<String, String> user = new HashMap<String, String>();
	private static String message;
	private String userName;
	
	private static boolean existUserName(String userName){
		if (user.containsKey(userName)){
			return true;
		}
		else return false;
	}
	
	public static void setUser(String userName, String secret){
		if(existUserName(userName)){
			 //user name already registered
		}else{
			user.put(userName, secret);
		}
	}
	
	public boolean checkUser(String userName, String secret){
		if(!existUserName(userName)){
			message="command : LOGIN_FAILED, \ninfo : user name does not exist";   //User name does not exist."
			return false;
		}else if(user.get(userName)!=secret){
			message="command : LOGIN_FAILED, \ninfo : attempt to login with wrong secret";   //Secret does not match userName
			return false;
		}else{
			message="logged in as user "+userName;
			this.userName = userName;
			return true;
		}
	}
	
	public String getMsg(){              //return message, and set message to null
		String msg =  message;
		message = null;
		return msg;
	}
	
	public String getUserName(){        //return useName, and set userName to null
		String usn = userName;
		userName = null;
		return usn;
	}

	public static void main(String[] args) {		
		user.put("smy", "123");        //for testing
		User u1 = new User();
		u1.checkUser("smy", "123");
		System.out.print(u1.getMsg()+"\nuserName: "+u1.getUserName());
	}

}
