<template>
    <div class="container align-content-center">
        <v-layout>
            <v-flex xs12 sm6 offset-sm3>
                <v-card>
                    <v-card-title primary-title>
                        <div>
                            <h3 class="headline mb-0">File</h3>
                            <input type="file" id="file" ref="file" @change="handleFileUpload()"/>
                        </div>
                    </v-card-title>
                    <v-card-actions>
                        <v-btn flat color="orange" @click="submitFile()" v-if="file !== ''">Submit</v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
        <v-snackbar
                :timeout="timeout"
                :top="y === 'top'"
                :bottom="y === 'bottom'"
                :right="x === 'right'"
                :left="x === 'left'"
                :multi-line="mode === 'multi-line'"
                :vertical="mode === 'vertical'"
                v-model="snackbar"
        >
            {{ text }}
            <v-btn flat color="pink" @click.native="snackbar = false">Close</v-btn>
        </v-snackbar>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                file: '',
                snackbar: false,
                y: 'top',
                x: null,
                mode: '',
                timeout: 1000,
                text: 'Hello, I\'m a snackbar'
            }
        },
        methods: {
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
            },
            submitFile() {
                let formData = new FormData();
                formData.append('file', this.file);

                axios.post('/UploadFile',
                    formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                ).then(function () {
                    console.log('SUCCESS!!');
                    this.text = 'SUCCESS!!';
                    this.snackbar = true;
                }).catch(function () {
                    this.text = 'SUCCESS!!';
                    this.snackbar = true;
                    console.log('FAILURE!!');
                });
            }
        }
    }
</script>

<style scoped>

</style>
