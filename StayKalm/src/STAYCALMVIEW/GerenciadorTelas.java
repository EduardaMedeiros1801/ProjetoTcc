/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STAYCALMVIEW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFrame;

/**
 *
 * @author dumed
 */
public class GerenciadorTelas {
    
    TelaApresentacaoTcc frame1;
    TelaInicialTcc frame2;
    TelaRegistrosTcc frame3;
    TelaRetornoDadosTcc frame4;    
    
    public void start() throws ParseException {
        
        frame1 = new TelaApresentacaoTcc(this);
        frame1.setVisible(true);
        
        frame2 = new TelaInicialTcc(this);
        frame2.setVisible(false);
        
        frame3 = new TelaRegistrosTcc(this);
        frame3.setVisible(false);
        
        frame4 = new TelaRetornoDadosTcc(this);        
        frame4.setVisible(false);
    }
    
    public void changeToTelaInicial(JFrame telaAnterior) throws SQLException {
        telaAnterior.setVisible(false);
        
        frame2.setVisible(true);
    }
    
    public void changeToTelaRegistro(JFrame telaAnterior) throws SQLException {
        telaAnterior.setVisible(false);
        frame3.setVisible(true);
        
    }
    
    public void changeToTelaRetornoDados(JFrame telaAnterior, ResultSet rs) throws SQLException{
        
        telaAnterior.setVisible(false);
        
      /*  frame4.tCodigo.setText(String.valueOf(rs.getObject(0)));
        frame4.tData.setText(String.valueOf(rs.getObject(1)));
        frame4.tHora.setText(String.valueOf(rs.getObject(2)));
        frame4.tDescricao.setText(String.valueOf(rs.getObject(3)));
        frame4.tEmocao.setText(String.valueOf(rs.getObject(4))); */ 
        
        frame4.setVisible(true);  
    }
    
}

/* //evento do botao ver cadastro da tela de listagem
    public void telaInicialToRegistro(ResultSet rs) throws SQLException{
        frame2.setVisible(false);
        
        frame3.tData.setText(String.valueOf(rs.getObject(1)));
        frame3.tData.setEditable(false); 
        
        
    }

}
    
 //evento do botao VerCadastro da tela de listagem
    public void tela4ToTela3(ResultSet rs) throws ParseException, SQLException {
        frame4.setVisible(false);

        frame3 = new Tela3(this);

        frame3.tNome.setText(String.valueOf(rs.getObject(1)));
        frame3.tNome.setEditable(false);

        frame3.maskCpfFormatado.setText(String.valueOf(rs.getObject(2)));
        frame3.maskCpfFormatado.setEditable(false);

        frame3.tData_nas.setText(String.valueOf(rs.getObject(3)));
        frame3.tData_nas.setEditable(false);

        //necessario pegar o objeto da coluna do bd e comparar
        if (rs.getObject("sexo").toString().equals("Feminino")) {
            frame3.sexo1.setSelected(true);
            frame3.sexo2.setSelected(false);
            frame3.sexo3.setSelected(false);

        } else if (rs.getObject("sexo").toString().equals("Masculino")) {
            frame3.sexo1.setSelected(false);
            frame3.sexo2.setSelected(true);
            frame3.sexo3.setSelected(false);
        } else {
            frame3.sexo1.setSelected(false);
            frame3.sexo2.setSelected(false);
            frame3.sexo3.setSelected(true);
        }

        frame3.tEmail.setText(String.valueOf(rs.getObject(5)));
        frame3.tEmail.setEditable(false);

        frame3.maskTelefoneFormatado.setText(String.valueOf(rs.getObject(6)));
        frame3.maskTelefoneFormatado.setEditable(false);

        if (rs.getObject("wpp").toString().equals("Sim")) {
            frame3.jCheck.setSelected(true);
            // frame3.jCheck.setEnabled(false);
        } else {
            frame3.jCheck.setSelected(false);
            //frame3.jCheck.setEnabled(false);
        }

       // frame3.estados.setSelectedIndex(Integer.parseInt(String.valueOf(rs.getObject(8))) - 1);
       // frame3.estados.setEditable(false);

        frame3.tCidade.setText(String.valueOf(rs.getObject(9)));
        frame3.tCidade.setEditable(false);

        frame3.tEndereco.setText(String.valueOf(rs.getObject(10)));
        frame3.tEndereco.setEditable(false);

        frame3.cadastrar.setEnabled(false);

        frame3.setVisible(true);
    } */
