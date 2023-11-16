<template>
    <div class="formcadastro">      
      <Instrucoes :texto="texto" />
      
      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="realizarCadastro" id="form">
        <div class="form-group">
          <label for="name">Nome Completo:</label>
          <input type="text" id="name" v-model="nome" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="cpf">CPF:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" placeholder="___.___.___-__" maxlength = "11" required>
        </div>
  
        <div class="form-group">
          <label for="email">E-mail:</label>
          <input type="email" id="email" v-model="email" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="phone">Telefone:</label>
          <input type="tel" id="telefone" v-model="telefone" class="form-control" placeholder="(  )" maxlength = "11" required>
        </div>       
  
        <div class="form-group">
          <label for="password">Senha:</label>
          <input type="password" id="senha" v-model="senha" class="form-control" required>
        </div>
  
        <button type="submit" class="btn btn-primary">Confirmar Cadastro</button>
      </form>
    </div>
  </template>
  
<script>
import Instrucoes from '@/components/Instrucoes.vue';
import axios from 'axios';
import Message from '@/components/Message.vue';
import MessageFailure from '@/components/MessageFailure.vue';

export default {
  name: 'Cadastrar Cliente',   
  components: {
      Instrucoes,
      Message,
      MessageFailure
  }, 
  data() {
    return {         
      nome: '',
      cpf: '',
      email: '',
      telefone: '',         
      senha: '',          
      texto: 'Prezado usuário, preencha abaixo todos os seus dados completos, para efetivação do seu cadastro:',
      msg: '',
      msg_failure: ''
    };
  },
  methods: {
    realizarCadastro() {

      const pessoa = {
        nome: this.nome,
        cpf: this.cpf,
        email: this.email,
        telefone: this.telefone       
      }         
      
      axios.post('http://localhost:8080/pessoa/cadastrar-pessoa', pessoa)
        .then(response => {         
          this.msg_failure = '';                                  
          this.msg = response.data;          
          
          this.cadastrarUsuario(); 
          this.limparCampos();          
        })
        .catch(error => {                    
          this.msg = '';                    
          this.msg_failure = error.response.data;   
        });
    },
    cadastrarUsuario() {      

      const usuario = {
        login: this.email,
        senha: this.senha           
      }

      axios.post('http://localhost:8080/usuario/cadastrar-usuario',usuario)
        .then(response => {           
            this.msg_failure = '';                                  
            this.msg = response.data;
        })
        .catch(error => {      
          this.msg = '';                    
          this.msg = error.response.data;
            
        });                
   },
   limparCampos() {

    setTimeout(() => this.msg = "", 5000);

    this.nome = "";
    this.cpf = "";
    this.telefone = "";
    this.senha = "";
    this.email = "";
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