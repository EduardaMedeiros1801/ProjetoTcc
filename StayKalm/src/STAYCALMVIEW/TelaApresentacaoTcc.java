/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author dumed
 */
public class TelaApresentacaoTcc extends ClasseAbstrata{

    private GerenciadorTelas gt;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;

    private final Font fontTitulo;
    private final JLabel label1;
    private final JLabel lTitulo1;
    private final JLabel lTitulo2;

    private final JButton botaoSair;
    private final JButton botaoContinuar;

    //menu
   // private JMenu inicio;
   // private final JMenu registros;
    //private final JMenu sobreNos;

  //  private final JMenuBar bar;

    public TelaApresentacaoTcc(GerenciadorTelas gt)  {
        super("Tela de Apresentação", gt);
        super.setLayout(new GridLayout(2, 1, 2, 2));

        this.gt = gt;
      
        ImageIcon image = new ImageIcon(getClass().getResource("capa.jpg"));

        label1 = new JLabel(image);
        label1.setHorizontalAlignment(JLabel.CENTER);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(2, 1));
        panel1.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        //panel1.setBackground(Color.ORANGE);
        panel1.add(this.label1, BorderLayout.CENTER);

        fontTitulo = new Font("MV Boli", Font.BOLD, 28);

        lTitulo1 = new JLabel("Bem-Vindos ao STAYKALM!");
        lTitulo1.setFont(fontTitulo);
        lTitulo1.setHorizontalAlignment(JLabel.CENTER);

        lTitulo2 = new JLabel(System.lineSeparator() + "Como deseja prosseguir?");
        //lTitulo2.setBounds(200,100,100,100);
        lTitulo2.setFont(fontTitulo);
        lTitulo2.setHorizontalAlignment(JLabel.CENTER);

        panel2 = new JPanel();
        // panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
        panel2.setLayout(new GridLayout(3, 1));
        panel2.setPreferredSize(new Dimension(380, 100));
        panel2.setBackground(Color.white);

        panel2.add(this.lTitulo1);
        panel2.add(this.lTitulo2);

        //ImageIcon image2 = new ImageIcon(getClass().getResource("emoji1.jpg"));
        botaoSair = new JButton("Sair =c");
        botaoSair.setBackground(new java.awt.Color(220, 20, 60));
        botaoSair.setFont(fontComponentesMenu);
        botaoSair.addActionListener(new ButtonHandler1());

        botaoContinuar = new JButton("Continuar =D");
        botaoContinuar.setBackground(new java.awt.Color(135, 206, 250));
        botaoContinuar.setFont(fontComponentesMenu);
        botaoContinuar.addActionListener(new ButtonHandler2());
        //botaoContinuar.addActionListener(new ButtonHandler2());

        panel3 = new JPanel();
        // panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
        panel3.setLayout(new FlowLayout());
        panel3.setPreferredSize(new Dimension(380, 100));
        panel3.setBackground(Color.white);

        panel3.add(this.botaoSair);
        panel3.add(this.botaoContinuar);

        add(this.panel1);
        panel2.add(panel3);
        add(this.panel2);

    }

    private class ButtonHandler1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    private class ButtonHandler2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                gt.changeToTelaInicial(TelaApresentacaoTcc.this);
            } catch (SQLException ex) {
                Logger.getLogger(TelaApresentacaoTcc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*private class ButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event) {
            
        }
    }*/
}
