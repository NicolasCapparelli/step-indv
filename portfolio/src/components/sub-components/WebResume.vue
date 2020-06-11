<template>
  <div id="web-resume-component-container">
    <Education/>    
    
    <v-divider></v-divider>  
    
    <Experience/>
    
    <v-divider></v-divider>
    
    <Projects/>

    <v-divider></v-divider>    

    <Skills/>

    <v-divider></v-divider>

    <Awards/>

  </div>
</template>

<script>
import Education from './Education.vue'
import Experience from './Experience.vue'
import Projects from './Projects.vue'
import Skills from './Skills.vue'
import Awards from './Awards.vue'

export default {
  name: 'Template',
  components: {
    Education,
    Experience,
    Skills,
    Projects,
    Awards
  }
}
</script>

<style scoped>
  #web-resume-component-container {
    width: 100%;
    height: 100%;
    padding: 10px 16px;
  }  

</style>