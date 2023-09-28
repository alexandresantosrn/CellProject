<template>
    <div id="nav">
        <router-link to="/" id="logo-url">
            <img :src="logo" :alt="alt" id="logo">
        </router-link>

        <router-link to="/">Home</router-link> |
        <router-link to="/atendimentos">Área do Usuário</router-link> 
        <router-link to="/atendimentos">Atendimentos</router-link> 
        <router-link to="/sair">Sair</router-link>
    </div>    
</template>

<script>
export default {
    name: 'Navbar',
    props: ['logo', 'alt']
}
</script>

<style scoped>
    #nav {
        background-color: #222;
        border-bottom: 4px solid #111;
        padding: 15px 50px;
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }

    #nav #logo-url {
        margin: auto;
        margin-left: 0;
    }

    #logo {
        width: 60px;
        height: 60px;
    }

    #nav a {
        color: #fcba03;
        text-decoration: none;
        margin: 12px;
        transition: .5s;
    }

    #nav a:hover{ 
        color: #fff;
    }        
</style>

