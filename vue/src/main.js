import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import VueCookies from "vue-cookies";
// import VueCompositionApi from "@vue/composition-api";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

// Vue.use(VueCompositionApi);
// Vue.use(VueCookies);
// window.Kakao.init("492b910cb97f037755e3cb49c363b390"); // Kakao Developers에서 요약 정보 -> JavaScript 키

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
