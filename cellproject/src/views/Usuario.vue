<template>
    <div class="usuario">
      <h2>Área do Cliente/Usuário</h2>
        <div id="descricao">
            <p>Prezado usuário, selecione abaixo uma das opções desejadas:</p>
        </div>

        <UserBanner />

    </div>    
</template>

<script>

import UserBanner
 from '@/components/UserBanner.vue';
export default {
    name: 'Usuario',
    components: {
        UserBanner
    }
}
</script>
  
<style scoped>

    .usuario {
        margin: 20px;
        text-align: center;
    }  

    #descricao {
        margin: auto;
        width: 500px;
        height: 20px;
        background-color: #FFFFE0;
        text-align: center;
    }
</style>