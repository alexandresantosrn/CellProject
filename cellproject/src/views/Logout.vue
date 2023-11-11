<template>    
</template>

<script>
name: 'Logout'
export default {
    data() {
        return {
            value: ''
        }
    },     
    methods: {
        logout() {
            sessionStorage.removeItem('token');            
            this.$router.push('/');

            this.value = localStorage.getItem('logoff'); 

            //Recarrega a página para ocultar os menus de acesso.
            if (this.value != 1) {
                window.location.reload();   
                const temp = 1;
                localStorage.setItem('logoff', temp);
            }           
        }        
    },
    mounted() {
        this.logout();
    }
}
</script>