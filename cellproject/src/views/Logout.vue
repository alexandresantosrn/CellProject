<template>    
</template>

<script>
name: 'Sair'
export default {
    methods: {
        logout() {
            this.$router.push('/');
        }
    },
    mounted() {
        this.logout();
    }
}
</script>