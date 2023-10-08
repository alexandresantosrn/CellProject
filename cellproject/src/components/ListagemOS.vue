<template>
  <div>
    <h2>Listagem de Ordens de Serviço</h2>
    
    <table class="table">
      <thead>
        <tr>
          <th>N&#186 Ordem de Serviço</th>                  
          <th>Tipo de Serviço</th>
          <th>Data de Entrada</th>            
          <th>Modelo</th>
          <th>Status do Conserto</th>
          <th>Preço Total</th>            
        </tr>
      </thead>
      <tbody>
        <tr v-for="ordem in ordens" :key="ordem.id">
          <td>{{ ordem.codigo }}</td>
          <td>{{ ordem.tipoServico.descricao }}</td>  
          <td>{{ ordem.dataCadastro }}</td>     
          <td>{{ ordem.smartphone.modelo }}</td>
          <td>{{ ordem.statusReparo.descricao }}</td>
          <td>R$ {{ ordem.precoTotal }}</td>
          <td>              
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
  
  <script>
  import axios from 'axios';

  export default {
    data() {
      return {
        ordens: ''      
        // ordens: [
        //   {
        //     id: 1,
        //     numero: 'OS2023001',
        //     dataEntrada: '2023-08-30',
        //     modelo: 'Smartphone XYZ',
        //     statusReparo: 'Em andamento',
        //     precoTotal: 100.00
        //   },
        //   {
        //     id: 2,
        //     numero: 'OS2023002',
        //     dataEntrada: '2023-08-31',
        //     modelo: 'Laptop ABC',
        //     statusReparo: 'Concluída',
        //     precoTotal: 250.00
        //   },         
       
        // ]
      };
    },
    methods: {
      async getOrdensServico() {        
        axios.get('http://localhost:8080/get-ordens-servico')
          .then(response => {
            this.ordens = response.data;               
          })
          .catch(error => {
            console.error('Erro ao buscar dados:', error);
          });
      }
    },
    mounted() {
      this.getOrdensServico();
    }
  };
  </script>

<style scoped>
.table {
  width: 1000px;       
  margin: auto;  
}
</style>