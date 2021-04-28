<template>
<h1>{{ random }}</h1>
</template>

<script>
export default {
    data() {
        return {
            random: "ready"
        }
    },
    created() {
        let that = this
        that.websock = new window.WebSocket("ws://localhost:8064/connect");
        // 连接建立时触发
        that.websock.onopen = (e) => {
            console.log('服务器连接成功')
        }
        // 客户端接收服务端数据时触发
        that.websock.onmessage = (e) => {
            console.log(e.data)
            this.random = e.data
        }
        // 通信发生错误时触发
        that.websock.onerror = (e) => {
            console.log('发生错误')
        }
        // 通信关闭
        that.websock.onclose = (e) => {
            console.log('关闭连接')
        }
    },
    mounted() {

    }
}
</script>

<style>

</style>
