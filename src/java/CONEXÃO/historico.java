package CONEXÃO;
import BANCO.Jdbc;
import classes.conta;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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


@WebServlet(name = "historico", urlPatterns = {"/historico"})
public class historico extends HttpServlet {
  
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Jdbc con = new Jdbc();
    private  Connection conexao;
   
    
public historico() throws SQLException, ClassNotFoundException{

    this.conexao = con.criarconexcao();
}    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
            request.setCharacterEncoding("UTF8");
            response.setContentType("html");
          
            conta conta_teste = new conta();
        try {
            
            conta_teste = conta_buscar(request.getParameter("conta"));
        } catch (SQLException ex) {
            Logger.getLogger(historico.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            if(conta_teste.getId_conta() <= 0){
            PrintWriter out = response.getWriter();  
            out.println("<b>A conta não existe</b>");
            out.println("<a href='/banco'>Voltar</b>");
            }else{
                try {
                  
                    
                    String sql = "SELECT * FROM `Operacao` WHERE `idContaCorrente` ="+conta_teste.getId_conta()+" ORDER BY `Operacao`.`DataOperacao` ASC";
                    
                    ps = conexao.prepareStatement(sql);
                 
                    rs = ps.executeQuery(sql);
                    
                    PrintWriter out = response.getWriter();
                  
                    String texto = " <p>CPF:"+conta_teste.getCpf_titular()+"<p><b>"+"<p>Número da conta: "+conta_teste.getNumero_conta()+"</p><b>\""
                            + "<tr>\n" +
                            "    <th class=\"tg-0pky\">Data</th>\n" +
                            "    <th class=\"tg-0pky\">Valor</th>\n" +
                            "    <th class=\"tg-0lax\">CPF</th>\n" +
                            "    <th class=\"tg-0lax\">id</th>\n" +
                            "    <th class=\"tg-0lax\">Tipo OP</th>\n" +
                            "  </tr>";
                    
                  
                    while (rs.next()) {
                      
                        String op;
                        
                        BigDecimal b = new BigDecimal(0);
                       
                        BigDecimal a = rs.getBigDecimal("ValorOperacao");
              
                        if(a.compareTo(b) > 0 ){
                            op = "C";
                        }else{
                            op = "D";
                        }
                    
                        texto += "<tr>\n" +
                                "    <td class=\"tg-0lax\">"+rs.getDate("DataOperacao")+"</td>\n" +
                                "    <td class=\"tg-0lax\">"+rs.getBigDecimal("ValorOperacao")+"</td>\n" +
                                "    <td class=\"tg-0lax\">"+rs.getString("CPFResponsavelOperacao")+"</td>\n" +
                                "    <td class=\"tg-0lax\">"+rs.getInt("idContaCorrente")+"</td>\n" +
                                "    <td class=\"tg-0lax\">"+op+"</td>\n" +
                                "  </tr>\n";
                        
                    }
                
                    out.println(texto+"\"</table><br>Saldo Final:"+total_soma(conta_teste.getId_conta())+"<br><a href='/banco'>Voltar</b>\"");
                } catch (SQLException ex) {
                    Logger.getLogger(historico.class.getName()).log(Level.SEVERE, null, ex);
                }
       
       
            
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
     
   
      public String total_soma (int id) throws SQLException {
                           
        String sql = "SELECT SUM(ValorOperacao) as soma FROM `Operacao` WHERE `idContaCorrente` = "+id+"";
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        String soma = "";
        while (rs.next()) {
            soma = rs.getString("soma");
                       
        }

        ps.close();
        return soma;
    }
            
             
             
             
        }
        
 