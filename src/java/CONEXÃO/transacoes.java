
package CONEXÃO;

import BANCO.Jdbc;
import classes.conta;
import classes.operacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "transacoes", urlPatterns = {"/transacoes"})
public class transacoes extends HttpServlet {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Jdbc con = new Jdbc();
    private  Connection conexao;
    
public transacoes() throws SQLException, ClassNotFoundException{
    this.conexao = con.criarconexcao();
}    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           
        try {
       
            request.setCharacterEncoding("UTF8");
            response.setContentType("html");
           
            conta conta_teste = new conta();
       
         
            conta_teste = conta_buscar(request.getParameter("conta"));
      
           
            if(conta_teste.getId_conta() <= 0){
                PrintWriter out = response.getWriter();
                out.println("<b>A conta não existe</b>");
                out.println("<a href='/banco'>Voltar</b>");
            }else{
              
                operacao o = new operacao();
                o.setCpf_responsavel_op(request.getParameter("cpf"));
                o.setValor_op(Float.parseFloat(request.getParameter("valor")));
                
               
                DateFormat formatter ;
                Date date ;
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                date = (Date)formatter.parse(request.getParameter("data"));
                o.setData_op(date);
                
                
    
                String sql = "INSERT INTO `teste`.`Operacao` (`idOperacao`, `DataOperacao`, `ValorOperacao`, `CPFResponsavelOperacao`, `idContaCorrente`) VALUES"+
                        "(NULL, '" +  new java.sql.Date(o.getData_op().getTime())+ "', '" + o.getValor_op()+ "','" + o.getCpf_responsavel_op()+ "','" + conta_teste.getId_conta()+ "')";
                
                ps = conexao.prepareStatement(sql);
                ps.execute(sql);
                ps.close();
                PrintWriter out = response.getWriter();
                out.println("<b>Cafastro realizado com sucesso!</b>");
                out.println("<a href='/banco'>Voltar</b>");
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(transacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(transacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            
            
         
     public conta conta_buscar(String conta) throws SQLException {

        String sql = "SELECT * FROM ContaCorrente WHERE `NumeroConta` = '" +conta+ "'";
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        conta cc = new conta();
        while (rs.next()) {
            cc.setCpf_titular(rs.getString("CPF_Titular"));
            cc.setNumero_conta(rs.getString("NumeroConta"));
            cc.setId_conta(rs.getInt("idContaCorrente"));
           
        }

        ps.close();
        return cc;
    }
            
             
             
             
        }
        
 