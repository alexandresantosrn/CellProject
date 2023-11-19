<template>
    <div id="banner">
        <div class="sessions">     

            <router-link to="/atendimentos">
                <div id="list-agendamentos" @click="listarAgendamentos(), mudarCorAgendamentos()" :style="{ backgroundColor: corListagemAgendamento}">
                    <img :src="list_agendamentos_src" :alt="list_agendamentos_alt" class="img-user"> 
                    <p>LISTAR / ATENDER</p> 
                    <p>AGENDAMENTOS</p>
                </div>
            </router-link>            

            <router-link to="/atendimentos">
                <div id="add-client" @click="adicionarCliente(), mudarCorClient()" :style="{ backgroundColor: corAgendamento}">                    
                    <img :src="add_client_src" :alt="add_client_alt" class="img-user">                
                    <p>CADASTRAR</p>
                    <p>CLIENTE</p>                                
                </div>
            </router-link>

            <router-link to="/atendimentos">
                <div id="list-os" @click="listarOs(), mudarCorOs()" :style="{ backgroundColor: corListagemOs}">
                    <img :src="list_os_src" :alt="list_os_alt" class="img-user"> 
                    <p>LISTAR / ALTERAR</p>
                    <p>CLIENTE</p>
                </div>
            </router-link>

            <router-link to="/atendimentos">
                <div id="list-os" @click="listarOs(), mudarCorOs()" :style="{ backgroundColor: corListagemOs}">
                    <img :src="list_os_src" :alt="list_os_alt" class="img-user"> 
                    <p>LISTAR</p>
                    <p>ORDENS DE SERVIÇO</p>
                </div>
            </router-link>

            <router-link to="/atendimentos">
                <div id="list-os" @click="listarOs(), mudarCorOs()" :style="{ backgroundColor: corListagemOs}">
                    <img :src="list_os_src" :alt="list_os_alt" class="img-user"> 
                    <p>LISTAR</p>
                    <p>ORDENS DE SERVIÇO</p>
                </div>
            </router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: 'AtendimentosBanner',
    data() {
        return {
            list_agendamentos_src: '/img/listar-agendamentos.png',
            list_agendamentos_alt: 'Listar/Atender Agendamentos',
            add_client_src: '/img/adicionar-cliente.png',
            add_client_alt: 'Cadastrar Cliente',
            list_os_src: '/img/listar-cliente.png',
            list_os_alt: 'Lista/Alterar Cliente',
            showFormAgendamentos: false,
            showListarAgendamentos: false,
            showListarOs: false,
            corAgendamento: 'white',
            corListagemAgendamento: 'white',
            corListagemOs: 'white'           
        }
    },
    methods: {
        adicionarCliente() {
            this.showFormAgendamentos = true;
            this.showListarAgendamentos = false;
            this.showListarOs = false;           
            this.$emit('adicionarCliente', this.showFormAgendamentos);
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
        mudarCorClient() {
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

    #add-client, 
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