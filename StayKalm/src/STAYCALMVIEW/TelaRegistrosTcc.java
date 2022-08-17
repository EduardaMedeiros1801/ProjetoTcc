/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import DAO.RegistrosDAO;
import DTO.RegistroDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author dumed
 */
public class TelaRegistrosTcc extends ClasseAbstrata {

    protected final FlowLayout layout1;

    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JPanel panel4;
    private final JPanel panel5;
    private final JPanel panel6;
    //private final JPanel panel7; 

    private final JLabel lTitulo;

    private final JLabel lData;
    private final JLabel lDescricao;
    private final JLabel lHora;

    public final JTextField tData;
    // private final JTextField tHora;

    private final JRadioButton bRadiante;
    private final JRadioButton bBem;
    private final JRadioButton bMaisOuMenos;
    private final JRadioButton bMal;
    private final JRadioButton bHorrivel;
    private final ButtonGroup bG;

    public final JTextArea textArea1;

    private final JButton bSalvar;
    private final JButton bCancelar;

    protected MaskFormatter maskHora = null;
    public JFormattedTextField maskHoraFormatada;

    String dateToBd;
    
    //public final JLabel lIdRegistro;
    //public final JTextField tIdRegistro;
    //String horaToBd;

    public TelaRegistrosTcc(GerenciadorTelas gt) throws ParseException {
        super("Tela de Registros", gt);
        super.setLayout(new GridLayout(7, 1, 2, 2));
        // super.setBackground(new java.awt.Color(250,128,114));

        this.gt = gt;

        layout1 = new FlowLayout();

        lTitulo = new JLabel("Como você está?");
        lTitulo.setFont(fontTitulo);
        lTitulo.setHorizontalAlignment(JLabel.CENTER);

        panel1 = new JPanel();
        panel1.setLayout(layout1);
        //panel1.setBackground(new java.awt.Color(233,150,122));

        panel1.add(lTitulo);

        ImageIcon image5 = new ImageIcon(getClass().getResource("iconeRadiante.jpg"));

        bRadiante = new JRadioButton("Radiante :D", false);
        bRadiante.setFont(fontComponentes);
        bRadiante.setBackground(new java.awt.Color(0, 255, 127));
        bRadiante.setIcon(image5);

        ImageIcon image4 = new ImageIcon(getClass().getResource("iconeBem.jpg"));

        bBem = new JRadioButton("Bem", false);
        bBem.setFont(fontComponentes);
        bBem.setBackground(new java.awt.Color(144, 238, 144));
        bBem.setIcon(image4);

        ImageIcon image3 = new ImageIcon(getClass().getResource("iconeMaisOuMenos.jpg"));

        bMaisOuMenos = new JRadioButton("Mais ou menos");
        bMaisOuMenos.setFont(fontComponentes);
        bMaisOuMenos.setBackground(new java.awt.Color(240, 230, 140));
        bMaisOuMenos.setIcon(image3);

        ImageIcon image2 = new ImageIcon(getClass().getResource("iconeMal.jpg"));

        bMal = new JRadioButton("Mal :c");
        bMal.setFont(fontComponentes);
        bMal.setBackground(new java.awt.Color(255, 99, 71));
        bMal.setIcon(image2);

        ImageIcon image1 = new ImageIcon(getClass().getResource("iconeHorrivel.jpg"));

        bHorrivel = new JRadioButton("Horrível");
        bHorrivel.setFont(fontComponentes);
        bHorrivel.setBackground(new java.awt.Color(255, 0, 0));
        bHorrivel.setIcon(image1);

        bG = new ButtonGroup();
        bG.add(bRadiante);
        bG.add(bBem);
        bG.add(bMaisOuMenos);
        bG.add(bMal);
        bG.add(bHorrivel);

        panel2 = new JPanel();
        panel2.setLayout(layout1);

        panel2.add(bRadiante);
        panel2.add(bBem);
        panel2.add(bMaisOuMenos);
        panel2.add(bMal);
        panel2.add(bHorrivel);

        /*lIdRegistro = new JLabel("Código: "); 
        lIdRegistro.setFont(fontComponentes);
        
        tIdRegistro = new JTextField(5); 
        
        panel7 = new JPanel(); 
        panel7.setLayout(layout1);
        
        panel7.add(lIdRegistro); 
        panel7.add(tIdRegistro);
        */ 
        
        lData = new JLabel("Data: ");
        lData.setFont(fontComponentes);
        lData.addFocusListener(new FocusHandlerDate());

        //converter para o formato data
        tData = new JTextField(10);
        tData.addFocusListener(new FocusHandlerDate());

        lHora = new JLabel("Hora: ");
        lHora.setFont(fontComponentes);

        // tHora = new JTextField(10);
        maskHora = new MaskFormatter("##:##");
        maskHoraFormatada = new JFormattedTextField(maskHora);
        maskHoraFormatada.setColumns(10);

        panel3 = new JPanel();
        panel3.setLayout(layout1);

        panel3.add(lData);
        panel3.add(tData);

        panel6 = new JPanel();
        panel6.setLayout(layout1);

        panel6.add(lHora);
        panel6.add(maskHoraFormatada);

        lDescricao = new JLabel("Descreva como foi seu dia: ");
        lDescricao.setFont(fontComponentes);

        Box box = Box.createHorizontalBox();

        textArea1 = new JTextArea(5, 20);
        box.add(new JScrollPane(textArea1));

        panel5 = new JPanel();
        panel5.setLayout(layout1);

        panel5.add(lDescricao);
        panel5.add(box);

        bSalvar = new JButton("Salvar Registro");
        bSalvar.setFont(fontComponentes);
        bSalvar.setBackground(new java.awt.Color(135, 206, 250));
        bSalvar.addActionListener(new ButtonHandler2());

        bCancelar = new JButton("Cancelar Registro");
        bCancelar.setFont(fontComponentes);
        bCancelar.setBackground(new java.awt.Color(220, 20, 60));
        bCancelar.addActionListener(new ButtonHandler1());

        panel4 = new JPanel();
        panel4.setLayout(layout1);

        panel4.add(bSalvar);
        panel4.add(bCancelar);

        add(panel1);
        add(panel2);
      //  add(panel7); 
        add(panel3);
        add(panel6);
        add(panel5); 
        add(panel4);
    }

