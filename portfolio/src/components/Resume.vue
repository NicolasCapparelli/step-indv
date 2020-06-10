<template>
  <div class="page-container" id="resume-container">
    <v-tabs 
        v-model="tab"
        id="p-tabs-style"
        background-color="transparent" 
        grow
    >
      <v-tab
          v-for="item in items"
          :key="item.tab"
      >
          {{ item.tab }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab" id="resume-tab-item-container" >      
      <v-tab-item class="resume-tab-item">
        <WebResume/>                
      </v-tab-item>

      <v-tab-item class="resume-tab-item">
        <object width="100%" height="90%" type="application/pdf" data="/nicolas-capparelli-resume.pdf">
          <div id="pdf-unavail-container">
            <p id="pdf-unavail-desc">PDF Preview is not supported on mobile, press the button to download</p>
            <v-btn 
                v-on:click="downloadPDF()"
                id="pdf-unavail-button"
            >
              Download PDF
            </v-btn>
          </div>
        </object>            
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import WebResume from "./sub-components/WebResume.vue";

export default {
  name: 'Resume',
  components: {
    WebResume
  },
  props: {
    msg: String
  },

  methods: {
    downloadPDF() {
      window.open("/assets/resume.pdf");
    }
  },

  data () {
    return {
      tab: null,
      items: [
        { tab: 'WEB'},
        { tab: 'PDF'},
      ],
    }
  }
}
</script>

<style scoped>

  #resume-container {
    padding: 80px 0px 0px 0px;
  }

  #p-tabs-style {
    width: 100%;
  }

  #resume-tab-item-container {
    height: 100%; 
    width: 100%;
    background-color: #292929;
  }

  .resume-tab-item {
    height: 100%;
    width: 100%;
    background-color: #292929;
  }

  #pdf-unavail-container {
    height: 100%; 
    width: 90%; 
    display: flex; 
    flex-direction: column; 
    align-items: center; 
    justify-content: center; 
    margin: auto;
  }

  #pdf-unavail-desc {
    font-size: 20px;
    text-align: center;
  }

  #pdf-unavail-button {
    margin-bottom: 1rem; 
    background-color: #424242;
  }

</style>
