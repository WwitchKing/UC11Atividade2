/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        
        try{
       prep = conn.prepareStatement("insert into produtos (nome, valor, Status) values (?,?,?)");
       
       prep.setString(1, produto.getNome());
       prep.setInt(2, produto.getValor());
       prep.setString(3, produto.getStatus());

       prep.executeUpdate();
       
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!" + ex.getMessage());
        
        
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
            List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();
            
                String sql = "select * from produtos";
                conn = new conectaDAO().connectDB();

        conn = new conectaDAO().connectDB();
        try{
        
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        while(resultset.next()){
        ProdutosDTO produto = new ProdutosDTO();
      
        produto.setId(resultset.getInt("id"));
        produto.setNome(resultset.getString("nome")); 
        produto.setValor(resultset.getInt("valor"));
        produto.setStatus(resultset.getString("status"));
        
        listagem.add(produto);
        
        }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos!" + ex.getMessage());
        }
        
        
        return listagem;
    }
    
    
    
        
}