    //Evento de Mouse
    private class FocusHandlerDate extends FocusAdapter {

        //quando perde o foco
        @Override
        public void focusLost(FocusEvent fe) {
            //Tela3.this.ConverteJTextField();

            String data = tData.getText();

            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataFormatada = LocalDate.parse(data, formato);
                //formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dateToBd = (dataFormatada.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                String minhaString = dataFormatada.format(formato);
                tData.setText(minhaString);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(TelaRegistrosTcc.this, "Digite a data no formato dd/MM/yyyy");
                tData.requestFocus();
            }

        }
    }

    private class ButtonHandler1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                gt.changeToTelaInicial(TelaRegistrosTcc.this); //Logger.getLogger(Tela3.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TelaRegistrosTcc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class ButtonHandler2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tData.getText().length() > 0
                    && maskHoraFormatada.getText().length() > 0
                    && textArea1.getText().length() > 0
                    && bRadiante.isSelected() || bBem.isSelected() || bMaisOuMenos.isSelected() || bMal.isSelected() || bHorrivel.isSelected()) {

                //CLASSE INSTANCIADA PARA ACESSAR OS OBJETOS
                RegistroDTO objRegistroDTO = new RegistroDTO();

                String dataRegistro, horaRegistro, descricaoRegistro, emocao;

                dataRegistro = tData.getText(); 
                objRegistroDTO.setData_Registro(dataRegistro); //PEGA O CONTEUDO QUE O USUARIO DIGITOU E SETA NO METODO DTO
                
                horaRegistro = maskHoraFormatada.getText();
                objRegistroDTO.setHora_Registro(horaRegistro);
                
                descricaoRegistro = textArea1.getText();
                objRegistroDTO.setDescricao(descricaoRegistro);

                if (bHorrivel.isSelected()) {
                    emocao = "Horrível";
                    objRegistroDTO.setEmocao(emocao);
                    
                } else if (bMal.isSelected()) {
                    emocao = "Mal";
                    objRegistroDTO.setEmocao(emocao);
                    
                } else if (bMaisOuMenos.isSelected()) {
                    emocao = "MaisOuMenos";
                    objRegistroDTO.setEmocao(emocao);
                    
                } else if (bBem.isSelected()) {
                    emocao = "Bem";
                    objRegistroDTO.setEmocao(emocao);
                    
                } else if (bRadiante.isSelected()) {
                    emocao = "Radiante";
                    objRegistroDTO.setEmocao(emocao);
                }


               RegistrosDAO objRegistrosDAO = new RegistrosDAO(); 
               objRegistrosDAO.registroCrise(objRegistroDTO); //EXCECUTA O MÉTODO
                try {
                    // String DATABASE_URL = "jdbc:postgresql://localhost:5432/aula2";
                    // String USER = "postgres";
                    // String PASSWORD = "postgres";
                    // String INSERT = "INSERT INTO public.\"Registros\"(\"Descricao\", \"Data_Registro\", \"Hora_Registro\", \"Emocao\") VALUES (";
                    
                    /*  try {
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                    //opera na conexao
                    java.sql.Statement stat = conn.createStatement();

                    INSERT += "'" + textArea1.getText() + "',";
                    INSERT += "'" + tData.getText() + "',";
                    INSERT += "'" + maskHoraFormatada.getText() + "',";

                    if (bHorrivel.isSelected()) {
                    INSERT += "'Horrivel');";
                    } else if (bMal.isSelected()) {
                    INSERT += "'Mal'" + ",";
                    } else if (bMaisOuMenos.isSelected()) {
                    INSERT += "'Mais ou menos');";
                    } else if (bBem.isSelected()) {
                    INSERT += "'Bem');";
                    } else if (bRadiante.isSelected()) {
                    INSERT += "'Radiante');";
                    }
                    stat.executeUpdate(INSERT);

                    JOptionPane.showMessageDialog(null, "Registro feito com sucesso!", "Cadastro de registro", JOptionPane.INFORMATION_MESSAGE);
                    gt.changeToTelaInicial(TelaRegistrosTcc.this);
                    
                    } catch (HeadlessException | SQLException ex) {
                    JOptionPane.showMessageDialog(TelaRegistrosTcc.this, ex.getMessage());
                    } */
                    
                    gt.changeToTelaInicial(TelaRegistrosTcc.this);
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "TelaRegistrosTCC" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Confira os campos obrigatórios", "Erro", JOptionPane.WARNING_MESSAGE);

            }

        }
    }
}
