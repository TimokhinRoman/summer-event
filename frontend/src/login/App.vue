<template>
  <div class="login-body">
    <div class="login-panel p-fluid">
      <div class="flex flex-column">
        <form @submit="onSubmit">
          <div class="form-container mb-4">
            <input-text-field type="email" name="email" label="Email"/>
            <password-field name="password" label="Пароль"/>
          </div>
          <div class="button-container">
            <Button type="submit" label="Войти" class="text-xl font-medium"/>
          </div>
          <small class="p-error" v-if="error">{{ error }}</small>
        </form>
        <a class="link" href="/register">Зарегистрироваться</a>
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
  })
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
  max-width: 320px;
  min-width: 270px;
}

.login-body .login-panel .form-container a {
  color: #868C9B;
  margin-bottom: 20px;
  font-size: 11px;
}

.login-body .login-panel .button-container button {
  display: block;
  max-width: 320px;
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

.login-body .link {
  text-decoration: none;
  text-align: center;
  color: #8dd0ff;
  margin-top: 0.5rem;
}
</style>
