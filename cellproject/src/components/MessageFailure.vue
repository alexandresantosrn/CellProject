<template>
    <div class="message-container">
       {{ msg_failure }}      
    </div>
</template>

<script>
export default {
    name: 'Message Failure',
    props: {
        msg_failure: String
    }
} 
</script>

<style scoped>    
    .message-container {
        color: white;
        background-color: #FE9099;
        border: 2px solid #FE9099;
        border-radius: 5px;
        padding: 10px;
        max-width: 400px;
        margin: 30px auto;       
    }
</style>