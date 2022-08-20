package pantallas;

import javax.swing.JFrame;

public class MarcoLogin extends JFrame{

	public MarcoLogin() {
		
		setBounds(700,300,500,400);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Login Screen");
		
		LaminaLogin laminaLogin = new LaminaLogin();
		
		add(laminaLogin);
		
		setVisible(true);
		
	}
	
}



