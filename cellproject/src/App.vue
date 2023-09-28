<template>
  <div id="app">
    <Navbar :logo="logo_src" :alt="app_name"/>
    <router-view/>
    <Footer />
  </div>
  
</template>

<script>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      logo_src: '/img/files.png',
      app_name: 'Papers Project'
    }
  }  
}
</script>

<style>
  * {
    font-family: HELVETICA;
    padding: 0;
    margin: 0;
    box-sizing: border-box;
  }

  .main-container{
     margin: 50px;
     min-height: 250px;
  }

  h1{
    text-align: center;
    font-size: 42px;
    margin-bottom: 30px;
    color: #222
  }
</style>
