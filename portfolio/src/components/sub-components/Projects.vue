<template>
  <div>
    <h2 class="section-title">PROJECTS</h2>
    <v-btn 
      v-on:click="goToProjects()"
      id="projects-button"
    >
      Projects Page
    </v-btn>
  </div>
</template>

<script>
export default {
  name: 'Projects',
  props: {
  msg: String
  },
  methods: {
    goToProjects() {
      this.$router.push({
        name: "Projects"
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .section-title {
    font-size: 20px;
    font-weight: normal;
    margin: 1rem 0;
    color: #919191;
  }

  #projects-button {
    margin-bottom: 1rem;     
    background-color: #424242;
  }
</style>
