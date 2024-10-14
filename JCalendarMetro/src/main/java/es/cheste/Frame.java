package es.cheste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JCalendar;

public class Frame extends JFrame {

    private JComboBox<String> mesComboBox;
    private JCalendar calendario;
    private JButton recargarButton;

    public Frame() {

        setTitle("Recarga de Abono Mensual de Transporte");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        mesComboBox = new JComboBox<>(meses);

        calendario = new JCalendar();

        recargarButton = new JButton("Recargar");
        recargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesSeleccionado = (String) mesComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null,
                        "Abono de " + mesSeleccionado + " recargado con éxito.",
                        "Recarga Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecciona el mes:"));
        topPanel.add(mesComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(calendario, BorderLayout.CENTER);
        add(recargarButton, BorderLayout.SOUTH);
    }
}
