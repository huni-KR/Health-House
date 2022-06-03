<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="useremail">
              <b-form-input
                id="useremail"
                v-model="user.useremail"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpw">
              <b-form-input
                type="password"
                id="userpw"
                v-model="user.userpw"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
            <div id="naverIdLogin"></div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script
  src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
  charset="utf-8"
></script>
<script>
import { naverService } from "@/services/login.js";
import api from "@/api/http.js";

export default {
  name: "MemberLogin",

  data() {
    return {
      isLoginError: false,
      user: {
        useremail: "",
        userpw: "",
      },
      client_id: "oHNDB9lqvrHFCQCzhL_Q",
      callbackUrl: "http://localhost:8080/naverLogin",
    };
  },
  methods: {
    async confirm() {
      let user = {
        email: this.user.useremail,
        pw: this.user.userpw,
      };
      await api.post(`user/login`, user).then(({ data }) => {
        console.log("data : " + data);
        if (data == "") alert("로그인 실패");
        else {
          console.log("로그인 성공");
          this.$store.dispatch("setLoginUser", data);
        }
      });
      // console.log(user.id + " " + user.pw);
      // this.$store.dispatch("setLoginUser", user);
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
  mounted() {
    naverService().setNaver();
  },
};
</script>

<style></style>
