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

            this.cleanLogon();

            //Recarrega a página para ocultar os menus de acesso.
            if (this.value != 1) {
                window.location.reload();   
                const temp = 1;
                localStorage.setItem('logoff', temp);
            }           
        },
        cleanLogon(){            
            //Remove a variável auxiliar de logon do localStorage.
            localStorage.removeItem('logon');        
        }        
    },
    mounted() {
        this.logout();
    }
}
</script>