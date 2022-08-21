package pantallas;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LaminaLogin extends JPanel{
	
	private MarcoLogin marcoLogin;

	public LaminaLogin(MarcoLogin marcoLogin) {
		
		setLayout(new BorderLayout());
		
		this.marcoLogin = marcoLogin;
		
		JButton jButton1 = new JButton("Siguiente");
		JButton jButton2 = new JButton("Cancelar");
		JButton jButton3 = new JButton("Crear nueva cuenta");
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				MarcoRegistro marcoRegistro = new MarcoRegistro(marcoLogin);
				setVisible(false);
				
			}
		});
		
		JPanel jPanel1 = new JPanel();
		
		jPanel1.add(jButton2);
		
		JPanel jPanel2 = new JPanel();
				
		Box verticalBox2 = Box.createVerticalBox();			
		
		JTextField jTextField1 = new JTextField("Introduce tu ID o correo electrónico.");
		
		jTextField1.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				if(jTextField1.getText().length() == 0) {
					jButton1.setEnabled(false);
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				jButton1.setEnabled(true);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
						
		jTextField1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				jTextField1.setText("Introduce tu ID o correo electrónico.");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				jTextField1.setText("");
			}
		});
		
		
		jTextField1.setPreferredSize(new Dimension(300,30));
		jButton1.setMaximumSize(new Dimension(300,30));
		jButton3.setMaximumSize(new Dimension(300,30));
		
		jButton1.setEnabled(false);
		
		verticalBox2.add(jTextField1);
		verticalBox2.add(Box.createVerticalStrut(10));
		verticalBox2.add(jButton1);		
		verticalBox2.add(Box.createVerticalStrut(10));
		verticalBox2.add(jButton3);
		
		jPanel2.add(verticalBox2);
		
		add(jPanel1, BorderLayout.SOUTH);
		add(jPanel2, BorderLayout.CENTER);
		
	}
	
}
