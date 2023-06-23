<template>
  <div :class="classes">
    <span class="p-float-label">
      <input-text :type="type" :id="name" class="p-inputtext-lg" v-model="value"
                  :class="{ 'p-invalid': errorMessage }"/>
      <label :for="name">{{ label }}</label>
    </span>
    <small class="p-error">{{ errorMessage }}</small>
  </div>
</template>

<script setup>
import InputText from 'primevue/inputtext';
import {defineProps} from 'vue';
import {useField} from 'vee-validate';

const props = defineProps({
  type: {
    type: String,
    required: true,
    default: "text"
  },
  name: {
    type: String,
    required: true,
  },
  label: {
    type: String,
    required: true,
  },
  classes: {
    type: String
  }
});

const {errorMessage, value} = useField(() => props.name);
</script>
