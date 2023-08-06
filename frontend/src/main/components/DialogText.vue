<template>
  <div v-html="text"/>
</template>

<script>
export default {
  name: "DialogText",
  inject: ['dialogRef'],
  data() {
    return {
      text: ""
    }
  },
  created() {
    this.text = this.dialogRef.data.text;
  }
}
</script>
