<template>
    <div class="page-container" id="connect-container">
        
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
            <a class="media-box" href="https://github.com/NicolasCapparelli">
                <img src="/assets/GitHub.svg" alt="github" >
            </a>
    
            <a class="media-box" style="display: flex; align-items: center; justify-content: center;" href="https://linkedin.com/in/NicolasCapparelli">
                <img style="width: 80%;" src="/assets/linkedin-logo-white.svg" alt="linkedin">
            </a>
    
            <a class="media-box" href="mailto:capparellinicog@hotmail.com">
                <img style="width: 100%" src="/assets/email-white.svg" alt="email">
            </a>
        </div>

        <h1 style="width: 100%;">Leave a Comment</h1>
        <CommentList :alertDelegate="alertDelegate"/>        

    </div>
</template>

<script>
import CommentList from './sub-components/CommentList'

export default {
    name: 'Connect',
    
    data () {
        return {
            alertMessage: "",
            alertType: "success",
            alert: false,
        }
    },

    components: {
        CommentList
    },

    methods: {
        alertDelegate: function (isVisibile, type, message) {
            this.alert = isVisibile
            this.alertType = type
            this.alertMessage = message
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
    #connect-container {
        padding: 80px 16px 25px 16px;
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
        width: 90%;
    }

    /* Have to override this to get the right background color */
    .theme--dark.v-skeleton-loader .v-skeleton-loader__actions, .theme--dark.v-skeleton-loader .v-skeleton-loader__article, .theme--dark.v-skeleton-loader .v-skeleton-loader__card-heading, .theme--dark.v-skeleton-loader .v-skeleton-loader__card-text, .theme--dark.v-skeleton-loader .v-skeleton-loader__date-picker, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar-three-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-avatar-two-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-text, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-three-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__list-item-two-line, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-heading, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-tbody, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-tfoot, .theme--dark.v-skeleton-loader .v-skeleton-loader__table-thead{
        background-color: #555555;
    }

    @media screen and (min-width: 750px){

        #social-media-container {
            width: 50%;
        }
     }
</style>
