<template>
  <div class="atendimentos" v-if="token">
    <h2>Módulo de Atendimentos</h2>
      <div id = "instrucoes">
          <Instrucoes :texto="texto" />
      </div>   
      
      <AtendimentosBanner v-on:adicionarCliente="showFormCliente" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarCliente="showListarCliente"
      v-on:adicionarOs="ShowFormOs" v-on:listarOs="ShowListarOs"/>
             
      <div v-show="exibirListagemAgendamento">
          <AtenderAgendamentos />
      </div> 

      <div v-show="exibirFormCliente">
          <CadastrarCliente />
      </div> 

      <div v-show="exibirListagemClientes">
          <ListarClientes />
      </div>

      <div v-show="exibirFormOs">
          <CadastrarOs />
      </div>

      <div v-show="exibirListagemOs">
          <ConsultarOs />
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
import ConsultarOs from '../components/ConsultarOs.vue';
import CadastrarOs from '../components/CadastrarOs.vue';
import axios from 'axios';

export default {
  name: 'Atendimentos',
  data() {
      return {
        acess: false,
        exibirFormCliente: false,
        exibirListagemAgendamento: false,
        exibirListagemClientes: false,
        exibirFormOs: false, 
        exibirListagemOs: false,
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
      ListarClientes,
      ConsultarOs,
      CadastrarOs
  },
  methods: {
    showFormCliente(value) {            
        this.exibirFormCliente = value;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = false;
        this.exibirFormOs = false;
        this.exibirListagemOs = false;
    },
    showListarAgendamentos(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = value;     
        this.exibirListagemClientes = false;
        this.exibirFormOs = false;
        this.exibirListagemOs = false;
    },
    showListarCliente(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = value;
        this.exibirFormOs = false;
        this.exibirListagemOs = false;
    },
    ShowFormOs(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = false;
        this.exibirFormOs = value;
        this.exibirListagemOs = false;
    },
    ShowListarOs(value) {
        this.exibirFormCliente = false;
        this.exibirListagemAgendamento = false;
        this.exibirListagemClientes = false;
        this.exibirFormOs = false;
        this.exibirListagemOs = value;
    },
    getToken() {            
        this.token = sessionStorage.getItem('token');       
        
        if (this.token === null) {
            this.$router.push('/');
        }
    },
    getAcess() {
        const token = sessionStorage.getItem('token');       
            
            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            };

            axios.get('http://localhost:8080/authorization/atendimento', config)
                .then(response => {
                   if (response.status === 401) {
                    this.$router.push('/proibido');
                   }              
                })
                .catch(error => {
                    console.error('Erro ao buscar dados:', error);                    
                    this.$router.push('/proibido');                              
                });
    }
  },
  mounted() {
    this.getToken();
    this.getAcess();
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