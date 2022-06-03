<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <!-- <template #header>HAPPY HOUSE</template> -->
          <marquee direction="up"
            ><img src="@/assets/healthhouse.png"
          /></marquee>

          <!-- <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="ssafy"
          /> -->

          <h2><marquee direction="left">부울경 4반</marquee> <br /></h2>
          <h2><marquee direction="right">김태훈 조헌우 팀</marquee> <br /></h2>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
