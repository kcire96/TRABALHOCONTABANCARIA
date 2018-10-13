package classes;

import java.util.Date;



public class operacao {
   private int id_op;
   private Date data_op;
   private float valor_op;
   private String cpf_responsavel_op;
   private int id_conta;

  
    public int getId_op() {
        return id_op;
    }

  
    public void setId_op(int id_op) {
        this.id_op = id_op;
    }

    
    public Date getData_op() {
        return data_op;
    }

    
    public void setData_op(Date data_op) {
        this.data_op = data_op;
    }

  
    public float getValor_op() {
        return valor_op;
    }

    public void setValor_op(float valor_op) {
        this.valor_op = valor_op;
    }


    public String getCpf_responsavel_op() {
        return cpf_responsavel_op;
    }

    public void setCpf_responsavel_op(String cpf_responsavel_op) {
        this.cpf_responsavel_op = cpf_responsavel_op;
    }


    public int getId_conta() {
        return id_conta;
    }

 
    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

   
   
}
