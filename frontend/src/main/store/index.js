import { createStore } from 'vuex'

export default createStore({
  state: {
    inited: false
  },
  getters: {
  },
  mutations: {
    init(state) {
      state.inited = true;
    }
  },
  actions: {
  },
  modules: {
  }
})
