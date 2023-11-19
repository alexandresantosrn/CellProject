<template>
  <div id="agendamentos">
    <h2>Listar/Atender Agendamentos</h2>

    <div id = "instrucoes">
          <Instrucoes :texto="texto" />
    </div>  

    <Message :msg="msg" v-show="msg" />
    <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

    <form @submit.prevent="consultarAgendamentos">
      <div class="form-group">
        <label for="dataAgendamento">Data de Agendamento:</label>
        <input type="date" id="dataAgendamento" v-model="dataAgendamento" class="form-control" />
      </div>
      <div class="form-group">
            <label for="statusagendamento">Status de Agendamento:</label>
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
            <th scope="col">Nº Agendamento</th>
            <th scope="col">Nome do Cliente</th>
            <th scope="col">Data</th>
            <th scope="col">Horário</th>
            <th scope="col">Status</th>
            <th scope="col">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(agendamento, index) in resultados" :key="index">
            <td>{{ agendamento.codigo }}</td>
            <td>{{ agendamento.pessoa.nome }}</td>
            <td>{{ formatarData(agendamento.dataAgendamento) }}</td>
            <td>{{ agendamento.horarioAgendamento }}</td>
            <td>{{ agendamento.statusAgendamento.descricao }}</td>
            <td>
              <button class="btn btn-primary" @click="iniciarAgendamento(agendamento.id)">
                Iniciar Atendimento
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
  name: 'AtenderAgendamentos',
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
      dataAgendamento: new Date().toISOString().split('T')[0], // Define a data atual
      msg: '',
      msg_failure: '',
      texto: 'Prezado(a) usuário(a), selecione a data desejada e o status para consulta dos agendamentos realizados.'
    };
  },
  methods: {
    async getStatusAgendamento() {
      const token = sessionStorage.getItem('token');

      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };
      
      axios.get('http://localhost:8080/statusagendamento', config)
        .then(response => {
          this.comboStatusAgendamento = response.data;
        })
        .catch(error => {
          this.msg_failure = response.data;
          console.error('Erro ao buscar dados:', error);
        });
    }, 
    consultarAgendamentos() {
      const token = sessionStorage.getItem('token');
      
      const dataAgendamento = this.dataAgendamento;        
      const statusAgendamento = this.selectedStatusAgendamento;        
      
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };

      axios.get('http://localhost:8080/agendamento/list-by-data?dataAgendamento='+dataAgendamento+'&id='+statusAgendamento, config)
        .then(response => {
          this.msg_failure = "";
          
          this.resultados = response.data; 

          if(this.resultados.length < 1) {
            this.msg_failure = 'Não foram localizados agendamentos com os parâmetros informados.'
          }

          this.limparCampos();
        })
        .catch(error => {
          this.msg_failure = error.response.data;           
          console.error('Erro ao buscar dados:', error);
        });

    },
    iniciarAgendamento(agendamentoId) {
      this.agendamentoId = agendamentoId;      

      const token = sessionStorage.getItem('token');
        
      const config = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }; 

      axios.post('http://localhost:8080/agendamento/iniciar-agendamento?id='+this.agendamentoId, config)
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
      setTimeout(() => this.msg = "", 5000);
      setTimeout(() => this.msg_failure = "", 5000);
    }  
  },
  mounted() {
    this.getStatusAgendamento();
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
