<template>
    <div id="web-resume-component-container">
        
        <div id="education-container">
            <h2 class="section-title">EDUCATION</h2>

            <div class="experience-box">
                <v-avatar class="experience-box-avatar">
                    <img
                        src="https://cdn.vuetifyjs.com/images/john.jpg"
                        alt="Kennesaw State University"
                    >
                </v-avatar>            
                <div class="experience-box-content">
                    <h1 style="font-size: 18px">Kennesaw State University</h1>                        
                    <span style="font-size: 16px;">BS Computer Science, May 2022</span><br/>
                    <span style="font-size: 14px; color: gray;">3.5 GPA</span>

                    <ul style="margin: 1rem 0;">
                        <li>Programming Principles I &amp; II</li>
                        <li>Intro to Software Engineering</li>
                        <li>Intro to Database Programming</li>
                        <li>Computer Org &amp; Arch</li>
                        <li>Discrete Math</li>
                    </ul>
                </div>
            </div>
        </div>
        
        <v-divider></v-divider>
        
        <div id="experience-container">
            
            <h2 class="section-title">EXPERIENCE</h2>
            
            <div id="experience-list">
                
                <div 
                    class="experience-box"
                    v-for="(experience, index) in experienceList"
                    :key="experience.dates"
                >
                    <v-avatar class="experience-box-avatar">
                        <img
                            :src="experience.image"
                            :alt="experience.employer"
                        >
                    </v-avatar>

                    <div 
                        class="experience-box-content"                                            
                    >
                        <h1 style="font-size: 18px">{{experience.employer}}</h1>
                        <span style="font-size: 16px;">{{experience.title}}</span><br/>
                        <span style="font-size: 14px; color: gray;">{{experience.dates}}</span>

                        <ul style="margin: 1rem 0;">
                            <li
                                v-for="bullet in experience.bullets"
                                :key="bullet"
                                style="margin: 0.5rem 0;"
                            >
                            {{bullet}}
                            </li>
                        </ul>

                        <v-divider v-if="index < (experienceList.length - 1)"></v-divider>
                    </div>
                </div>

            </div>
        </div>

        <v-divider></v-divider>

        <div id="skills-container">
            
            <h2 class="section-title">SKILLS</h2>
            
            <div id="skills-list">
                <div class="skill-chip-outer">
                    <span style="color: #DBDBDB; font-weight: bold; font-size: 18px">Languages</span>
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
                    <span style="color: #DBDBDB; font-weight: bold; font-size: 18px">Technologies &amp; Frameworks</span>
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
                    <span style="color: #DBDBDB; font-weight: bold; font-size: 18px">Skills</span>
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
            
    </div>
</template>

<script>
export default {
  name: 'Template',
  props: {
    msg: String
  },
  data () {
      return {
          experienceList: [
              {
                  image: "https://cdn.vuetifyjs.com/images/john.jpg",
                  employer: "Google",
                  title: "STEP Intern",
                  dates: "May 2020 - August 2020",
                  bullets: [                      
                      "Lorem ipsum dolor sit, amet consectetur adipisicing elit",
                      "Quisquam quis sequi atque praesentium tempora dolore pariatur esse quidem explicabo",
                      "Consectetur modi iste quos assumenda distinctio, tenetur cum odit, temporibus libero"
                  ]
              },

              {
                  image: "https://cdn.vuetifyjs.com/images/john.jpg",
                  employer: "Stanford",
                  title: "Volunteer Section Leader",
                  dates: "April 2020 - May 2020",
                  bullets: [                      
                      "Lorem ipsum dolor sit, amet consectetur adipisicing elit",
                      "Quisquam quis sequi atque praesentium tempora dolore pariatur esse quidem explicabo",
                      "Consectetur modi iste quos assumenda distinctio, tenetur cum odit, temporibus libero",
                      "Consectetur modi iste quos assumenda distinctio, tenetur cum odit, temporibus libero"
                  ]
              }
          ],

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

    #web-resume-component-container {
        width: 100%;
        height: 100%;
        padding: 10px 16px;
    }
    
    #education-container {
        width: 100%;
        height: auto;        
    }

    #experience-container {
        width: 100%;
        height: auto;
    }

    .section-title {
        font-size: 20px;
        font-weight: normal;
        margin: 1rem 0;
        color: #919191;
    }

    #experience-list {
        width: 100%;
        height: auto;
    }

    .experience-box {
        width: 100%;
        height: auto;

        display: grid;
        grid-template-columns: min-content auto;
        column-gap: 5px;

        margin: 1rem 0;
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
