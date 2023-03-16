package loginframe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public static void main(String[] args) {
    LoginFrame ventana = new LoginFrame(); 
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setResizable(false);
      ventana.setSize(500, 500); 
      ventana.setVisible(true);
    }

    // Declarar los componentes del panel
    private final JPanel panel;
    private final JLabel userLabel;
    private final JLabel passLabel;
    private JTextField userField;
    private JTextField passField;
    private final JButton loginButton;
    private final JButton showButton;

    // Declarar el archivo donde se guardará el login
    private File file;

    // Constructor de la clase
    public LoginFrame() {
        // Inicializar el panel y sus componentes
        panel = new JPanel();
        userLabel = new JLabel("Usuario:");
        passLabel = new JLabel("Contraseña:");
        userField = new JTextField(10);
        passField = new JTextField(10);
        loginButton = new JButton("Iniciar sesión");
        showButton = new JButton("Mostrar datos");

        // Agregar los componentes al panel con un layout de GridLayout
        panel.setLayout(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(showButton);

        // Agregar el panel al frame
        this.add(panel);

        // Inicializar el archivo donde se guardará el login
        String cad = "";
        char temp;
        String carpeta = System.getProperty("user.dir");
        for (int i=0; i<carpeta.length();i++){
            temp = carpeta.charAt(i);
            if (temp == 92){
                temp = 47;
                cad = cad + temp;
            } else {
                cad = cad + temp;
            }
        }
        carpeta = cad + "/src/archivos";
        System.out.println(carpeta);

        // Agregar un ActionListener al botón de iniciar sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el usuario y la contraseña ingresados por el usuario
                String user = userField.getText();
                String pass = passField.getText();

                // Verificar que no estén vacíos
                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un usuario y una contraseña válidos.");
                } else {
                    try {
                        // Crear un objeto FileWriter para escribir en el archivo
                        FileWriter writer = new FileWriter(file);

                        // Escribir el usuario y la contraseña separados por una coma
                        writer.write(user + "," + pass);

                        // Cerrar el writer
                        writer.close();

                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Se ha guardado el login en el archivo.");

                    } catch (IOException ex) {
                        // Mostrar un mensaje de error si ocurre una excepción de entrada/salida
                        JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el login en el archivo.");
                    }
                }
            }
        });
    }
}
