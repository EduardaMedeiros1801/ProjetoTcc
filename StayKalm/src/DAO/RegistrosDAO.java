package DAO;

import DTO.RegistroDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RegistrosDAO {

    //reaproveitamento
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;  //trabalha com o comando sql

    //armazena as informaçãoes do banco
    ArrayList<RegistroDTO> lista = new ArrayList<>();

    public void registroCrise(RegistroDTO objRegistroDTO) {
        String sql = "INSERT INTO public.\"Registros\"(\n"
                + "	\"Data_Registro\", \"Hora_Registro\", \"Descricao\", \"Emocao\")\n"
                + "	VALUES (?, ?, ?, ?);";

        conn = new ConexaoDAO().conectaBD(); //CHAMA A CLASSE E ACESSA O METODO DE CONEXAO AO BD

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objRegistroDTO.getData_Registro()); //PEGA AS INFORMACOES
            pstm.setString(2, objRegistroDTO.getHora_Registro());
            pstm.setString(3, objRegistroDTO.getDescricao());
            pstm.setString(4, objRegistroDTO.getEmocao());

            pstm.execute();

            JOptionPane.showMessageDialog(null, "Registro feito com sucesso!", "Cadastro de registro", JOptionPane.INFORMATION_MESSAGE);

            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "RegistrosDAO: RegistroCrise" + erro.getMessage());
        }
    }

    //retorna uma lista pronta com os dados do BD
    public ArrayList<RegistroDTO> pesquisarRegistro() {
        String sql = "SELECT * FROM public.\"Registros\";";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            //enquanto tiver proximo
            while (rs.next()) {
                RegistroDTO objRegistroDTO = new RegistroDTO();
                objRegistroDTO.setId_Registro(rs.getString("id_Registro")); //seta no objeto um valor do BD
                objRegistroDTO.setData_Registro(rs.getString("Data_Registro"));
                objRegistroDTO.setHora_Registro(rs.getString("Hora_Registro"));
                objRegistroDTO.setDescricao(rs.getString("Descricao"));
                objRegistroDTO.setEmocao(rs.getString("Emocao"));

                lista.add(objRegistroDTO);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "RegistrosDAO: pesquisarRegistro" + erro.getMessage());
        }

        return lista;
    }

    public void alterarRegistro(RegistroDTO objRegistroDTO) {
        String sql = "UPDATE public.\"Registros\"\n"
                + "	SET \"Hora_Registro\"= '?', \"Descricao\"= '?', \"Data_Registro\"= '?'\n"
                + "	WHERE \"id_Registro\" = '?';";

        conn = new ConexaoDAO().conectaBD(); //CHAMA A CLASSE E ACESSA O METODO DE CONEXAO AO BD

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(3, objRegistroDTO.getData_Registro()); //PEGA AS INFORMACOES
            pstm.setString(1, objRegistroDTO.getHora_Registro());
            pstm.setString(2, objRegistroDTO.getDescricao());
            pstm.setString(4, objRegistroDTO.getId_Registro());

            pstm.execute();

            JOptionPane.showMessageDialog(null, "Alteracao feita com sucesso!", "Alteracao de registro", JOptionPane.INFORMATION_MESSAGE);

            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "RegistrosDAO: alterarRegistro" + erro.getMessage());
        }

    }

    public void excluiRegistro(RegistroDTO objRegistroDTO) {
        String sql = "DELETE from public.\"Registros\" where \"id_Registro\" = '?'; ";

        conn = new ConexaoDAO().conectaBD(); //CHAMA A CLASSE E ACESSA O METODO DE CONEXAO AO BD

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objRegistroDTO.getId_Registro());

            pstm.execute();

            JOptionPane.showMessageDialog(null, "Exclusao feita com sucesso!", "Alteracao de registro", JOptionPane.INFORMATION_MESSAGE);

            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "RegistrosDAO: excluirRegistro" + erro.getMessage());
        }

    }
}
