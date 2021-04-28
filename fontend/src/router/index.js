import Vue from 'vue'
import Router from 'vue-router'
import InteractiveChat from '@/components/InteractiveChat'
import Random from '@/components/Random'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/chat',
      component: InteractiveChat
    },
    {
      path: '/random',
      component: Random
    }
  ]
})