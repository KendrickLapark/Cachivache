package pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.*;

import layouts.FormularioLayout;

/*
 * 
 */

public class LaminaRegistro extends JPanel{
	
	private static final long serialVersionUID = 1830945354837387527L;
	
	private int dia, mes, año, cuentaPuntos;
	private int diasMesAnterior, diasAEliminar;
	
	private boolean correoCorrecto;
	
	private JPanel jPanel1, jPanel2;
	
	private String [] datos =  {"Nombre", "Apellidos", "Fecha de nacimiento","Localidad","Provincia","País","Domicilio",
			"Nombre de usuario", "Contraseña", "Confirmación"};

	public LaminaRegistro() {
	
		dia = 1;
		mes = 1;
		año = 1900;				
		diasAEliminar = 0;
		
		setLayout(new BorderLayout());
		
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		
		jPanel1.setLayout(new FormularioLayout());				
		
		JLabel jLabel1 = new JLabel("Nombre");
		JLabel jLabel2 = new JLabel("Apellidos");
		JLabel jLabel3 = new JLabel("Fecha de nacimiento");
		JLabel jLabel4 = new JLabel("Localidad");
		JLabel jLabel5 = new JLabel("Provincia");
		JLabel jLabel6 = new JLabel("País");
		JLabel jLabel7 = new JLabel("Domicilio");
		JLabel jLabel8 = new JLabel("Correo electrónico");
		JLabel jLabel9 = new JLabel("Nombre de usuario");
		JLabel jLabel10 = new JLabel("Contraseña");
		JLabel jLabel11 = new JLabel("Confirmación");
		
		JButton jButton1 = new JButton("Aceptar");
		JButton jButton2 = new JButton("Cancelar");
		
		JTextField jTextField1 = new JTextField();
		JTextField jTextField2 = new JTextField();
		JTextField jTextField3 = new JTextField(getFecha(1,1,1900));
		JTextField jTextField4 = new JTextField();
		JTextField jTextField5 = new JTextField();
		JTextField jTextField6 = new JTextField();
		JTextField jTextField7 = new JTextField();
		JTextField jTextField8 = new JTextField();
		JTextField jTextField9 = new JTextField();
		JTextField jTextField10 = new JTextField();
		JTextField jTextField11 = new JTextField();
		
		jTextField3.setFocusable(false);
		
		jButton1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(validaCorreo(jTextField8.getText())) {
							System.out.println("Correo electrónico válido.");
						}else {
							System.out.println("Correo electrónico no válido.");
						}
						
						if(validaEntrada(jTextField1.getText())) {
							
						}else {
							
						}
						
					}
				});
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.exit(0);
			}
			
		});
		
		JComboBox<Integer> jComboBox1 = new JComboBox<Integer>();
		JComboBox<Integer> jComboBox2 = new JComboBox<Integer>();
		JComboBox<Integer> jComboBox3 = new JComboBox<Integer>();
		
		for (int i = 1; i <= 31; i++) {
			jComboBox1.addItem(i);
			if(i<13) {
				jComboBox2.addItem(i);
			}
		}
		
		for(int i = 1900; i <= 2022; i++) {
			jComboBox3.addItem(i);
		}
		
		diasMesAnterior = jComboBox1.getItemCount();
		
		jComboBox1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
								
				dia = Integer.parseInt(jComboBox1.getSelectedItem().toString());
							
				jTextField3.setText(getFecha(dia, mes, año));				
			
			}
		});		
		
		jComboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
										
				diasMesAnterior = jComboBox1.getItemCount();				
				
				mes = Integer.parseInt(jComboBox2.getSelectedItem().toString());
				
				jTextField3.setText(getFecha(dia, mes, año));
								 
				int diasMes=getDíasDelMes(mes, año);
				
				diasAEliminar = diasMesAnterior - diasMes;
				
				if(diasAEliminar > 0) {
					for(int i = 1; i <= diasAEliminar; i++) {
						jComboBox1.removeItemAt(jComboBox1.getItemCount()-1);
					}
				}else {
					System.out.println(-diasAEliminar);
					
					for(int i = 1; i <= -diasAEliminar; i++) {
						jComboBox1.addItem(jComboBox1.getItemCount()+1);
					}
				}

			}
		});

		jComboBox3.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				
				diasMesAnterior = jComboBox1.getItemCount();
				
				año = Integer.parseInt(jComboBox3.getSelectedItem().toString());
				
				int diasMes=getDíasDelMes(mes, año);
				
				diasAEliminar = diasMesAnterior - diasMes;
				
				jTextField3.setText(getFecha(dia, mes, año));									
												
				if(diasAEliminar > 0) {
					for(int i = 1; i <= diasAEliminar; i++) {
						jComboBox1.removeItemAt(jComboBox1.getItemCount()-1);
					}
				}else {
					System.out.println(-diasAEliminar);
					
					for(int i = 1; i <= -diasAEliminar; i++) {
						jComboBox1.addItem(jComboBox1.getItemCount()+1);
					}
				}

			}
		});
			
		jPanel1.add(jLabel1);
		jPanel1.add(jTextField1);
		jPanel1.add(jLabel2);		
		jPanel1.add(jTextField2);
		jPanel1.add(jLabel3);
		jPanel1.add(jComboBox1);
		jPanel1.add(jComboBox2);
		jPanel1.add(jComboBox3);
		jPanel1.add(jTextField3);
		jPanel1.add(jLabel4); 
		jPanel1.add(jTextField4);
		jPanel1.add(jLabel5);
		jPanel1.add(jTextField5);
		jPanel1.add(jLabel6);
		jPanel1.add(jTextField6);
		jPanel1.add(jLabel7);
		jPanel1.add(jTextField7);
		jPanel1.add(jLabel8);
		jPanel1.add(jTextField8);
		jPanel1.add(jLabel9);
		jPanel1.add(jTextField9);
		jPanel1.add(jLabel10);
		jPanel1.add(jTextField10);
		jPanel1.add(jLabel11);
		jPanel1.add(jTextField11);
				
		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		
		add(jPanel1, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);
		
		jTextField3.setText(getFecha(dia, mes, año));
				
	}
	
	public int getDíasDelMes(int mes, int año) {
		
		int diasMes = 0;
		
		Date date = new Date();
		
		String fechaAEvaluar = new SimpleDateFormat("01/0"+mes+"/"+año).format(date);
		
		int mesAEvaluar = Integer.parseInt(fechaAEvaluar.substring(3, 5));
		int añoAEvaluar = Integer.parseInt(fechaAEvaluar.substring(7, 10));
		
		YearMonth yearMonth = YearMonth.of(añoAEvaluar, mesAEvaluar);
		
		diasMes = yearMonth.lengthOfMonth();
		
		return diasMes;
		
	}
	
	public String getFecha(int dias, int mes, int año) {
		
		String fecha = "";
		
		if(dias<10) {
			if(mes<10) {
				fecha = "0"+dias+"/0"+mes+"/"+año;
			}else {
				fecha = "0"+dias+"/"+mes+"/"+año;
			}		
		}else {	
			if(mes<10) {
				fecha = dias+"/0"+mes+"/"+año;
			}else {
				fecha = dias+"/"+mes+"/"+año;
			}
			
		}		
			
		return fecha;						
		
	}
	
