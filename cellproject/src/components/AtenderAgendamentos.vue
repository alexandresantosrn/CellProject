<template>
  <div id="agendamentos">
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
      // Aqui você pode enviar a consulta para o backend Java/Spring
      // Use this.dataAgendamento e this.statusAgendamento para acessar os valores selecionados
    }
  }
};
</script>

<style scoped>
  #agendamentos {
    width: 300px;       
    margin: auto;  
  }
</style>
