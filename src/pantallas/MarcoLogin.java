package pantallas;

import javax.swing.JFrame;

public class MarcoLogin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4292314621939171218L;

	public MarcoLogin() {
		
		setBounds(700,300,500,400);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Login Screen");
		
		LaminaLogin laminaLogin = new LaminaLogin(this);
		
		add(laminaLogin);
		
		setVisible(true);
		
	}
	
}



