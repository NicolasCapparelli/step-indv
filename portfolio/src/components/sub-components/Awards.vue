<template>

  <div id="awards-container">
    <h2 class="section-title">AWARDS</h2>

    <div id="award-list">
      <div
          class="award-box"
          v-for="(award, index) in awardList"
          :key="index"
      >
        <v-avatar tile>
          <img
              :src="award.image"
              :alt="award.giver"
          >
        </v-avatar>

        <div>
          <h1 class="award-title">{{award.title}}</h1>
          <span class="award-giver">{{award.giver}}</span><br/>
          <span class="award-date">{{award.date}}</span>

          <p class="award-desc">{{award.description}}</p>

          <v-divider v-if="index < (awardList.length - 1)"></v-divider>                
        </div>
      </div>                    
    </div>
  </div>

</template>

<script>
export default {
  name: 'Awards',
  data () {
    return {
      awardList: [
        {
          image: "/assets/trophy.svg",
          title: "1st Place Hackathon Winner ",
          giver: "Kennesaw State Universtiy | Equifax",
          date: "September 2019",                    
          description: "Placed 1st on Equifax's security challenge by applying knowledge of multiprocessing & multithreading in Python and C++ to optimize a password cracking algorithm"
        },
        
        {
          image: "/assets/scholar.svg",
          title: "HSF Scholar",
          giver: "Hispanic Scholarship Fund",
          date: "September 2019",                    
          description: "\"One of approximately 10,000 of the best and brightest Hispanic students in the country who are eligible to receive HSF's Scholar Support Services\""
        },

        {
          image: "/assets/scholar.svg",
          title: "HOPE Scholarship",
          giver: "State of Georgia",
          date: "May 2018",                    
          description: "\"The HOPE Scholarship program is for students who have demonstrated academic achievement and are seeking a college degree.\""
        }
      ]
    }
  }
}
</script>

<style scoped>
  .section-title {
    font-size: 20px;
    font-weight: normal;
    margin: 1rem 0;
    color: #919191;
  }

  #award-list {
    width: 100%;
    height: auto;
  }
  
  .award-box {
    width: 100%;
    height: auto;

    display: grid;
    grid-template-columns: min-content auto;
    column-gap: 12px;

    margin: 1rem 0;
  }

  .award-title {
    font-size: 18px;
  }

  .award-giver {
    font-size: 16px;
  }

  .award-date {
    font-size: 14px;
    color: gray;
  }

  .award-desc {
    margin: 1rem 0;
  }

</style>
