<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <fieldset class="text-center">
          <label for="id">이메일</label>
          <input type="text" id="email" v-model="email" class="view" /><br />
          <label for="pw">비밀번호</label>
          <input type="password" id="pw" v-model="pw" class="view" /><br />
          <label for="username">이름</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="view"
          /><br />
          <label for="email">닉네임</label>
          <input
            type="email"
            id="nickname"
            v-model="nickname"
            class="view"
          /><br />
          <button class="btn" @click="regist">등록</button>
          <button class="btn" @click="clear">지우기</button>
        </fieldset>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import api from "@/api/http.js";
export default {
  name: "MemberRegister",
  data() {
    return {
      email: "",
      pw: "",
      username: "",
      nickname: "",
    };
  },
  methods: {
    clear() {
      this.email = "";
      this.pw = "";
      this.username = "";
      this.nickname = "";
    },
    regist() {
      if (
        this.email === "" ||
        this.pw === "" ||
        this.username === "" ||
        this.nickname === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      api
        .post("/user/regist", {
          email: this.email,
          pw: this.pw,
          username: this.username,
          nickname: this.nickname,
          token: null,
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

<style>
.text-center {
  text-align: center;
}
</style>
