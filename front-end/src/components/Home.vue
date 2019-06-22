<template>
    <div class="container">
        <v-card>
            <v-expansion-panel>
                <v-expansion-panel-content>
                    <div slot="header">User List</div>
                    <ol class="container">
                        <li v-for="user in users" :key="user" @click="getForms(user)"> {{ user }}</li>
                    </ol>
                </v-expansion-panel-content>
                <v-expansion-panel-content>
                    <div slot="header">Top 5 Forms</div>
                    <ol class="container">
                        <li v-for="(form,index) in forms" :key="index"> {{ form }}</li>
                    </ol>
                </v-expansion-panel-content>
            </v-expansion-panel>

        </v-card>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Home",
        data() {
            return {
                users: [],
                forms: [],
            }
        }, methods: {
            getForms(user) {
                this.$router.push(
                    {name: 'forms', params: {user}})
            }
        },
        created() {
            axios.get('/GetUsers')
                .then(data => {
                    this.users = data.data;
                    console.log(data.data)
                }).catch(error => {
                console.log(error);
            });
            axios.get('/GetTopForms')
                .then(data => {
                    this.forms = data.data;
                }).catch(error => {
                console.log(error);
            })
        }
    }
</script>

<style scoped>

</style>
