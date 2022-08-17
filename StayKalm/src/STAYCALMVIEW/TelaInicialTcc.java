/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import DAO.ConexaoDAO;
import DAO.RegistrosDAO;
import DTO.RegistroDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author dumed
 */
public class TelaInicialTcc extends ClasseAbstrata {

    private final GerenciadorTelas gt;

    protected final FlowLayout layout1;

    private final JTable tabelaRegistro;

    //private final JButton bAlterarDado;
    //private final JButton bExcluirDado;
    //  private final JButton bVerRegistro;
    private final JButton bPesquisar;

    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;

    // private static final String URL = "jdbc:postgresql://localhost:5432/aula2";
    // private static final String USER = "postgres";
    // private static final String PASSWORD = "postgres";
    //consulta padrao resupera todos os dados da tabela
    //private static final String QUERY = "SELECT \"id_Registro\", \"Data_Registro\", \"Hora_Registro\", \"Descricao\", \"Emocao\"\n"
    //        + "	FROM public.\"Registros\";";
    private Connection conn;
    private Statement state;
    private ResultSet resultSet;

    // private static TableModelTcc tableModel;
    public TelaInicialTcc(GerenciadorTelas gt) {
        super("Tela Inicial", gt);
        super.setLayout(new BorderLayout());
        super.setSize(900, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);

        this.gt = gt;

        layout1 = new FlowLayout();

        bPesquisar = new JButton("Pesquisar");
        bPesquisar.setFont(fontComponentes);
        bPesquisar.setBackground(new java.awt.Color(173, 216, 230));
        bPesquisar.addActionListener(new bPesquisar());

        panel1 = new JPanel();
        panel1.setLayout(layout1);

        panel1.add(bPesquisar);

        add(panel1, BorderLayout.NORTH);

       /* bAlterarDado = new JButton("Alterar Dados");
        bAlterarDado.setFont(fontComponentes);
        bAlterarDado.setBackground(Color.lightGray);
        bAlterarDado.setEnabled(false);
        bAlterarDado.addActionListener(new bAlterarRegistro());

        bExcluirDado = new JButton("Excluir Dados");
        bExcluirDado.setFont(fontComponentes);
        bExcluirDado.setBackground(Color.lightGray);
        bExcluirDado.setEnabled(false);
        bExcluirDado.addActionListener(new bExcluirRegistro()); */ 

        panel3 = new JPanel();
        panel3.setLayout(layout1);
        // panel3.add(bVerRegistro);
      //  panel3.add(bAlterarDado);
       // panel3.add(bExcluirDado);

        //teste
        String[] colunas = {"Nome", "Telefone", "Email"};
        Object[][] dados = {
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
        };
//
        tabelaRegistro = new JTable(dados, colunas);
        tabelaRegistro.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Código", "Data", "Hora", "Descricao", "Emocao"
                }
        ));
        tabelaRegistro.setSelectionBackground(Color.ORANGE);
        JScrollPane sp = new JScrollPane(tabelaRegistro);
        tabelaRegistro.addMouseListener(new MouseClicked());

        //declara a jtable como ouvinte dos eventos da tablemodel
        // table = new JTable(tableModel);
        //table.setSelectionBackground(Color.CYAN);
        // JScrollPane sp = new JScrollPane(table);
        /*  ArrayList dados = new ArrayList();
        
        try {
        tableModel.resultSet.first();
        do {
        //adicionando objetos nas linhas
        dados.add(new Object[]{tableModel.resultSet.getInt("id_Registro"), tableModel.resultSet.getDate("Data_Registro"),
        tableModel.resultSet.getString("Hora_Registro"), tableModel.resultSet.getString("Descricao"), tableModel.resultSet.getString("Emocao")});
        
        } while (tableModel.resultSet.next());  //enquando houver dados ele percorrera o registro
        } catch (SQLException sqlException) {
        System.out.println("Erro ao preencher arraylist \n" + sqlException.getMessage());
        }
        
        table.getColumnModel().getColumn(0).setPreferredWidth(560);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(232);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(560);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(232);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(232);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(232);
        table.getColumnModel().getColumn(4).setResizable(false);
        
        table.getTableHeader().setReorderingAllowed(false); //não pode reordenar o cabecario
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF); //O usuario não pode redimensionar a tabela
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //seleciona apenas um dado por vez
        
        table.addMouseListener(new MouseClicked());
        
        //classifica as linhas que exibem o resultado das colunas, permitem reordenar
        final TableRowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<javax.swing.table.TableModel>(tableModel);
        
        table.setRowSorter(sorter); */
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(sp);
        add(this.panel2, BorderLayout.CENTER);
        add(this.panel3, BorderLayout.SOUTH);

    }

    private class MouseClicked extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int yPos = e.getY(); //recebe a posicao y do mouse 
            int xPos = e.getX(); //recebe a posicao x do mouse

           // bAlterarDado.setEnabled(true);

           // bExcluirDado.setEnabled(true);

        }

    }

    private class bPesquisar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            listarValoresRegistros();
        }

    }

    public void listarValoresRegistros() {
        try {
            RegistrosDAO objRegistrosDAO = new RegistrosDAO();
            RegistroDTO objRegistroDTO = new RegistroDTO();

            DefaultTableModel tableModel = (DefaultTableModel) tabelaRegistro.getModel();
            tableModel.setNumRows(0); //num de linhas

            ArrayList<RegistroDTO> lista = objRegistrosDAO.pesquisarRegistro();

            //enquanto tiver lista ele fica no loop
            for (int num = 0; num < lista.size(); num++) {

                //linhas e colunas
                tableModel.addRow(new Object[]{
                    lista.get(num).getId_Registro(),
                    lista.get(num).getData_Registro(),
                    lista.get(num).getHora_Registro(),
                    lista.get(num).getDescricao(),
                    lista.get(num).getEmocao()
                });
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Tela Inicial: Listas valores " + erro.getMessage());

        }
    }

    private class bAlterarRegistro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try { 
                alterarRegistro();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Tela Inicial: alterarRegistro " + ex.getMessage());
            }
        }
    }

    private class bExcluirRegistro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try { 
                excluirRegistro();
                listarValoresRegistros(); 
                
            } catch (ParseException ex) {
                Logger.getLogger(TelaInicialTcc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void alterarRegistro() throws ParseException {
        String idRegistro, dataRegistro, horaRegistro, descricaoRegistro;

        TelaRegistrosTcc objTelaRegistro = new TelaRegistrosTcc(gt);

      //  idRegistro = objTelaRegistro.tIdRegistro.getText();
        
        //System.out.println(idRegistro);
        
        dataRegistro = objTelaRegistro.tData.getText();
        
        //System.out.println(dataRegistro);
        
        horaRegistro = objTelaRegistro.maskHoraFormatada.getText();
        descricaoRegistro = objTelaRegistro.textArea1.getText();

        RegistroDTO objRegistroDTO = new RegistroDTO();
        //objRegistroDTO.setId_Registro(idRegistro);
        objRegistroDTO.setData_Registro(dataRegistro);
        objRegistroDTO.setHora_Registro(horaRegistro);
        objRegistroDTO.setDescricao(descricaoRegistro);

        RegistrosDAO objRegistrosDAO = new RegistrosDAO();
        objRegistrosDAO.alterarRegistro(objRegistroDTO);

    }
    
    public void excluirRegistro() throws ParseException{
        String idRegistro, dataRegistro, horaRegistro, descricaoRegistro;

        TelaRegistrosTcc objTelaRegistro = new TelaRegistrosTcc(gt);
        
       // idRegistro = objTelaRegistro.tIdRegistro.getText(); 
        
        RegistroDTO objRegistroDTO = new RegistroDTO(); 
       // objRegistroDTO.setId_Registro(idRegistro);
        
        RegistrosDAO objRegistrosDAO = new RegistrosDAO(); 
        objRegistrosDAO.excluiRegistro(objRegistroDTO);
        
        
        
    }

    /*private class bCarregarCampos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                carregarCampos();
                
                gt.changeToTelaRegistro(TelaInicialTcc.this);
                
            } catch (SQLException ex) {
                Logger.getLogger(TelaInicialTcc.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaInicialTcc.class.getName()).log(Level.SEVERE, null, ex);
            }

            /* if(tFiltro.getText().length() > 0){
                ConexaoDAO conexao = new ConexaoDAO(); 
                conexao.conectaBD(); 
                
                try { 
                    state = conexao.conn.createStatement();
                    resultSet = state.executeQuery("SELECT \"id_Registro\", \"Data_Registro\", \"Hora_Registro\", \"Descricao\", \"Emocao\"\n" +
"	FROM public.\"Registros\" WHERE \"Emocao\" like '%" + tFiltro.getText() + "%';");
                    
                    resultSet.next(); 
                    gt.changeToTelaRetornoDados(TelaInicialTcc.this, resultSet);
                    
                    conexao.conn.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TelaInicialTcc.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else{
                JOptionPane.showMessageDialog(null, "Preencha o filtro de pesquisa");
            }

        } 
        }

    }

    //NÃO FUNCIONA
    private void carregarCampos() throws SQLException, ParseException {
        int setar = tabelaRegistro.getSelectedRow(); //pega a linha selecionada

        TelaRegistrosTcc objRegistroTcc = new TelaRegistrosTcc(gt); 

        objRegistroTcc.tData.setText(tabelaRegistro.getModel().getValueAt(setar, 0).toString());
        objRegistroTcc.maskHoraFormatada.setText(tabelaRegistro.getModel().getValueAt(setar, 1).toString());
        objRegistroTcc.textArea1.setText(tabelaRegistro.getModel().getValueAt(setar, 3).toString());
          
    }
    
    private void alterarRegistro(){
        String idRegistro; 
        String horaRegistro; 
        String descricaoRegistro; 
        String emocaoRegistro;
        String dataRegistro; 
    } */
 /*private class ButtonVerCadastro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (fieldFiltro.getText().length() > 0) {
                try {
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    //JOptionPane.showMessageDialog(null, "Conectado com sucesso");

                    state = conn.createStatement();
                    resultSet = state.executeQuery("select nome, cpf, data_nasc, sexo, email, telefone, wpp, estados_id, cidade, endereco from clientes where nome like '%" + fieldFiltro.getText() + "%';");

                    //JOptionPane.showMessageDialog(null, "Query produzida com sucesso");
                    try {
                        resultSet.next();
                        gf.tela4ToTela3(resultSet);

                        //JOptionPane.showMessageDialog(null, "Inserção feita com sucesso");
                        conn.close();

                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na insercao" + ex.getMessage());
                    }
                } catch (SQLException ex2) {
                    System.out.println("Erro ao conectar" + ex2.getMessage());
                    // conn.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preenchimento do campo filtrar é obrigatório", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /*private class ButtonAlterarDebito implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tFiltro.getText().length() > 0) {
                try {
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = state.executeQuery("select id_Registro, Data_Registro, Hora_Registro, Descricao, Emocao from Registros where nome Emocao '%" + tFiltro.getText() + "%';");

                    resultSet.next();

                    String id = resultSet.getObject(0).toString();
                    String data_registro = resultSet.getObject(1).toString();
                    String hora_registro = resultSet.getObject(2).toString();
                    String Descricao = resultSet.getObject(3).toString();
                    String Emocao = resultSet.getObject(4).toString();

                    gt.tela4ToTela6(nome, cpf, debito);

                } catch (SQLException ex) {
                    System.out.println("erro na conexao" + ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Preenchimento do campo filtrar é obrigatório", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        } */
}
