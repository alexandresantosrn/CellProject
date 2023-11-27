<template>
    <div class="formcadastro">      
      <h2>Cadastrar Produto</h2>

      <Instrucoes :texto="texto" />
      
      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="realizarCadastro" id="form">

      <div class="row">
        
        <div class="col-md-4">
          <label for="codigo">Código:</label>
          <input type="text" id="codigo" v-model="codigo" class="form-control" maxlength = "6" required>
        </div>
                    

        <div class="col-md-5">
          <label for="dataNascimento">Data de Entrada:</label>
          <input v-model="dataEntrada" type="date" class="form-control" id="dataEntrada" required>
        </div> 
        
       
        <div class="col-md-3">
          <label for="quantidade">Quantidade:</label>
          <input type="text" id="quantidade" v-model="quantidade" class="form-control" maxlength = "4" required>
        </div>   
  
        <div class="form-group">
          <label for="denominacao">Denominação:</label>
          <input type="denominacao" id="denominacao" v-model="denominacao" class="form-control" required>
        </div>    
    
        <button type="submit" class="btn btn-primary">Confirmar Cadastro</button>
      </div>
      </form>
    </div>
  </template>
  
<script>
import Instrucoes from '@/components/Instrucoes.vue';
import axios from 'axios';
import Message from '@/components/Message.vue';
import MessageFailure from '@/components/MessageFailure.vue';

export default {
  name: 'Cadastrar Produto',   
  components: {
      Instrucoes,
      Message,
      MessageFailure
  }, 
  data() {
    return {         
      codigo: '',     
      dataEntrada: '',   
      quantidade: '',
      denominacao: '',       
      texto: 'Prezado(a) usuário(a), informe abaixo todos os dados do produto a ser cadastrado.',
      msg: '',
      msg_failure: ''
    };
  },
  methods: {
    realizarCadastro() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      const produto = {
        codigo: this.codigo,
        dataEntrada: this.dataEntrada,
        quantidade: this.quantidade,
        denominacao: this.denominacao
      }         
      
      axios.post('http://localhost:8080/produto/cadastrar-produto', produto, config)
        .then(response => {         
          this.msg_failure = '';                                  
          this.msg = response.data;       
          this.limparCampos();          
        })
        .catch(error => {                    
          this.msg = '';                    
          this.msg_failure = error.response.data;   
        });
    },    
    limparCampos() {
      setTimeout(() => this.msg = "", 5000);
      this.codigo = "";
      this.dataEntrada = "";
      this.quantidade = "";
      this.denominacao = "";        
    }
  }
}
</script>

<style scoped>
  .formcadastro {
      width: 500px;       
      margin: auto;        
      text-align: center;       
  }

  #form{
    display: flexbox;
    align-content: flex-start;
  }

  label {             
      margin-bottom: 5px;
      margin-top: 5px;
      color: #222;        
      border-left: 4px solid ;
      width: 500px;
      text-align: left;
      padding: 8px;
  }
  
  input {
      margin-bottom: 10px;
  } 

  button {
    margin-top: 10px;
    margin-bottom: 20px;
  } 
</style>