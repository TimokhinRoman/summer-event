<template>
  <div style="position: absolute; width: 360px; height: 820px">
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
          console.log(response);
          this.points = response.data.points;
          this.canSelect = response.data.canSelect;

          if (!this.canSelect) {
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

      point.selected = !point.selected;

      if (point.selected) {
        this.selectedPoint = point;
      } else {
        this.selectedPoint = null;
      }
    },
    selectTask(id) {
      console.log(id);
      axios.post(`/api/task/${id}/select`)
      .then(response => {
        console.log(response);
        this.$router.push("/task/current");
      })
    },
    listenToTaskStart() {
      this.listeningToTaskStart = setInterval(() => {
        axios.get("/api/task/current").then(response => {
          let task = response.data;
          if (task) {
            this.cancelListeningToTaskStart();
            this.$router.push("/task/current");
          }
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
