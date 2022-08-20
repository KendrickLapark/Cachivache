package pantallas;

import javax.swing.JFrame;

public class MarcoRegistro extends JFrame{

	public MarcoRegistro() {
		
		setBounds(700,300,500,600);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Pantalla de registro");
		
		LaminaRegistro laminaRegistro = new LaminaRegistro();
		
		add(laminaRegistro);
		
		setVisible(true);
		
	}
	
}
