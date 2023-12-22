import Vue from "vue";
import Router from "vue-router";
//import DashboardLayout from '../layout/starter/SampleLayout.vue';
import Starter from "../signIn/Index.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "login",
      redirect: "/signin",
      component: LoginLayout,
      children: [
        {
          path: "signIn",
          name: "signIn",
          components: { default: Starter },
        },
      ],
    },
  ],
});
