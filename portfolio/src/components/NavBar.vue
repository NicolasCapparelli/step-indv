<template>
    <div id="nav-container"> 
        <v-app-bar id="toolbar" app fixed color="#343333" elevation="2" height="auto">
            <v-list id="toolbar-list">
                <v-list-item two-line style="padding: 0;">
                    <v-list-item-avatar>
                            <v-img src="/assets/me.jpeg"></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content>                
                        <v-list-item-title style="font-weight: bold; font-size: 18px">Nicolas Capparelli</v-list-item-title>
                        <v-list-item-subtitle style="font-size: 14px">Software Engineer</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            
            <v-spacer></v-spacer>
            <v-app-bar-nav-icon v-on:click="drawer = !drawer" ></v-app-bar-nav-icon>
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

        </v-list>
    </v-navigation-drawer>

    </div>
</template>

<script>
export default {
    name: 'Template',
    props: {
    msg: String
    },

    created () {
        this.$root.$on('pchange', data => {
            this.currentPage = data
        });      
    },

    methods: {
        navigateTo(route) {
            this.currentPage = route
            this.$router.push({
                name: route
            })
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

    #toolbar-list {
        height: 100%;
        width: 100%;
        background-color: #343333;
        padding: 0;
    }

    @media screen and (min-width: 1200px){
        #toolbar {
            padding: 0 20%;
        }
    }

</style>
