<template>
    <div class="usuario">
      <h2>Área do Cliente/Usuário</h2>
        <div id = "instrucoes">
            <Instrucoes :texto="texto" />
        </div>   

        <UserBanner v-on:adicionarAgendamento="showFormAgendamentos" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarOs="showListarOs"/>
        
        <div v-show="exibirFormAgendamento">
            <AgendarAtendimento />
        </div>   
        
        <div v-show="exibirListagemAgendamento">
            <ListagemAgendamentos />
        </div> 

        <div v-show="exibirListagemOS">
            <ListagemOS />
        </div>
    </div>    
</template>

<script>

import UserBanner from '@/components/UserBanner.vue';
import AgendarAtendimento from '@/components/AgendarAtendimento.vue';
import ListagemAgendamentos from '@/components/ListagemAgendamentos.vue';
import ListagemOS from '@/components/ListagemOS.vue';
import Instrucoes from '@/components/Instrucoes.vue';

export default {
    name: 'Usuario',
    data() {
        return {
            exibirFormAgendamento: false,
            exibirListagemAgendamento: false,
            exibirListagemOS: false,
            texto: 'Prezado usuário, selecione abaixo uma das opções desejadas:'
        }
    },            
    components: {
        UserBanner,
        AgendarAtendimento,
        ListagemAgendamentos,
        ListagemOS,
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
    .usuario {         
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