<template>
  <div id="nav-container"> 
    <v-app-bar id="toolbar" app fixed color="#343333" elevation="2" height="auto">        
      
      <div id="toolbar-content-container">
        <v-list id="toolbar-list">          
          <v-list-item two-line class="content-li">
            
            <v-list-item-avatar>
              <v-img src="/assets/me.jpeg"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>                
              <v-list-item-title class="content-li-title">Nicolas Capparelli</v-list-item-title>
              <v-list-item-subtitle class="content-li-subtitle">Software Engineer</v-list-item-subtitle>
            </v-list-item-content>

          </v-list-item>
        </v-list>
        
        <v-spacer></v-spacer>
        <v-app-bar-nav-icon v-on:click="drawer = !drawer" ></v-app-bar-nav-icon>
      </div>
    </v-app-bar>

    <v-navigation-drawer
        v-model="drawer"
        temporary
        fixed
        right
    >
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>{{currentPage}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item v-if="isUserLoggedIn">
        <v-list-item-content>
          <v-list-item-title>{{userEmail}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list>
        <v-list-item 
          v-for="page in pageList"
          :key="page.name"
          v-on:click="navigateTo(page.name)"
          link
        >
          <v-list-item-icon>
            <v-icon>{{page.icon}}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{page.name}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item 
            v-if="isUserLoggedIn"
            v-on:click="logout()"
            link
        >
          <v-list-item-icon>
            <v-icon>mdi-account</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import { WEBSITE_URL } from '../utils/constants.js'
export default {
  name: 'Template',

  created () {
    this.$root.$on('pchange', data => {
      this.currentPage = data;
    });      

    this.$root.$on('authCheck', data => {
      this.isUserLoggedIn = data.isLoggedIn;
      this.userEmail = data.email;
    });
  },

  methods: {
    navigateTo(route) {
      this.currentPage = route;
      this.$router.push({
        name: route
      })
    },


    logout: async function () {
      let response = await fetch(WEBSITE_URL + '/authenticate');

      if (response.ok) {
        let respObject = await response.json();
        location.href = respObject.authURL;
      } else {
        alert("There was an issue processing your request, please try again later");
      }   
    }
  },

  data () {
    return {
      drawer: false,
      currentPage: "Home",
      pageList: [
        {
          name: "Home",
          icon: "mdi-home"
        },

        {
          name: "Resume",
          icon: "mdi-file"
        },

        {
          name: "Projects",
          icon: "mdi-code-greater-than-or-equal"
        },

        {
          name: "About",
          icon: "mdi-information-outline"
        },

        {
          name: "Connect",
          icon: "mdi-email"
        }
      ]
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

  #nav-container {
    width: 100%;
    height: auto;
    overflow: hidden;
  }

  #toolbar {
    width: 100%;
  }

  .content-li {
    padding: 0;
  }

  .content-li-title {
    font-weight: bold;
     font-size: 18px;
  }

  .content-li-subtitle {
    font-size: 14px;
  }

  #toolbar-content-container {
    width: 100%; 
    max-width: 900px; 
    display: flex; 
    align-items: center; 
    justify-content: space-between; 
    margin: auto;
  }

  #toolbar-list {
    height: 100%;
    width: 100%;
    background-color: #343333;
    padding: 0;
  }

</style>
