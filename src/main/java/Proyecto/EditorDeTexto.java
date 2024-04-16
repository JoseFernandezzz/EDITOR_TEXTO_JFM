package Proyecto;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorDeTexto extends JFrame implements ActionListener {

   //Variables
    private JButton Negrita;
    private JButton Cursiva;
    private JButton Subrayado;
    private JMenuBar Menu1, Menu2, Menu3;
    private JMenu MenuColor, MenuFuente, MenuTamano;
    private JMenuItem Rojo, Verde, Azul, Negro, Serif, SansSerif, MonoSpace, Size1, Size2, Size3, CustomSize;
    private JTextPane AreaDeTexto;

    // Variables de estado para los botones de Negrita y Cursiva
    private boolean negritaActiva = false;
    private boolean cursivaActiva = false;

    public EditorDeTexto() {
        super("Editor de Texto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Estilo
        Negrita = new JButton("Negrita");
        Cursiva = new JButton("Cursiva");
        Subrayado = new JButton("Subrayado");

        Negrita.setBounds(10, 10, 100, 40);
        Cursiva.setBounds(120, 10, 100, 40);
        Subrayado.setBounds(230, 10, 100, 40);

        Negrita.addActionListener(this);
        Cursiva.addActionListener(this);
        Subrayado.addActionListener(this);

        // Menu
        Menu1 = new JMenuBar();
        Menu2 = new JMenuBar();
        Menu3 = new JMenuBar();

        // Fuente
        MenuFuente = new JMenu("Fuente");

        Serif = new JMenuItem("Serif");
        SansSerif = new JMenuItem("SansSerif");
        MonoSpace = new JMenuItem("MonoSpace");

        Serif.addActionListener(this);
        SansSerif.addActionListener(this);
        MonoSpace.addActionListener(this);

        MenuFuente.add(Serif);
        MenuFuente.add(SansSerif);
        MenuFuente.add(MonoSpace);

        Menu2.add(MenuFuente);
        Menu2.setBounds(400, 15, 50, 30);

        // Tamaño
        MenuTamano = new JMenu("Tamaño");
        Size1 = new JMenuItem("12");
        Size2 = new JMenuItem("14");
        Size3 = new JMenuItem("20");
        CustomSize = new JMenuItem("Personalizado");

        Size1.addActionListener(this);
        Size2.addActionListener(this);
        Size3.addActionListener(this);
        CustomSize.addActionListener(this);

        MenuTamano.add(Size1);
        MenuTamano.add(Size2);
        MenuTamano.add(Size3);
        MenuTamano.add(CustomSize);

        Menu3.add(MenuTamano);
        Menu3.setBounds(500, 15, 55, 30);

        // Color
        MenuColor = new JMenu("Color");
        Rojo = new JMenuItem("Rojo");
        Verde = new JMenuItem("Verde");
        Azul = new JMenuItem("Azul");
        Negro = new JMenuItem("Negro");

        Rojo.addActionListener(this);
        Verde.addActionListener(this);
        Azul.addActionListener(this);
        Negro.addActionListener(this);

        MenuColor.add(Rojo);
        MenuColor.add(Verde);
        MenuColor.add(Azul);
        MenuColor.add(Negro);

        Menu1.add(MenuColor);
        Menu1.setBounds(600, 15, 50, 30);

        // Area de texto
        AreaDeTexto = new JTextPane();
        AreaDeTexto.setBounds(10, 60, 650, 600);
        AreaDeTexto.setEditable(true);

        //Se agregan a la interfaz
        add(AreaDeTexto);
        add(Negrita);
        add(Cursiva);
        add(Subrayado);
        add(Menu1);
        add(Menu2);
        add(Menu3);
    }

    public void actionPerformed(ActionEvent e) {
        StyledDocument doc = AreaDeTexto.getStyledDocument();
        int start = AreaDeTexto.getSelectionStart();
        int end = AreaDeTexto.getSelectionEnd();

        // Tamaño
        if (e.getSource() == Size1) {
            setSizeFuente(12, start, end);
        } else if (e.getSource() == Size2) {
            setSizeFuente(14, start, end);
        } else if (e.getSource() == Size3) {
            setSizeFuente(20, start, end);
        } else if (e.getSource() == CustomSize) {
            setCustomSize(start, end);
        }

        // Fuente
        if (e.getSource() == SansSerif) {
            setTipoFuente("SansSerif", start, end);
        } else if (e.getSource() == Serif) {
            setTipoFuente("Serif", start, end);
        } else if (e.getSource() == MonoSpace) {
            setTipoFuente("MonoSpace", start, end);
        }

        // Color
        if (e.getSource() == Rojo) {
            setColorFuente(Color.RED, start, end);
        } else if (e.getSource() == Verde) {
            setColorFuente(Color.GREEN, start, end);
        } else if (e.getSource() == Azul) {
            setColorFuente(Color.BLUE, start, end);
        } else if (e.getSource() == Negro) {
            setColorFuente(Color.BLACK, start, end);
        }

        // Estilo
        if (e.getSource() == Negrita) {
            negritaActiva = !negritaActiva;
            setEstilo(Font.BOLD, start, end, negritaActiva);
        } else if (e.getSource() == Cursiva) {
            cursivaActiva = !cursivaActiva;
            setEstilo(Font.ITALIC, start, end, cursivaActiva);
        } else if (e.getSource() == Subrayado) {
            toggleSubrayado(start, end);
        }
    }

    //Tamaño predeterminado
    private void setSizeFuente(int size, int start, int end) {
        Style estilo = AreaDeTexto.addStyle("Style", null);
        StyleConstants.setFontSize(estilo, size);
        AreaDeTexto.setCharacterAttributes(estilo, false);
    }

    //Tamaño Personalizado
    private void setCustomSize(int start, int end) {
        String input = JOptionPane.showInputDialog(null, "Introduce el tamaño de la letra:");
        int newSize = Integer.parseInt(input);
        setSizeFuente(newSize, start, end);
    }

    //FUente
    private void setTipoFuente(String tipo, int start, int end) {
        Style estilo = AreaDeTexto.addStyle("Style", null);
        StyleConstants.setFontFamily(estilo, tipo);
        AreaDeTexto.setCharacterAttributes(estilo, false);
    }

    //Color
    private void setColorFuente(Color color, int start, int end) {
        Style estilo = AreaDeTexto.addStyle("Style", null);
        StyleConstants.setForeground(estilo, color);
        AreaDeTexto.setCharacterAttributes(estilo, false);
    }

   //Estilos
    private void setEstilo(int estilo, int start, int end, boolean activo) {
        StyledDocument doc = AreaDeTexto.getStyledDocument();
        Style estiloTexto = doc.addStyle("Style", null);
        StyleConstants.setBold(estiloTexto, activo && estilo == Font.BOLD);
        StyleConstants.setItalic(estiloTexto, activo && estilo == Font.ITALIC);
        AreaDeTexto.setCharacterAttributes(estiloTexto, false);
    }

    //Subrayado
    private void toggleSubrayado(int start, int end) {
        StyledDocument doc = AreaDeTexto.getStyledDocument();
        Element element = doc.getCharacterElement(start);
        AttributeSet as = element.getAttributes();
        boolean isUnderline = (StyleConstants.isUnderline(as)) ? false : true;

        Style estilo = AreaDeTexto.addStyle("Style", null);
        StyleConstants.setUnderline(estilo, isUnderline);
        AreaDeTexto.setCharacterAttributes(estilo, false);
    }

    //Main
    public static void main(String[] args) {
        EditorDeTexto de = new EditorDeTexto();
        de.setVisible(true);
        de.setSize(700, 700);
    }
}
