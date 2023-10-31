<template>    
</template>

<script>
name: 'Logout'
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