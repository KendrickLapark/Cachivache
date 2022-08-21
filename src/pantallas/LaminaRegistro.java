package pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import layouts.FormularioLayout;

public class LaminaRegistro extends JPanel{
	
	private MarcoRegistro marcoRegistro;
	private String dia, mes, a�o;

	public LaminaRegistro(MarcoRegistro marcoRegistro) {

		this.marcoRegistro = marcoRegistro;
		
		dia = "1";
		mes = "1";
		a�o = "1900";
		
		setLayout(new BorderLayout());
		
		JPanel jPanel1 = new JPanel();
		JPanel jPanel2 = new JPanel();
		
		jPanel1.setLayout(new FormularioLayout());
				
		JLabel jLabel1 = new JLabel("Nombre");
		JLabel jLabel2 = new JLabel("Apellidos");
		JLabel jLabel3 = new JLabel("Fecha de nacimiento");
		JLabel jLabel4 = new JLabel("Localidad");
		JLabel jLabel5 = new JLabel("Provincia");
		JLabel jLabel6 = new JLabel("Pa�s");
		JLabel jLabel7 = new JLabel("Domicilio");
		JLabel jLabel8 = new JLabel("Nombre de usuario");
		JLabel jLabel9 = new JLabel("Contrase�a");
		JLabel jLabel10 = new JLabel("Confirmaci�n");
		
		JButton jButton1 = new JButton("Aceptar");
		JButton jButton2 = new JButton("Cancelar");
		
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
				marcoRegistro.setVisible(false);
			}
		});
		
		JTextField jTextField1 = new JTextField();
		JTextField jTextField2 = new JTextField();
		JTextField jTextField3 = new JTextField("01/01/1900");
		JTextField jTextField4 = new JTextField();
		JTextField jTextField5 = new JTextField();
		JTextField jTextField6 = new JTextField();
		JTextField jTextField7 = new JTextField();
		JTextField jTextField8 = new JTextField();
		JTextField jTextField9 = new JTextField();
		JTextField jTextField10 = new JTextField();
		
		jTextField3.setFocusable(false);
		
		JComboBox jComboBox1 = new JComboBox();
		JComboBox jComboBox2 = new JComboBox();
		JComboBox jComboBox3 = new JComboBox();
		
		jComboBox1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println("D�a seleccionados: "+jComboBox1.getSelectedItem());

				dia = jComboBox1.getSelectedItem().toString();
				
				jTextField3.setText(getFecha(dia, mes, a�o));
				
			
			}
		});
		
		jComboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println("Mes seleccionados: "+jComboBox2.getSelectedItem());
				
				mes = 0+jComboBox2.getSelectedItem().toString();
				
				jTextField3.setText(getFecha(dia, mes, a�o));
				
				getD�asDelMes(mes, a�o);
				
				System.out.println("D�as de febrero del a�o seleccionado "+getD�asDelMes(mes, a�o));
			}
		});

		jComboBox3.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println("A�o seleccionados: "+jComboBox3.getSelectedItem());
				
				a�o = jComboBox3.getSelectedItem().toString();
				
				jTextField3.setText(getFecha(dia, mes, a�o));
				
				getD�asDelMes(mes, a�o);
				
				System.out.println("D�as de febrero del a�o seleccionado "+getD�asDelMes(mes, a�o));

			}
		});
		
		for (int i = 1; i <= 31; i++) {
			jComboBox1.addItem(i);
			if(i<13) {
				jComboBox2.addItem(i);
			}
		}
		
		for(int i = 1900; i <= 2022; i++) {
			jComboBox3.addItem(i);
		}
		
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
		
		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		
		add(jPanel1, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);
		
		jTextField3.setText(getFecha(dia, mes, a�o));
		
		System.out.println(jComboBox1.getSelectedItem());
		
	}
	
	public int getD�asDelMes(String mes, String a�o) {
		
		int diasMes = 0;
		
		Date date = new Date();
		
		String fechaAEvaluar = new SimpleDateFormat("01/"+mes+"/"+a�o).format(date);
		
		int dia = Integer.parseInt(fechaAEvaluar.substring(0, 2));
		int mesAEvaluar = Integer.parseInt(fechaAEvaluar.substring(3, 5));
		int a�oAEvaluar = Integer.parseInt(fechaAEvaluar.substring(6, 10));
		
		YearMonth yearMonth = YearMonth.of(a�oAEvaluar, mesAEvaluar);
		
		diasMes = yearMonth.lengthOfMonth();
		
		System.out.println("D�as del mes : "+mes+" "+diasMes);
		
		return diasMes;
		
	}
	
	public String getFecha(String dias, String mes, String a�o) {
		
		String fecha = "";
		
		if(Integer.parseInt(dias)<10) {

			fecha = "0"+dias+"/"+mes+"/"+a�o;
		}else {
			if(Integer.parseInt(mes)<10) {
				fecha = dias+"/0"+mes+"/"+a�o;

			}else {
				fecha = dias+"/"+mes+"/"+a�o;

			}
		}
		
		return fecha;						
		
	}
	
}
