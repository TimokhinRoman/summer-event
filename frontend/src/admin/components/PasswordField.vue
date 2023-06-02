<template>
  <div class="mb-4">
    <span class="p-float-label">
      <password :id="name" class="p-inputtext-lg" v-model="value" :feedback="false" toggleMask
                :class="{ 'p-invalid': errorMessage }"/>
      <label :for="name">{{ label }}</label>
    </span>
    <small class="p-error">{{ errorMessage }}</small>
  </div>
</template>

<script setup>
import Password from 'primevue/password';
import {defineProps} from 'vue';
import {useField} from 'vee-validate';

const props = defineProps({
  name: {
    type: String,
    required: true,
  },
  label: {
    type: String,
    required: true,
  },
});

const {errorMessage, value} = useField(() => props.name);
</script>
