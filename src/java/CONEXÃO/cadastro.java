
package CONEXÃO;

import BANCO.Jdbc;
import classes.conta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "cadastro", urlPatterns = {"/cadastro"})
public class cadastro extends HttpServlet {
    
   
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Jdbc con = new Jdbc();
    private  Connection conexao;

  
public cadastro() throws SQLException, ClassNotFoundException{
    
    this.conexao = con.criarconexcao();
}    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   
        try {
           
            request.setCharacterEncoding("UTF8");
       
            response.setContentType("html");
            
           
            conta c = new conta();
        
            c.setCpf_titular(request.getParameter("cpf"));
            c.setNumero_conta(request.getParameter("conta"));
           
           
            String sql = "INSERT INTO `ContaCorrente` (`idContaCorrente`, `NumeroConta`, `CPF_Titular`) VALUES (NULL, '" + c.getNumero_conta()+ "', '" + c.getCpf_titular() + "')";
      
            ps = conexao.prepareStatement(sql);
         
            ps.execute(sql);
        
            ps.close();
            
            
         
            PrintWriter out = response.getWriter();  
     
            out.println("<b>Cafastro realizado com sucesso!</b>");
            out.println("<a href='/banco'>Voltar</b>");
        } catch (SQLException ex) {
            Logger.getLogger(cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            }
            
            
             
             
             
        }
        
 