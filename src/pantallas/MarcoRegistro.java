package pantallas;

import javax.swing.JFrame;

public class MarcoRegistro extends JFrame{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 4089193305549219358L;

	public MarcoRegistro(MarcoLogin marcoLogin) {
		
		setBounds(700,200,580,720);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Pantalla de registro");
		
		LaminaRegistro laminaRegistro = new LaminaRegistro();
		
		add(laminaRegistro);
		
		setVisible(true);
		
	}
	
}
