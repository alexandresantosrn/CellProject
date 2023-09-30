<template>
    <div id="logon">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Login</h3>
                    </div>
                    <div class="card-body">
                        <form @submit.prevent="login">
                            <div class="form-group">
                                <label for="username">Usuário:</label>
                                <input type="text" class="form-control" id="username" v-model="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Senha:</label>
                                <input type="password" class="form-control" id="password" v-model="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block" @click="realizarLogin()">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>
</template>

<script>
export default {
    name: 'Logon',
    data() {
        return {
         authenticated: false
        }
    },
    methods: {
        realizarLogin() {
            this.authenticated = true;           
            this.$emit('realizarLogin', this.authenticated);
        }
    }
}
</script>

<style scoped>
    button{
        margin-top: 10px;
    }
</style>