import java.awt.*;
import javax.swing.*;

public class FrameCombo extends JFrame {

    private JComboBox combo;
    private JLabel label;
    private JPanel panel;
    private String[] imagenes = {"Mercedes-Benz 190E 2.4-16 Evolution II (1990)", "Audi Sport Quattro (1985)", "BMW M3 E30 (1986)", "Lancia 037 Stradale (1982)", "Ford Sierra RS500 Cosworth (1986)"};
    private Icon img;

    public FrameCombo() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 900);
        setTitle("Combo Cars");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        combo = new JComboBox(imagenes);
        panel.add(combo, BorderLayout.NORTH);

        img = new ImageIcon(getClass().getResource("resources/190e_amg_evo.jpg"));
        label = new JLabel();
        label.setText("Mercedes-Benz 190E 2.4-16 Evolution II (1990)");
        label.setIcon(img);
        panel.add(label, BorderLayout.CENTER);

    }

}
