<template>
    <div id="agendamento">
      <form>
        <div class="form-group">
          <label for="nomeCompleto">Nome Completo:</label>
          <input type="text" class="form-control" id="nomeCompleto" v-model="nomeCompleto">
        </div>
  
        <div class="form-group">
          <label for="cpf">CPF:</label>
          <input type="text" class="form-control" id="cpf" v-model="cpf">
        </div>
  
        <div class="form-group">
          <label for="tipoAgendamento">Tipo de Agendamento:</label>
          <select class="form-control" id="tipoAgendamento" v-model="tipoAgendamento">
            <option value="Conserto em Garantia">Conserto em Garantia</option>
            <option value="Conserto Fora de Garantia">Conserto Fora de Garantia</option>
          </select>
        </div>
  
        <div class="form-group">
          <label for="dataAgendamento">Data de Agendamento:</label>
          <input type="date" class="form-control" id="dataAgendamento" v-model="dataAgendamento">
        </div>
  
        <div class="form-group">
          <label for="horarioAgendamento">Horário de Agendamento:</label>
          <input type="time" class="form-control" id="horarioAgendamento" v-model="horarioAgendamento">
        </div>
  
        <button type="button" class="btn btn-primary" @click="agendar">Agendar</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Agendar Aterndimento',
    data() {
      return {
        nomeCompleto: '',
        cpf: '',
        tipoAgendamento: 'Conserto em Garantia',
        dataAgendamento: '',
        horarioAgendamento: ''
      };
    },
    methods: {
      agendar() {
        // Aqui você pode adicionar a lógica para processar o agendamento
        console.log('Agendamento enviado');
      }
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
        border-left: 2px solid #0275d8;
        width: 500px;
        text-align: left;
        padding: 5px;
    }

    input, select {
        margin-bottom: 10px;
    }
 </style>