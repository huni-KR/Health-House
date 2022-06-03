import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import ReviewView from "../views/ReviewView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "modify",
        name: "modify",
        component: () => import("@/components/user/MemberDetail.vue"),
      },
      {
        path: "auth",
        name: "auth",
        component: () => import("@/components/user/AuthView.vue"),
      },
    ],
  },

  {
    name: "review",
    path: "/review",
    component: ReviewView,
    children: [
      {
        path: "",
        name: "review-list",
        component: () => import("@/components/review/ReviewList.vue"),
      },
      {
        path: ":no",
        name: "review-items",
        component: () => import("@/components/review/ReviewItems.vue"),
      },
      {
        path: "create",
        name: "review-create",
        component: () => import("@/components/review/ReviewCreate.vue"),
      },
      {
        path: "view",
        name: "review-view",
        component: () => import("@/components/review/ReviewView.vue"),
      },
      {
        path: "modify/:no",
        name: "review-modify",
        component: () => import("@/components/review/ReviewModify.vue"),
      },
    ],
    redirect: () => {
      return "/review";
    },
  },
  {
    path: "/instagram",
    name: "instagram",
    component: () => import("@/views/InstagramView.vue"),
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/todo",
    name: "todo",
    component: () => import("@/views/TodoView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
