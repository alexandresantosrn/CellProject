<template>
    <div class="formcadastro">      
      <h2>Requisitar Peças</h2>

      <Instrucoes :texto="texto" />
      
      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="cadastrarRequisicao" id="form">

      <div class="row">       
        <div class="col-md-6">
          <label for="cpf">N&#186 Ordem de Serviço:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" required>
        </div>

        <div class="col-md-6">
            <button type="submit" class="btn btn-primary" @click="consultarOs()">Consultar OS</button>
              <div v-if="found">
                <h7>Os: {{ pessoa.codigo }}</h7>
              </div>
              <div v-else>
                <h6>Ordem de serviço não localizada.</h6>
              </div>
        </div>  
        
        <div class="col-md-2">
          <label for="quantidade">Qtd:</label>
          <input type="text" id="quantidade" v-model="quantidade" class="form-control" required>
        </div>

        <div class="col-md-10">
            <label for="tipoServico">Peça / Produto:</label>
            <select class="form-control" id="tipoServico" v-model="selectedTipoServico" required>
              <option v-for="tipo in comboTipoServico" :key="tipo.id" :value="tipo.id">{{ tipo.denominacao }}</option>           
            </select>
        </div> 
       
        <button type="submit" class="btn btn-primary">Requisitar Peça</button>
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
  name: 'Requisitar Peças',   
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
      quantidade: '',
      comboTipoServico: [], 
      problemas: '',        
      texto: 'Prezado(a) usuário(a), informe abaixo todos os dados para efetivação do cadastro da requisição da peça.',
      msg: '',
      msg_failure: '',
      pessoa: '',
      pessoaId: '',
      found: false
    };
  },
  methods: {
    consultarOs() {
      const codigo = this.cpf;

      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
      }; 

      axios.get('http://localhost:8080/ordemservico/ordem-by-codigo?codigo='+codigo, config)
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
    getPecas() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }; 

      axios.get('http://localhost:8080/produto/listar-produtos', config)
      .then(response => {
          this.comboTipoServico = response.data;
      })
      .catch(error => {
          this.msg_failure = error.response.data;
          console.error('Erro ao buscar dados:', error);
      });
    },   
    cadastrarRequisicao() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      const ordem = {       
        id: this.pessoaId
      }           
      
      axios.post('http://localhost:8080/ordemservico/atualizar-ordem', ordem, config)
        .then(response => {         
          this.msg_failure = '';                                  
         
          this.requisitarPeca();
          
          this.limparCampos();          
        })
        .catch(error => {                    
          this.msg = '';                    
          this.msg_failure = error.response.data;   
        });
    },
    requisitarPeca() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      const requisicao = {     
        ordemServico: this.pessoaId,
        produto: this.selectedTipoServico, 
        quantidade: this.quantidade            
      }           
      
      axios.post('http://localhost:8080/requisicaopecas/cadastrar-requisicao', requisicao, config)
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
      this.selectedTipoServico = "";
  }
  },
  mounted() {
    this.getPecas();    
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