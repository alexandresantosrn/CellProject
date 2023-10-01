<template>
  <div>
    <h2>Listagem de Agendamentos</h2>
    <table class="table">
      <thead>
        <tr>
          <th>N&#186 Agendamento</th>
          <th>Data de Agendamento</th>
          <th>Horário de Agendamento</th>
          <th>Status do Agendamento</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="agendamento in agendamentos" :key="agendamento.id">
          <td>{{ agendamento.numero }}</td>
          <td>{{ agendamento.data }}</td>
          <td>{{ agendamento.horario }}</td>
          <td>{{ agendamento.status }}</td>
          <td>
            <button class="btn btn-danger" @click="cancelarAgendamento(agendamento.id)">
              Cancelar Agendamento
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'Listagem Agendamentos',
  data() {
    return {
      agendamentos: [
        {
          id: 1,
          numero: 'AG2023001',
          data: '2023-08-30',
          horario: '09:00 AM',
          status: 'Agendado'
        },
        {
          id: 2,
          numero: 'AG2023002',
          data: '2023-08-31',
          horario: '02:30 PM',
          status: 'Confirmado'
        },
        // Adicione mais objetos de agendamento conforme necessário
      ]
    };
  },
  methods: {
    cancelarAgendamento(agendamentoId) {
      // Lógica para cancelar o agendamento com base no ID
      console.log(`Cancelar agendamento com ID: ${agendamentoId}`);
    }
  }
};
</script>

<style scoped>
  .table {
    width: 1000px;       
    margin: auto;  
  }
</style>