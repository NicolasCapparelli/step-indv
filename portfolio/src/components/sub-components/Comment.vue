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
          <v-icon :color="upvoteSelected ? colors.selected : colors.unSelected">mdi-thumb-up</v-icon>
        </v-btn>
        <span>{{upvotes}}</span>
      </div>

      <div>
        <v-btn 
            icon 
            small
            class="vote-button"
            v-on:click="commentRatingChange(false)"
        >
          <v-icon :color="downvoteSelected ? colors.selected : colors.unSelected">mdi-thumb-down</v-icon>
        </v-btn>
        <span>{{downvotes}}</span>
      </div>
    </div>

  </div>
</template>

<script>
import {WEBSITE_URL, COLORS} from '../../utils/constants.js';

export default {
  name: 'Comment',  
  mounted (){    
    this.updateRatings();
  },
  
  props: {
    commentData: Object,
    successfulChangeCallback: Function,
  },

  data() {
    return {
      upvotes: 0,
      downvotes: 0,
      
      upvoteSelected: false,
      downvoteSelected: false,

      colors: COLORS
    }
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
          vote: isUpvote ? 1 : -1
        })
      });

      if (response.ok) {
        await this.successfulChangeCallback();
        this.updateRatings();    
      } else {
        alert("You must be logged in to rate comments");
      }
    },

    updateRatings: function() {
      this.countVotes();
      this.updateSelected();
    },

    countVotes: function () {    
      let upvotes = 0;
      let downvotes = 0;
      if (this.commentData.voteMap) {
        let voterMap = this.commentData.voteMap.propertyMap;
        for (let voter in voterMap){
          if (voterMap[voter] == 1) {
            upvotes++;
          } else {
            downvotes++;
          }
        }

        // Everytime a state attribute is changed, the view is rendered again, which is why I don't 
        // increment this.upvotes and this.downvotes in the loop
        this.upvotes = upvotes;
        this.downvotes = downvotes;
      }
    },

    updateSelected: function () {
      let voteMap;
      
      // If voteMap == null there are no ratings
      if (this.commentData.voteMap) {
        voteMap = this.commentData.voteMap.propertyMap;
      } else {
        return;
      }
      
      
      const userID = this.commentData.userID;
      if (userID in voteMap) {      
        if (voteMap[userID] == 1) { 
          this.upvoteSelected = true;
          this.downvoteSelected = false;
        } else {
          this.upvoteSelected = false;       
          this.downvoteSelected = true;
        }      
      } else {
          this.upvoteSelected = false; 
          this.downvoteSelected = false;
      }
    }
    
  },
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