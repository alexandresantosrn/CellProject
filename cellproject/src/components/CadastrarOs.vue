<template>
    <div class="formcadastro">      
      <h2>Cadastrar Ordem de Serviço</h2>

      <Instrucoes :texto="texto" />
      
      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="cadastrarOs" id="form">

      <div class="row">       
        <div class="col-md-6">
          <label for="cpf">CPF do Cliente:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" placeholder="___.___.___-__" maxlength = "11" required>
        </div>

        <div class="col-md-6">
            <button type="submit" class="btn btn-primary" @click="consultarCliente()">Consultar Cliente</button>
              <div v-if="found">
                <h7>Cliente: {{ pessoa.nome }}</h7>
              </div>
              <div v-else>
                <h6>Cliente não localizado.</h6>
              </div>
        </div>  
        
        <div class="form-group">
            <label for="tipoServico">Tipo de Serviço:</label>
            <select class="form-control" id="tipoServico" v-model="selectedTipoServico" required>
              <option v-for="tipo in comboTipoServico" :key="tipo.id" :value="tipo.id">{{ tipo.descricao }}</option>           
            </select>
        </div>
  
        <div class="col-md-6">
            <label for="fabricante">Fabricante:</label>
            <select class="form-control" id="fabricante" v-model="selectedFabricante" required @change="getModelo">
              <option v-for="tipo in comboFabricante" :key="tipo.id" :value="tipo.id">{{ tipo.denominacao }}</option>           
            </select>
        </div>    

        <div class="col-md-6">
            <label for="modelo">Modelo:</label>
            <select class="form-control" id="modelo" v-model="selectedModelo" required>
              <option v-for="tipo in comboModelo" :key="tipo.id" :value="tipo.id">{{ tipo.modelo }}</option>           
            </select>
        </div>  
        
        <div class="form-group">
          <label for="imei">IMEI:</label>
          <input type="text" id="imei" v-model="imei" class="form-control" required>
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
      cpf: '',
      imei: '',
      selectedTipoServico: '',
      selectedFabricante: '',
      selectedModelo: '',
      comboTipoServico: [], 
      comboFabricante: [], 
      comboModelo: [],
      problemas: '',        
      texto: 'Prezado(a) usuário(a), informe abaixo todos os dados para efetivação do cadastro da ordem de serviço.',
      msg: '',
      msg_failure: '',
      pessoa: '',
      pessoaId: '',
      found: false
    };
  },
  methods: {
    consultarCliente() {
      const cpf = this.cpf;

      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
      }; 

      axios.get('http://localhost:8080/pessoa/pessoa-by-cpf?cpf='+cpf, config)
      .then(response => {
        this.pessoa = response.data;
        this.pessoaId = response.data.id;   
        this.found = true;

        if(this.pessoa.length < 1) {            
          this.found = false;
        }
      })
      .catch(error => {
        this.msg_failure = error.response.data;
        console.error('Erro ao buscar dados:', error);
      });
    },
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
    getFabricante() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
      }; 

      axios.get('http://localhost:8080/fabricante', config)
      .then(response => {
          this.comboFabricante = response.data;
      })
      .catch(error => {
          this.msg_failure = error.response.data;
          console.error('Erro ao buscar dados:', error);
      });
    }, 
    getModelo() {
      const token = sessionStorage.getItem('token');

      const id = this.selectedFabricante;

      const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
      }; 

      axios.get('http://localhost:8080/smartphone/modelo-by-fabricante?id='+id, config)
      .then(response => {
          this.comboModelo = response.data;
      })
      .catch(error => {
          this.msg_failure = error.response.data;
          console.error('Erro ao buscar dados:', error);
      });

    },  
    cadastrarOs() {
      const token = sessionStorage.getItem('token');
      console.log(this.imei);
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      const ordem = {
        problemas: this.problemas,
        tipoServico: this.selectedTipoServico,
        smartphone: this.selectedModelo,
        pessoa: this.pessoaId,
        imei: this.imei
      }           
      
      axios.post('http://localhost:8080/ordemservico/cadastrar-ordem', ordem, config)
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

      this.cpf = "";
      this.imei = "";
      this.problemas = "";
      this.selectedFabricante = "";
      this.selectedModelo = "";
      this.selectedTipoServico = "";
  }
  },
  mounted() {
    this.getTipoServico();
    this.getFabricante();
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

  h6 {
    color: red;
  }
  
  input {
      margin-bottom: 10px;
  } 

  button {
    margin-top: 10px;
    margin-bottom: 20px;
  } 
</style>