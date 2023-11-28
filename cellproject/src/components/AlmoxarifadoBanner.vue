<template>
    <div id="banner">
        <div class="sessions">            
            <router-link to="/almoxarifado">
                <div id="add-agendamento" @click="adicionarAgendamento(), mudarCorAgendamento()" :style="{ backgroundColor: corAgendamento}">                    
                    <img :src="add_agendamento_src" :alt="add_agendamento_alt" class="img-user">                
                    <p>CADASTRAR</p>
                    <p>PRODUTO</p>                                
                </div>
            </router-link>

            <router-link to="/almoxarifado">
                <div id="list-agendamentos" @click="listarAgendamentos(), mudarCorAgendamentos()" :style="{ backgroundColor: corListagemAgendamento}">
                    <img :src="list_agendamentos_src" :alt="list_agendamentos_alt" class="img-user"> 
                    <p>LISTAR / ATENDER</p> 
                    <p>REQUISIÇÕES</p>
                </div>
            </router-link>

            <router-link to="/almoxarifado">
                <div id="list-os" @click="listarOs(), mudarCorOs()" :style="{ backgroundColor: corListagemOs}">
                    <img :src="list_os_src" :alt="list_os_alt" class="img-user"> 
                    <p>LISTAR</p>
                    <p>PRODUTOS</p>
                </div>
            </router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: 'Almoxarifado Banner',
    data() {
        return {
            add_agendamento_src: '/img/adicionar-item.png',
            add_agendamento_alt: 'Cadastrar Produto',
            list_agendamentos_src: '/img/listar-agendamentos.png',
            list_agendamentos_alt: 'Listar Agendamentos',
            list_os_src: '/img/produtos.png',
            list_os_alt: 'Listar Ordens de Serviço',
            showFormAgendamentos: false,
            showListarAgendamentos: false,
            showListarOs: false,
            corAgendamento: 'white',
            corListagemAgendamento: 'white',
            corListagemOs: 'white'           
        }
    },
    methods: {
        adicionarAgendamento() {
            this.showFormAgendamentos = true;
            this.showListarAgendamentos = false;
            this.showListarOs = false;           
            this.$emit('adicionarAgendamento', this.showFormAgendamentos);
        },
        listarAgendamentos(){
            this.showFormAgendamentos = false;
            this.showListarAgendamentos = true;
            this.showListarOs = false;                  
            this.$emit('listarAgendamentos', this.showListarAgendamentos);
        },
        listarOs(){
            this.showFormAgendamentos = false;
            this.showListarAgendamentos = false;
            this.showListarOs = true; 
            
            this.$emit('listarOs', this.showListarOs);
        },
        mudarCorAgendamento() {
            this.corAgendamento = '#FFFFE0', 
            this.corListagemAgendamento = 'White',
            this.corListagemOs = 'White'          
        },
        mudarCorAgendamentos() {
            this.corAgendamento = 'White',
            this.corListagemAgendamento = '#FFFFE0', 
            this.corListagemOs = 'White'          
        },
        mudarCorOs() {
            this.corAgendamento = 'White', 
            this.corListagemAgendamento = 'White',
            this.corListagemOs = '#FFFFE0'          
        }
    }
}
</script>

<style scoped>
    #banner {       
        height: 200px;
        width: 100%;
        display: flex;         
    }

    p {
        margin-bottom: 5px;
    }
    .sessions {
        margin: auto;
        display: flex;
    }
    .sessions a {
        color: black;
        text-decoration: none;
    }      

    #add-agendamento, 
    #list-agendamentos,
    #list-os {
        border: 2px solid black;
        border-radius: 10px;
        height: 140px;
        width: 140px;      
        text-align: center; 
        font-size: 12px;
        font-weight: bold;  
        line-height: 8px;  
        margin: 10px;     
    }
    .img-user {
        height: 80px;
        margin: 15px;
    }       
</style>