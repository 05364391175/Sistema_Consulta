/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Consulta;
import mapeamento.Paciente;
import utilitario.Conectar;

/**
 *
 * @author Elias
 */
public class ConsultaDAO {
    
    
    public List<Consulta> listarTodas(){
        Connection con = Conectar.getConectar();
        List<Consulta> listaFuncionario = new ArrayList<>();
        String sql = "SELECT * FROM consulta INNER JOIN paciente ON paciente.id_paciente = consulta.id_paciente INNER JOIN medico ON medico.id_medico = consulta.id_medico INNER JOIN funcionario ON funcionario.id_funcionario = consulta.id_funcionario";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Consulta consulta = new Consulta();
                consulta.setId_consulta(resultado.getInt("id_consulta"));
                consulta.setDataAtendimento(resultado.getString("dataAtendimento"));
                consulta.setHorario(resultado.getString("horario"));
                Paciente p = new Paciente();
                p.setId_pacinte(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("paciente.nome"));
                consulta.setP(p);
                System.out.println(resultado.getInt("id_consulta")+" - "+resultado.getString("dataAtendimento"));
                listaFuncionario.add(consulta);
            }
            smt.close();
            con.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os funcionários "+ex.getMessage());
        }
        return listaFuncionario;
    }
    
    public static void main(String []args){
        ConsultaDAO cdao = new ConsultaDAO();
         List<Consulta> lista = cdao.listarTodas();
         for(Consulta c : lista){
             System.out.println(c.getP().getNome());
         }
    }
}
