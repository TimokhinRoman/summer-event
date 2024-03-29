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
import DialogService from 'primevue/dialogservice';

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faLocationDot, faCrown, faDice } from '@fortawesome/free-solid-svg-icons'

library.add(faLocationDot, faCrown, faDice)

createApp(App)
  .use(store)
  .use(router)
  .use(PrimeVue)
  .use(DialogService)
  .component("Button", Button)
  .component('font-awesome-icon', FontAwesomeIcon)
  .mount('#app')
