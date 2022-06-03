<template>
  <div>
    <div id="naverIdLogin" style="display: none"></div>
    <button @click="getInfo">asdasdf</button>
  </div>
</template>

<script>
import { naverService } from "@/services/login";
import api from "@/api/http.js";
export default {
  name: "AuthView",
  mounted() {
    if (this.$route.hash) {
      naverService().getUserInfo();
    }
  },
  methods: {
    movePage() {
      this.$router.push({ name: "signUp" });
    },

    getInfo() {
      naverService().getUserInfo();
      console.log(this.$store.getters.getToken);
    },

    regist() {
      api
        .post("/user/regist", {
          id: this.id,
          pw: this.pw,
          username: this.username,
          email: this.email,
          tel: this.tel,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/");
        });
    },
  },
};
</script>
