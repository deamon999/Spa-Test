<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title class="headline text-uppercase">
                <span>Test</span>
                <span class="font-weight-light">Application</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn
                    flat
            >
                <router-link to="/"><span class="mr-2">Home</span></router-link>
            </v-btn>
            <v-btn
                    flat
            >
                <router-link to="/load"><span class="mr-2">Load File</span></router-link>
            </v-btn>
        </v-toolbar>

        <v-content>
            <router-view/>
        </v-content>
    </v-app>
</template>

<script>

    export default {
        name: 'App',
        data() {
            return {
                //
            }
        }
    }
</script>
