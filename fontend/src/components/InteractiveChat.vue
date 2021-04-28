<template>
<div id="app">
    <div class="chart_box">
        <h5>当前在线人数: {{ count }}</h5>
        <ul id="content_box">
        </ul>
        <div class="input_box">
            <el-input placeholder="请输入内容" v-model="chartContent">
                <el-button slot="append" type="primary" @click="sendFnc">发送</el-button>
            </el-input>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'App',
    data() {
        return {
            count: '1',

            connect: '未连接',

            websock: null,
            tableData: [{
                    label: '京东',
                    value: '0'
                },
                {
                    label: '淘宝',
                    value: '0'
                },
                {
                    label: '拼多多',
                    value: '0'
                }
            ],
            chartContent: ''
        }
    },
    created() {
        this.init()
    },
    methods: {
        // 启动 websocket 连接
        init() {
            let that = this
            that.websock = new window.WebSocket("ws://localhost:8083/connect/weChat/{username}");
            // 连接建立时触发
            that.websock.onopen = (e) => {
                console.log('服务器连接成功')
            }
            // 客户端接收服务端数据时触发
            that.websock.onmessage = (e) => {
                console.log(e)
                let data = JSON.parse(e.data)
                if (data.messageType === "ONLINE") {
                    data.message
                }

                this.addItemFnc(e.data)
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
        onlineCount(count) {
            this.count = count
        },
        // 发送消息
        sendFnc() {
            let body = {
                person: 'A用户：',
                className: 'right_content',
                content: this.chartContent
            }
            this.websock.send(JSON.stringify(body))
            this.chartContent = ''
        },
        // 公用方法
        addItemFnc(data) {
            let newData = JSON.parse(data)
            let chartBox = document.getElementById('content_box')
            let item = document.createElement('li')
            item.className = newData.className
            item.innerHTML = newData.person + newData.content
            chartBox.appendChild(item)
        },
        // 关闭通讯
        stopFnc(e) {
            this.websock.close()
        }
    }
}
</script>

<style>
* {
    margin: 0;
    padding: 0
}

#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    width: 100%;
    height: 100%;
    margin-top: 60px;
}

.chart_box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center
}

#content_box {
    width: 500px;
    height: 300px;
    border: 1px solid grey;
    box-sizing: border-box;
    overflow-y: auto
}

.input_box {
    width: 500px
}

#content_box li {
    list-style-type: none;
    height: 40px;
}

.left_content {
    float: left;
    width: 400px;
    line-height: 40px;
    padding-left: 10px;
    margin: 5px 0;
    text-align: left;
    border-radius: 8px
}

.right_content {
    float: right;
    width: 400px;
    line-height: 40px;
    margin: 5px 0;
    text-align: right;
    padding-right: 10px;
}
</style>
