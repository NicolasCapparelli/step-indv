<template>
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
        style="width: 100%;"
        :color="colors.textPrimary"
        dark
      ></v-textarea>
      <v-btn
        v-if="isUserLoggedIn"
        v-on:click="sendComment()"
        text
        class="message-button"
        :disabled="isProcessingNewComment"
      >
        Send
        <v-progress-circular
          v-if="isProcessingNewComment"
          class="message-progress"
          indeterminate
          color="primary"
        ></v-progress-circular>
      </v-btn>

      <v-btn
        v-else
        v-on:click="authenticate()"
        text
        :disabled="isCheckingAuth"
        class="message-button"
      >
        <div
          class="message-progress-container"
        >
          <v-progress-circular
            v-if="isCheckingAuth"
            style="width: 32px;"
            indeterminate
            color="primary"
          ></v-progress-circular>          
        </div>                
        Login to comment
      </v-btn>
    </div>

    <div id="comment-actions-container">
      <v-select
        v-model="numComments"
        v-bind:style="{ justifyContent: justifyActionBox}"
        :items="numCommentOptions"
        :color="colors.textPrimary"
        label="Number of Comments"
        id="num-comments-filter"
        prepend-icon="filter_list"
        single-line
        v-on:change="changeNumComments()"
      ></v-select>

      <div id="pagination-container">
        <v-btn 
          icon
          v-on:click="changeCommentPage(false)"
        >
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>

        <span id="current-page">{{page}}</span>

        <v-btn 
          icon
          v-on:click="changeCommentPage(true)"
        >
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </div>

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
      <Comment
        v-for="comment in commentList"
        :key="comment.id"
        :commentData="comment"
        :successfulChangeCallback="commentSuccessCallback"
      />
    </div>

    <div 
      v-if="!firstLoadDone"
      id="skeleton-load-container"
    >
      <v-skeleton-loader
        v-for="i in numComments"
        :key="i"
        class="comment-skeleton"
        color="#555555"
        type="list-item-three-line"
      ></v-skeleton-loader>
    </div>
  </div>
</template>

<script>
import {COLORS, WEBSITE_URL} from '../../utils/constants.js';
import Comment from '../sub-components/Comment.vue';
const defaultNumComments = 5;

