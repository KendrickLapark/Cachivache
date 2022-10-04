package pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;
import java.util.Date;

import javax.swing.*;

import layouts.FormularioLayout;

/*
 * 
 */

public class LaminaRegistro extends JPanel {

	private static final long serialVersionUID = 1830945354837387527L;

	private int dia, mes, año, cuentaPuntos, diasMesAnterior, diasAEliminar;
	
	private static int codigo_usuario = 0;

	private boolean correoCorrecto, telefonoCorrecto;

	private JPanel jPanel1, jPanel2;

	private ArrayList<JComponent> componentes;

	private TreeMap<Integer, Object> registros;

	private TreeMap<Integer, JTextField> lJTextFields;

	private String[] campos = { "Nombre", "Apellidos", "Fecha de nacimiento", "Correo electrónico", "Localidad",
			"Provincia", "País", "Domicilio", "Nombre de usuario", "Contraseña", "Confirmación", "Teléfono" };

	public LaminaRegistro() {

		dia = 1;
		mes = 1;
		año = 1900;
		diasAEliminar = 0;

		componentes = new ArrayList<>();

		registros = new TreeMap<>();

		lJTextFields = new TreeMap<>();

		setLayout(new BorderLayout());

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();

		jPanel1.setLayout(new FormularioLayout());

		JComboBox<Integer> jComboBox1 = new JComboBox<Integer>();
		JComboBox<Integer> jComboBox2 = new JComboBox<Integer>();
		JComboBox<Integer> jComboBox3 = new JComboBox<Integer>();

		for (int i = 1; i <= 31; i++) {
			jComboBox1.addItem(i);
			if (i < 13) {
				jComboBox2.addItem(i);
			}
		}

		for (int i = 1900; i <= 2022; i++) {
			jComboBox3.addItem(i);
		}

		diasMesAnterior = jComboBox1.getItemCount();

		iniciaComponentes(campos, 0, 2);

		componentes.add(new JLabel(campos[2]));

		componentes.add(jComboBox1);
		componentes.add(jComboBox2);
		componentes.add(jComboBox3);

		componentes.add(new JTextField());

		iniciaComponentes(campos, 3, 12);

		JTextField jTextFieldFecha = (JTextField) componentes.get(8);

		jTextFieldFecha.setFocusable(false);

		lJTextFields.put(2, jTextFieldFecha);

		jComboBox1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dia = Integer.parseInt(jComboBox1.getSelectedItem().toString());

				jTextFieldFecha.setText(getFecha(dia, mes, año));

			}
		});

		jComboBox2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				diasMesAnterior = jComboBox1.getItemCount();

				mes = Integer.parseInt(jComboBox2.getSelectedItem().toString());

				jTextFieldFecha.setText(getFecha(dia, mes, año));

				int diasMes = getDíasDelMes(mes, año);

				diasAEliminar = diasMesAnterior - diasMes;

				if (diasAEliminar > 0) {
					for (int i = 1; i <= diasAEliminar; i++) {
						jComboBox1.removeItemAt(jComboBox1.getItemCount() - 1);
					}
				} else {
					System.out.println(-diasAEliminar);

					for (int i = 1; i <= -diasAEliminar; i++) {
						jComboBox1.addItem(jComboBox1.getItemCount() + 1);
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

				int diasMes = getDíasDelMes(mes, año);

				diasAEliminar = diasMesAnterior - diasMes;

				jTextFieldFecha.setText(getFecha(dia, mes, año));

				if (diasAEliminar > 0) {
					for (int i = 1; i <= diasAEliminar; i++) {
						jComboBox1.removeItemAt(jComboBox1.getItemCount() - 1);
					}
				} else {
					System.out.println(-diasAEliminar);

					for (int i = 1; i <= -diasAEliminar; i++) {
						jComboBox1.addItem(jComboBox1.getItemCount() + 1);
					}
				}

			}
		});

		for (JComponent c : componentes) {
			jPanel1.add(c);
		}

		JButton jButton1 = new JButton("Aceptar");
		JButton jButton2 = new JButton("Cancelar");

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(validaCampos(lJTextFields)) {					
					
					String cadenaRegistro = "insert into usuarios values(";
					
					int codigoBD = consultaCodigoUsuario();
					
					int codigoNuevoRegistro = (codigoBD + 1);
					
					String cadenaCodigo = Integer.toString(codigoNuevoRegistro);
					
					String valoresRegistro = "";					
					
					Set entrySet = registros.entrySet();
					
					Iterator<Object> it = entrySet.iterator();
					
					while(it.hasNext()) {
						
						Map.Entry me = (Map.Entry) it.next();
						
						System.out.println("Número clave treemap "+me.getKey()+" texto "+me.getValue());
						
						if((Integer)me.getKey() != 11) {
							valoresRegistro+=", '"+me.getValue()+"'";	
						}else {
							valoresRegistro+=", "+me.getValue();
						}
																		
					}
					
					valoresRegistro+=");";
					
					String insertSQL = cadenaRegistro + cadenaCodigo + valoresRegistro;
					
					System.out.println("El insert quedaría así : ");
					
					System.out.println(insertSQL);
					
					insertRegistroDB(insertSQL);	
					
				}else {
					System.err.println("Hay errores en el registro.");
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

		jPanel2.add(jButton1);
		jPanel2.add(jButton2);

		add(jPanel1, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);

		jTextFieldFecha.setText(getFecha(dia, mes, año));

	}

	public int getDíasDelMes(int mes, int año) {

		int diasMes = 0;

		Date date = new Date();

		String fechaAEvaluar = new SimpleDateFormat("01/0" + mes + "/" + año).format(date);

		int mesAEvaluar = Integer.parseInt(fechaAEvaluar.substring(3, 5));
		int añoAEvaluar = Integer.parseInt(fechaAEvaluar.substring(7, 10));

		YearMonth yearMonth = YearMonth.of(añoAEvaluar, mesAEvaluar);

		diasMes = yearMonth.lengthOfMonth();

		return diasMes;

	}

	public String getFecha(int dias, int mes, int año) {

		String fecha = "";

		if (dias < 10) {
			if (mes < 10) {
				fecha = "0" + dias + "/0" + mes + "/" + año;
			} else {
				fecha = "0" + dias + "/" + mes + "/" + año;
			}
		} else {
			if (mes < 10) {
				fecha = dias + "/0" + mes + "/" + año;
			} else {
				fecha = dias + "/" + mes + "/" + año;
			}

		}

		return fecha;

	}

	public boolean validaCorreo(String correo) {

		String prefijo = "";

		if (!correo.contains("@") || !correo.contains(".") || correo.length() < 13) {
			System.err.println("Correo incorrecto. No es un correo electrónico.");

			return correoCorrecto = false;

		} else {

			int posicionArroba = correo.indexOf('@');

			prefijo = correo.substring(0, correo.indexOf('@'));

			String dominio = (String) correo.subSequence(posicionArroba + 1, correo.length());

			if (prefijo.length() < 8) {

				System.err.println(
						"Error. La dirección de correo electrónico debe tener al menos 8 caracteres de longitud.");

				return correoCorrecto = false;
			}

			cuentaPuntos = 0;

			if (dominio.length() < 4) {

				System.err.println("Error. El dominio no se encuentra.");

				return correoCorrecto = false;

			} else {

				cuentaPuntos = 0;

				for (int i = 0; i < dominio.length(); i++) {

					String muestraDominio = Character.toString(dominio.charAt(i));

					if (muestraDominio.equals(".")) {

						cuentaPuntos++;

						if (dominio.indexOf(".") < 1 || dominio.indexOf(".") >= (dominio.length() - 2)) {

							System.err.println("Error, el punto esta mal colocado. "
									+ "No puede situarse al principio ni al final del dominio.");

							return correoCorrecto = false;

						}

					}

				}

				if (cuentaPuntos != 1) {

					System.err.println("Dominio incorrecto.");

					return correoCorrecto = false;
				}

			}

			String prohibidos = "()<>@,;:\"[]ç%&";

			for (int i = 0; i < prefijo.length(); i++) {

				String muestra = Character.toString(correo.charAt(i));

				for (int j = 0; j < prohibidos.length(); j++) {

					if (muestra.equals(Character.toString(prohibidos.charAt(j)))) {

						System.err.println("Error, carácteres prohibidos. "
								+ "La dirección de correo no puede contener los siguientes caracteres: " + prohibidos);

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

				if (muestra.equals(Character.toString(prohibidos.charAt(j)))) {

					System.out.println("Error, entrada incorrecta: " + entrada);

					return correoCorrecto = false;
				}

			}

		}

		return true;
	}

	public boolean validaContraseña(String contraseña) {

		if (contraseña.length() < 8 || contraseña.length() > 16) {

			System.err.println("Error. La contraseña debe tener entre 8 y 16 caracteres de longitud");

			return false;

		}

		return true;

	}

	private boolean validaTelefono(String telefono) {

		String prohibidos = "ºª@·#~$%&¬()='?¡¿`^+*´¨_.:,;¨{}[]€<>abcdefghijklmnñopqrstuvwxyz-";

		int numValidado = 0;

		if (telefono.length() != 9) {

			System.err.println("Error. El número de teléfono debe tener 9 dígitos.");

			return telefonoCorrecto = false;

		} else {

			for (int i = 0; i < telefono.length(); i++) {

				String muestra = Character.toString(telefono.charAt(i));

				for (int j = 0; j < prohibidos.length(); j++) {
					if (muestra.equals(Character.toString(prohibidos.charAt(j)))) {

						System.err.println("Teléfono incorrecto, el número de teléfono sólo puede contener números.");

						return false;

					}
				}

			}

			return telefonoCorrecto = true;
		}

	}

	private void iniciaComponentes(String[] campos, int inicio, int fin) {

		String clave = "";

		for (int i = inicio; i < fin; i++) {
			JLabel jLabel = new JLabel(campos[i]);
			JTextField jTextField = new JTextField();

			componentes.add(jLabel);
			componentes.add(jTextField);

			lJTextFields.put(i, jTextField);

		}

	}

	private boolean validaCampos(TreeMap lJTextFields) {

		String contraseña = "";

		Set entrySet = lJTextFields.entrySet();

		Iterator it = entrySet.iterator();

		System.out.println("Método de validación");

		while (it.hasNext()) {

			Map.Entry me = (Map.Entry) it.next();

			JTextField jTextField = (JTextField) me.getValue();

			int numJTF = (Integer) me.getKey();

			if (jTextField.getText().equals("")) {

				System.err.println("El campo " + numJTF + " esta vacío");

				return false;

			} else {

				if (numJTF != 2) {

					if (numJTF == 3) {

						if (validaCorreo(jTextField.getText())) {
							registros.put(numJTF, jTextField.getText());
						} else {
							return false;
						}

					} else if (numJTF == 9) {

						if (validaContraseña(jTextField.getText())) {
							contraseña = jTextField.getText();
						} else {
							System.err.println("Error, la contraseña debe ser de entre 8 y 16 dígitos.");

							return false;
						}

					} else if (numJTF == 10) {

						if (contraseña.equals(jTextField.getText())) {

							registros.put(numJTF, jTextField.getText());

						} else {
							System.err.println("Error, las contraseñas no coinciden");

							return false;
						}

					} else if (numJTF == 11) {

						if (validaTelefono(jTextField.getText())) {

							registros.put(numJTF, jTextField.getText());

						} else {

							return false;

						}

					}else {
						if(validaEntrada(jTextField.getText())){
							registros.put(numJTF, jTextField.getText());
						}else {
							return false;
						}
					}

				} else {
					String fechaAFormatear = jTextField.getText();

					String fechaFormateada = fechaAFormatear.replace("/", "");

					String prefijo = fechaFormateada.substring(0, 2);

					String raiz = fechaFormateada.substring(2, 4);

					String sufijo = fechaFormateada.substring(4, fechaFormateada.length());

					String fechaValida = sufijo + raiz + prefijo;
					
					registros.put(numJTF, fechaValida);

				}
			}

		}

		return true;

	}

	public void insertRegistroDB(String insertSQL) {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");

			Statement statement = connection.createStatement();

			int insert = statement.executeUpdate(insertSQL);			
			
			statement.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int consultaCodigoUsuario() {
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
			
			Statement statement = connection.createStatement();
			
			String consultaCódigo = "select max(usuario_id) from usuarios;";
			
			ResultSet resultSet = statement.executeQuery(consultaCódigo);
			
			while(resultSet.next()) {
				codigo_usuario = resultSet.getInt(1);
			}
			
			System.out.println("Código de base de datos: "+codigo_usuario);

			resultSet.close();
			
			statement.close();
			
			connection.close();			
			
			return codigo_usuario;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
		
	}

}
