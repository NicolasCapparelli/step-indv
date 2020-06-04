<template>
    <div id="connect-container">
        
        <v-alert 
            id="connect-alert"
            v-model="alert"
            :type="alertType"
            dismissible
            elevation="3"
            transition="scroll-y-transition"
        >
            {{alertMessage}}
        </v-alert>
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
                    style="position: relative; margin: 0.5rem 0 0 auto;"
                    :disabled="isProcessingNewComment"
                >
                    Send
                    <v-progress-circular
                        v-if="isProcessingNewComment"
                        style="position: absolute; background-color: #555555; width: 100%"
                        indeterminate
                        color="primary"
                    ></v-progress-circular>
                </v-btn>
            </div>

            <div id="comment-actions-container">
                <v-select
                    v-model="numComments"
                    v-bind:style="{ justifyContent: justifyActionBox}"
                    :items="numCommentOptions"
                    :color="colors.textPrimary"
                    label="Number of Comments"
                    style="max-width: 100px; margin-top: 1rem;" 
                    prepend-icon="filter_list"
                    single-line
                    v-on:change="getDataFromServer()"
                ></v-select>

                 <v-btn 
                    v-if="possibleAdmin"
                    icon
                    :color="colors.textPrimary"
                    v-on:click="deleteComments()"
                >
                    <v-icon>mdi-close</v-icon>
                </v-btn>
            </div>                
            <div 
                id="comment-list" 
                v-if="firstLoadDone"
            >
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

            <div 
                v-else
                id="skeleton-load-container"
            >
                <v-skeleton-loader
                    v-for="i in numComments"
                    :key="i"
                    style="width: 100%; margin-bottom: 1rem;"
                    color="#555555"
                    type="list-item-three-line"
                ></v-skeleton-loader>
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
            this.firstLoadDone = true
        }.bind(this))

        let adminKey = getCookie("xcvd_admin_key_xcvi")
        if (adminKey.length > 0) {
            this.possibleAdmin = true;
            this.adminKey = adminKey
        }
    },
    
    data () {
        return {
            name: "",
            message: "",
            
            numComments: 5,
            commentList: [],
            numCommentOptions: [5, 10, 15],

            alertMessage: "",
            alertType: "success",
            alert: false,
            
            firstLoadDone: false,
            isProcessingNewComment: false,

            possibleAdmin: false,
            justifyActionBox: "flex-start",
            colors: COLORS
        }
    },

    methods: {

        getDataFromServer: async function () {
            
            let response = await fetch('https://8080-b83fc153-d2cf-481d-a321-9342cdf80f21.us-east1.cloudshell.dev/comments?numComments=' + this.numComments)

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

            this.isProcessingNewComment = false
        },

        sendComment: async function() {
            
            this.isProcessingNewComment = true

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

            this.isProcessingNewComment = false

            // Call the get command to update the UI with the comment sent via the POST above
            await this.getDataFromServer()

        },

        deleteComments: async function () {
            let response = await fetch('https://8080-b83fc153-d2cf-481d-a321-9342cdf80f21.us-east1.cloudshell.dev/delete-data', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    adminKey: this.adminKey
                })
            })

            if (response.ok) {
                
                this.alertType = "success"
                this.alertMessage = "Comments delete successfully"

                // Call the get command to update the UI with the comment sent via the POST above
                await this.getDataFromServer()
            } else {
                this.alertType = "error"
                this.alertMessage = "You are not in the sudoers file, this incident will be reported"
            }

            this.alert = true
        }
    }
}


// Cookie reading function from https://www.w3schools.com/js/js_cookies.asp 
function getCookie(cname) {
    var name = cname + "="
    var decodedCookie = document.cookie
    var ca = decodedCookie.split(';')
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i]
        while (c.charAt(0) == ' ') {
            c = c.substring(1)
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length)
        }
    }
    return ""
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
    #connect-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 80px 16px 25px 16px;

        background-color: #292929;
    }

    #connect-alert {
        position: absolute;
        max-width: 800px;
        z-index: 10;
        width: 90%; 
        margin: 2rem auto;
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

    #skeleton-load-container {
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
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

    #comment-actions-container {
        width: 100%;
        
        display: flex;
        flex-direction: row;
        align-items: center;        
        justify-content: space-between;
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

    /* Have to override this to get the right background color */
    .theme--dark.v-skeleton-loader .v-skeleton-loader__actions, .theme--dark.v-skeleton-loader .v-skeleton-loader__article, .theme--dark.v-skeleton-loader .v-skeleton-loader__card-heading, .theme--dark.v-skeleton-loader .v-skeleton-loader__card-text, .theme--dark.v-skeleton-loader .v-skeleton-loader__date-picker, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar-three-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar-two-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-text, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-three-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-two-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-heading, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-tbody, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-tfoot, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-thead{
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
