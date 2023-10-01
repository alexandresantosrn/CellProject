<template>
    <div class="usuario">
      <h2>Área do Cliente/Usuário</h2>
        <div id="descricao">
            <p>Prezado usuário, selecione abaixo uma das opções desejadas:</p>
        </div>
        
        <UserBanner v-on:adicionarAgendamento="showFormAgendamentos" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarOs="showListarOs"/>
        
        <div v-show="exibirFormAgendamento">
            <AgendarAtendimento />
        </div>   
        
        <div v-show="exibirListagemAgendamento">
            <ListagemAgendamentos />
        </div> 
    </div>    
</template>

<script>

import UserBanner from '@/components/UserBanner.vue';
import AgendarAtendimento from '@/components/AgendarAtendimento.vue';
import ListagemAgendamentos from '@/components/ListagemAgendamentos.vue';

export default {
    name: 'Usuario',
    data() {
        return {
            exibirFormAgendamento: false,
            exibirListagemAgendamento: false
        }
    },            
    components: {
        UserBanner,
        AgendarAtendimento,
        ListagemAgendamentos
    },
    methods: {
        showFormAgendamentos(value) {            
            this.exibirFormAgendamento = value;
            this.exibirListagemAgendamento = false;
        },
        showListarAgendamentos(value) {
            this.exibirFormAgendamento = false;
            this.exibirListagemAgendamento = value;     
            console.log(this.exibirListagemAgendamento);      
        },
        showListarOs(value) {
            this.exibirFormAgendamento = false;
            this.exibirListagemAgendamento = false;
        }
    }
}
</script>
  
<style scoped>

    .usuario {
        margin: 20px;
        text-align: center;        
    }  

    #descricao {
        margin: auto;
        width: 500px;
        height: 20px;
        background-color: #FFFFE0;
        text-align: center;
        font-size: 16px;
        font-weight: bold;
    }
</style>