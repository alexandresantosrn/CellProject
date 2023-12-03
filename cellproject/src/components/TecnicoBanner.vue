<template>
    <div id="banner">
        <div class="sessions">     

            <router-link to="/tecnico">
                <div id="list-agendamentos" @click="listarAgendamentos(), mudarCorAgendamentos()" :style="{ backgroundColor: corListagemAgendamento}">
                    <img :src="list_agendamentos_src" :alt="list_agendamentos_alt" class="img-user"> 
                    <p>LISTAR / ATENDER</p> 
                    <p>ORDENS</p>
                </div>
            </router-link>            

            <router-link to="/tecnico">
                <div id="add-client" @click="adicionarCliente(), mudarCorClient()" :style="{ backgroundColor: corCliente}">                    
                    <img :src="add_client_src" :alt="add_client_alt" class="img-user">                
                    <p>CADASTRAR</p>
                    <p>CLIENTE</p>                                
                </div>
            </router-link>

            <router-link to="/tecnico">
                <div id="list-os" @click="listarCliente(), mudarCorListagemCliente()" :style="{ backgroundColor: corListagemCliente}">
                    <img :src="list_client_src" :alt="list_client_alt" class="img-user"> 
                    <p>CADASTRAR</p>
                    <p>CONSERTO</p>
                </div>
            </router-link>

            <router-link to="/tecnico">
                <div id="list-os" @click="adicionarOs(), mudarCorOs()" :style="{ backgroundColor: corOs}">
                    <img :src="add_os_src" :alt="add_os_alt" class="img-user"> 
                    <p>CADASTRAR</p>
                    <p>ORDEM DE SERVIÇO</p>
                </div>
            </router-link>

            <router-link to="/tecnico">
                <div id="list-os" @click="listarOs(), mudarCorListagemOs()" :style="{ backgroundColor: corListagemOs}">
                    <img :src="list_os_src" :alt="list_os_alt" class="img-user"> 
                    <p>LISTAR / CONSULTAR</p>
                    <p>ORDEM DE SERVIÇO</p>
                </div>
            </router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: 'Tecnico Banner',
    data() {
        return {
            list_agendamentos_src: '/img/listar-agendamentos.png',
            list_agendamentos_alt: 'Listar/Atender Agendamentos',
            add_client_src: '/img/adicionar-cliente.png',
            add_client_alt: 'Cadastrar Cliente',
            list_client_src: '/img/listar-cliente.png',
            list_client_alt: 'Lista/Alterar Cliente',
            add_os_src: '/img/adicionar-os.png',
            add_os_alt: 'Lista/Consultar Ordem de Serviço',
            list_os_src: '/img/listar-os.png',
            list_os_alt: 'Lista/Consultar Ordem de Serviço',
            showFormCliente: false,
            showListarAgendamentos: false,
            showListarCliente: false,
            ShowFormOs: false,
            ShowListarOs: false,
            corCliente: 'white',
            corListagemAgendamento: 'white',
            corListagemCliente: 'white',
            corOs: 'white',
            corListagemOs: 'white'           
        }
    },
    methods: {
        adicionarCliente() {
            this.showFormCliente = true;
            this.showListarAgendamentos = false;
            this.showListarCliente = false;   
            this.ShowFormOs = false;
            this.ShowListarOs = false;        
            this.$emit('adicionarCliente', this.showFormCliente);            
        },
        listarAgendamentos(){
            this.showFormCliente = false;
            this.showListarAgendamentos = true;
            this.showListarCliente = false;
            this.ShowFormOs = false;
            this.ShowListarOs = false;                  
            this.$emit('listarAgendamentos', this.showListarAgendamentos);
        },
        listarCliente(){
            this.showFormCliente = false;
            this.showListarAgendamentos = false;
            this.showListarCliente = true; 
            this.ShowFormOs = false;
            this.ShowListarOs = false;
            this.$emit('listarCliente', this.showListarCliente);
        },
        adicionarOs() {
            this.showFormCliente = false;
            this.showListarAgendamentos = false;
            this.showListarCliente = false; 
            this.ShowFormOs = true;
            this.ShowListarOs = false;
            this.$emit('adicionarOs', this.ShowFormOs);
        },
        listarOs(){
            this.showFormCliente = false;
            this.showListarAgendamentos = false;
            this.showListarCliente = false; 
            this.ShowFormOs = false;
            this.ShowListarOs = true;
            this.$emit('listarOs', this.ShowListarOs);
        },
        mudarCorClient() {
            this.corCliente = '#FFFFE0', 
            this.corListagemAgendamento = 'White',
            this.corListagemCliente = 'White'
            this.corOs = 'White',      
            this.corListagemOs = 'White'              
        },
        mudarCorAgendamentos() {
            this.corCliente = 'White',
            this.corListagemAgendamento = '#FFFFE0', 
            this.corListagemCliente = 'White',  
            this.corOs = 'White',      
            this.corListagemOs = 'White'    
        },
        mudarCorListagemCliente() {
            this.corCliente = 'White', 
            this.corListagemAgendamento = 'White',
            this.corListagemCliente = '#FFFFE0'      
            this.corOs = 'White',      
            this.corListagemOs = 'White'        
        },
        mudarCorOs(){
            this.corCliente = 'White', 
            this.corListagemAgendamento = 'White',
            this.corListagemCliente = 'White'      
            this.corOs = '#FFFFE0',      
            this.corListagemOs = 'White'  
        },
        mudarCorListagemOs(){
            this.corCliente = 'White', 
            this.corListagemAgendamento = 'White',
            this.corListagemCliente = 'White'      
            this.corOs = 'White',      
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