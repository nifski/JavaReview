import java.util.HashMap;
public class IDandPassword {
	//here i will put a hashmap, to store key value pairs(username, password) obviously as strings
	HashMap<String,String> logininfo = new HashMap<String,String> ();
	//this will act like a container i believe to hold the two values that we getting which are username and password. Adding the new HashMap allows us to contain the numerous username and passwords we will have. The name is loginfo. I believe it might feed into the main method, Im not too sure how but we'll see.
	// now i will create a constructor** (not too sure why)
	// i am also not sure why i had to add put to the statement
	IDandPassword(){
		logininfo.put("Nifski1234", "Pizza");
		logininfo.put("Pharmer101", "abc123");
		logininfo.put("Nifemi", "PASSWORD");
	}
	//now i will create getters and setters because we want any class thats needs this info to be able to have access to it
	protected HashMap getLoginInfo(){
		return logininfo;// is this a method?
		//Hashmap is the type i want getLoginfo in
		// secondly, i gave it a protected privacy status NOT private because not just anybody can call this method.

		//any time the login info is called, the method will return the values. 
		//Im not sure if i understand the syntax to type gtters and setters yet
	}
		
	
}