<template>
  <div class="atendimentos" v-if="token">
    <h2>Módulo de Atendimentos</h2>
      <div id = "instrucoes">
          <Instrucoes :texto="texto" />
      </div>   
      
      <AtendimentosBanner v-on:adicionarCliente="showFormCliente" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarCliente="showListarCliente"/>
             
      <div v-show="exibirListagemAgendamento">
          <AtenderAgendamentos />
      </div> 

      <div v-show="exibirFormCliente">
          <CadastrarCliente />
      </div> 

      <div v-show="exibirListagemClientes">
          <ListarClientes />
      </div>
  </div>    
</template>

<script>

import AtendimentosBanner from '../components/AtendimentosBanner.vue';
import AgendarAtendimento from '@/components/AgendarAtendimento.vue';
import ListagemOS from '@/components/ListagemOS.vue';
import Instrucoes from '@/components/Instrucoes.vue';
import AtenderAgendamentos from '../components/AtenderAgendamentos.vue';
import CadastrarCliente from '../components/CadastrarCliente.vue';
import ListarClientes from '../components/ListarClientes.vue';

export default {
  name: 'Atendimentos',
  data() {
      return {
          exibirFormCliente: false,
          exibirListagemAgendamento: false,
          exibirListagemClientes: false,
          texto: 'Prezado usuário(a), selecione abaixo uma das opções desejadas:',
          token: ''
      }
  },            
  components: {
      AtendimentosBanner,
      AgendarAtendimento,      
      ListagemOS,
      AtenderAgendamentos,
      Instrucoes,
      CadastrarCliente,
      ListarClientes
  },
  methods: {
    showFormCliente(value) {            
        this.exibirFormCliente = value;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = false;
    },
    showListarAgendamentos(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = value;     
        this.exibirListagemClientes = false;
    },
    showListarCliente(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = value;
    },
    getToken() {            
        this.token = sessionStorage.getItem('token');       
        
        if (this.token === null) {
            this.$router.push('/');
        }
    }
  },
  mounted() {
    this.getToken();
  }
}
</script>

<style scoped>
  .atendimentos {         
      margin: auto;        
      text-align: center;
      margin-bottom: 20px;       
  } 
  #instrucoes {
      width: 500px;
      margin: auto;        
  }
  h2{
      margin-top: 10px;
  }
</style>