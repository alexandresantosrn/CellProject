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

export default {
    name: 'FormSenha',
    components: {
        Instrucoes
    },
    data() {
        return {
            texto: 'Prezado usuário, informe abaixo o e-mail utilizado durante cadastro:'   
        }
    },
    methods: {
        submitForm() {
            
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
