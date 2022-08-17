/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author dumed
 */
public class ClasseAbstrata extends JFrame {

    protected Font font;
    protected Font fontComponentesMenu;
    protected Font fontComponentes;
    protected Font fontTitulo;

    protected JLabel labelTitulo;

    protected JMenu principalMenu;
    protected JMenuItem registrosItem;
    protected JMenuItem inicioItem;
    protected JMenuBar bar;
    
    protected FlowLayout layout1; 

    protected GerenciadorTelas gt;

    public ClasseAbstrata(String titulo, GerenciadorTelas gt) {

        super.setSize(900, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);

        this.gt = gt;
        
        layout1 = new FlowLayout(); 

        font = new Font("Sitka Text", Font.PLAIN, 20);

        fontTitulo = new Font("Arial Black", Font.PLAIN, 25);

        fontComponentesMenu = new Font("Sitka Text", Font.PLAIN, 12);
        fontComponentes = new Font("Sitka Text", Font.PLAIN, 18);

        principalMenu = new JMenu(" Ir para | ");
        principalMenu.setFont(fontComponentesMenu);

        inicioItem = new JMenuItem("Tela inicial");
        inicioItem.setFont(fontComponentesMenu);

        inicioItem.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    gt.changeToTelaInicial(ClasseAbstrata.this);
                } catch (SQLException ex) {
                    Logger.getLogger(ClasseAbstrata.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        registrosItem = new JMenuItem("Tela de registros");
        registrosItem.setFont(fontComponentesMenu);
        registrosItem.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    gt.changeToTelaRegistro(ClasseAbstrata.this);
                } catch (SQLException ex) {
                    Logger.getLogger(ClasseAbstrata.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        principalMenu.add(inicioItem);
        principalMenu.add(registrosItem);

        bar = new JMenuBar(); //cria barra de menus
        bar.setBackground(new java.awt.Color(240, 230, 140));
        bar.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 20, new java.awt.Color(240, 230, 140)));

        bar.add(principalMenu);

        this.setJMenuBar(bar); //add uma barra de menu ao aplicativo

        //  bar.addKeyListener(listener);
    }

}
