<template>
  <div class="form-group row">
    <label :for="name">{{ label }}</label>
    <div class="col-sm-10">
      <select class="form-select"
              v-model="value"
              :id="name"
              :name="name">
        <option v-for="item in items" :value="item.id" :key="item.id" :selected="item.id === selectedItem">{{item.view}}</option>
      </select>
    </div>
  </div>
</template>

<script>
export default {
  name: "SelectField",
  props: ["modelValue", "name", "label", "items", "selectedItem"],
  emits: ['update:modelValue'],
  computed: {
    value: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      }
    }
  }
}
</script>

<style scoped>

</style>
