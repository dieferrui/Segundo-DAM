package es.cheste;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    public Frame() {

        setTitle("Ejemplos de Swing con tanques");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 3));

        Border marginBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);


        JButton messageDialogButton = new JButton("MessageDialog");
        messageDialogButton.setBorder(marginBorder);
        messageDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialogExample();
            }
        });

        JButton confirmButton = new JButton("ConfirmDialog");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showConfirmDialogExample();
            }
        });

        add(messageDialogButton);
        add(confirmButton);
    }

    private void showMessageDialogExample() {
        JOptionPane.showMessageDialog(this, "El T-34 está en el garaje, listo para la acción.",
                "MessageDialog", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showConfirmDialogExample() {
        int option = JOptionPane.showConfirmDialog(this, "La reparación del M4 Sherman costará 1000 créditos. ¿Desea continuar?",
                "ConfirmDialog", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
