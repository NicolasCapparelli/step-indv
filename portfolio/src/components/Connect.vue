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
                    color="#DBDBDB"
                    dark
                ></v-text-field>
                <v-textarea
                    v-model="message"
                    label="Message"
                    style="width: 100%" 
                    color="#DBDBDB"
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
                        <span style="color: #a3a2a2; font-size: 14px">{{comment.timeCreated}}</span>
                    </div>
                    <p style="width: 100%">{{comment.message}}</p>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Connect',

    created () {
        this.getDataFromServer().then(function () {
            console.log("Finished!")
        })
    },
    
    data () {
        return {
            name: "",
            message: "",
            commentList: []
        }
    },

    methods: {
        sendComment: async function() {

            const userName = this.name
            const userMessage = this.message
            let response = await fetch('https://8080-dot-12454009-dot-devshell.appspot.com/comments', {
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
                console.log("POST OK")
                this.name = ""
                this.message = ""
            } else {
                console.log("POST ERR")  
            }


            // Call the get command to update the UI with the comment sent via the POST above
            await this.getDataFromServer()

        },

        getDataFromServer: async function () {
            let response = await fetch('https://8080-dot-12454009-dot-devshell.appspot.com/comments')

            if (response.ok) {
                let respData = await response.json()
                console.log(respData)
                this.commentList = respData

            } else {
                console.log("GET ERR")
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
