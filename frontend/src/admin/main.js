import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import PrimeVue from "primevue/config";

import "primevue/resources/primevue.min.css";
import "primevue/resources/themes/bootstrap4-dark-blue/theme.css";
import "primeicons/primeicons.css"
import "primeflex/primeflex.css"
import Button from "primevue/button";


import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUser, faUsersLine, faCalendarDays, faDice, faCrown } from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
library.add(faUser, faUsersLine, faCalendarDays, faDice, faCrown)

const app = createApp(App)
  .use(store)
  .use(router)
  .use(PrimeVue);

app.component("Button", Button)
app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app')
