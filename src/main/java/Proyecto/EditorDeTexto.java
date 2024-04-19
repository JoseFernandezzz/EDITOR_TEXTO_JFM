package Proyecto;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorDeTexto extends JFrame implements ActionListener {

    private JButton negrita;
    private JButton cursiva;
    private JButton subrayado;
    private JMenuBar menu1, menu2, menu3;
    private JMenu menuColor, menuFuente, menuTamano;
    private JMenuItem rojo, verde, azul, negro, serif, sansSerif, monoSpace, size1, size2, size3, customSize;
    private JTextPane areaDeTexto;
    private JScrollPane scrollPane;

    // Variables de estado para los botones de Negrita y Cursiva
    private boolean negritaActiva = false;
    private boolean cursivaActiva = false;

    public EditorDeTexto() {
        super("Editor de Texto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JFrame frame = new JFrame("Ventana con Color de Fondo");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Estilo
        negrita = new JButton("Negrita");
        cursiva = new JButton("Cursiva");
        subrayado = new JButton("Subrayado");

        negrita.setBackground(Color.black);
        cursiva.setBackground(Color.black);
        subrayado.setBackground(Color.black);
        negrita.setForeground(Color.LIGHT_GRAY);
        cursiva.setForeground(Color.LIGHT_GRAY);
        subrayado.setForeground(Color.LIGHT_GRAY);

        negrita.setBounds(10, 10, 100, 40);
        cursiva.setBounds(120, 10, 100, 40);
        subrayado.setBounds(230, 10, 100, 40);

        negrita.addActionListener(this);
        cursiva.addActionListener(this);
        subrayado.addActionListener(this);

        // Menu
        menu1 = new JMenuBar();
        menu2 = new JMenuBar();
        menu3 = new JMenuBar();

        // Fuente
        menuFuente = new JMenu("Fuente");

        serif = new JMenuItem("Serif");
        sansSerif = new JMenuItem("SansSerif");
        monoSpace = new JMenuItem("MonoSpace");

        serif.addActionListener(this);
        sansSerif.addActionListener(this);
        monoSpace.addActionListener(this);

        menuFuente.add(serif);
        menuFuente.add(sansSerif);
        menuFuente.add(monoSpace);

        menu2.add(menuFuente);
        menu2.setBounds(400, 15, 50, 30);

        menu2.setBackground(Color.black);
        menu2.setForeground(Color.LIGHT_GRAY);
        menuFuente.setBackground(Color.black);
        menuFuente.setForeground(Color.LIGHT_GRAY);

        // Tamaño
        menuTamano = new JMenu("Tamaño");
        size1 = new JMenuItem("12");
        size2 = new JMenuItem("14");
        size3 = new JMenuItem("20");
        customSize = new JMenuItem("Personalizado");

        size1.addActionListener(this);
        size2.addActionListener(this);
        size3.addActionListener(this);
        customSize.addActionListener(this);

        menuTamano.add(size1);
        menuTamano.add(size2);
        menuTamano.add(size3);
        menuTamano.add(customSize);

        menu3.add(menuTamano);
        menu3.setBounds(500, 15, 55, 30);

        menu3.setBackground(Color.BLACK);
        menuTamano.setForeground(Color.LIGHT_GRAY);


        // Color
        menuColor = new JMenu("Color");
        rojo = new JMenuItem("Rojo");
        verde = new JMenuItem("Verde");
        azul = new JMenuItem("Azul");
        negro = new JMenuItem("Negro");

        rojo.addActionListener(this);
        verde.addActionListener(this);
        azul.addActionListener(this);
        negro.addActionListener(this);

        menuColor.add(rojo);
        menuColor.add(verde);
        menuColor.add(azul);
        menuColor.add(negro);

        menu1.add(menuColor);
        menu1.setBounds(600, 15, 50, 30);

        menu1.setBackground(Color.black);
        menu1.setForeground(Color.LIGHT_GRAY);
        menuColor.setBackground(Color.black);
        menuColor.setForeground(Color.LIGHT_GRAY);

        areaDeTexto = new JTextPane();
        scrollPane = new JScrollPane(areaDeTexto);
        scrollPane.setBounds(10, 60, 650, 600);
        areaDeTexto.setEditable(true);



        add(scrollPane);
        add(negrita);
        add(cursiva);
        add(subrayado);
        add(menu1);
        add(menu2);
        add(menu3);
    }

    public void actionPerformed(ActionEvent e) {
        StyledDocument doc = areaDeTexto.getStyledDocument();
        int start = areaDeTexto.getSelectionStart();
        int end = areaDeTexto.getSelectionEnd();

        // Tamaño
        if (e.getSource() == size1) {
            setSizeFuente(12, start, end);
        } else if (e.getSource() == size2) {
            setSizeFuente(14, start, end);
        } else if (e.getSource() == size3) {
            setSizeFuente(20, start, end);
        } else if (e.getSource() == customSize) {
            setCustomSize(start, end);
        }

        // Fuente
        if (e.getSource() == sansSerif) {
            setTipoFuente("SansSerif", start, end);
        } else if (e.getSource() == serif) {
            setTipoFuente("Serif", start, end);
        } else if (e.getSource() == monoSpace) {
            setTipoFuente("MonoSpace", start, end);
        }

        // Color
        if (e.getSource() == rojo) {
            setColorFuente(Color.RED, start, end);
        } else if (e.getSource() == verde) {
            setColorFuente(Color.GREEN, start, end);
        } else if (e.getSource() == azul) {
            setColorFuente(Color.BLUE, start, end);
        } else if (e.getSource() == negro) {
            setColorFuente(Color.BLACK, start, end);
        }

        // Estilo
        if (e.getSource() == negrita) {
            negritaActiva = !negritaActiva;
            setEstilo(Font.BOLD, start, end, negritaActiva);
        } else if (e.getSource() == cursiva) {
            cursivaActiva = !cursivaActiva;
            setEstilo(Font.ITALIC, start, end, cursivaActiva);
        } else if (e.getSource() == subrayado) {
            toggleSubrayado(start, end);
        }
    }

    private void setSizeFuente(int size, int start, int end) {
        Style estilo = areaDeTexto.addStyle("Style", null);
        StyleConstants.setFontSize(estilo, size);
        areaDeTexto.setCharacterAttributes(estilo, false);
    }

    private void setCustomSize(int start, int end) {
        String input = JOptionPane.showInputDialog(null, "Introduce el tamaño de la letra:");
        int newSize = Integer.parseInt(input);
        setSizeFuente(newSize, start, end);
    }

    private void setTipoFuente(String tipo, int start, int end) {
        Style estilo = areaDeTexto.addStyle("Style", null);
        StyleConstants.setFontFamily(estilo, tipo);
        areaDeTexto.setCharacterAttributes(estilo, false);
    }

    private void setColorFuente(Color color, int start, int end) {
        Style estilo = areaDeTexto.addStyle("Style", null);
        StyleConstants.setForeground(estilo, color);
        areaDeTexto.setCharacterAttributes(estilo, false);
    }

    private void setEstilo(int estilo, int start, int end, boolean activo) {
        StyledDocument doc = areaDeTexto.getStyledDocument();
        Style estiloTexto = doc.addStyle("Style", null);
        StyleConstants.setBold(estiloTexto, activo && estilo == Font.BOLD);
        StyleConstants.setItalic(estiloTexto, activo && estilo == Font.ITALIC);
        areaDeTexto.setCharacterAttributes(estiloTexto, false);
    }

    private void toggleSubrayado(int start, int end) {
        StyledDocument doc = areaDeTexto.getStyledDocument();
        Element element = doc.getCharacterElement(start);
        AttributeSet as = element.getAttributes();
        boolean isUnderline = (StyleConstants.isUnderline(as)) ? false : true;

        Style estilo = areaDeTexto.addStyle("Style", null);
        StyleConstants.setUnderline(estilo, isUnderline);
        areaDeTexto.setCharacterAttributes(estilo, false);
    }

    public static void main(String[] args) {
        EditorDeTexto de = new EditorDeTexto();
        de.setVisible(true);
        de.setSize(700, 700);
    }
}
