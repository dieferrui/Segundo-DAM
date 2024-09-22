import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {

        setTitle("Hola!!!");
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.red);

        JButton boton = new JButton("Azul?");
        // boton.addActionListener(new EscuchadorBoton());
        Dimension d = new Dimension();
        d.height = 40;
        d.width = 100;
        boton.setPreferredSize(d);
        panel.add(boton);

    }
}
