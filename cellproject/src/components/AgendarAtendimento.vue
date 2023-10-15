<template>
  <div id="agendamento">
      <h2>Agendar Atendimento</h2>

      <Message :msg="msg" v-show="msg" />
      <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

      <form @submit.prevent="agendarAtendimento" id="form">       
        <div class="form-group">
            <label for="tipoServico">Tipo de Serviço:</label>
            <select class="form-control" id="tipoServico" v-model="selectedTipoServico" required>
              <option v-for="tipo in comboTipoServico" :key="tipo.id" :value="tipo.id">{{ tipo.descricao }}</option>           
            </select>
        </div>

        <div class="form-group">
            <label for="dataAgendamento">Data de Agendamento:</label>
            <input type="date" class="form-control" id="dataAgendamento" v-model="dataAgendamento" required>
        </div>

        <div class="form-group">
            <label for="horarioAgendamento">Horário de Agendamento:</label>
            <input type="time" class="form-control" id="horarioAgendamento" v-model="horarioAgendamento" required>
        </div>

        <button type="submit" class="btn btn-primary" >Agendar</button>
      </form>
  </div>
</template>
  
<script>
import Message from './Message.vue';
import MessageFailure from './MessageFailure.vue';
import axios from 'axios';  

export default {
  name: 'Agendar Atendimento',
  components: {
    Message,
    MessageFailure
  },
  data() {
    return {        
      selectedTipoServico: '',
      comboTipoServico: [],
      dataAgendamento: '',
      horarioAgendamento: '',
      msg: '',
      msg_failure: ''
    };
  },
  methods: {
    async getTipoServico() {
      axios.get('http://localhost:8080/tiposervico')
        .then(response => {
          this.comboTipoServico = response.data;
        })
        .catch(error => {
          console.error('Erro ao buscar dados:', error);
        });
    },  
    agendarAtendimento() {

      const agendamento = {
        dataAgendamento: this.dataAgendamento,
        horarioAgendamento: this.horarioAgendamento,
        tipoServico: this.selectedTipoServico 
      }  
        axios.post('http://localhost:8080/agendamento/cadastrar-agendamento', agendamento)
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
    limparCampos(){
      setTimeout(() => this.msg = "", 5000);
      this.selectedTipoServico = "";
      this.dataAgendamento = "";
      this.horarioAgendamento = "";
    }
  },
  mounted() {
    this.getTipoServico();
  }
};
</script>

<style scoped>
  #agendamento {
      width: 500px;       
      margin: auto;        
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
</style>