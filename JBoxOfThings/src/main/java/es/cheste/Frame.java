package es.cheste;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.File;

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

        JButton inputButton = new JButton("InputDialog");
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputDialogExample();
            }
        });

        JButton optionButton = new JButton("OptionDialog");
        optionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOptionDialogExample();
            }
        });

        JButton newFrameButton = new JButton("¿JFrame?");
        newFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewFrame();
            }
        });

        JButton textFieldButton = new JButton("JTextField");
        textFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextFieldFrame();
            }
        });

        JButton checkBoxButton = new JButton("JCheckBox");
        checkBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckBoxFrame();
            }
        });

        JButton radioButtonButton = new JButton("JRadioButton");
        radioButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RadioButtonFrame();
            }
        });

        JButton listButton = new JButton("JList");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListFrame();
            }
        });

        JButton scrollBarButton = new JButton("JScrollBar");
        scrollBarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScrollBarFrame();
            }
        });

        JButton sliderButton = new JButton("JSlider");
        sliderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SliderFrame();
            }
        });

        JButton progressBarButton = new JButton("JProgressBar");
        progressBarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProgressBarFrame();
            }
        });

        JButton chooserButton = new JButton("JFileChooser");
        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooserFrame();
            }
        });

        JButton colorChooserButton = new JButton("JColorChooser");
        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ColorChooserFrame();
            }
        });

        add(messageDialogButton);
        add(confirmButton);
        add(inputButton);
        add(optionButton);
        add(newFrameButton);
        add(textFieldButton);
        add(checkBoxButton);
        add(radioButtonButton);
        add(listButton);
        add(scrollBarButton);
        add(sliderButton);
        add(progressBarButton);
        add(chooserButton);
        add(colorChooserButton);
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

    private void showInputDialogExample() {
        String tank = JOptionPane.showInputDialog(this, "¿Cómo se llama tu tanque?", "Input Dialog",
                JOptionPane.QUESTION_MESSAGE
        );

        if (tank != null) {
            JOptionPane.showMessageDialog(this, "Tu tanque es " + tank + "!");
        } else {
            JOptionPane.showMessageDialog(this, "Tu tanque no tiene nombre.");
        }
    }

    private void showOptionDialogExample() {
        String[] options = {"T-34", "M4 Sherman", "Panzer IV", "A27M Cromwell"};
        int choice = JOptionPane.showOptionDialog(this, "¿Qué tanque prefieres?",
                "OptionDialog", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice != JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(this, "Tu tanque es el " + options[choice] + "!");
        }
    }

    private static class NewFrame extends JFrame {
        public NewFrame() {
            setTitle("¿Eres tonto?");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    private static class TextFieldFrame extends JFrame {
        public TextFieldFrame() {
            setTitle("Parte favorita");
            setSize(500, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(2, 1));
            setVisible(true);

            JLabel label = new JLabel("¿Cuál es tu parte favorita de un tanque?");
            JTextField textField = new JTextField();
            add(label);
            add(textField);
        }
    }

    private static class CheckBoxFrame extends JFrame {
        public CheckBoxFrame() {
            setTitle("Parte favorita II");
            setSize(500, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 1));

            JLabel label = new JLabel("Sabemos que te inventaste tu respuesta anterior, responde de nuevo:");
            JCheckBox cannonCheckBox = new JCheckBox("Cañón");
            JCheckBox armorCheckBox = new JCheckBox("Blindaje");
            JCheckBox tracksCheckBox = new JCheckBox("Orugas");

            JButton submitButton = new JButton("Enviar");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSelectedParts(cannonCheckBox, armorCheckBox, tracksCheckBox);
                }
            });

            add(label);
            add(cannonCheckBox);
            add(armorCheckBox);
            add(tracksCheckBox);
            add(submitButton);

            setVisible(true);
        }

        private void showSelectedParts(JCheckBox cannon, JCheckBox armor, JCheckBox tracks) {
            StringBuilder selectedParts = new StringBuilder("Partes seleccionadas: ");

            if (cannon.isSelected()) {
                selectedParts.append("Cañón ");
            }
            if (armor.isSelected()) {
                selectedParts.append("Blindaje ");
            }
            if (tracks.isSelected()) {
                selectedParts.append("Orugas ");
            }

            if (selectedParts.toString().equals("Partes seleccionadas: ")) {
                selectedParts.append("Ninguna");
            }

            JOptionPane.showMessageDialog(this, selectedParts.toString());
        }
    }

    private static class RadioButtonFrame extends JFrame {
        public RadioButtonFrame() {
            setTitle("Fabricante favorito");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new FlowLayout());

            JLabel label = new JLabel("¿Cuál es tu fabricante de tanques favorito?");
            JRadioButton krupp = new JRadioButton("Krupp");
            JRadioButton porsche = new JRadioButton("Porsche");
            JRadioButton rheinmetall = new JRadioButton("Rheinmetall");

            ButtonGroup group = new ButtonGroup();
            group.add(krupp);
            group.add(porsche);
            group.add(rheinmetall);

            JButton submitButton = new JButton("Enviar");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSelectedPart(krupp, porsche, rheinmetall);
                }
            });

            add(label);
            add(krupp);
            add(porsche);
            add(rheinmetall);
            add(submitButton);

            setVisible(true);
        }

        private void showSelectedPart(JRadioButton cannon, JRadioButton armor, JRadioButton tracks) {
            String selectedPart = "Fabricante elegido: ";

            if (cannon.isSelected()) {
                selectedPart += "Krupp";
            } else if (armor.isSelected()) {
                selectedPart += "Porsche";
            } else if (tracks.isSelected()) {
                selectedPart += "Rheinmetall";
            } else {
                selectedPart = "Ninguno";
            }

            JOptionPane.showMessageDialog(this, selectedPart);
        }
    }

    private static class ListFrame extends JFrame {
        public ListFrame() {
            setTitle("Tipo de Munición Favorito");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel label = new JLabel("Selecciona tu tipo favorito de munición:");
            String[] ammoTypes = {"AP", "HE", "HEAT", "APCR", "APDSFS"};
            JList<String> ammoList = new JList<>(ammoTypes);
            ammoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JButton submitButton = new JButton("Elegir");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSelectedAmmo(ammoList);
                }
            });

            add(label, BorderLayout.NORTH);
            add(new JScrollPane(ammoList), BorderLayout.CENTER);
            add(submitButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        private void showSelectedAmmo(JList<String> ammoList) {
            String selectedAmmo = ammoList.getSelectedValue();

            switch (selectedAmmo) {
                case "AP":
                    JOptionPane.showMessageDialog(this, "Armor Piercing");
                    break;
                case "HE":
                    JOptionPane.showMessageDialog(this, "High Explosive");
                    break;
                case "HEAT":
                    JOptionPane.showMessageDialog(this, "High Explosive Anti-Tank");
                    break;
                case "APCR":
                    JOptionPane.showMessageDialog(this, "Armor Piercing Composite Rigid");
                    break;
                case "APDSFS":
                    JOptionPane.showMessageDialog(this, "Armor Piercing Discarding Sabot Fin-Stabilized");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "No has seleccionado nada.");
            }
        }
    }

    private static class ScrollBarFrame extends JFrame {

        public ScrollBarFrame() {
            setTitle("Repostar combustible");
            setSize(300, 105);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel label = new JLabel("Nivel de combustible:");
            JLabel fuelLevelLabel = new JLabel("0%");
            JLabel fuelStatusLabel = new JLabel("El depósito no está lleno.");
            JScrollBar fuelScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);

            fuelScrollBar.addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    int fuelLevel = fuelScrollBar.getValue();
                    fuelLevelLabel.setText(fuelLevel + "%");

                    if (fuelLevel == 100) {
                        fuelStatusLabel.setText("¡Listo para la línea del frente!");
                    } else {
                        fuelStatusLabel.setText("El depósito no está lleno.");
                    }
                }
            });

            add(label, BorderLayout.NORTH);
            add(fuelScrollBar, BorderLayout.CENTER);
            add(fuelLevelLabel, BorderLayout.WEST);
            add(fuelStatusLabel, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public class SliderFrame extends JFrame {
        public SliderFrame() {
            setTitle("Ajuste de Aceleración del Tanque");
            setSize(350, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel speedLabel = new JLabel("Velocidad del tanque:");
            JLabel speedValueLabel = new JLabel("0 km/h");
            JLabel speedStatusLabel = new JLabel("Parado.");
            JSlider speedSlider = new JSlider(0, 100, 0);

            speedSlider.setMajorTickSpacing(10);
            speedSlider.setMinorTickSpacing(1);
            speedSlider.setPaintTicks(true);
            speedSlider.setPaintLabels(true);

            speedSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int speed = speedSlider.getValue();
                    speedValueLabel.setText(String.format("%.2f km/h", speed / 1.85));

                    if (speed >= 80) {
                        speedStatusLabel.setText("¡A toda marcha!");
                    } else if (speed == 0) {
                        speedStatusLabel.setText("Parado.");
                    } else {
                        speedStatusLabel.setText("Velocidad de crucero.");
                    }
                }
            });

            add(speedLabel, BorderLayout.NORTH);
            add(speedSlider, BorderLayout.CENTER);
            add(speedValueLabel, BorderLayout.WEST);
            add(speedStatusLabel, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public class ProgressBarFrame extends JFrame {
        private JProgressBar fuelProgressBar;
        private JLabel fuelLevelLabel;
        private Timer timer;
        private int fuelLevel = 100;

        public ProgressBarFrame() {
            setTitle("Simulador gasto de combustible");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            fuelProgressBar = new JProgressBar(0, 100);
            fuelProgressBar.setValue(fuelLevel);
            fuelProgressBar.setStringPainted(true);

            fuelLevelLabel = new JLabel("Nivel de combustible: 375 L");

            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (fuelLevel > 0) {
                        fuelLevel -= 1;
                        fuelProgressBar.setValue(fuelLevel);
                        fuelLevelLabel.setText(String.format("Nivel de combustible: %.2f L", fuelLevel * 3.75));
                    } else {
                        fuelProgressBar.setValue(0);
                        fuelLevelLabel.setText("Nivel de combustible: 0%");
                        timer.stop();
                    }
                }
            });
            timer.start();

            add(fuelProgressBar, BorderLayout.CENTER);
            add(fuelLevelLabel, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public class ChooserFrame extends JFrame {
        private JLabel imageLabel;

        public ChooserFrame() {
            setTitle("Pegatinas para tu tanque");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            imageLabel = new JLabel("Selecciona una imagen para decorar tu tanque", JLabel.CENTER);
            imageLabel.setPreferredSize(new Dimension(600, 300));
            add(imageLabel, BorderLayout.CENTER);

            JButton openButton = new JButton("Abrir imagen...");
            openButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "gif"));
                    int returnValue = fileChooser.showOpenDialog(ChooserFrame.this);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        displayImage(selectedFile);
                    }
                }
            });
            add(openButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        private void displayImage(File file) {
            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            imageLabel.setIcon(imageIcon);
            imageLabel.setText("");
        }
    }

    public class ColorChooserFrame extends JFrame {
        private JLabel imageLabel;
        private BufferedImage originalImage;

        public ColorChooserFrame() {
            setTitle("Colorea tu tanque");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            imageLabel = new JLabel("Cargando imagen...", JLabel.CENTER);
            imageLabel.setPreferredSize(new Dimension(600, 300));
            add(imageLabel, BorderLayout.CENTER);

            loadImage();
            imageLabel.setIcon(new ImageIcon(originalImage));
            imageLabel.setText("");

            JButton colorButton = new JButton("Cambiar Color de Fondo");
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color newColor = JColorChooser.showDialog(ColorChooserFrame.this, "Selecciona un Color", Color.WHITE);
                    if (newColor != null) {
                        changeBackgroundColor(newColor);
                    }
                }
            });
            add(colorButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        private void loadImage() {
            try {
                originalImage = ImageIO.read(new File("src/main/resources/tank.png"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void changeBackgroundColor(Color newColor) {
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = originalImage.getRGB(x, y);

                    Color color = new Color(pixel, true);

                    if (color.getAlpha() == 0) {
                        processedImage.setRGB(x, y, newColor.getRGB() & 0x00FFFFFF); // Mantener la transparencia
                    } else {
                        processedImage.setRGB(x, y, pixel);
                    }
                }
            }

            imageLabel.setIcon(new ImageIcon(processedImage));
        }
    }
}
