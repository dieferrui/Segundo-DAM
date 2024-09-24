import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Frame extends JFrame {

    private JTextField campoTexto; // se utiliza para mostrar los cambios en el tipo de letra

    private Font tipoLetraSimple; // tipo de letra para texto simple
    private Font tipoLetraNegrita; // tipo de letra para texto en negrita
    private Font tipoLetraCursiva; // tipo de letra para texto en cursiva
    private Font tipoLetraNegritaCursiva; // tipo de letra para texto en negrita y cursiva

    private JRadioButton simpleJRadioButton; // selecciona texto simple
    private JRadioButton negritaJRadioButton; // selecciona texto en negrita
    private JRadioButton cursivaJRadioButton; // selecciona texto en cursiva
    private JRadioButton negritaCursivaJRadioButton; // negrita y cursiva
    private ButtonGroup grupoOpciones; // grupo de botones que contiene los botones de opción


    public Frame() {

        setSize(800, 80);
        setTitle("Prueba de RadioButton");
        setLayout(new FlowLayout());
        campoTexto = new JTextField("Observe el cambio en el estilo de la letra", 28);
        add(campoTexto);

        simpleJRadioButton = new JRadioButton("Simple", true );
        negritaJRadioButton = new JRadioButton("Negrita", false );
        cursivaJRadioButton = new JRadioButton("Cursiva", false );
        negritaCursivaJRadioButton = new JRadioButton("Negrita/Cursiva", false );

        add(simpleJRadioButton);
        add(negritaJRadioButton);
        add(cursivaJRadioButton);
        add(negritaCursivaJRadioButton);

        grupoOpciones = new ButtonGroup();
        grupoOpciones.add(simpleJRadioButton);
        grupoOpciones.add(negritaJRadioButton);
        grupoOpciones.add(cursivaJRadioButton);
        grupoOpciones.add(negritaCursivaJRadioButton);

        tipoLetraSimple = new Font("Serif", Font.PLAIN, 14);
        tipoLetraNegrita = new Font("Serif", Font.BOLD, 14);
        tipoLetraCursiva = new Font("Serif", Font.ITALIC, 14);
        tipoLetraNegritaCursiva = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
        campoTexto.setFont(tipoLetraSimple);

        simpleJRadioButton.addItemListener(
                new ManejadorBotonOpcion(tipoLetraSimple));
        negritaJRadioButton.addItemListener(
                new ManejadorBotonOpcion(tipoLetraNegrita));
        cursivaJRadioButton.addItemListener(
                new ManejadorBotonOpcion(tipoLetraCursiva));
        negritaCursivaJRadioButton.addItemListener(
                new ManejadorBotonOpcion(tipoLetraNegritaCursiva));
    }

    private class ManejadorBotonOpcion implements ItemListener {

        private Font tipoLetra;
        public ManejadorBotonOpcion(Font f) {

            tipoLetra = f;

        }

        public void itemStateChanged(ItemEvent evento) {

            campoTexto.setFont(tipoLetra);

        }
    }
}