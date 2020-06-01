<template>
    <div id="about-container">

        <div class="about-section">
            <h1 class="section-title">Info</h1>
            <ul>
                <li>🇺🇸 U.S. Citizen</li>
                <li>20 Years Old</li>
                <li>Class of 2022</li>
                <li>Pursuing B.S. in Computer Science</li>
                <li>Kennesaw State University</li>
            </ul>

        </div>

        <div class="about-section">
            <h1 class="section-title">About</h1>
            <p style="margin-top: 0.5rem;">Ever since I was 15, I've loved the process of turning ideas into reality; so naturally, I gained great interest in Software Development and Computer Science.</p>
            <p style="margin-top: 0.5rem;">Since then, I've worked on several personal projects and freelance work in a variety of languages &amp; frameworks to expand my knowledge and gather experience.</p>
            <p style="margin-top: 0.5rem;">From native mobile apps to voice assistants, I have a cast a wide net in order to diversify my skillset and tackle new challenges. Most recently I have started taking part in internships to work with large teams and learn from those around me.</p>

        </div>

        <div class="about-section">
            <h1 class="section-title">Interests</h1>
            <ul>
                <li>Software Engineering</li>
                <li>Finance</li>
                <li>UFC</li>
                <li>Football</li>
            </ul>
        </div>

        <div class="about-section">
            <h1 class="section-title">Location</h1>
            <p>I currently reside in Georgia, United States</p>
            <div id="globe"></div>

        </div>

    </div>
</template>

<script>
import gmapsInit from '../utils/gmaps'

export default {
    name: 'About',

    async mounted() {
        try {
            const google = await gmapsInit();
            const geocoder = new google.maps.Geocoder();
            const map = new google.maps.Map(document.getElementById('globe'));

            geocoder.geocode({ address: 'Austria' }, (results, status) => {
            if (status !== 'OK' || !results[0]) {
                throw new Error(status);
            }

            map.setCenter(results[0].geometry.location);
            map.fitBounds(results[0].geometry.viewport);
            });
        } catch (error) {
            console.error(error);
        }
    },
    
    props: {
        msg: String
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #about-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 80px 16px 0px 16px;

        background-color: #292929;
    }

    .about-section {
        width: 100%;
        height: fit-content;
        
        padding: 16px;
        margin-top: 1rem;

        border: solid 1px #dbdbdb;
        border-radius: 10px;
    }

     @media screen and (min-width: 750px){
        #about-container {
            padding: 80px 20% 0px 20%;
        }
     }

</style>
