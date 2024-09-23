import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    public Frame() {

        setTitle("Hola!!!");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.red);

        JButton boton = new JButton("Azul?");
        Dimension d = new Dimension(100, 40);
        boton.setPreferredSize(d);
        panel.add(boton);

        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boton.setText("Azul.");
                panel.setBackground(Color.blue);

            }

        });

    }
}
