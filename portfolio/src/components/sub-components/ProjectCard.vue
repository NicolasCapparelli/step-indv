<template>
  <div class="project-card-container">
    <div class="project-content">
      <div class="project-header">
        <h1 class="project-title">{{title}}</h1>
        <p>{{description}}</p>
      </div>
      
      <div class="project-footer">        
        <div class="project-icon-container">
          
          <v-btn 
            v-if="webLink" 
            icon 
            small 
            v-on:click="openWebLink()"
          >
            <v-icon>mdi-web</v-icon>
          </v-btn>
          
          <a 
            v-if="githubLink"
            v-ripple
            class="github-link"
            :href="githubLink"            
          >
            <img
              src="/assets/GitHub.svg"
              alt="Github"
              class="github-logo"
            />
          </a>          
        </div>
        
      
        <v-divider class="pc-divider"></v-divider>
        
        <div class="project-chip-container">
          <span 
            class="chip"
            v-for="technology in technologyList"
            :key="technology"
          >
            {{technology}}
          </span>
        </div>
      </div>
    </div>

    <div 
      v-if="imageList.length > 0"
      class="project-expansion-container"
    >
      
      <div class="expanded-slot" v-if="isExpanded">
        <v-carousel 
          height="100%"
          :show-arrows="false"
        >
          <v-carousel-item
          v-for="(image,i) in imageList"
          :key="i"
          :src="image"
          ></v-carousel-item>
        </v-carousel>
      </div>
      
      <v-btn icon large v-on:click="expandCard()">
        <v-icon>{{arrowIcon}}</v-icon>
      </v-btn>
    </div>

  </div>
</template>

<script>
export default {
  name: 'ProjectCard',
  props: {
    title: String,
    description: String,
    githubLink: String,
    webLink: String,
    technologyList: Array,
    imageList: Array
  },

  data (){
    return {
      isExpanded: false,
      arrowIcon: 'mdi-chevron-down'
    }
  },

  methods: {
    expandCard() {
      this.isExpanded = !this.isExpanded;
      this.arrowIcon = this.isExpanded ? 'mdi-chevron-up' : 'mdi-chevron-down';
    },

    openWebLink() {
      window.open(this.webLink);
    }
  },
    
}
</script>

<style scoped>
  .project-card-container {
    height: fit-content;
    width: 100%;    
    background-color: #424242;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;

    border-radius: 10px;
    box-shadow: 0 3px 4px 0 rgba(0,0,0,0.14), 0 3px 3px -2px rgba(0,0,0,0.12), 0 1px 8px 0 rgba(0,0,0,0.20);

    -webkit-transition: height 1s; 
    -moz-transition: height 1s; 
    -ms-transition: height 1s; 
    -o-transition: height 1s; 
    transition: height 1s;  

    margin-bottom: 2rem;
  }

  .project-content {
    height: auto;
    width: 100%;
    padding: 10px 16px 10px 16px;

    border-radius: 10px;
    box-shadow: 0 3px 4px 0 rgba(0,0,0,0.14), 0 3px 3px -2px rgba(0,0,0,0.12), 0 1px 8px 0 rgba(0,0,0,0.20);
  }

  .project-header {
    height: auto;
    width: 100%;
  }

  .project-title {
    font-size: 22px;
  }

  .project-footer {
    height: auto;
    width: 100%;

    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: flex-end;    
  }

  .project-icon-container {
    height: auto;
    width: 100%;    
    
    display: flex;
    align-items: center;
    justify-content: flex-end;
  }

  .github-link {
    height: 28px; 
    width: 28px; 
    border-radius: 28px;
  }

  .github-logo {
    height: 28px; 
    width: 28px;
  }

  .pc-divider {
    height: 1px; 
    width: 100%; 
    background-color: gray; 
    margin: .5rem 0 .7rem 0;
  }

  .project-chip-container {
    height: auto;
    width: 100%;

    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: flex-start;

    overflow: scroll;
  }

  .project-expansion-container {
    width: 100%; 
    height: auto;
    
    display: flex; 
    flex-direction: column;
    align-items: center; 
    justify-content: center; 
  }

  .expanded-slot {
    height: 350px; 
    width: 100%; 
    padding: 16px;
  }

  .chip {

    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;

    font-size: 14px;
    min-width: 80px;
    width: fit-content;
    padding: 4px 6px; 
    height: auto; 
    border: solid 1px #C4C4C4; 
    border-radius: 20px;

    color: #C4C4C4;

    margin-right: 5px;
  }
  
</style>
