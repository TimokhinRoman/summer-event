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

<style>
.poem {
  margin: auto;
  font-family: Baskerville;
  font-size: 14px;
  text-align: left;
}

.poem p {
  margin-bottom: 15px;
  line-height: 1.45em;
}

.poem p:first-child {
  margin-top: 0;
}

.poem p:first-child:first-letter {
  float: left;
  font-size: 40px;
  margin: 12px 5px 0 0;
}

.poem p:first-line {
  font-variant: small-caps;
  letter-spacing: 1px;
}

.poem p:last-child {
  margin-bottom: 0;
}
</style>
