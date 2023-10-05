<template>
  <div id="app">
    <Navbar :logo="logo_src" :alt="app_name" :allowLinks="viewLinks"/>
    <router-view/>
    <router-link to="/atendimentos"></router-link>
    <Footer />
  </div>
  
</template>

<script>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import HomeView from './views/HomeView.vue';

export default {
  name: 'App',
  components: {
    Navbar,
    Footer,
    HomeView
  },
  data() {
    return {
      logo_src: '/img/logo.png',
      app_name: 'Cell Project',
      viewLinks: false
    }
  },
  methods: {
    linksOn(value) {
      this.viewLinks = true;
    }
  }  
}
</script>

<style>
  * {
    font-family: Arial, Helvetica, sans-serif;
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
