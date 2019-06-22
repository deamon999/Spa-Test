<template>
    <div class="container align-content-center">
        <h1>List of all forms for user</h1>
        <h2>{{ user }}</h2>
        <v-list>
            <v-list-tile v-for="(form,index) in forms" :key="index">
                <v-list-tile-content>
                    <v-list-tile-title v-text="form"></v-list-tile-title>
                </v-list-tile-content>
            </v-list-tile>
        </v-list>
    </div>

</template>

<script>
    import axios from 'axios'

    export default {
        name: "UserForms",
        props: {
            user: String
        },
        data() {
            return {
                forms: []
            }
        },
        created() {
            axios.get('/GetForms', {
                params: {
                    id: this.user
                }
            }).then(data => {
                console.log(data.data);
                this.forms = data.data;
            }).catch(error => {
                console.log(error);
            })
        }
    }
</script>

<style scoped>

</style>
