package es.cheste;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class Editor extends JFrame implements ClipboardOwner {
    JMenuBar jmbarEditor;
    JToolBar jtbarEditor;
    JScrollPane jscrpaneEditor;
    JTextArea jtxtaEditor;
    Clipboard portapapeles;

    JMenu jmnuArchivo;
    JMenuItem jitemSalir;

    JMenu jmnuEdicion;
    JMenuItem jitemCortar;
    JMenuItem jitemCopiar;
    JMenuItem jitemPegar;

    JMenu jmnuOpciones;
    JMenu jmnuFuente;
    JCheckBoxMenuItem jitemCourier;
    JCheckBoxMenuItem jitemArial;
    JCheckBoxMenuItem jitemPredeterminada;
    JMenu jmnuTamanio;
    JRadioButtonMenuItem jitemSize16;
    JRadioButtonMenuItem jitemSize24;
    JRadioButtonMenuItem jitemSizePredeterminado;

    JButton jbtCortar;
    JButton jbtCopiar;
    JButton jbtPegar;

    Font fuentePr;

    public Editor() {
        setSize(520, 300);
        setTitle("Editor de texto");
        initComponents();

        portapapeles = getToolkit().getSystemClipboard();
    }

    private void initComponents() {
        getContentPane().setLayout(null);
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        // JMenuBar and JToolBar
        jmbarEditor = new JMenuBar();
        jtbarEditor = new JToolBar();

        getContentPane().add(jtbarEditor);
        jtbarEditor.setBounds(0, 0, 492, 24);
        setJMenuBar(jmbarEditor);

        // JScrollPanel
        jscrpaneEditor = new JScrollPane();
        getContentPane().add(jscrpaneEditor);
        jscrpaneEditor.setBounds(0, 24, 492, 200);

        jscrpaneEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // JtextArea
        jtxtaEditor = new JTextArea();
        jscrpaneEditor.setViewportView(jtxtaEditor);

        jtxtaEditor.setLineWrap(true);
        jtxtaEditor.setWrapStyleWord(true);

        // Componentes del JMenuBar: Archivo, Edición, Opciones
        // Archivo
        jmnuArchivo = new JMenu("Archivo");

        jitemSalir = new JMenuItem("Salir");
        jitemSalir.setMnemonic('S');
        jitemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        jitemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        jmnuArchivo.add(jitemSalir);

        jmbarEditor.add(jmnuArchivo);

        // Edición
        jmnuEdicion = new JMenu("Edición");
        jmnuEdicion.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent evt) {
                jmnuEdicionMenuSelected(evt);
            }
            public void menuDeselected(MenuEvent evt) {}
            public void menuCanceled(MenuEvent evt) {}
        });

        jitemPegar = new JMenuItem("Pegar");
        jitemPegar.setMnemonic('P');
        jitemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        jitemPegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.paste();
            }
        });
        jmnuEdicion.add(jitemPegar);

        jitemCortar = new JMenuItem("Cortar");
        jitemCortar.setMnemonic('T');
        jitemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        jitemCortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.cut();
            }
        });
        jmnuEdicion.add(jitemCortar);

        jitemCopiar = new JMenuItem("Copiar");
        jitemCopiar.setMnemonic('C');
        jitemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        jitemCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.copy();
            }
        });
        jmnuEdicion.add(jitemCopiar);

        jmbarEditor.add(jmnuEdicion);

        // Opciones
        jmnuOpciones = new JMenu("Opciones");

        // Opciones - Fuente
        jmnuFuente = new JMenu("Fuente");

        jitemCourier = new JCheckBoxMenuItem("Courier");
        jitemCourier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemCourierActionPerformed(evt);
            }
        });

        jitemArial = new JCheckBoxMenuItem("Arial");
        jitemArial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemArialActionPerformed(evt);
            }
        });

        jitemPredeterminada = new JCheckBoxMenuItem("Predeterminada");
        jitemPredeterminada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemPredeterminadaActionPerformed(evt);
            }
        });
        jitemPredeterminada.setSelected(true);

        jmnuFuente.add(jitemCourier);
        jmnuFuente.add(jitemArial);
        jmnuFuente.add(jitemPredeterminada);

        jmnuOpciones.add(jmnuFuente);

        // Opciones - Tamaño
        jmnuTamanio = new JMenu("Tamaño");

        jitemSize16 = new JRadioButtonMenuItem("Tamaño 16");
        jitemSize16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemSize16ActionPerformed(evt);
            }
        });

        jitemSize24 = new JRadioButtonMenuItem("Tamaño 24");
        jitemSize24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemSize24ActionPerformed(evt);
            }
        });

        jitemSizePredeterminado = new JRadioButtonMenuItem("Predeterminado");
        jitemSizePredeterminado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemSizePredeterminadoActionPerformed(evt);
            }
        });
        jitemSizePredeterminado.setSelected(true);

        jmnuTamanio.add(jitemSize16);
        jmnuTamanio.add(jitemSize24);
        jmnuTamanio.add(jitemSizePredeterminado);

        jmnuOpciones.add(jmnuTamanio);

        jmbarEditor.add(jmnuOpciones);

        // Componentes del JToolBar: Cortar, Copiar y Pegar
        jbtCortar = new JButton();
        jbtCortar.setIcon(new ImageIcon(getClass().getResource("/cortar.gif")));
        jbtCortar.setToolTipText("Cortar");
        jbtCortar.setFocusPainted(false);
        jbtCortar.setMargin(new Insets(0, 0, 0, 0));
        jbtCortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.cut();
            }
        });

        jbtCopiar = new JButton();
        jbtCopiar.setIcon(new ImageIcon(getClass().getResource("/copiar.gif")));
        jbtCopiar.setToolTipText("Copiar");
        jbtCopiar.setFocusPainted(false);
        jbtCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.copy();
            }
        });
        jbtCopiar.setMargin(new Insets(0, 0, 0, 0));

        jbtPegar = new JButton();
        jbtPegar.setIcon(new ImageIcon(getClass().getResource("/pegar.gif")));
        jbtPegar.setToolTipText("Pegar");
        jbtPegar.setFocusPainted(false);
        jbtPegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtxtaEditor.paste();
            }
        });
        jbtPegar.setMargin(new Insets(0, 0, 0, 0));

        jtbarEditor.add(jbtCortar);
        jtbarEditor.add(jbtCopiar);
        jtbarEditor.add(jbtPegar);
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void formWindowOpened(WindowEvent evt) {
        jtxtaEditor.requestFocus();
        fuentePr = jtxtaEditor.getFont();
    }

    private void pegar(ActionEvent evt) {
        Transferable objTrans = portapapeles.getContents(this);
        if (objTrans != null) {
            try {
                String textPegar = (String)objTrans.getTransferData(DataFlavor.stringFlavor);
                jtxtaEditor.replaceSelection(textPegar);

            } catch (Exception e) {
                System.out.println("No se pudo pegar el contenido del portapapeles");
            }
        }
    }

    private void copiar(ActionEvent evt) {
        String textSel = jtxtaEditor.getSelectedText();
        Transferable objTrans = new java.awt.datatransfer.StringSelection(textSel);
        portapapeles.setContents(objTrans, this);
    }

    private void jitemCourierActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font("Courier new", f.getStyle(), f.getSize());
        jtxtaEditor.setFont(f);
        jitemArial.setState(false);
        jitemPredeterminada.setState(false);
    }

    private void jitemArialActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font("Arial", f.getStyle(), f.getSize());
        jtxtaEditor.setFont(f);
        jitemCourier.setState(false);
        jitemPredeterminada.setState(false);
    }

    private void jitemPredeterminadaActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font(fuentePr.getFamily(), f.getStyle(), f.getSize());
        jtxtaEditor.setFont(f);
        jitemArial.setState(false);
        jitemCourier.setState(false);
    }

    private void jitemSize16ActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font(f.getFamily(), f.getStyle(), 16);
        jtxtaEditor.setFont(f);
    }

    private void jitemSize24ActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font(f.getFamily(), f.getStyle(), 24);
        jtxtaEditor.setFont(f);
    }

    private void jitemSizePredeterminadoActionPerformed(ActionEvent evt) {
        Font f = jtxtaEditor.getFont();
        f = new Font(f.getFamily(), f.getStyle(), fuentePr.getSize());
        jtxtaEditor.setFont(f);
    }

    private void jmnuEdicionMenuSelected(MenuEvent evt) {
        String texto = jtxtaEditor.getSelectedText();
        boolean seleccionado = texto != null && !texto.isEmpty();

        if (seleccionado) {
            jitemCortar.setEnabled(true);
            jitemCopiar.setEnabled(true);
        } else {
            jitemCortar.setEnabled(false);
            jitemCopiar.setEnabled(false);
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Se ha perdido la propiedad del portapapeles");
    }
}
