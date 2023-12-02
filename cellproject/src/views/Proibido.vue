<template>
    <div class="main">
        <h2>Acesso Não Autorizado</h2>

        <div>
            <img :src="img_src" :alt="img_alt">
        </div>  
    </div>     
</template>


<script>
export default {
    data() {
        return {
            img_src: '/img/negado.jpg',
            img_alt: 'Acesso Não Autorizado'
        }
    }        
}
</script>

<style scoped>
    .main {         
        margin: auto;        
        text-align: center;
        margin-bottom: 20px;       
    } 
</style>