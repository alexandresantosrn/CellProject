<template>   
    <div id="logon">
        <Message :msg="msg" v-show="msg" />
        <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />

        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Login</h3>
                    </div>
                    <div class="card-body">
                        <form @submit.prevent="realizarLogin">
                            <div class="form-group">
                                <label for="username">Email:</label>
                                <input type="email" class="form-control" id="usuario" v-model="usuario" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Senha:</label>
                                <input type="password" class="form-control" id="senha" v-model="senha" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                        </form>
                    </div>
                </div>
                <p class="mt-3 text-center">
                    Caso ainda não possua cadastro, <router-link to="/formcadastro">clique aqui.</router-link>
                </p>

                <p class="mt-3 text-center">
                    <router-link to="/formsenha">Clique aqui</router-link>, para recuperar sua senha.
                </p>
            </div>
        </div>  
        
    </div>
</template>

<script>
import Message from './Message.vue';
import axios from 'axios';
import MessageFailure from './MessageFailure.vue';

export default {
    name: 'Logon',
    components: {
        Message,
        MessageFailure
    },
    data() {
        return {
         authenticated: false,
         msg: '',
         msg_failure: '',
         usuario: '',
         senha: ''
        }
    },
    methods: {
        realizarLogin() { 
            
            const usuario = {
                login: this.usuario,
                senha: this.senha
            }

            axios.post('http://localhost:8080/logon/realizar-login', usuario)
                .then(response => {                     
                    this.msg_failure = '';                               
                    this.msg = response.data;  
                    this.authenticated = true;  
                    this.limparCampos();
                    this.$router.push('/LoginAutenticado');
                    this.$emit('realizarLogin', this.authenticated);          
                })
                .catch(error => {                              
                    this.msg = '';                   
                    this.msg_failure = error.response.data;       
                    this.limparCampos();        
                });            
        },
        limparCampos() {
            setTimeout(() => this.msg = "", 10000); 
            setTimeout(() => this.msg_failure = "", 10000); 
            this.usuario = "";
            this.senha = "";
        }  
    }
}
</script>

<style scoped>
    button{
        margin-top: 10px;
    }
</style>