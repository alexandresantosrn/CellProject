<template>
  <div class="main-container">
   <div v-show="logoff">     
    <LogonVue v-on:realizarLogin="authenticated"/>
  </div> 
  
    <div v-show="linksOn">
      <p>Usuário autenticado com sucesso! Clique <router-link to="/usuario">aqui</router-link> para acessar a área de usuário.</p>
    </div>
  </div> 
</template>

<script>
import LogonVue from '@/components/Logon.vue'

export default {
  name: 'HomeView',
   components: {
    LogonVue
  },
  data() {
    return {
      logoff: true,
      linksOn: false
    }
  }, 
  methods: {
    authenticated(value) {      
      this.logoff = !value;
      this.showLinks();
    },
    showLinks() {
      this.linksOn = true;
      this.$emit('showLinks', this.linksOn);
    }
  }  
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