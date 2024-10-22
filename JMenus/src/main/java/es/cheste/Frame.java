package es.cheste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class Frame extends JFrame {
    public Frame() {
        setTitle("Ejemplo de Ventana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");

        JMenuItem abrirItem = new JMenuItem("Abrir");
        abrirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Frame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Aquí veremos qué hacemos con el archivo
                    System.out.println("Archivo abierto: " + file.getAbsolutePath());
                }
            }
        });

        JMenuItem guardarItem = new JMenuItem("Guardar");
        guardarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(Frame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Aquí va el código para guardar el archivo
                    System.out.println("Archivo guardado: " + file.getAbsolutePath());
                }
            }
        });

        menuArchivo.add(abrirItem);
        menuArchivo.add(guardarItem);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        ImageIcon abrirIcon = new ImageIcon(getClass().getResource("/Abrir.gif"));
        ImageIcon guardarIcon = new ImageIcon(getClass().getResource("/Guardar.gif"));2


        JButton abrirButton = new JButton(abrirIcon);
        abrirButton.setToolTipText("Abrir");
        abrirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Frame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Aquí veremos qué hacemos con el archivo
                    System.out.println("Archivo abierto: " + file.getAbsolutePath());
                }
            }
        });

        JButton guardarButton = new JButton(guardarIcon);
        guardarButton.setToolTipText("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(Frame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Aquí va el código para guardar el archivo
                    System.out.println("Archivo guardado: " + file.getAbsolutePath());
                }
            }
        });

        toolBar.add(abrirButton);
        toolBar.add(guardarButton);
        add(toolBar, "North");

        JLabel statusBar = new JLabel("Listo");
        add(statusBar, "South");

        setVisible(true);
    }
}

