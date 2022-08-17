/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author dumed
 */

//encapsulamento
public class RegistroDTO {
    private String id_Registro;  //string, pois não vou fazer conta
    private String data_Registro; 
    private String hora_Registro; 
    private String descricao; 
    private String emocao; 

    //RETORNA
    public String getId_Registro() {
        return id_Registro;
    }

    //MODIFICA
    public void setId_Registro(String id_Registro) {
        this.id_Registro = id_Registro;
    }

    public String getData_Registro() {
        return data_Registro;
    }

    public void setData_Registro(String data_Registro) {
        this.data_Registro = data_Registro;
    }

    public String getHora_Registro() {
        return hora_Registro;
    }

    public void setHora_Registro(String hora_Registro) {
        this.hora_Registro = hora_Registro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }
    
}
