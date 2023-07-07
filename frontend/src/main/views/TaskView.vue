<template>
  <template v-if="task">
    <div class="mb-auto text-2xl font-bold">
      {{task.name}}
    </div>
  </template>
  <div v-else>
    loading...
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TaskView",
  data() {
    return {
      task: null
    }
  },
  created() {
    this.loadTask();
  },
  methods: {
    loadTask() {

      axios.get(`/api/task/current`)
        .then(response => {
          console.log(response);
          this.task = response.data;
        })
    }
  }
}
</script>

<style scoped>

</style>