public boolean validaCorreo(String correo) {
						
		String prefijo = "";
		
		if(!correo.contains("@") || !correo.contains(".") || correo.length() < 13) {
			System.out.println("Correo incorrecto. No es un correo electrónico.");
			
			return correoCorrecto = false;
			
		}else {
			
			int posicionArroba = correo.indexOf('@');
			
			prefijo = correo.substring(0, correo.indexOf('@'));
			
			String dominio =(String) correo.subSequence(posicionArroba+1, correo.length());
			
			if(prefijo.length()<8) {
				
				System.out.println("Error. La dirección debe tener al menos 8 caracteres de longitud.");
				
				return correoCorrecto = false;
			}
			
			cuentaPuntos = 0;
			
			if(dominio.length()<4) {
				
				System.out.println("Error. El dominio no se encuentra.");
				
				return correoCorrecto = false;
				
			}else {
				
				cuentaPuntos = 0;
							
				for (int i = 0; i < dominio.length(); i++) {
					
					String muestraDominio = Character.toString(dominio.charAt(i));
					
					if(muestraDominio.equals(".")) {
						
						cuentaPuntos++;									
						
						if(dominio.indexOf(".") < 1 || dominio.indexOf(".") >= (dominio.length()-2)) {
							
							System.out.println("Error, el punto esta mal colocado. "
									+ "No puede situarse al principio ni al final del dominio.");
							
							return correoCorrecto = false;
														
						}
						
					}
					
				}
				
				if(cuentaPuntos != 1) {
					
					System.out.println("Dominio incorrecto.");
					
					return correoCorrecto = false;
				}
				
			}
			
			String prohibidos = "()<>@,;:\"[]ç%&";				
			
			for (int i = 0; i < prefijo.length(); i++) {
				
				String muestra = Character.toString(correo.charAt(i));
				
				for (int j = 0; j < prohibidos.length(); j++) {
					
					if(muestra.equals(Character.toString(prohibidos.charAt(j)))) {
						
						System.out.println("Error, carácteres prohibidos. "
								+ "La dirección de correo no puede contener los siguientes caracteres: "+prohibidos);
						
						return correoCorrecto = false;
					}
					
				}
			}			
			
			System.out.println("Correo correcto.");
			
			return correoCorrecto = true;
		}		
		
	}

	public boolean validaEntrada(String entrada) {
		
		String prohibidos = "1234567890ºª@·#~$%&¬()='?¡¿`^+*´¨_.:,;¨{}[]€<>";
		
		for (int i = 0; i < entrada.length(); i++) {
			
			String muestra = Character.toString(entrada.charAt(i));
			
			for (int j = 0; j < prohibidos.length(); j++) {
				
				if(muestra.equals(Character.toString(prohibidos.charAt(j)))) {
					
					System.out.println("Error, entrada incorrecta.");
					
					return correoCorrecto = false;
				}
				
			}
			
		}
		
		return true;
	}

}
