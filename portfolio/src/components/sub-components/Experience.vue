<template>
    <div id="experience-container">
        
        <h2 class="section-title">EXPERIENCE</h2>
        
        <div id="experience-list">
            
            <div 
                class="experience-box"
                v-for="(experience, index) in experienceList"
                :key="experience.dates"
            >
                <v-avatar>
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
                            v-for="(bullet, index) in experience.bullets"
                            :key="index"
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
</template>

<script>
export default {
    name: 'Experience',
    props: {
    msg: String
    },
    data() {
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
          ]       
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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

</style>
