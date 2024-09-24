import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Frame extends JFrame {

    JTextField textField1, textField2, textField3;
    JPanel panel = new JPanel();

    public Frame() {

        setTitle("Conversor de Celsius a Kelvin y Fahrenheit");
        setSize(600,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(4, 2, 30, 10));

        JLabel label1 = new JLabel("Introduzca grados Celsius y presione Return...", JLabel.CENTER);
        textField1 = new JTextField(20);
        textField1.addActionListener(new TextFieldListener());
        textField1.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label2 = new JLabel("Equivalencia Kelvin", JLabel.CENTER);
        textField2 = new JTextField(20);
        textField2.setBorder(BorderFactory.createLineBorder(Color.blue));

        JLabel label3 = new JLabel("Equivalencia Fahrenheit", JLabel.CENTER);
        textField3 = new JTextField(20);
        textField2.setBorder(BorderFactory.createLineBorder(Color.red));

        JButton button = new JButton("Limpiar");
        button.addActionListener(new ButtonListener());

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(button);
        add(panel);

    }

    class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String text = textField1.getText();

            try {

                double celsius = Double.parseDouble(text);
                double kelvin = celsius + 273.15;
                double fahrenheit = celsius * 9/5 + 32;
                textField2.setText(String.valueOf(kelvin));
                textField3.setText(String.valueOf(fahrenheit));

            } catch (NumberFormatException ex) {

                textField2.setText("Error");

            }
        }
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            textField1.setText("");
            textField1.requestFocus();
            textField2.setText("");
            textField3.setText("");

        }
    }
}
