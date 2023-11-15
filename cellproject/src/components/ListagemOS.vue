<template>
  <div>
    <h2>Listagem de Ordens de Serviço</h2>
    
    <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

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
          <td>{{ ordem.dataEntrada }}</td>     
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
import MessageFailure from './MessageFailure.vue';
import axios from 'axios'; 

  export default {
    data() {
      return {
        ordens: '',
        msg_failure: ''    
      };
    },
    components: {
      MessageFailure
    },
    methods: {
      getOrdensServico() {       
        const token = sessionStorage.getItem('token');
        
        const config = {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }; 
        
        axios.get('http://localhost:8080/ordemservico', config)
          .then(response => {
            this.ordens = response.data;               
          })
          .catch(error => {
            this.msg_failure = error.response.data;
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