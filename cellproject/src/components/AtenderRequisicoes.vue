<template>
    <div id="agendamentos">
        <h2>Listar/Atender Requisições</h2>

        <div id = "instrucoes">
            <Instrucoes :texto="texto" />
        </div>  

        <Message :msg="msg" v-show="msg" />
        <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

        <form @submit.prevent="consultarRequisicoes">
       
            <div class="form-group">
                    <label for="statusagendamento">Status da Requisição:</label>
                    <select class="form-control" id="statusagendamento" v-model="selectedStatusAgendamento" required>
                    <option v-for="status in comboStatusAgendamento" :key="status.id" :value="status.id">{{ status.descricao }}</option>           
                    </select>
                </div>
            <button type="submit" class="btn btn-primary">Consultar</button>
            
        </form>

        <div v-if="resultados.length > 0" class="result">      
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Nº Ordem de Serviço</th>
                <th scope="col">Data da Solicitação</th>                
                <th scope="col">Status</th>
                <th scope="col">Quantidade</th>
                <th scope="col">Peça / Item Solicitado</th>
                <th scope="col">Ações</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(requisicao, index) in resultados" :key="index">
              <td>{{ requisicao.ordemServico.codigo }}</td>
              <td>{{ formatarData(requisicao.dataSolicitacao) }}</td>    
              <td>{{ requisicao.statusRequisicao.descricao }}</td> 
              <td>{{ requisicao.quantidade }}</td> 
              <td>{{ requisicao.produto.denominacao }}</td> 
                <td>
                <button class="btn btn-primary" @click="autorizarRequisicao(requisicao.id)">
                    Autorizar Requisição
                </button>

                <button class="btn btn-danger" @click="negarRequisicao(requisicao.id)">
                    Negar Requisição
                </button>
                </td>
            </tr>
            </tbody>
        </table>
        </div>

    </div>
</template>
  
<script>

import Message from './Message.vue';
import MessageFailure from './MessageFailure.vue';
import axios from 'axios';  
import Instrucoes from './Instrucoes.vue';

export default {
  name: 'Atender Requisições',
  components: {
    Message,
    MessageFailure,
    Instrucoes
  },
  data() {
    return {
      resultados: [],
      comboStatusAgendamento: [],
      selectedStatusAgendamento: '',
      agendamentoId: '',
      msg: '',
      msg_failure: '',
      texto: 'Prezado(a) usuário(a), selecione abaixo o status da requisição para efetivação da consulta.'
    };
  },
  methods: {
    getStatusRequisicao() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };
      
      axios.get('http://localhost:8080/statusrequisicao', config)
        .then(response => {
          this.comboStatusAgendamento = response.data;
        })
        .catch(error => {
          this.msg_failure = response.data;
          console.error('Erro ao buscar dados:', error);
        });
    }, 
    consultarRequisicoes() {
      const token = sessionStorage.getItem('token');
      
      const id = this.selectedStatusAgendamento;        
      
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      axios.get('http://localhost:8080/requisicaopecas/requisicao-by-status?id='+id, config)
        .then(response => {
          this.msg_failure = "";
          
          this.resultados = response.data; 
          console.log(this.resultados);

          if(this.resultados.length < 1) {
            this.msg_failure = 'Não foram localizadas requisições com os parâmetros informados.'
          }

          this.limparCampos();
        })
        .catch(error => {
          this.msg_failure = error.response.data;           
          console.error('Erro ao buscar dados:', error);
        });

    },
    autorizarRequisicao(agendamentoId) {
      this.agendamentoId = agendamentoId;      

      const token = sessionStorage.getItem('token');
        
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }; 

      const requisicao = {
        id: agendamentoId
      }  
      
      axios.post('http://localhost:8080/requisicaopecas/autorizar-requisicao', requisicao, config)
        .then(response => {           
          this.msg_failure = '';                                           
          this.msg = response.data;  
          this.limparCampos();          
        })
        .catch(error => {                  
          this.msg = '';                   
          this.msg_failure = error.response.data; 
          this.limparCampos();
        });         
    },
    negarRequisicao(agendamentoId) {
      this.agendamentoId = agendamentoId;      

      const token = sessionStorage.getItem('token');
        
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }; 

      const requisicao = {
        id: agendamentoId
      }  
      
      axios.post('http://localhost:8080/requisicaopecas/negar-requisicao', requisicao, config)
        .then(response => {           
          this.msg_failure = '';                                           
          this.msg = response.data;  
          this.limparCampos();          
        })
        .catch(error => {                  
          this.msg = '';                   
          this.msg_failure = error.response.data; 
          this.limparCampos();
        });         
    },
    formatarData(data) {
      //Convertendo data para formato brasileiro.
      const [ano, mes, dia] = data.split('-');
      return `${dia}/${mes}/${ano}`;
    },
    limparCampos() {
      setTimeout(() => this.msg = "", 10000);
      setTimeout(() => this.msg_failure = "", 10000);
    }  
  },
  mounted() {
    this.getStatusRequisicao();
  }
};
</script>
  
  <style scoped>
    .form-group {
      width: 500px;       
      margin: auto; 
    }
    #instrucoes {
        width: 500px;
        margin: auto;        
    }
    .table {
      width: 1200px;
      margin: auto;      
    }
  
    td {
      text-align: center;
      vertical-align: middle;
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
      margin: 10px
    } 
  </style>
  