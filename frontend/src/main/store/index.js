import { createStore } from 'vuex'

export default createStore({
  state: {
    event: null,
    user: null
  },
  getters: {
  },
  mutations: {
    event(state, event) {
      state.event = event;
    },
    user(state, user) {
      state.user = user;
    }
  },
  actions: {
  },
  modules: {
  }
})
