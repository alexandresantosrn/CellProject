<template>
  <div class="atendimentos">
    <h2>Módulo de Atendimentos</h2>
      <div id = "instrucoes">
          <Instrucoes :texto="texto" />
      </div>   
      
      <AtendimentosBanner v-on:adicionarAgendamento="showFormAgendamentos" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarOs="showListarOs"/>
      
      <div v-show="exibirFormAgendamento">
          <AgendarAtendimento />
      </div>   
      
      <div v-show="exibirListagemAgendamento">
          <AtenderAgendamentos />
      </div> 

      <div v-show="exibirListagemOS">
          <ListagemOS />
      </div>
  </div>    
</template>

<script>

import AtendimentosBanner from '../components/AtendimentosBanner.vue';
import AgendarAtendimento from '@/components/AgendarAtendimento.vue';
import ListagemOS from '@/components/ListagemOS.vue';
import Instrucoes from '@/components/Instrucoes.vue';
import AtenderAgendamentos from '../components/AtenderAgendamentos.vue';

export default {
  name: 'Atendimentos',
  data() {
      return {
          exibirFormAgendamento: false,
          exibirListagemAgendamento: false,
          exibirListagemOS: false,
          texto: 'Prezado usuário(a), selecione abaixo uma das opções desejadas:'
      }
  },            
  components: {
      AtendimentosBanner,
      AgendarAtendimento,      
      ListagemOS,
      AtenderAgendamentos,
      Instrucoes
  },
  methods: {
      showFormAgendamentos(value) {            
          this.exibirFormAgendamento = value;
          this.exibirListagemAgendamento = false;
          this.exibirListagemOS = false;
      },
      showListarAgendamentos(value) {
          this.exibirFormAgendamento = false;
          this.exibirListagemAgendamento = value;     
          this.exibirListagemOS = false;
      },
      showListarOs(value) {
          this.exibirFormAgendamento = false;
          this.exibirListagemAgendamento = false;
          this.exibirListagemOS = value;
      }
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