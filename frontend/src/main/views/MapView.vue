<template>

  <!--div class="sky"/-->
  <!--div class="full-fixed">
    <img src="@/assets/img/map.png" alt=""/>
    <div class="map-points">
      <font-awesome-icon v-for="point in points" :key="point.id"
                         icon="fa-solid fa-location-dot"
                         size="2xl"
                         :style="pointStyles(point)"
                         @click="selectPoint(point)"
      />
    </div>
  </div-->

  <div ref="map" class="map-container">
    <img src="@/assets/img/map.png" alt="" @load="centerMap"/>
  </div>

  <div class="sky"/>

  <!--template v-if="canSelect">
    <div class="mb-auto text-2xl font-bold">
      Выберите задание
    </div>
  </template>

  <div class="bottom-fixed">
    <Button v-if="canSelect" v-show="selectedPoint" label="Выбрать" class="text-xl font-medium"
            @click="selectTask(selectedPoint.id)"/>

    <Button v-if="taskInProgress" label="К заданию" class="text-xl font-medium"
            @click="showTask"/>
  </div-->

  <DynamicDialog/>
</template>

<script>
import axios from "axios";
import DynamicDialog from 'primevue/dynamicdialog';
import DialogText from "@/main/components/DialogText";

export default {
  name: "MapView",
  components: {DynamicDialog, DialogText},
  data() {
    return {
      points: [],
      selectedPoint: null,
      canSelect: false,
      taskInProgress: false,
      listeningToTaskStart: null
    }
  },
  created() {
    this.loadMap();
  },
  methods: {
    loadMap() {
      axios.get("/api/map")
        .then(response => {
          this.points = response.data.points;
          this.canSelect = response.data.canSelect;
          this.taskInProgress = response.data.taskInProgress;

          if (this.taskInProgress) {
            this.selectedPoint = this.points[0];
          }

          if (!this.canSelect && !this.taskInProgress) {
            this.listenToTaskStart();
          }
        })
    },
    centerMap() {
      const map = this.$refs.map;
      map.scrollLeft = (map.scrollWidth - map.clientWidth) / 2;
      map.scrollTop = (map.scrollHeight - map.clientHeight) / 2;
    },
    pointStyles(point) {
      const styles = {
        position: 'absolute',
        left: point.x + 'px',
        top: point.y + 'px'
      }
      if (point.selected) {
        styles.transform = 'scale(1.5)';
        styles.color = '#8dd0ff';
      }
      return styles;
    },
    selectPoint(point) {
      if (this.selectedPoint && this.selectedPoint !== point) {
        this.selectedPoint.selected = false;
      }

      //point.selected = !point.selected;
      point.selected = true;

      if (point.selected) {
        this.selectedPoint = point;
        this.showTaskPreview(point);
      } else {
        this.selectedPoint = null;
      }
    },
    showTaskPreview(point) {
      this.$dialog.open(DialogText, {
        props: {
          modal: true,
          style: {
            width: '50vw',
          },
          breakpoints: {
            '960px': '75vw',
            '640px': '90vw'
          },
        },
        data: {
          text: point.text
        }
      });
    },
    selectTask(id) {
      axios.post(`/api/task/${id}/select`)
        .then(response => {
          this.$router.push("/task");
        })
    },
    showTask() {
      this.$router.push("/task");
    },
    listenToTaskStart() {
      this.listeningToTaskStart = setInterval(() => {
        axios.get("/api/event").then(response => {
          let event = response.data;
          if (event) {
            switch (event.status) {
              case "TASK_SELECTION":
                return;
              case "TASK_IN_PROGRESS":
                this.$router.push("/task");
                break;
              default:
                this.$router.push("/");
                break;
            }
          } else {
            this.$router.push("/");
          }
          this.cancelListeningToTaskStart();
        })
      }, 2000)
    },
    cancelListeningToTaskStart() {
      if (this.listeningToTaskStart) {
        clearInterval(this.listeningToTaskStart);
        this.listeningToTaskStart = null;
      }
    }
  }
}
</script>

<style scoped>

@media screen and (orientation:portrait) {
}

@media screen and (orientation:landscape) {
}

.map-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: calc(100% - 1px);
  max-height: calc(100% - 1px);
  overflow: auto;
}

.full-fixed {
  position:fixed;
  width: 100dvw;
  height: 100dvh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sky {
  position: fixed;
  width: 100dvw;
  height: 100dvh;
  background: url("~@/assets/img/sky.png") no-repeat fixed center;
  pointer-events: none;
}

.map {
  position: absolute;
  width: 611px;
  height: 700px;
}

.map-image {
  width: 100%;
  height: 100%;
  background: url("~@/assets/img/map.png") center;
  z-index: -2;
}
</style>
