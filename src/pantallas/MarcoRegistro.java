package pantallas;

import javax.swing.JFrame;

public class MarcoRegistro extends JFrame{

	public MarcoRegistro(MarcoLogin marcoLogin) {
		
		setBounds(700,300,580,600);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Pantalla de registro");
		
		LaminaRegistro laminaRegistro = new LaminaRegistro(this);
		
		add(laminaRegistro);
		
		setVisible(true);
		
	}
	
}
