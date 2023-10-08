<template>
    <div>
      <h2>Listagem de Ordens de Serviço</h2>
      <table class="table">
        <thead>
          <tr>
            <th>N. Ordem de Serviço</th>
            <th>Data de Entrada</th>
            <th>Modelo</th>
            <th>Status do Reparo</th>
            <th>Preço Total</th>            
          </tr>
        </thead>
        <tbody>
          <tr v-for="ordem in ordens" :key="ordem.id">
            <td>{{ ordem.numero }}</td>
            <td>{{ ordem.dataEntrada }}</td>
            <td>{{ ordem.modelo }}</td>
            <td>{{ ordem.statusReparo }}</td>
            <td>{{ ordem.precoTotal }}</td>
            <td>              
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        ordens: [
          {
            id: 1,
            numero: 'OS2023001',
            dataEntrada: '2023-08-30',
            modelo: 'Smartphone XYZ',
            statusReparo: 'Em andamento',
            precoTotal: 100.00
          },
          {
            id: 2,
            numero: 'OS2023002',
            dataEntrada: '2023-08-31',
            modelo: 'Laptop ABC',
            statusReparo: 'Concluída',
            precoTotal: 250.00
          },
          // Adicione mais objetos de ordem de serviço conforme necessário
        ]
      };
    },
    methods: {
      visualizarDetalhes(ordemId) {
        // Lógica para visualizar os detalhes da ordem com base no ID
        console.log(`Visualizar detalhes da ordem com ID: ${ordemId}`);
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