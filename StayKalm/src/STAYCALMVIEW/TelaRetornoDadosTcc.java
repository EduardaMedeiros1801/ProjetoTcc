/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author dumed
 */
public class TelaRetornoDadosTcc extends ClasseAbstrata {

    GerenciadorTelas gt;

    private final JLabel lCodigo;
    private final JLabel lData;
    private final JLabel lHora;
    private final JLabel lDescricao;
    private final JLabel lEmocao;

    protected final JTextField tCodigo;
    protected final JTextField tData;
    protected final JTextField tHora;
    protected final JTextArea tDescricao;
    protected final JTextField tEmocao;

    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JPanel panel4;
    private final JPanel panel5;
    private final JPanel panel6;

    private final JButton bVoltar;

    public TelaRetornoDadosTcc(GerenciadorTelas gt) {
        super("Ver Registro", gt);
        super.setLayout(new GridLayout(6, 1, 2, 2));

        lCodigo = new JLabel("Código de registro: ");
        lCodigo.setFont(fontComponentes);

        tCodigo = new JTextField(20);
        tCodigo.setEditable(false);

        panel1 = new JPanel();
        panel1.setLayout(layout1);

        panel1.add(lCodigo);
        panel1.add(tCodigo);

        lData = new JLabel("Data: ");
        lData.setFont(fontComponentes);

        tData = new JTextField(20);
        tData.setEditable(false);

        panel2 = new JPanel();
        panel2.setLayout(layout1);

        panel2.add(lData);
        panel2.add(tData);

        lHora = new JLabel("Hora: ");
        lHora.setFont(fontComponentes);

        tHora = new JTextField(20);
        tHora.setEditable(false);

        panel3 = new JPanel();
        panel3.setLayout(layout1);

        panel3.add(lHora);
        panel3.add(tHora);
        
        lEmocao = new JLabel("Humor: "); 
        lEmocao.setFont(fontComponentes);
        
        tEmocao = new JTextField(20); 
        tEmocao.setEditable(false);
        
        panel4 = new JPanel(); 
        panel4.setLayout(layout1);
        
        panel4.add(lEmocao); 
        panel4.add(tEmocao); 
        
        lDescricao = new JLabel("Descrição: "); 
        lDescricao.setFont(fontComponentes);
        
        Box box2 = Box.createHorizontalBox(); 
        tDescricao = new JTextArea(5,20); 
        tDescricao.setEditable(false);
        box2.add(new JScrollPane(tDescricao)); 
        
        panel5 = new JPanel(); 
        panel5.setLayout(layout1);
        
        panel5.add(lDescricao);
        panel5.add(box2);
        
        bVoltar = new JButton("Voltar Tela Inicial"); 
        bVoltar.setBackground(new java.awt.Color(135, 206, 250));
        bVoltar.setFont(fontComponentes);
        bVoltar.addActionListener((
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        gt.changeToTelaInicial(TelaRetornoDadosTcc.this);
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaRetornoDadosTcc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }));
        
        panel6 = new JPanel(); 
        panel6.setLayout(layout1);
        
        panel6.add(bVoltar); 
        
        add(panel1); 
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(panel6);

    }

}
