<template>
    <div class="formcadastro">      
      <h2>Cadastrar Ordem de Serviço</h2>

      <Instrucoes :texto="texto" />
      
      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="realizarCadastro" id="form">

      <div class="row">       
        <div class="col-md-6">
          <label for="cpf">CPF do Cliente:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" placeholder="___.___.___-__" maxlength = "11" required>
        </div>

        <div class="col-md-6">
            <button type="submit" class="btn btn-primary">Consultar Cliente</button>
            <h6>Cliente não localizado.</h6>
        </div>  
        
        <div class="form-group">
            <label for="tipoServico">Tipo de Serviço:</label>
            <select class="form-control" id="tipoServico" v-model="selectedTipoServico" required>
              <option v-for="tipo in comboTipoServico" :key="tipo.id" :value="tipo.id">{{ tipo.descricao }}</option>           
            </select>
        </div>
  
        <div class="col-md-6">
            <label for="fabricante">Fabricante:</label>
            <select class="form-control" id="fabricante" v-model="selectedFabricante" required>
              <option v-for="tipo in comboFabricante" :key="tipo.id" :value="tipo.id">{{ tipo.descricao }}</option>           
            </select>
        </div>    

        <div class="col-md-6">
            <label for="modelo">Modelo:</label>
            <select class="form-control" id="modelo" v-model="selectedModelo" required>
              <option v-for="tipo in comboModelo" :key="tipo.id" :value="tipo.id">{{ tipo.descricao }}</option>           
            </select>
        </div>  
        
        <div class="form-group">
          <label for="cpf">IMEI:</label>
          <input type="text" id="imedi" v-model="imed" class="form-control" required>
        </div>

        <div class="form-group">
          <label for="problemas">Problemas Apresentados:</label>
          <textarea class="form-control" id="problemas" rows="3"></textarea>
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
      sexo: '',
      dataNascimento: '',
      selectedTipoServico: '',
      comboTipoServico: [],          
      texto: 'Prezado(a) usuário(a), informe abaixo todos os dados para efetivação do cadastro da ordem de serviço.',
      msg: '',
      msg_failure: ''
    };
  },
  methods: {
    getTipoServico() {
      const token = sessionStorage.getItem('token');

      const config = {
          headers: {
              Authorization: `Bearer ${token}`
          }
      }; 

      axios.get('http://localhost:8080/tiposervico', config)
      .then(response => {
          this.comboTipoServico = response.data;
      })
      .catch(error => {
          this.msg_failure = error.response.data;
          console.error('Erro ao buscar dados:', error);
      });
    },  
    realizarCadastro() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      const pessoa = {
        nome: this.nome,
        cpf: this.cpf,
        email: this.email,
        telefone: this.telefone, 
        dataNascimento: this.dataNascimento,
        sexo: this.sexo
      }         
      
      axios.post('http://localhost:8080/pessoa/cadastrar-pessoa', pessoa, config)
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

      this.nome = "";
      this.cpf = "";
      this.telefone = "";
      this.senha = "";
      this.email = "";
   }
  },
  mounted() {
    this.getTipoServico();
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