<template>
  <div class="container">
    <div class="content p-fluid">
      <div id="map" style="position: absolute; width: 360px; height: 820px">
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
</template>

<script>
import axios from "axios";

export default {
  name: "MapView",
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
      if (!this.canSelect) return;

      if (this.selectedPoint && this.selectedPoint !== point) {
        this.selectedPoint.selected = false;
      }

      point.selected = !point.selected;

      if (point.selected) {
        this.selectedPoint = point;
      } else {
        this.selectedPoint = null;
      }
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

</style>
