<template>
    <div id="connect-container">
        <h1 style="width: 100%; margin-top: 1rem;">Connect</h1>
        <div id="social-media-container">            
            <img class="media-box" src="/assets/GitHub.svg" alt="linkedin">
            <img class="media-box" src="/assets/GitHub.svg" alt="linkedin">
            <img class="media-box" src="/assets/GitHub.svg" alt="linkedin">
        </div>

        <h1 style="width: 100%;">Leave a Comment</h1>
        <div id="comments-container">
            <div id="message-input-container" class="comment">
                <v-text-field 
                    style="width: 100%" 
                    label="Name"
                    v-model="name"
                    :color="colors.textPrimary"
                    dark
                ></v-text-field>
                <v-textarea
                    v-model="message"
                    label="Message"
                    style="width: 100%" 
                    :color="colors.textPrimary"
                    dark
                ></v-textarea>
                <v-btn
                    v-on:click="sendComment()"
                    text
                    style="margin: 0.5rem 0 0 auto;"
                >Send</v-btn>
            </div>

            <div id="comment-list">
                <div 
                    class="comment"
                    v-for="(comment, index) in commentList"
                    :key="index"
                >
                    <div class="comment-header-container">
                        <h1 style="font-size: 18px">{{comment.name}}</h1>
                        <span style="color: #a3a2a2; font-size: 14px">{{comment.timestamp}}</span>
                    </div>
                    <p style="width: 100%">{{comment.message}}</p>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import COLORS from '../utils/constants'
export default {
    name: 'Connect',

    created () {
        this.getDataFromServer().then(function () {
        })
    },
    
    data () {
        return {
            name: "",
            message: "",
            commentList: [],

            colors: COLORS
        }
    },

    methods: {
        sendComment: async function() {

            const userName = this.name
            const userMessage = this.message
            let response = await fetch('https://8080-b83fc153-d2cf-481d-a321-9342cdf80f21.us-east1.cloudshell.dev/comments', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    datePosted: null,
                    name: userName,
                    message: userMessage
                })
            })

            if (response.ok) {
                this.name = ""
                this.message = ""
            } else {
                alert("There appears to be a connection error, please try again later")
            }


            // Call the get command to update the UI with the comment sent via the POST above
            await this.getDataFromServer()

        },

        getDataFromServer: async function () {
            
            let response = await fetch('https://8080-b83fc153-d2cf-481d-a321-9342cdf80f21.us-east1.cloudshell.dev/comments')

            // Parse epoch to formatted date
            if (response.ok) {
                
                let respData = await response.json()                
                for (let comment of respData){
                    
                    let date = new Date(comment.timestamp)                    
                    comment.timestamp = (date.getMonth() + 1).toString() + "/" + (date.getDay()).toString() +  "/"  + (date.getFullYear()).toString()
                }

                this.commentList = respData
            } else {
                alert("There appears to be a connection error, please try again later")
            }
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #connect-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 80px 16px 0px 16px;

        background-color: #292929;
    }

    #social-media-container {
        width: 100%;
        height: auto;
        
        display: grid;
        align-items: center;
        justify-items: center;
        grid-template-columns: 1fr 1fr 1fr;        

        margin: 1rem 0;
    }

    .media-box {
        height: auto;
        width: 90%
    }

    #comments-container {
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
    }

    #message-input-container {
        box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.20);

    }

    #comment-list {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;        
    }

    .comment-header-container {
        width: 100%;
        height: auto;
        
        display: flex;
        align-items: center;
        justify-content: space-between;

        margin-bottom: 0.5rem;
    }

    .comment {
        height: fit-content;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;        

        margin-top: 1rem;
        padding: 16px;

        border-radius: 2px;
        background-color: #555555;
    }

    @media screen and (min-width: 750px){
        #connect-container {
            padding: 80px 20% 0px 20%;
        }

        #social-media-container {
            width: 50%;
        }
     }
</style>
