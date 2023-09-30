<template>   
    <div id="logon">
        <Message :msg="msg" v-show="msg" />
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Login</h3>
                    </div>
                    <div class="card-body">
                        <form @submit.prevent="realizarLogin">
                            <div class="form-group">
                                <label for="username">Usuário:</label>
                                <input type="text" class="form-control" id="username" v-model="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Senha:</label>
                                <input type="password" class="form-control" id="password" v-model="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>
</template>

<script>
import Message from './Message.vue';

export default {
    name: 'Logon',
    components: {
        Message
    },
    data() {
        return {
         authenticated: false,
         msg: null
        }
    },
    methods: {
        realizarLogin() {

            if (this.username == this.password) {
                this.authenticated = true;           
                this.$emit('realizarLogin', this.authenticated);
            }
            
            else {                
                this.msg = 'Usuário ou senha incorretos. Tente outra vez!'                
            }
        }
    }
}
</script>

<style scoped>
    button{
        margin-top: 10px;
    }
</style>