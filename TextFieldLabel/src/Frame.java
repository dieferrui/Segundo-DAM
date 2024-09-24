import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Frame extends JFrame {

    JTextField textField1, textField2;
    JPanel panel = new JPanel();

    public Frame() {

        setTitle("Ejemplo de JTextField");
        setSize(600,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(3, 2, 30, 10));

        JLabel label1 = new JLabel("Escribe algo y presiona return...", JLabel.LEFT);
        textField1 = new JTextField(20);
        textField1.addActionListener(new TextFieldListener());
        textField1.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label2 = new JLabel("Retorno de texto", JLabel.CENTER);
        textField2 = new JTextField(20);
        textField2.setBorder(BorderFactory.createLineBorder(Color.blue));

        JLabel label3 = new JLabel("Borra los textos...", JLabel.RIGHT);
        JButton button = new JButton("Limpiar");
        button.addActionListener(new ButtonListener());

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(button);
        add(panel);

    }

    class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String text = textField1.getText();
            textField2.setText(text);

        }
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            textField1.setText("");
            textField1.requestFocus();
            textField2.setText("");

        }
    }
}
