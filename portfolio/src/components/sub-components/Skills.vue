<template>
  <div id="skills-container">

    <h2 class="section-title">SKILLS</h2>
    
    <div id="skills-list">
      <div class="skill-chip-outer">
        <span class="chip-container-title" style="">Languages</span>
        <div class="skill-chip-inner">
          <span 
            class="chip"
            v-for="language in languageList"
            :key="language"
          >
            {{language}}
          </span>
        </div>

      </div>

      <div class="skill-chip-outer">
        <span class="chip-container-title">Technologies &amp; Frameworks</span>
        <div class="skill-chip-inner">
          <span 
            class="chip"
            v-for="technology in technologyList"
            :key="technology"
          >
            {{technology}}
          </span>
        </div>

      </div>

      <div class="skill-chip-outer">
        <span class="chip-container-title">Skills</span>
        <div class="skill-chip-inner" id="skills-inner">
          <span 
            class="chip"
            v-for="skill in skillList"
            :key="skill"
          >
            {{skill}}
          </span>
        </div>
      </div>        

    </div>
  </div>
</template>

<script>
export default {
  name: 'Template',
  data() {
    return {
      languageList: [
        "Javascript",
        "Python",
        "Java",
        "Swift",
        "Kotlin",
        "C#"

      ],

      technologyList: [
        "Git",
        "Github",
        "Bitbucket",
        "GIRA",        
        "VueJS",
        "ReactJS",        
        "NodeJS",
        "Android",
        "IOS",
        "MongoDB",
        "Elastic",
        "Azure",
        "Google Cloud"
      ],

      skillList: [
        "Software Architecture",
        "UI/UX Design",
        "Unit Testing",
        "Web Dev",
        "App Dev",
        "Backend Dev"
      ]
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

  #skill-list {
    overflow: hidden;
    width: 100%;
  }

  #skill-list {
    overflow: hidden;
    width: 100%;
  }

  .skill-chip-outer {
    display: flex;
    flex-direction: column;
    margin: 2rem 0;
  }

  .chip-container-title {
    color: #dbdbdb;
    font-weight: bold; 
    font-size: 18px;
  }

  .skill-chip-inner {
    display: grid;
    height: 100%;
    width: 100%;
    grid-template-columns: max-content max-content max-content;
    column-gap: 10px;
    row-gap: 10px;

    margin-top: 0.5rem;
  }  

  #skills-inner {
    grid-template-columns: max-content max-content;
  }

  .chip {

    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;

    min-width: 100px;
    width: fit-content;
    padding: 6px 10px; 
    height: auto; 
    border: solid 1px #C4C4C4; 
    border-radius: 20px;

    color: #C4C4C4;
  }

  @media screen and (max-width: 365px){
    .skill-chip-inner {
      grid-template-columns: repeat(2, max-content);
    }

    #skills-inner {
      grid-template-columns: repeat(1, max-content);
    }
  }

  @media screen and (min-width: 530px){
    .skill-chip-inner {
      grid-template-columns: repeat(4, max-content);
    }

    #skills-inner {
      grid-template-columns: repeat(3, max-content);
    }
  }

  @media screen and (min-width: 750px){
    .skill-chip-inner {
      grid-template-columns: repeat(6, max-content);
    }

    #skills-inner {
      grid-template-columns: repeat(5, max-content);
    }
  }

  @media screen and (min-width: 900px){
    .skill-chip-inner {
      grid-template-columns: repeat(8, max-content);
    }

    #skills-inner {
      grid-template-columns: repeat(7, max-content);
    }
  }

</style>
