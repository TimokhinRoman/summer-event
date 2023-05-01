<template>
  <div class="form-group row">
    <label :for="name">{{ label }}</label>
    <div class="col-sm-10">
      <textarea
        class="form-control"
        v-model="value"
        :id="name"
        :name="name"
        :placeholder="placeholder"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "InputTextAreaField",
  props: ["modelValue", "name", "label", "placeholder"],
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
