<template>
  <div class="login-body">
    <div class="login-panel p-fluid">
      <div class="flex flex-column">
        <form @submit="onSubmit">
          <div class="form-container mb-4">
            <input-text-field name="name" label="Имя"/>
            <input-text-field type="email" name="email" label="Email"/>
            <password-field name="password" label="Пароль"/>
            <password-field name="passwordConfirmation" label="Ещё раз пароль"/>
          </div>
          <div class="button-container">
            <Button type="submit" label="Зарегистрироваться" class="text-xl font-medium"/>
          </div>
          <small class="p-error" v-if="error">{{ error }}</small>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import InputTextField from "@/admin/components/InputTextField";
import PasswordField from "@/admin/components/PasswordField";
import Button from "primevue/button";
import {ref} from 'vue'
import {useForm} from 'vee-validate';
import {object, string, ref as yref} from 'yup';
import axios from "axios";


const schema = object({
  name: string()
    .required("имя обязательно для заполнения")
    .max(100, "имя не должно превышать 100 символов"),
  email: string()
    .required("email обязателен для заполнения")
    .email("email указан неверно")
    .max(100, "email не должен превышать 100 символов"),
  password: string()
    .required("пароль обязателен для заполнения")
    .min(5, "пароль должен быть не менее 5 символов")
    .max(100, "пароль не должен превышать 100 символов"),
  passwordConfirmation: string()
    .required("пароль обязателен для заполнения")
    .oneOf([yref("password"), ""], "пароль должен совпадать")
})

const {handleSubmit} = useForm({
  validationSchema: schema
});

const error = ref("");

const onSubmit = handleSubmit((values) => {
  error.value = "";

  axios.post("/register", values)
    .then(_ => {
      window.location.href = "/";
    })
    .catch(e => {
      error.value = e.response.data.error
    });
});
</script>

<style>
body {
  font-family: var(--font-family);
}

.login-body {
  display: flex;
  box-sizing: border-box;
  min-height: 100vh;
}

.login-body .login-panel {
  margin: auto;
  box-sizing: border-box;
}

.login-body .login-panel .form-container input {
  display: block;
  width: 300px;
}

.login-body .login-panel .form-container a {
  color: #868C9B;
  margin-bottom: 20px;
  font-size: 11px;
}

.login-body .login-panel .button-container button {
  display: block;
  width: 300px;
}

.login-body .login-panel .button-container > span {
  display: flex;
  font-size: 11px;
  color: #868C9B;
}

.login-body .login-panel .button-container > span a {
  cursor: pointer;
  margin-left: 0.25rem;
}

@media (max-width: 991px) {
  .login-body .login-image {
    display: none;
  }

  .login-body .login-panel {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 60px 50px;
  }
}
</style>
