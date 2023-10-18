<template>
     <div class="authenticated">
        <p>Usuário autenticado com sucesso! Clique <router-link to="/usuario">aqui</router-link> para acessar a área de usuário.</p>
    </div>    
</template>

<script>
export default {
    
}
</script>

<style scoped>
  p {
    text-align: center;
    font-weight: bold;
    font-size: 20px;
  }

  a{
    color: #FCBA03;
    text-decoration: solid;
  }   
</style>

<style scoped>
    .authenticated {        
        text-align: center;
        margin: 80px;       
    }
</style>