<template>
    <div class="formsenha">
        <Instrucoes :texto="texto" />
        
        <Message :msg="msg" v-show="msg" />
        <MessageFailure :msg_failure ="msg_failure" v-show="msg_failure"/>          

        <form @submit.prevent="recuperarSenha">
            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="email" id="email" v-model="email" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Recuperar Senha</button>
        </form>
    </div>    
</template>

<script>
import Instrucoes from '@/components/Instrucoes.vue';
import Message from '@/components/Message.vue';
import MessageFailure from '@/components/MessageFailure.vue';
import axios from 'axios';

export default {
    name: 'FormSenha',
    components: {
        Instrucoes,
        Message,
        MessageFailure
    },
    data() {
        return {
            email: '',
            msg: '',
            msg_failure: '',
            texto: 'Prezado usuário, informe abaixo o e-mail utili  zado durante cadastro:'   
        }
    },
    methods: {
        recuperarSenha() {

            const usuario = {
                usuario: this.email
            } 
            
            axios.post('http://localhost:8080/logon/recuperar-senha', usuario)
                .then(response => {                    
                    this.msg_failure = '';                                  
                    this.msg = response.data;   
                    this.limparCampos();
                })
                .catch(error => {                 
                    this.msg = '';                      
                    this.msg_failure = error.response.data;                                           
                });          
           
        },
        limparCampos() {
            setTimeout(() => this.msg = "", 10000); 
            this.email = "";
        }  
    }
}    
</script>

<style scoped>
    .formsenha {
        width: 500px;       
        margin: auto;        
        text-align: center;       
    }

    label {             
        margin-bottom: 5px;
        margin-top: 5px;
        color: #222;        
        border-left: 4px solid ;
        width: 500px;
        text-align: left;
        padding: 8px;
    }
    
    input {
        margin-bottom: 10px;
    } 

    button {
        margin-bottom: 20px;
    }

    .form-group {
        width:  400px;
        margin: auto;  
    }
</style>
