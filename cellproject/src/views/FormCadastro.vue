<template>
    <div class="formcadastro">
      
      <div id="descricao">
            <p>Prezado usuário, preencha abaixo todos os seus dados completos, para efetivação do seu cadastro:</p>
      </div>

      <form @submit.prevent="submitForm" id="form">
        <div class="form-group">
          <label for="name">Nome Completo:</label>
          <input type="text" id="name" v-model="formData.name" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="cpf">CPF:</label>
          <input type="text" id="cpf" v-model="formData.cpf" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="email">E-mail:</label>
          <input type="email" id="email" v-model="formData.email" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="phone">Telefone:</label>
          <input type="tel" id="phone" v-model="formData.phone" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="login">Usuário:</label>
          <input type="text" id="login" v-model="formData.login" class="form-control" required>
        </div>
  
        <div class="form-group">
          <label for="password">Senha:</label>
          <input type="password" id="password" v-model="formData.password" class="form-control" required>
        </div>
  
        <button type="submit" class="btn btn-primary">Confirmar Cadastro</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        formData: {
          name: '',
          cpf: '',
          email: '',
          phone: '',
          login: '',
          password: '',
        }
      };
    },
    methods: {
      submitForm() {
        // Enviar dados para o backend (usando uma chamada Axios, por exemplo)
        // Limpar o formulário ou executar qualquer ação desejada após o envio
      }
    }
  };
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

    #descricao {
        margin-top: 10px;
        margin-bottom: 10px;
        width: auto;
        height: auto;
        background-color: #FFFFE0;
        text-align: center;
        font-size: 16px;
        
    }

    input, button {
        margin-bottom: 10px;
    } 
  </style>