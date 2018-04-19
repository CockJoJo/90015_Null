package activitystreamer.server;

import java.util.ArrayList;

public class Login {
		
	private ArrayList<String> logInUser = new ArrayList<String>();
	User u1 = new User();
	static int logInState = 0;      //0: haven't log in; 1: logged in; 2: logged in as anonymous
	
	public void logIn(String userName, String secret){
		if(userName=="anonymous"){
			setState(2);
			  //---------------------to be done
		}else if(u1.checkUser(userName, secret)){
			logInUser.add(userName);
			setState(1);
		}else{
			u1.checkUser(userName, secret);
		}
		System.out.println(u1.getMsg());   // where to put this message?
	}
	
	
	public void logOut(){
		logInUser.remove(u1.getUserName());
	}
	
	private void setState(int i){
		logInState = i;
	}
	
	public static int getState(){
		return logInState;
	}

	
	

	public static void main(String[] args) {
		User.setUser("yms", "321");              //for test
		Login l1 = new Login();
		l1.logIn("yms", "321");
		System.out.println("logInUser: "+l1.logInUser);
		l1.logOut();
		System.out.println("logInUser: "+l1.logInUser);

	}

}
