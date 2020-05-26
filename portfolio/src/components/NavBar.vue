<template>
    <div id="nav-container"> 
        <v-app-bar id="toolbar" app color="#343333" elevation="2">
            <v-list-item two-line>
                <v-list-item-avatar>
                        <v-img src="https://cdn.vuetifyjs.com/images/lists/3.jpg"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>                
                    <v-list-item-title style="font-weight: bold;">Nicolas Capparelli</v-list-item-title>
                    <v-list-item-subtitle>Software Engineer</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <v-spacer></v-spacer>
            <v-app-bar-nav-icon v-on:click="drawer = !drawer"></v-app-bar-nav-icon>
        </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      right
    >
      <v-list-item>
            <v-list-item-avatar>
                <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
                <v-list-item-title>Nicolas Capparelli</v-list-item-title>
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

    methods: {
        navigateTo(route) {
            this.$router.push({
                name: route
            })
        }
    },

    data () {
        return {
            drawer: false,
            pageList: [
                {
                    name: "Home",
                    icon: "mdi-home"
                },

                {
                    name: "Resume",
                    icon: "mdi-file"
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
    }

    #toolbar {
        width: 100%;
    }

    @media screen and (min-width: 1200px){
        #toolbar {
            padding: 0 20%;
        }
    }

</style>
