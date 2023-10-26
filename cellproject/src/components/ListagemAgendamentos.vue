<template>
  <div>
    <h2>Listagem de Agendamentos</h2>

    <Message :msg="msg" v-show="msg" />
    <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

    <table class="table">
      <thead>
        <tr>
          <th>N&#186 Agendamento</th>
          <th>Data do Agendamento</th>
          <th>Horário do Agendamento</th>
          <th>Status do Agendamento</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="agendamento in agendamentos" :key="agendamento.id">
          <td>{{ agendamento.codigo }}</td>
          <td>{{ agendamento.dataAgendamento }}</td>
          <td>{{ agendamento.horarioAgendamento }}</td>
          <td>{{ agendamento.statusAgendamento.descricao }}</td>
          <td>
            <button class="btn btn-danger" @click="realizarCancelamento(agendamento.id)">
              Cancelar Agendamento
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="modal" tabindex="-1" role="dialog" :class="{ 'd-block': showModal }">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title">Confirmação de Cancelamento do Agendamento</h5>
                  <button type="button" class="close" @click="hideModal">
                      <span>&times;</span>
                  </button>
              </div>
              <div class="modal-body">
                  Confirma o cancelamento do agendamento?
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" @click="hideModal">Cancelar</button>
                  <button type="button" class="btn btn-danger" @click="cancelarAgendamento">Confirmar</button>
              </div>
          </div>
      </div>
    </div>

  </div>
</template>

<script>
import Message from './Message.vue';
import axios from 'axios';
import MessageFailure from './MessageFailure.vue';

export default {
  name: 'Listagem Agendamentos',
  components: {
    Message,
    MessageFailure
  },
  data() {
    return {
      agendamentos: '',
      msg: '',
      msg_failure: '',
      agendamentoId: '',
      showModal: false      
    };
  },
  methods: {
    async getAgendamentos() {        
        axios.get('http://localhost:8080/agendamento')
          .then(response => {
            this.agendamentos = response.data;               
          })
          .catch(error => {
            this.msg = '';
            this.msg_failure = error.response.data;
          });
      },
    cancelarAgendamento() {
      const agendamentoId = this.agendamentoId;
      axios.post('http://localhost:8080/agendamento/cancelar-agendamento?id='+agendamentoId)
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
        
        this.showModal = false;
    },
    limparCampos(){
      setTimeout(() => this.msg = "", 5000);
      setTimeout(() => this.msg_failure = "", 5000);
    },
    realizarCancelamento(agendamentoId) {
      this.agendamentoId = agendamentoId;
      this.showModal = true;
    },
    hideModal() {
      this.showModal = false;
    }   
  },
  mounted() {
    this.getAgendamentos();
  },
  updated() {
    this.getAgendamentos();
  }
};
</script>

<style scoped>
  .table {
    width: 1000px;       
    margin: auto;  
  }
</style>