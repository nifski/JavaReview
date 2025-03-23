import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.awt.event.ActionListener ;
import javax.swing.*;





public class LoginPage implements ActionListener {// not too sure yet
	
	JFrame frame = new JFrame();
	// i believe this will make button instances
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField username = new JTextField();
	JPasswordField userpassword = new JPasswordField();
	JLabel usernameLabel = new JLabel("username: ");
	JLabel userpasswordLabel = new JLabel("password: ");
	// these are all different types of buttons that will be used in the log in process
	// now i will make the message that lets the user know if they were successful
	JLabel messageLabel = new JLabel();
	
	
	//  i need to create a constructor for LoginPage so that they are ready to recieve hashmap values
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfoOriginal) {
		//i want to make a copy of the login info, so that it will exist in this world**
		logininfo = loginInfoOriginal;
			// remember logInfo is the name of the hashmap so we will equate it to logInfoOriginal. This will now put the values (i think it will be same username and password) into logInfoOriginal which is a copy
		
		usernameLabel.setBounds(50,100,75,25); // x, y, width, height
		userpasswordLabel.setBounds(50,150,75,25) ;

				messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 25 ));

		username.setBounds(125,100,200,25);
		userpassword.setBounds(125,150,200,25);

		loginButton.setBounds(125,200,100,25);
		loginButton.setFocusable(false); // this just changes the shadow on the button when pressed
		loginButton.addActionListener(this);

		resetButton.setBounds(225,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this); // what exactly does the action listener do? and why do we need it here?


		frame.add(usernameLabel);
		frame.add(userpasswordLabel);
		frame.add(messageLabel);// dont forget to add this frame because you made the message label up there
		frame.add(username);
		frame.add(userpassword);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this will exit out of the button the user presses
		frame.setSize(420,420); // this is the size but im not sure what size it is for
		frame.setLayout(null); // why do we use null? Why cant I use a manager
		frame.setVisible(true); // this si so we can see it
	}
	// i believe this is the over ride not sure what e is for
	public void actionPerformed(ActionEvent e ) {

		if(e.getSource() == resetButton) {
			username.setText("");
			userpassword.setText("");
			// this will perform the task of clearing both username and passowrd when the reset button is clicked
		}
		if(e.getSource()==loginButton) {

			String usernameText = username.getText();
			String passwordText = String.valueOf(userpassword.getPassword());
			// this will get the password, the password isnt a string, so we have to use .valueOf to get the vlue of what is in that field


			if(logininfo.containsKey(usernameText)) {
				 if(logininfo.get(usernameText).equals(passwordText)) {
					 messageLabel.setForeground(Color.GREEN);
					 messageLabel.setText("logged in");
					 frame.dispose(); // this is to stop two windows (login page and wlecome page) from being open, dispose will close the login page
					 WelcomePage welcomePage = new WelcomePage(usernameText);
				 }
				 else {
					 messageLabel.setForeground(Color.RED);
					 messageLabel.setText("try again, invalid credentials");
				 }
			}
			else {
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("username not found");
			}
		}
	}
	
}