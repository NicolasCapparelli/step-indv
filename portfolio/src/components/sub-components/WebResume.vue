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
import Education from './Education'
import Experience from './Experience'
import Projects from './Projects'
import Skills from './Skills'
import Awards from './Awards'

export default {
  name: 'Template',
  props: {
    msg: String
  },
  components: {
      Education,
      Experience,
      Skills,
      Projects,
      Awards
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

    #web-resume-component-container {
        width: 100%;
        height: 100%;
        padding: 10px 16px;
    }    

</style>