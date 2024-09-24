import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Frame extends JFrame {

    private JTextField campoTexto; // se utiliza para mostrar los cambios en el tipo de letra

    private Font tipoLetraSimple; // tipo de letra para texto simple
    private Font tipoLetraNegrita; // tipo de letra para texto en negrita
    private Font tipoLetraCursiva; // tipo de letra para texto en cursiva
    private Font tipoLetraNegritaCursiva; // tipo de letra para texto en negrita y cursiva
    private Font fuenteActual; // almacena la fuente actual

    private JRadioButton simpleJRadioButton; // selecciona texto simple
    private JRadioButton negritaJRadioButton; // selecciona texto en negrita
    private JRadioButton cursivaJRadioButton; // selecciona texto en cursiva
    private JRadioButton negritaCursivaJRadioButton; // negrita y cursiva
    private ButtonGroup grupoOpciones; // grupo de botones que contiene los botones de opción

    private JCheckBox checkBoxFuente; // CheckBox para alternar entre Arial y Papyrus
    private Font fuenteArial; // Fuente Arial
    private Font fuentePapyrus; // Fuente Papyrus (usando una alternativa si no está disponible)

    public Frame() {

        setSize(600, 200);
        setTitle("Prueba de RadioButton y CheckBox");
        setLayout(new GridLayout(3, 1, 10, 10));
        campoTexto = new JTextField("Esto es Arial o es Calibri", 28);
        add(campoTexto);

        simpleJRadioButton = new JRadioButton("Simple", true );
        negritaJRadioButton = new JRadioButton("Negrita", false );
        cursivaJRadioButton = new JRadioButton("Cursiva", false );
        negritaCursivaJRadioButton = new JRadioButton("Negrita/Cursiva", false );

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        add(panelButtons);
        panelButtons.add(simpleJRadioButton);
        panelButtons.add(negritaJRadioButton);
        panelButtons.add(cursivaJRadioButton);
        panelButtons.add(negritaCursivaJRadioButton);

        grupoOpciones = new ButtonGroup();
        grupoOpciones.add(simpleJRadioButton);
        grupoOpciones.add(negritaJRadioButton);
        grupoOpciones.add(cursivaJRadioButton);
        grupoOpciones.add(negritaCursivaJRadioButton);

        tipoLetraSimple = new Font("Serif", Font.PLAIN, 14);
        tipoLetraNegrita = new Font("Serif", Font.BOLD, 14);
        tipoLetraCursiva = new Font("Serif", Font.ITALIC, 14);
        tipoLetraNegritaCursiva = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
        fuenteActual = tipoLetraSimple;
        campoTexto.setFont(fuenteActual);

        simpleJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraSimple));
        negritaJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraNegrita));
        cursivaJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraCursiva));
        negritaCursivaJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraNegritaCursiva));

        JPanel panelFuente = new JPanel();
        panelFuente.setLayout(new FlowLayout());
        checkBoxFuente = new JCheckBox("Usar fuente alternativa");

        fuenteArial = new Font("Arial", Font.PLAIN, 14);
        fuentePapyrus = new Font("Calibri", Font.PLAIN, 14);

        panelFuente.add(checkBoxFuente);
        add(panelFuente);

        checkBoxFuente.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (checkBoxFuente.isSelected()) {

                    cambiarFuente(fuentePapyrus);

                } else {

                    cambiarFuente(fuenteArial);

                }
            }
        });
    }

    private void cambiarFuente(Font nuevaFuente) {
        fuenteActual = nuevaFuente;
        campoTexto.setFont(fuenteActual);
    }

    private class ManejadorBotonOpcion implements ItemListener {

        private Font tipoLetra;

        public ManejadorBotonOpcion(Font f) {

            tipoLetra = f;

        }

        public void itemStateChanged(ItemEvent evento) {

            Font nuevaFuente = new Font(fuenteActual.getFontName(), tipoLetra.getStyle(), fuenteActual.getSize());
            campoTexto.setFont(nuevaFuente);

        }
    }
}
