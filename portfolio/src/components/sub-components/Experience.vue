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
          <h1 class="employer">{{experience.employer}}</h1>
          <span class="exp-title">{{experience.title}}</span><br/>
          <span class="dates">{{experience.dates}}</span>

          <ul class="bullet-list">
            <li
              v-for="(bullet, index) in experience.bullets"
              :key="index"
              class="bullet"
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
          image: "/assets/google.svg",
          employer: "Google",
          title: "STEP Intern",
          dates: "May 2020 - August 2020",
          bullets: []
        },

        {
          image: "/assets/stanford.svg",
          employer: "Stanford",
          title: "Volunteer Section Leader",
          dates: "April 2020 - May 2020",
          bullets: [            
            "Participated in Stanford's Covid-19 online programming course as a section leader",
            "Taught programming principles to a group of 10 students on a weekly basis",
            "Section topics included: variables, loops, functions, arrays, image manipulation, and data science",
          ]
        },

        {
          image: "/assets/motorola-fixed.svg",
          employer: "Motorla Solutions",
          title: "Software Engineering Intern",
          dates: "May 2019 - August 2019",
          bullets: [            
            "Created fault alerts on radios that caught over 50 critical bugs and failures before reaching production",
            "Developed Python scripts to automate the transfer of logs and statistics between 3 internal company databases",
            "Worked across 4 teams to design and implement an error logging database using Azure Cloud and ElasticSearch",
            "Created and executed over 60 unit tests to detect flaws in proprietary software modules"
          ]
        },

        {
          image: "/assets/contractor.svg",
          employer: "Independent Software Contractor",
          title: "Software Developer",
          dates: "November 2016 - May 2019",
          bullets: [            
            "Created and utilized an incremental project management model to maintain constant contact with clients and their needs",
            "Designed and implemented a system to synchronize online and offline user data across multiple devices",
            "Designed and implemented a central hub for commanding and monitoring social media advertising bots",
            "Implemented a multi-tier recurring payment system using Stripe"
          ]
        },

        {
          image: "/assets/fulton.png",
          employer: "Fulton County Schools",
          title: "Network Engineer Shadow",
          dates: "May 2017 - July 2017",
          bullets: [            
            "Worked alongside professional network engineers to set up and maintain physical network servers across 15 schools",
            "Monitored and recorded network performance across all Fulton schools using enterprise IT Management Software (Solarwinds)",
            "Participated in large scale deployment of factory images for over 1,000 devices",
          ]
        }
      ]     
    }
  }
}
</script>

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
    column-gap: 12px;

    margin: 1rem 0;
  }

  .employer {
    font-size: 18px;
  }

  .exp-title {
    font-size: 16px;
  }

  .dates {
    font-size: 14px; 
    color: gray;
  }

  .bullet-list {
    margin: 1rem 0;
  }

  .bullet {
    margin: .5rem 0;
  }

</style>
