<template>
    <div class="estoque">
        <h2>Consultar Estoque</h2> 
        
        <div id = "instrucoes">
            <Instrucoes :texto="texto" />
        </div> 
        
        <form @submit.prevent="consultarEstoque" id="form">  
            <div class = "content">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="ordenarDenominacao" value="denominacao" v-model="criterioOrdenacao">
                    <label class="form-check-label" for="ordenarDenominacao">Ordenar por Denominação</label>
                </div>

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="ordenarQuantidade" value="quantidade" v-model="criterioOrdenacao">
                    <label class="form-check-label" for="ordenarQuantidade">Ordenar por Quantidade</label>
                </div>

                <button type="submit" class="btn btn-primary">Consultar</button>
            </div>    
        </form>    

        <div v-if="produtos.length > 0" class="result"> 
            <table class="table mt-3">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Denominação</th>
                        <th scope="col">Data Entrada</th>
                        <th scope="col">Quantidade</th>
                    </tr>
                </thead>           
                
                <tbody>
                    <tr v-for="produto in produtos" :key="produto.id">
                        <td>{{ produto.codigo }}</td>
                        <td>{{ produto.denominacao }}</td>
                        <td>{{ formatarData(produto.dataEntrada) }}</td>
                        <td>{{ produto.quantidade }}</td>
                    </tr>
                </tbody>
            </table>
        </div>    
    </div>
</template>
  
<script>
import axios from 'axios';
import Instrucoes from './Instrucoes.vue';

export default {
    components: {
        Instrucoes
    },
    data() {
        return {
            criterioOrdenacao: 'denominacao',
            produtos: '',
            texto: 'Prezado(a) usuário(a), selecione abaixo o critério de ordenação.',
        }; 
    },
    methods: {
        consultarEstoque() {       
            const token = sessionStorage.getItem('token');
            
            const config = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }; 

            const criterioOrdenacao = this.criterioOrdenacao;

            axios.get('http://localhost:8080/produto?criterioOrdenacao='+criterioOrdenacao, config)
                .then(response => {
                    this.produtos = response.data;   

                    if(this.produtos.length < 1) {
                        this.msg_failure = 'Não foram localizados produtos na base de dados.'
                    }          
                })
                .catch(error => {
                    this.msg_failure = error.response.data;
                    console.error('Erro ao buscar dados:', error);
                });
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
    #instrucoes {
      width: 500px;
      margin: auto;        
    }

    .content{
        width: 500px;
        margin: auto; 
    }

    .result {
        width: 800px;
        margin: auto;
    }
    input {
        margin-bottom: 10px;
    }

    button {
        margin: 10px
    } 
</style>
