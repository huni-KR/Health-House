<template>
  <div class="container">
    <h2>회원 정보</h2>
    <fieldset class="text-center">
      <label for="id">이메일</label>
      <input
        readonly
        type="text"
        id="email"
        v-model="loginUser.email"
        class="view"
      /><br />
      <label for="password">비밀번호</label>
      <input
        type="password"
        id="pw"
        v-model="loginUser.pw"
        class="view"
      /><br />
      <label for="name">이름</label>
      <input
        type="text"
        id="username"
        v-model="loginUser.username"
        class="view"
      /><br />
      <label for="email">닉네임</label>
      <input
        type="text"
        id="nickname"
        v-model="loginUser.nickname"
        class="view"
      /><br />
      <button class="btn" @click="updateUser">수정</button>
    </fieldset>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "MemberDetail",
  methods: {
    updateUser() {
      this.$store.dispatch("updateUser", this.loginUser);
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const email = pathName[pathName.length - 1];

    this.$store.dispatch("setUser", email);
  },
};
</script>
