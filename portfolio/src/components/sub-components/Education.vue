<template>
    <div id="education-container">
        <h2 class="section-title">EDUCATION</h2>

        <div class="education-box">
            <v-avatar
                tile
            >
                <img
                    src="/assets/KSU.svg"
                    alt="Kennesaw State University"
                >
            </v-avatar>            
            <div>
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
</template>

<script>
export default {
  name: 'Education',
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #education-container {
        width: 100%;
        height: auto;        
    }

    .section-title {
        font-size: 20px;
        font-weight: normal;
        margin: 1rem 0;
        color: #919191;
    }

    .education-box {
        width: 100%;
        height: auto;

        display: grid;
        grid-template-columns: min-content auto;
        column-gap: 12px;

        margin: 1rem 0;
    }

</style>
