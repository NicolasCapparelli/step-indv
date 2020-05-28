<template>
    <div id="resume-container">
        <v-tabs 
            v-model="tab"
            style="width: 100%"     
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
                <v-card flat>
                    <v-card-text>PDF</v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs-items>
    </div>
</template>

<script>
import WebResume from "./sub-components/WebResume"

export default {
  name: 'Template',
  components: {
      WebResume
  },
  props: {
    msg: String
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
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 80px 0px 0px 0px;

        background-color: #292929;
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

    @media screen and (min-width: 1200px){
        #resume-container {
            padding: 80px 20% 0px 20%;
            justify-content: center;
        }
    }

</style>
