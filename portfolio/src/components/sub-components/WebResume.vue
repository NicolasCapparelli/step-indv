<template>
    <div id="web-resume-component-container">
        <Education/>        
        
        <v-divider></v-divider>    
        
        <Experience/>
        
        <v-divider></v-divider>
        
        <Skills/>
    </div>
</template>

<script>
import Education from './Education'
import Experience from './Experience'
import Skills from './Skills'

export default {
  name: 'Template',
  props: {
    msg: String
  },
  components: {
      Education,
      Experience,
      Skills
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
