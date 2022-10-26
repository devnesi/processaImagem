package screens;

import utils.ProcessaImagem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainScreen extends JFrame {
    private JSlider buffer, bufferY;
    private final JLabel jLabelImagemCru = new JLabel();;
    private final JLabel jLabelImagemProcessada = new JLabel();;
    private final ProcessaImagem processaImagem = new ProcessaImagem();

    //Construtor padrão, quando cria uma tela executa esse método
    //Ele faz as definições da janela e cria os componentes chamando createComponets();
    public MainScreen() throws HeadlessException {
        setSize(820, 600);
        setTitle("Processamento imagem");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createComponets();
    }
    

    //Método que cria os componentes visiveis em tela
    //E cria o menubar createMenu();
    public void createComponets(){
        jLabelImagemCru.setBounds(20, 75, 332, 300);

        try {
            jLabelImagemCru.setIcon(processaImagem.getImagemIcon());
        } catch (Exception e) {
            System.err.println(e);
        }
        getContentPane().add(jLabelImagemCru);

        jLabelImagemProcessada.setBounds(450, 75, 332, 300);
        try {
            jLabelImagemProcessada.setIcon(processaImagem.getImagemIcon());
        } catch (Exception e) {
            System.err.println(e);
        }
        getContentPane().add(jLabelImagemProcessada);
        createMenu();
    }

    //Método responsável por criar o menu bar e tudo que envolva ele
    public void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Efeitos");
        JMenuItem menuItem = new JMenuItem("Gride");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaGride()));

        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Vermelho");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaVermelho()));
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Azul");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaAzul()));
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Verde");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaVerde()));
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Cinza");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaCinza()));
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Preto e Branco");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaPretoBranco()));
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(e -> {
            jLabelImagemProcessada.setIcon(processaImagem.getImagemIcon());
        });
        menu.add(menuItem);

        buffer = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);

        buffer.setSize(345,20);
        buffer.setLocation(445,400);
        buffer.addChangeListener(e -> atualizaNariz());

        this.add(buffer);

        bufferY = new JSlider(JSlider.VERTICAL,
                0, 300, 300);

        bufferY.setSize(20,313);
        bufferY.setLocation(410,70);
        bufferY.addChangeListener(e -> atualizaNariz());

        this.add(bufferY);


        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void atualizaNariz(){
        jLabelImagemProcessada.setIcon(new ImageIcon(processaImagem.aplicaNariz(buffer.getValue(), Math.abs(bufferY.getValue()-300))));
    }
}
