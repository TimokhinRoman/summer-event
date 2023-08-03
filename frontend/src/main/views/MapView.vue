<template>
  <div class="container">
    <div class="content p-fluid">
      <div class="sky"/>
      <div id="map" class="map">
        <font-awesome-icon v-for="point in points" :key="point.id"
                           icon="fa-solid fa-location-dot"
                           size="2xl"
                           :style="pointStyles(point)"
                           @click="selectPoint(point)"
        />
      </div>
      <template v-if="canSelect">
        <div class="mb-auto text-2xl font-bold">
          Выберите задание
        </div>
        <Button v-show="selectedPoint" label="Выбрать" class="mt-auto text-xl font-medium"
                @click="selectTask(selectedPoint.id)"/>
      </template>
      <template v-if="taskInProgress">
        <Button label="К заданию" class="mt-auto text-xl font-medium"
                @click="showTask"/>
      </template>
    </div>
  </div>

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
.sky {
  position: absolute;
  width: 1024px;
  height: 1024px;
  background: url("~@/assets/img/sky.png") center;
  z-index: -1;
}

.map {
  position: absolute;
  width: 1024px;
  height: 1024px;
  background: url("~@/assets/img/map.png") center;
  z-index: -2;
}
</style>
