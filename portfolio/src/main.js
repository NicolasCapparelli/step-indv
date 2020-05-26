import App from './App.vue'
import Vue from 'vue'
import vuetify from '@/plugins/vuetify'
import VueRouter from 'vue-router'
import Home from './components/Home'
import Resume from './components/Resume'

Vue.config.productionTip = false
Vue.use(VueRouter)

const routes = [
  { name: "Home", path:"/", component: Home },
  { name: "Resume", path:"/resume", component: Resume }
]

const router = new VueRouter({
  routes
})

new Vue({
  vuetify,
  router,
  render: h => h(App),
}).$mount('#app')
