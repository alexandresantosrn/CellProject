<template>
    <div id="agendamento">
        <h2>Agendar Atendimento</h2>
 
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
            <label for="tipoServico">Tipo de Serviço:</label>
            <select class="form-control" id="tipoServico" v-model="selectedTipoServico">
              <option v-for="tipo in comboTipoServico" :key="tipo.id" :value="tipo.descricao">{{ tipo.descricao }}</option>           
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
        selectedTipoServico: '',
        comboTipoServico: [],
        dataAgendamento: '',
        horarioAgendamento: ''
      };
    },
    methods: {
      async getTipoServico() {
        const req = await fetch('http://localhost:8080/tiposervico');              
        const data = await req.json();
        this.comboTipoServico = data; 
        console.log(data);
      },
      agendar() {
        // Aqui você pode adicionar a lógica para processar o agendamento
        console.log('Agendamento enviado');
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
        font-weight: bold;      
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