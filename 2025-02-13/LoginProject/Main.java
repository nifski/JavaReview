public class Main{
	public static void main(String[] args){
		//here i will make an instance of my id and password. 
		//so that i can create more
		IDandPassword idandPassword = new IDandPassword();
		//i always end statements with a parenthesis and semi colon
		//i declared the variable*, gave it a name, and assigned it "new" IDandPassword which gives it an instance, i think making it an object?

		LoginPage loginPage = new LoginPage(idandPassword.getLoginInfo());

        //i will send arguements from login info (username and password) into my log in page
// secondly i will type the instance of the class which is logininfo and i will add the dot operator (not sure why) and then type the getLoginInfo which will just feed the values into each new log in page
//  i also need to create a constructor for LoginPage so that they are ready to recieve hashmap values
		
	}
}