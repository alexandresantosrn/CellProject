<template>
  <div id="agendamentos">
    <h2>Listar/Atender Agendamentos</h2>

    <form @submit.prevent="consultarAgendamentos">
      <div class="form-group">
        <label for="dataAgendamento">Data de Agendamento:</label>
        <input type="date" id="dataAgendamento" v-model="dataAgendamento" class="form-control" />
      </div>
      <div class="form-group">
        <label for="statusAgendamento">Status de Agendamento:</label>
        <select id="statusAgendamento" v-model="statusAgendamento" class="form-control">
          <option value="Confirmado">Confirmado</option>
          <option value="Pendente">Pendente</option>
          <option value="Cancelado">Cancelado</option>
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
            <th scope="col">Data do Agendamento</th>
            <th scope="col">Horário do Agendamento</th>
            <th scope="col">Status do Agendamento</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(agendamento, index) in resultados" :key="index">
            <td>{{ agendamento.numero }}</td>
            <td>{{ agendamento.nomeCliente }}</td>
            <td>{{ formatarData(agendamento.dataAgendamento) }}</td>
            <td>{{ agendamento.horarioAgendamento }}</td>
            <td>{{ agendamento.statusAgendamento }}</td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      resultados: [],

      dataAgendamento: new Date().toISOString().split('T')[0], // Define a data atual
      statusAgendamento: "Confirmado" // Define o status como Confirmado
    };
  },
  methods: {
    consultarAgendamentos() {
      this.resultados = [
        { numero: 1, nomeCliente: 'João', dataAgendamento: '2023-11-10', horarioAgendamento: '14:00', statusAgendamento: 'Confirmado' },
        { numero: 2, nomeCliente: 'Maria', dataAgendamento: '2023-11-12', horarioAgendamento: '15:30', statusAgendamento: 'Confirmado' },
      ];
    },
    formatarData(data) {
      //Convertendo data para formato brasileiro.
      const [ano, mes, dia] = data.split('-');
      return `${dia}/${mes}/${ano}`;
    }  
  }
};
</script>

<style scoped>
  .form-group {
    width: 500px;       
    margin: auto; 
  }

  .table {
    width: 1000px;
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

  button {
    margin: 10px
  } 
</style>
