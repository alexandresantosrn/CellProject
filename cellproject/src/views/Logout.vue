<template>    
</template>

<script>
name: 'Logout'
export default {
    methods: {
        logout() {
            sessionStorage.removeItem('token');
            this.$router.push('/');
        }
    },
    mounted() {
        this.logout();
    }
}
</script>