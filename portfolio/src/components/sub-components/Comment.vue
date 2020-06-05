<template>
    <div class="comment">
        <div class="comment-header-container">
            <h1 style="font-size: 18px">{{commentData.name}}</h1>
            <span style="color: #a3a2a2; font-size: 14px">{{commentData.timestamp}}</span>
        </div>
        <p style="width: 100%">{{commentData.message}}</p>
        <div style="width: 100%; display: flex; align-items: center; justify-content: flex-end;">
            
            <div style="margin-right: 1.5rem;">
                <v-btn
                    icon
                    small                     
                    class="vote-button"
                    v-on:click="commentRatingChange(true)"
                >
                    <v-icon>mdi-thumb-up</v-icon>
                </v-btn>
                <span>{{commentData.upvotes}}</span>                            
            </div>

            <div>
                <v-btn 
                    icon 
                    small
                    class="vote-button"
                    v-on:click="commentRatingChange(false)"
                >
                    <v-icon>mdi-thumb-down</v-icon>
                </v-btn>
                <span>{{commentData.downvotes}}</span>
            </div>
        </div>

    </div>
</template>

<script>
export default {
    name: 'Comment',
    props: {
        commentData: Object,
        successfulChangeCallback: Function
    },
    methods: {
        commentRatingChange: async function(isUpvote) {
            let response = await fetch('https://8080-b83fc153-d2cf-481d-a321-9342cdf80f21.us-east1.cloudshell.dev/rating', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    commentID: this.commentData.id,
                    isUpvote: isUpvote

                })
            })

            if (response.ok) {
                this.successfulChangeCallback()
            } else {
                alert("There was an error processing your request, please try again later")
            }
        }
    }
}
</script>

<style scoped>
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

    .vote-button {
        margin-right: 0.5rem;
    }
</style>