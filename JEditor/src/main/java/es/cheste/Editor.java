package es.cheste;

import javax.swing.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Editor extends JFrame implements ClipboardOwner {
    JMenuBar jmbarEditor;
    JToolBar jtbarEditor;
    JScrollPane jscrpaneEditor;
    JTextArea jtxtaEditor;
    Clipboard portapapeles;

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
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        jtxtaEditor.requestFocus();
    }
}
