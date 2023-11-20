<template>
    <div id="clientes">
        <h2>Listar/Consultar Ordem de Serviço</h2>
    
        <div id = "instrucoes">
            <Instrucoes :texto="texto" />
        </div>  
    
        <Message :msg="msg" v-show="msg" />
        <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />
            
        <form @submit.prevent="consultarOrdem">
            <div class="form-group">
              <label for="codigo">N&#186 Ordem de Serviço:</label>
              <input type="text" id="codigo" v-model="codigo" class="form-control" required>
            </div>
        <button type="submit" class="btn btn-primary">Consultar</button>
        </form>             
         
        <div v-if="ordem" class="result">      
        <table class="table">
            <thead>
            <tr>
                <th scope="col">N&#186 Ordem de Serviço</th>
                <th scope="col">Nome do Cliente</th>         
                <th scope="col">Tipo de Serviço</th>
                <th scope="col">Data de Entrada</th>   
                <th scope="col">Status do Conserto</th>
            </tr>
            </thead>
            <tbody>
                <tr>        
                    <td>{{ ordem.codigo }}</td>
                    <td>{{ ordem.pessoa.nome }}</td>  
                    <td>{{ ordem.tipoServico.descricao }}</td>  
                    <td>{{ ordem.dataEntrada }}</td>    
                    <td>{{ ordem.statusReparo.descricao }}</td>           
                    <td>
                        <button class="btn btn-danger" @click="removerOrdem(ordem.id)">
                            Remover
                        </button>
                    </td>    
                </tr>           
            </tbody>
        </table>
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
        ordem: '',        
        codigo: '',        
        msg: '',
        msg_failure: '',
        texto: 'Prezado(a) usuário(a), informe o número/código da ordem de serviço para registro da consulta.',
        ordemId: ''
    };
},
methods: {    
    consultarOrdem() {
        const token = sessionStorage.getItem('token');
        
        const codigo = this.codigo;       
        this.update = false;

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        axios.get('http://localhost:8080/ordemservico/ordem-by-codigo?codigo='+codigo, config)
            .then(response => {
                this.msg_failure = "";
                
                this.ordem = response.data; 

                if(this.ordem.length < 1) {
                    this.msg_failure = 'Não foram localizados ordens de serviço com o número/código informado.'
                }

                this.limparCampos();
            })
            .catch(error => {
                this.msg_failure = error.response.data;           
                console.error('Erro ao buscar dados:', error);
            });
        },
    removerOrdem(ordemId) {
        this.ordemId = ordemId;      

        const token = sessionStorage.getItem('token');
        
        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }; 

        axios.post('http://localhost:8080/ordemservico/remover-ordem?id='+ordemId, config)
            .then(response => {           
            this.msg_failure = '';                                           
            this.msg = response.data;  
            this.limparCampos();          
        })
            .catch(error => {                  
            this.msg = '';                   
            this.msg_failure = error.response.data; 
            this.limparCampos();
        });
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
    width: 1000px;
    margin: auto;      
}

td {
    text-align: center;
    vertical-align: middle;
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
      