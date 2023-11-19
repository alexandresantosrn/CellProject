<template>
<div id="clientes">
    <h2>Listar/Alterar Cliente</h2>

    <div id = "instrucoes">
        <Instrucoes :texto="texto" />
    </div>  

    <Message :msg="msg" v-show="msg" />
    <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />
        
    <form @submit.prevent="consultarPessoa">
        <div class="form-group">
          <label for="cpf">CPF:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" placeholder="___.___.___-__" maxlength = "11" required>
        </div>
    <button type="submit" class="btn btn-primary">Consultar</button>
    </form>

    <div v-if="update">
        <AlterarPessoa :pessoaId="pessoaId"/>
    </div>  
    <div v-else> 
    <div v-if="pessoa" class="result">      
    <table class="table">
        <thead>
        <tr>
            <th scope="col">CPF</th>
            <th scope="col">Nome do Cliente</th>         
            <th scope="col">Ações</th>   
        </tr>
        </thead>
        <tbody>
            <tr>        
                <td>{{ pessoa.cpf }}</td>
                <td>{{ pessoa.nome }}</td>            
                <td>
                    <button class="btn btn-warning" @click="alterarPessoa(pessoa.id)">
                        Alterar
                    </button>
                </td>    
            </tr>           
        </tbody>
    </table>
    </div>
    </div>
</div>
</template>
  
<script>

import Message from './Message.vue';
import MessageFailure from './MessageFailure.vue';
import axios from 'axios';  
import Instrucoes from './Instrucoes.vue';
import AlterarPessoa from './AlterarPessoa.vue';

export default {
name: 'Listar Clientes',
components: {
    Message,
    MessageFailure,
    Instrucoes,
    AlterarPessoa
},
data() {
    return {
        pessoa: '',        
        cpf: '',
        nome: '',
        msg: '',
        msg_failure: '',
        texto: 'Prezado(a) usuário(a), informe o cpf do cliente para registro da consulta.',
        update: false,
        pessoaId: ''
    };
},
methods: {    
    consultarPessoa() {
        const token = sessionStorage.getItem('token');
        
        const cpf = this.cpf;       
        this.update = false;

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        axios.get('http://localhost:8080/pessoa/pessoa-by-cpf?cpf='+cpf, config)
            .then(response => {
                this.msg_failure = "";
                
                this.pessoa = response.data; 

                if(this.pessoa.length < 1) {
                    this.msg_failure = 'Não foram localizados clientes com o cpf informado.'
                }

                this.limparCampos();
            })
            .catch(error => {
                this.msg_failure = error.response.data;           
                console.error('Erro ao buscar dados:', error);
            });

        },
    alterarPessoa(pessoaId) {
        this.pessoaId = pessoaId;      

        this.update = true;  
    },   
    limparCampos() {
        setTimeout(() => this.msg = "", 5000);
        setTimeout(() => this.msg_failure = "", 5000);
    }  
}
};
</script>
  
<style scoped>
.form-group {
    width: 300px;       
    margin: auto; 
}
#instrucoes {
    width: 500px;
    margin: auto;        
}
.table {
    width: 600px;
    margin: auto;      
}

td {
    text-align: center;
    vertical-align: middle;
}

tr {
    width: 1500px;
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
    margin: 10px
} 
</style>
  