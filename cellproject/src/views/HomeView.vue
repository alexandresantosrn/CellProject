<template>
  <div class="main-container">
   <div v-show="logoff">     
    <LogonVue v-on:realizarLogin="authenticated"/>
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