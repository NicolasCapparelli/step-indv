<template>
    <div id="project-container">
        <v-combobox
            item-color="#999999"
            color="#FFFFFF"
            style=" width: 100%; margin-top: 1rem;"
            v-model="chips"
            :items="items"
            chips
            clearable
            label="Filter by language, technology..."
            multiple
            single-line
            solo
            v-on:input="filterProjectCards"
        >
            <template v-slot:selection="{ attrs, item, select, selected, index }" style="background-color: #999999;">
                <v-chip
                    v-if="index <= numChipsDisplayed"
                    v-bind="attrs"
                    :input-value="selected"
                    close
                    @click="select"
                    @click:close="remove(item)"                    
                >
                    <strong>{{ item }}</strong>
                </v-chip>

                <span
                    v-if="index === (numChipsDisplayed + 1)"
                    class="grey--text caption"
                >
                    (+{{ chips.length - 1 }} others)
                </span>

            </template>
        </v-combobox>

        <div id="project-list">
            <ProjectCard
                v-for="project in visibleProjectList"
                :key="project.title"
                :title="project.title"
                :description="project.description"
                :githubLink="project.githubLink"
                :webLink="project.webLink"
                :technologyList="project.technologyList"
                :imageList="project.imageList"        
            />

        </div>
    </div>
</template>

<script>
import ProjectCard from './sub-components/ProjectCard'

const projectList = [

    {
        title: "Portfolio Website",
        description: "A simple website to display my information, resume, and projects as well as provide contact information.",
        githubLink: "https://github.com/NicolasCapparelli/step-indv/tree/master/portfolio",
        webLink: "capparelli-step-2020.appspot.com",
        technologyList: ["VueJS", "HTML", "CSS", "Javascript"],
        imageList: []
    },

    {
        title: "Instructor Toolkit",
        description: "Cloud based administration tool & management service aiming to support over 500 basic life support training centers and their instructors (work in progress)",
        githubLink: null,
        webLink: "https://instructortk.com",
        technologyList: ["React", "NodesJS", "HTML", "CSS", "Javascript", "Swift"],
        imageList: []
    },

    {
        title: "Project Valery",
        description: "A personal desktop voice assistant capable of controlling the computer, searching the web, and opening files in their respective applications",
        githubLink: "https://github.com/NicolasCapparelli/Project-Valery",
        webLink: null,
        technologyList: ["Python", "speech_recognition"],
        imageList: ["https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg"]
    },

    {
        title: "NC9v2",
        description: "A chat bot on the discord platform used for retrieving financial data through the iex stock Exchange api. At its peak, this bot served over 1,000 users across 5 different communities",
        githubLink: "https://github.com/NicolasCapparelli/NC9v2",
        webLink: null,
        technologyList: ["Python"],
        imageList: ["https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg"]
    },

    {
        title: "RemindMe",
        description: "A lightweight python module to create and notify users of reminders using asynchronous functions",
        githubLink: "https://github.com/NicolasCapparelli/NC9v2/blob/master/remindme.py",
        webLink: null,
        technologyList: ["Python"],
        imageList: ["https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg"]
    },

    {
        title: "Clip History",
        description: "A simple and lightweight clipboard history tool for windows",
        githubLink: "https://github.com/NicolasCapparelli/Clip-History",
        webLink: null,
        technologyList: ["C#"],
        imageList: ["https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg", "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg"]
    },
]

export default {
    name: 'Projects',

    mounted () {
        window.scrollTo(0,0);
    },

    data () {
        return {
            numChipsDisplayed: 0, // 0 indexed
            chips: [],
            items: ['Javascript', 'Python', 'C#', "VueJS", "React", "HTML", "CSS"],            

            visibleProjectList: projectList
        }
    },

    components: {
        ProjectCard
    },

    methods: {

        remove (item) {
            this.chips.splice(this.chips.indexOf(item), 1)
            this.chips = [...this.chips]
        },

        filterProjectCards (filterList){
            if (filterList.length < 1) {
                this.visibleProjectList = projectList                
            } 
            else {
                let fileteredArray = []
                for (let project of projectList) {
                    for (let tech of project.technologyList){
                        for (let filterTech of filterList){
                            if (filterTech.toLowerCase() === tech.toLowerCase()) {
                                fileteredArray.push(project)
                                break
                            }
                        }
                    }
                }                
                this.visibleProjectList = fileteredArray
            }

        }
    }
}
</script>

<style scoped>
    #project-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        padding: 80px 16px 0px 16px;

        background-color: #292929;
    }


    #project-list {
        height: 100%;
        width: 100%;
        margin-top: 1rem;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
    }

    @media screen and (min-width: 1200px){
        #project-container {
            padding: 80px 20% 0px 20%;
            justify-content: center;
        }

        #project-list {
            display: grid;
            grid-template-columns: 49% 49%;
            grid-auto-rows: min-content;
            align-items: start;
            justify-items: start;

            column-gap: 1rem;
        }
    }

</style>
