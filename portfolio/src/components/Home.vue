<template>
    <div id="home-container">
        <div id="landing-text">
            <h1>Hi. I'm Nico.</h1>
            <p>I'm a Computer Science student with a deep passion for software engineering. Checkout some of my work below and feel free to get in touch</p>
        </div>

        <div id="navigation-square-container">
            <v-sheet 
                v-ripple
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
            
            this.$root.$emit('pchange', navSquareTitle)
            
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

        padding: 100px 16px 16px 16px;

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
        
        background-color: #424242;
        cursor: pointer;
    }

    @media screen and (min-width: 1200px){
        #home-container {
            padding: 100px 20% 5% 20%;
        }

        #landing-text {        
            margin-bottom: 60px;
        }

        #landing-text h1 {        
            font-size: 36px;
        }

        #landing-text p {        
            font-size: 26px;
        }

        #navigation-square-container {
            height: 100%;
            width: 60%;
            grid-template-columns: auto auto;
        }

        .navigation-square {
            height: 100%;
            width: 100%;
            padding: 16px;

            display: flex;
            align-items: center;
            justify-content: space-evenly;
        }

        .navigation-square img {
            width: 40%;
        }
    }

</style>
