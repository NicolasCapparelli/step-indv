<template>
  <div class="comment">
    <div class="comment-header-container">
      <h1 class="username">{{commentData.name}}</h1>
      <span class="timestamp">{{commentData.timestamp}}</span>
    </div>
    <p class="message">{{commentData.message}}</p>
    <div class="actions-container">
      
      <div class="ratings-container">
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
import {WEBSITE_URL} from '../../utils/constants.js';

export default {
  name: 'Comment',
  props: {
    commentData: Object,
    successfulChangeCallback: Function
  },
  methods: {
    commentRatingChange: async function(isUpvote) {  
      let response = await fetch(WEBSITE_URL + '/rating', {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          commentID: this.commentData.id,
          isUpvote: isUpvote

        })
      });

      if (response.ok) {
        this.successfulChangeCallback();
      } else {
        alert("There was an error processing your request, please try again later");
      }
    }
  }
}
</script>

<style scoped>

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

  .username {
    font-size: 18px;
  }

  .timestamp {
    color: #a3a2a2; 
    font-size: 14px;
  }

  .message {
    width: 100%;
  }

  .actions-container {
    width: 100%; 
    display: flex; 
    align-items: center; 
    justify-content: flex-end;
  }

  .ratings-container {
    margin-right: 1.5rem;
  }

  .vote-button {
    margin-right: 0.5rem;
  }
</style>