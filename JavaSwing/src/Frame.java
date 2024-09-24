import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    public Frame() {

        setTitle("Hola!!!");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.red);

        JButton botonNorte = new JButton("Azul");
        add(botonNorte, BorderLayout.NORTH);

        JButton botonSur = new JButton("Verde");
        add(botonSur, BorderLayout.SOUTH);

        JButton botonEste = new JButton("Rosa");
        add(botonEste, BorderLayout.EAST);

        JButton botonOeste = new JButton("Amarillo");
        add(botonOeste, BorderLayout.WEST);

        botonNorte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.setBackground(Color.blue);

            }

        });

        botonSur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.setBackground(Color.green);

            }

        });

        botonEste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.setBackground(Color.pink);

            }

        });

        botonOeste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.setBackground(Color.yellow);

            }

        });

    }
}
