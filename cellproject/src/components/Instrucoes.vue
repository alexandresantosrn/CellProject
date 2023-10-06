<template>
    <div class="descricao">
        <p> {{ texto }} </p> 
    </div>
</template>

<script>
export default {
    name: 'Instrucoes',
    props: {
        texto: String
    }
}
</script>

<style scoped>
    .descricao {
        margin-top: 10px;
        margin-bottom: 10px;
        width: auto;
        height: auto;
        background-color: #FFFFE0;
        text-align: center;
        font-size: 16px;        
    }
</style>