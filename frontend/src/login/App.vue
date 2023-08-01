<template>
  <div class="background"/>
  <div class="container">
    <div class="content p-fluid">
      <form class="w-full" @submit="onSubmit">
        <div class="form-container">
          <input-text-field type="email" name="email" label="Email" classes="mb-4"/>
          <password-field name="password" label="Пароль"/>
        </div>
        <div class="button-container mt-3">
          <Button type="submit" label="Войти" class="text-xl font-medium"/>
        </div>
        <small class="p-error" v-if="error">{{ error }}</small>
      </form>
      <a class="link" href="/register">Зарегистрироваться</a>
    </div>
  </div>
</template>

<script setup>
import InputTextField from "@/admin/components/InputTextField";
import PasswordField from "@/admin/components/PasswordField";
import Button from "primevue/button";
import {ref} from 'vue'
import {useForm} from 'vee-validate';
import {object, string} from 'yup';
import axios from "axios";


const schema = object({
  email: string()
    .required("email обязателен для заполнения")
    .email("email указан неверно")
    .max(100, "email не должен превышать 100 символов"),
  password: string()
    .required("пароль обязателен для заполнения")
    .min(5, "пароль должен быть не менее 5 символов")
    .max(100, "пароль не должен превышать 100 символов")
})

const {handleSubmit} = useForm({
  validationSchema: schema
});

const error = ref("");

const onSubmit = handleSubmit((values) => {
  error.value = "";

  axios.post("/login", values, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  }).then(response => {
    console.log(response);
    const user = response.data;
    if (user.admin) {
      window.location.href = "/admin#/events";
    } else {
      window.location.href = "/";
    }
  }).catch(e => {
    error.value = e.response.data.error
  });
});
</script>

<style>
@import "../assets/app.css";

.form-container a {
  color: #868C9B;
  margin-bottom: 20px;
  font-size: 11px;
}

.button-container > span {
  display: flex;
  font-size: 11px;
  color: #868C9B;
}

.button-container > span a {
  cursor: pointer;
  margin-left: 0.25rem;
}

.link {
  text-decoration: none;
  text-align: center;
  color: #8dd0ff;
  margin-top: 0.5rem;
}
</style>
