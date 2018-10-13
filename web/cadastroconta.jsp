<html lang="pt-br">
    <head>
        <title>Banco</title>
        <meta charset="utf-8">
 
    </head>
    <body>
        <div>Banco</div>
   
        
        <form   action="/banco/cadastro" method="post" class="form-horizontal">
<fieldset>


<legend>Cadastro de conta</legend>


<div class="form-group">
  <label class="col-md-4 control-label" for="conta">conta</label>  
  <div class="col-md-4">
  <input id="conta" name="conta" type="text"  class="form-control input-md">
    
  </div>
</div>




<div class="form-group">
  <label class="col-md-4 control-label" for="cpf">Cpf</label>  
  <div class="col-md-4">
  <input id="cpf" name="cpf" type="text" class="form-control input-md" >
  
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="enviar"></label>
  <div class="col-md-4">
      <button id="enviar" type="submit" name="enviar" class="btn btn-success">Pronto</button>
  </div>
</div>
</fieldset>
</form>
        
    
    </body>
</html>