export default {
  name: 'CommentList',
  components: {
    Comment
  },
  props: {
    alertDelegate: Function
  },

  async created () {

    let respData = await this.getComments(this.numComments, this.page);
    this.updateCommentList(respData);
    this.firstLoadDone = true;

    let adminKey = getCookie("xcvd_admin_key_xcvi");
    if (adminKey.length > 0) {
      this.possibleAdmin = true;
      this.adminKey = adminKey;
    }
  },

  async mounted () {
    await this.checkAuth();
  },

  data () {
    return {
      name: "",
      message: "",

      commentList: [],

      isUserLoggedIn: false,
      isProcessingNewComment: false,
      isCheckingAuth: false,

      page: 1,
      numComments: defaultNumComments,      
      numCommentOptions: [5, 10, 15],
      lastPage: Number.MAX_SAFE_INTEGER,

      possibleAdmin: false,
      firstLoadDone: false,    

      justifyActionBox: "flex-start",

      colors: COLORS
    }
  },

  methods: {

    checkAuth: async function() {
      this.isCheckingAuth = true;
      let response = await fetch(WEBSITE_URL + '/checkAuth');

      if (response.ok){

        let respObject = await response.json();
        this.isUserLoggedIn = respObject.isLoggedIn;

        this.$root.$emit('authCheck', respObject);
      }

      this.isCheckingAuth = false;
    },

     getComments: async function (numComments, page) {    

      let response = await fetch(WEBSITE_URL + '/comments?numComments=' + numComments + "&page=" + page);
      let respData;
      
      if (response.ok) {        
        respData = await response.json();   
      } else {
        alert("There appears to be a connection error, please try again later");
        respData = [];
      }

      this.isProcessingNewComment = false;
      return respData;
    },  

    authenticate: async function () {
      let response = await fetch(WEBSITE_URL + '/authenticate');

      if (response.ok) {
        let respObject = await response.json();
        location.href = respObject.authURL;
      } else {
        alert("There was an issue processing your request, please try again later");
      }        
    },

    sendComment: async function() {
      
      this.isProcessingNewComment = true;

      const userName = this.name;
      const userMessage = this.message;
      let response = await fetch(WEBSITE_URL + '/comments', {
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
        this.name = "";
        this.message = "";
      } else {
        alert("There appears to be a connection error, please try again later");
      }

      this.isProcessingNewComment = false;

      // Call the get command to update the UI with the comment sent via the POST above
      let respData = await this.getComments(this.numComments, this.page);
      this.updateCommentList(respData);

    },

    changeNumComments: async function() {

      // Reset last page value since there will be a new number of comments per page
      this.lastPage = Number.MAX_SAFE_INTEGER;  

      let respData = await this.getComments(this.numComments, this.page);

      if (respData.length < 1){
        // Set the last page as the current page, as there are no comments in the current page
        this.lastPage = this.page - 1;

        // If the current page is not 1, then show user the previous page. If it is 1 it means there are no comments at all, so do nothing
        this.page != 1 && (this.page -= 1);

        let respData = await this.getComments(this.numComments, this.page);
        
        this.updateCommentList(respData);
        
        return
      }

      this.updateCommentList(respData);
    },

    changeCommentPage: async function (isForward) {
      if (isForward) {
        if (this.page < this.lastPage){this.page += 1}
      }

      else {
        if (this.page > 1){ this.page -= 1 }
      }
      
      // Get data for new comments page
      let respData = await this.getComments(this.numComments, this.page);

      if (respData.length < 1){

        // Set the last page as the current page, as there are no comments in the current page
        this.lastPage = this.page - 1;

        // If the current page is not 1, then show user the previous page. If it is 1 it means there are no comments at all, so do nothing
        this.page != 1 && (this.page -= 1);
        
        return
      } 
      
      else { 
        this.updateCommentList(respData);
      }
    },

    deleteComments: async function () {
      let response = await fetch(WEBSITE_URL + '/delete-data', {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          adminKey: this.adminKey
        })
      })

      let alertType;
      let alertMessage;      

      if (response.ok) {
        
        alertType = "success";
        alertMessage = "Comments delete successfully";

        // Call the get command to update the UI with the comment sent via the POST above
        let respData = await this.getComments(this.numComments, this.page);
        this.updateCommentList(respData);
      } else {
        alertType = "error";
        alertMessage = "You are not in the sudoers file, this incident will be reported";
      }

      this.alertDelegate(true, alertType, alertMessage);
    },

    updateCommentList: function (respData) {
      
      // Format timestamp to MM/DD/YYYY
      for (let comment of respData){          
        let date = new Date(comment.timestamp);       
        comment.timestamp = (date.getMonth() + 1).toString() + "/" + (date.getDay()).toString() +  "/"  + (date.getFullYear()).toString();
      }

      this.commentList = respData;
    },

    commentSuccessCallback: async function () {
      let respData = await this.getComments(this.numComments, this.page);
      this.updateCommentList(respData);
    },

  }
}

// Cookie reading function from https://www.w3schools.com/js/js_cookies.asp 
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = document.cookie;
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }

    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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

  .message-button {
    position: relative; 
    margin: .5rem 0 0 auto;
  }

  .message-progress-container {
    position: absolute; 
    display: flex; 
    align-items: center; 
    justify-content: flex-end; 
    background-color: #555555; 
    width: 100%;
  }

  .message-progress {
    position: absolute; 
    background-color: #555555; 
    width: 100%;
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

  #comment-actions-container {
    width: 100%;
    
    display: flex;
    flex-direction: row;
    align-items: center;    
    justify-content: space-between;
  }

  #num-comments-filter {
    max-width: 100px;
    margin-top: 1rem;
  }

  #current-page {
    margin: 0 0.5rem;
  }

  #comment-list {
    height: 100%;
    width: 100%;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;    
  }

  .comment-skeleton {
    width: 100%; 
    margin-bottom: 1rem;
  }

  #pagination-container {
    display: flex;
    width: 100%;

    align-items: center;
    justify-content: center;

    margin-top: 1rem;
  }

  #skeleton-load-container {
    width: 100%;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
  }
</style>
