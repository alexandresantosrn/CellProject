<template>
<div id="clientes">
    <h2>Cadastrar Conserto</h2>

    <div id = "instrucoes">
        <Instrucoes :texto="texto" />
    </div>  

    <Message :msg="msg" v-show="msg" />
    <MessageFailure :msg_failure="msg_failure" v-show="msg_failure" />
        
    <form @submit.prevent="consultarPessoa">
        <div class="form-group">
          <label for="cpf">N&#186 Ordem de Serviço:</label>
          <input type="text" id="cpf" v-model="cpf" class="form-control" required>
        </div>
    <button type="submit" class="btn btn-primary">Consultar</button>
    </form>

    <div v-if="update">
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
                    <td>{{ formatarData(ordem.dataEntrada) }}</td>    
                    <td>{{ ordem.statusReparo.descricao }}</td>        
                </tr>           
            </tbody>
        </table>

        <form @submit.prevent="finalizarConserto" id="form">
            <div class="form-group2">
            <label for="solucao">Solução Apresentada:</label>
            <textarea class="form-control" id="solucao" rows="3"></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Finalizar Conserto</button>
        </form>    
    </div>  
    <div v-else> 
    <div v-if="ordem" class="result">      
    <table class="table">
        <thead>
        <tr>
            <th scope="col">N&#186 Ordem de Serviço</th>
            <th scope="col">Nome do Cliente</th>         
            <th scope="col">Tipo de Serviço</th>
            <th scope="col">Data de Entrada</th>   
            <th scope="col">Status do Conserto</th>
            <th scope="col">Ações</th>
        </tr>
        </thead>
        <tbody>
            <tr>        
                <td>{{ ordem.codigo }}</td>
                <td>{{ ordem.pessoa.nome }}</td>  
                <td>{{ ordem.tipoServico.descricao }}</td>  
                <td>{{ formatarData(ordem.dataEntrada) }}</td>    
                <td>{{ ordem.statusReparo.descricao }}</td>         
                <td>
                    <button class="btn btn-primary" @click="cadastrarConserto(ordem.id)">
                        Cadastrar
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

export default {
name: 'Cadastrar Conserto',
components: {
    Message,
    MessageFailure,
    Instrucoes
},
data() {
    return {
        ordem: '',        
        cpf: '',
        nome: '',
        msg: '',
        msg_failure: '',
        texto: 'Prezado(a) usuário(a), informe o número/código da ordem de serviço para registro da consulta.',
        update: false,
        pessoaId: '',
        solucao: ''
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

        axios.get('http://localhost:8080/ordemservico/ordem-by-codigo?codigo='+cpf, config)
            .then(response => {
                this.msg_failure = "";
                
                this.ordem = response.data; 

                if(this.ordem.length < 1) {
                    this.msg_failure = 'Não foram localizadas ordens de servico com o código informado.'
                }
                
                this.limparCampos();
            })
            .catch(error => {
                this.msg_failure = error.response.data;           
                console.error('Erro ao buscar dados:', error);
            });

        },
    cadastrarConserto(pessoaId) {
        this.pessoaId = pessoaId;      

        this.update = true;  
    }, 
    finalizarConserto() {
        const token = sessionStorage.getItem('token');
        
        const ordem = {
            id: this.pessoaId
        }

        const config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        axios.post('http://localhost:8080/ordemservico/finalizar-reparo', ordem, config)
            .then(response => {
                this.msg_failure = "";                
                this.msg = response.data;
                
                this.limparCampos();
            })
            .catch(error => {
                this.msg_failure = error.response.data;           
                console.error('Erro ao buscar dados:', error);
            });

    }, 
    limparCampos() {
        setTimeout(() => this.msg = "", 10000);
        setTimeout(() => this.msg_failure = "", 10000);
    },
    formatarData(data) {
        //Convertendo data para formato brasileiro.
        const [ano, mes, dia] = data.split('-');
        return `${dia}/${mes}/${ano}`;
    },  
}
};
</script>
  
<style scoped>
.form-group {
    width: 300px;       
    margin: auto; 
}
.form-group2 {
    width: 500px;       
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
  