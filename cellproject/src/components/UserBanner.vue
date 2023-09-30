<template>
    <div id="banner">
        <div class="sessions">            
            <router-link to="/usuario">
                <div id="users-list" @click="listUsers(), mudarCorUsers()" :style="{ backgroundColor: colorUsers}">                    
                    <img :src="users_list_src" :alt="users_list_alt" class="img-users-list">                
                    <p>AGENDAR</p>
                    <p>ATENDIMENTOS</p>                                
                </div>
            </router-link>

            <router-link to="/usuario">
                <div id="create-users" @click="createUsers(), mudarCorUser()" :style="{ backgroundColor: colorUser}">
                    <img :src="add_user_src" :alt="add_user_alt" class="img-users-list"> 
                    <p>LISTAR</p> 
                    <p>AGENDAMENTOS</p>
                </div>
            </router-link>

            <router-link to="/usuario">
                <div id="create-papers" @click="createPapers(), mudarCorPapers()" :style="{ backgroundColor: colorPaper}">
                    <img :src="add_paper_src" :alt="add_paper_alt" class="img-users-list"> 
                    <p>LISTAR</p>
                    <p>ORDENS DE SERVIÇO</p>
                </div>
            </router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: 'UserBanner',
    data() {
        return {
            users_list_src: '/img/agendamento.png',
            users_list_alt: 'Agendar Atendimentos',
            add_user_src: '/img/listar-agendamentos.png',
            add_user_alt: 'Listar Agendamentos',
            add_paper_src: '/img/listar-os.png',
            add_paper_alt: 'Listar Ordens de Serviço',
            show_users: false,
            show_add_user: false,
            show_add_paper: false,
            colorUsers: 'white',
            colorUser: 'white',
            colorPaper: 'white'           
        }
    },
    methods: {
        listUsers() {
            this.show_users = true;
            this.show_add_user = false;
            this.show_add_paper = false;
            
            this.$emit('list_users', this.show_users);
        },
        createUsers(){
            this.show_users = false;
            this.show_add_user = true;
            this.show_add_paper = false;          

            this.$emit('create_users', this.show_add_user);
        },
        createPapers(){
            this.show_users = false;
            this.show_add_user = false;
            this.show_add_paper = true; 
            
            this.$emit('create_papers', this.show_add_paper);
        },
        mudarCorUsers() {
            this.colorUsers = '#FFFFE0', 
            this.colorUser = 'White',
            this.colorPaper = 'White'          
        },
        mudarCorUser() {
            this.colorUsers = 'White',
            this.colorUser = '#FFFFE0', 
            this.colorPaper = 'White'          
        },
        mudarCorPapers() {
            this.colorUsers = 'White', 
            this.colorUser = 'White',
            this.colorPaper = '#FFFFE0'          
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

    #users-list, 
    #create-users,
    #create-papers {
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

    .img-users-list {
        height: 80px;
        margin: 15px;
    }       
</style>