<template>
  <div id="app">
    <v-app>
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons" rel="stylesheet">
        <NavBar/>
        
        <v-content>
          <router-view :key="$route.fullPath"/>
        </v-content>
    </v-app>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'

export default {
  name: 'App',
  components: {
    NavBar
  }
}
</script>

<style>

  @import url(https://fonts.googleapis.com/css?family=Roboto&display=swap);

  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    -ms-overflow-style: none;
  }

  * ::-webkit-scrollbar {
    display: none;
  }

  body {
    font-family: 'Roboto', sans-serif;
    line-height: 1.4;
  }

  #app {
    background-color: #292929;
    min-height: 100vh;
    width: 100vw;
    overflow: hidden;
  }

  .page-container {
    height: 100%;
    width: 100%;
    max-width: 900px;
    margin: auto;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;

    padding: 100px 16px 16px 16px;
    background-color: #292929;
  }

  @media screen and (min-width: 1200px){
    .page-container {
        padding: 100px 0 0 0;
    }
  }
</style>
