package es.cheste;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.net.URL;

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
    JMenuItem jitemDeshacer;
    JMenuItem jitemRehacer;

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

    UndoManager jumDeshacerRehacer;

    JPopupMenu jpmnuEdicion;
    JMenuItem jpmitemCortar;
    JMenuItem jpmitemCopiar;
    JMenuItem jpmitemPegar;

    int anchoForm;
    int altoForm;

    URL imageURL = getClass().getResource("/txtEd.gif");
    Image icon = getToolkit().getImage(imageURL);

    public Editor() {
        anchoForm = 520;
        altoForm = 300;

        setSize(anchoForm, altoForm);
        setTitle("TXT ED");
        initComponents();
        initOtherComponents();
        setIconImage(icon);

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

        jitemDeshacer = new JMenuItem();
        jitemDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        jitemDeshacer.setMnemonic('D');
        jitemDeshacer.setText("Deshacer");
        jitemDeshacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemDeshacerActionPerformed(evt);
            }
        });
        jmnuEdicion.add(jitemDeshacer);

        jitemRehacer = new JMenuItem();
        jitemRehacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        jitemRehacer.setMnemonic('R');
        jitemRehacer.setText("Rehacer");
        jitemRehacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jitemRehacerActionPerformed(evt);
            }
        });
        jmnuEdicion.add(jitemRehacer);

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

        // PopupMenu
        jpmnuEdicion = new JPopupMenu();

        jtxtaEditor.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jtxtaEditorMousePressed(evt);
            }
        });

        jpmitemCortar = new JMenuItem("Cortar");
        jpmitemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        jpmitemCortar.setMnemonic('T');
        jpmitemCortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { jtxtaEditor.cut(); }
        });
        jpmnuEdicion.add(jpmitemCortar);

        jpmitemCopiar = new JMenuItem("Copiar");
        jpmitemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        jpmitemCopiar.setMnemonic('C');
        jpmitemCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { jtxtaEditor.copy(); }
        });
        jpmnuEdicion.add(jpmitemCopiar);

        jpmitemPegar = new JMenuItem("Pegar");
        jpmitemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        jpmitemPegar.setMnemonic('P');
        jpmitemPegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { jtxtaEditor.paste(); }
        });
        jpmnuEdicion.add(jpmitemPegar);

        // Redimensionado
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void formComponentResized(ComponentEvent evt) {
        int ancho = getWidth();
        int alto = getHeight();

        int xscrpane = jscrpaneEditor.getWidth();
        int yscrpane = jscrpaneEditor.getHeight();

        jscrpaneEditor.setSize(xscrpane + ancho - anchoForm, yscrpane + alto - altoForm);
        jtxtaEditor.setSize(xscrpane + ancho - anchoForm, yscrpane + alto - altoForm);

        anchoForm = ancho;
        altoForm = alto;
    }

    private void jtxtaEditorMousePressed(MouseEvent evt) {
        boolean textoSeleccionado = jtxtaEditor.getSelectedText() != null;
        jpmitemCopiar.setEnabled(textoSeleccionado);
        jpmitemCortar.setEnabled(textoSeleccionado);

        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmnuEdicion.show(jtxtaEditor, evt.getX(), evt.getY());
        }
    }

    private void jitemDeshacerActionPerformed(ActionEvent evt) {
        if (jumDeshacerRehacer.canUndo()) {
            jumDeshacerRehacer.undo();
        }
    }

    private void jitemRehacerActionPerformed(ActionEvent evt) {
        if (jumDeshacerRehacer.canRedo()) {
            jumDeshacerRehacer.redo();
        }
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

    private void initOtherComponents() {
        jumDeshacerRehacer = new UndoManager();
        jtxtaEditor.getDocument().addUndoableEditListener(jumDeshacerRehacer);
    }
}
