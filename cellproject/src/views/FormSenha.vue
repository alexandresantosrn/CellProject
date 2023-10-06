<template>
    <div class="formsenha">
        <Instrucoes :texto="texto" />

        <form @submit.prevent="submitForm">
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

export default {
    name: 'FormSenha',
    components: {
        Instrucoes
    },
    data() {
        return {
            email: '',
            texto: 'Prezado usuário, informe abaixo o e-mail utilizado durante cadastro:'   
        }
    },
    methods: {
        submitForm() {
            email = this.email;
            
            axios.get('http://localhost:8080/recupera-senha?email='+email)
                .then(response => {
                // Verificar a resposta do servidor
                if (response.status === 200) {
                    // Email encontrado - prosseguir com a recuperação da senha
                    // Normalmente, você redirecionaria o usuário para uma página de confirmação
                    // ou exibiria uma mensagem de sucesso.
                    console.log('E-mail encontrado. Enviando e-mail de recuperação de senha.');
                } else {
                    // Email não encontrado - exibir mensagem de erro
                    console.error('E-mail não encontrado.');
                }
            })
            .catch(error => {
                // Lidar com erros de solicitação, como problemas de rede ou problemas do servidor
                console.error('Erro ao verificar o e-mail:', error);
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
