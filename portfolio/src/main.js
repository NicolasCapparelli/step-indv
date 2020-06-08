import App from './App.vue'
import Vue from 'vue'
import LoadScript from 'vue-plugin-load-script';
import vuetify from '@/plugins/vuetify'
import VueRouter from 'vue-router'
import Home from './components/Home'
import Resume from './components/Resume'
import Projects from './components/Projects'
import About from './components/About'
import Connect from './components/Connect'

Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(LoadScript)

const routes = [
  { name: "Home", path:"/", component: Home },
  { name: "Resume", path:"/resume", component: Resume },
  { name: "Projects", path:"/projects", component: Projects },
  { name: "About", path:"/about", component: About },
  { name: "Connect", path:"/connect", component: Connect },
]

const router = new VueRouter({
  routes
})

new Vue({
  vuetify,
  router,
  render: h => h(App),
}).$mount('#app')

