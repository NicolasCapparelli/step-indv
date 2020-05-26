<template>
    <div id="home-container">
        <div id="landing-text">
            <h1>Lorem ipsum dolor sit amet</h1>
            <p>Repellat exercitationem totam quidem nemo dicta, ducimus, fugiat dolor tenetur sed obcaecati ipsum consectetur? Reiciendis explicabo.</p>
        </div>

        <div id="navigation-square-container">
            <v-sheet 
                v-for="navSquare in navSquareList"
                :key="navSquare.title"
                class="navigation-square" 
                elevation="2"
                v-on:click="squareClickedHandler(navSquare.title)"
                >
                <img :src="navSquare.img" alt="image">
                <h2>{{navSquare.title}}</h2>
            </v-sheet>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Home',
    props: {
    msg: String
    },
    methods: {

        // Navigates to respective route when a navigation square is clicked
        squareClickedHandler(navSquareTitle) {
            this.$router.push({
                name: navSquareTitle
            })
        }
        
    },
    data () {
        return {
            navSquareList: [
                {
                    title: "Resume",
                    img: "/assets/Resume.svg"
                },
                
                {
                    title: "Projects",
                    img: "/assets/Projects.svg"
                },
                
                {
                    title: "About",
                    img: "/assets/About.svg"
                },
                
                {
                    title: "Connect",
                    img: "/assets/Connect.svg"
                }
            ]
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #home-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 16px;

        background-color: transparent;
    }

    #landing-text {
        text-align: center;
        margin-bottom: 20px;
    }

    #navigation-square-container {
        height: auto;
        width: 100%;
        display: grid;
        grid-template-columns: auto auto;
        grid-gap: 10px;
    }

    .navigation-square {
        height: 200px;
        width: 100%;   
        
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-end;
        padding: 10% 0;
        
        background-color: #424242
    }

    @media screen and (min-width: 1200px){
        #home-container {
            padding: 0 20%;
        }
    }

</style>
