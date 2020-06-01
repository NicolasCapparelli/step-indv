<template>
    <div id="connect-container">
        <span>Coming in week 3!</span>
    </div>
</template>

<script>
export default {
  name: 'Connect',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #connect-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        padding: 80px 16px 0px 16px;

        background-color: #292929;
    }

     @media screen and (min-width: 750px){
        #connect-container {
            padding: 80px 20% 0px 20%;
        }
     }
</style>
