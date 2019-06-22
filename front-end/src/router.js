import Vue from 'vue'
import Router from 'vue-router'
import Home from './components/Home'
import Load from './components/LoadFile'
import UserForms from './components/UserForms'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/load',
            name: 'load',
            component: Load
        },
        {
            path: '/userForms',
            name: 'forms',
            component: UserForms,
            props: true
        }
    ],
    mode: 'history'
})
