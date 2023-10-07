<template>
    <div class="formsenha">
        <Instrucoes :texto="texto" />
        
        <Message :msg="msg" v-show="msg" />

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
import Instrucoes from '@/components/Instrucoes.vue'
import axios from 'axios';
import Message from '@/components/Message.vue';

export default {
    name: 'FormSenha',
    components: {
        Instrucoes,
        Message
    },
    data() {
        return {
            email: '',
            msg: '',
            texto: 'Prezado usuário, informe abaixo o e-mail utilizado durante cadastro:'   
        }
    },
    methods: {
        recuperarSenha() {
            email = this.email;
            
            axios.post('http://localhost:8080/recupera-senha?email='+email)
                .then(response => {
                    // Verifica a resposta do servidor                                   
                    this.msg = response.data;             
                })
                .catch(error => {                    
                    
                    if (error.response.status === 404) {
                       // Lida com o status 404 (Not Found)                       
                       this.msg = error.response.data;                       
                    } else { 
                        //Demais erros                     
                        this.msg = error.response.data;
                    }
                });
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
    
    input, button {
        margin-bottom: 10px;
    } 

    .form-group {
        width:  400px;
        margin: auto;  
    }
</style>
