<template>
    <div class="almoxarifado" v-if="token">
        <h2>Módulo de Almoxarifado</h2>
            <div id = "instrucoes">
                <Instrucoes :texto="texto" />
            </div>   

            <AlmoxarifadoBanner v-on:adicionarAgendamento="showFormAgendamentos" v-on:listarAgendamentos="showListarAgendamentos" v-on:listarOs="showListarOs"/>
            
            <div v-show="exibirFormAgendamento">
                <CadastrarProduto />
            </div>   
            
            <div v-show="exibirListagemAgendamento">
                <AtenderRequisicoes />
            </div> 

            <div v-show="exibirListagemOS">
                <ConsultarEstoque />
            </div>
    </div> 
</template>

<script>
import Instrucoes from '@/components/Instrucoes.vue';
import AlmoxarifadoBanner from '../components/AlmoxarifadoBanner.vue';
import CadastrarProduto from '../components/CadastrarProduto.vue';
import ConsultarEstoque from '../components/ConsultarEstoque.vue';
import AtenderRequisicoes from '@/components/AtenderRequisicoes.vue';
import axios from 'axios';

export default {
    name: 'Almoxarifado',
    data() {
        return {
            exibirFormAgendamento: false,
            exibirListagemAgendamento: false,
            exibirListagemOS: false,
            texto: 'Prezado usuário(a), selecione abaixo uma das opções desejadas:',
            token: ''
        }
    },            
    components: {
        AlmoxarifadoBanner,
        CadastrarProduto,
        ConsultarEstoque,
        Instrucoes,
        AtenderRequisicoes
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

                axios.get('http://localhost:8080/authorization/almoxarifado', config)
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
    .almoxarifado {         
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