<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="github"></b-icon> 개발자 GitHub
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <template #header>개발자 GitHub</template>

          <hr class="my-4" />

          <div class="container mt-4 mb-4 p-3 d-flex justify-content-center">
            <div class="card p-4">
              <div
                class="image d-flex flex-column justify-content-center align-items-center"
              >
                <img src="@/assets/profileKTH.jpg" height="100" width="100" />
                <span class="name mt-3">
                  <marquee direction="left">김태훈</marquee></span
                >
                <div class="d-flex mt-2">
                  <button
                    class="btn1 btn-dark"
                    onclick="window.location.href='https://github.com/huni-KR'"
                  >
                    GitHub
                  </button>
                </div>
              </div>
            </div>
            <div class="card p-4">
              <div
                class="image d-flex flex-column justify-content-center align-items-center"
              >
                <img src="@/assets/profileJHU.jpg" height="100" width="100" />

                <span class="name mt-3">
                  <marquee direction="right">조헌우</marquee></span
                >
                <div class="d-flex mt-2">
                  <button
                    class="btn1 btn-dark"
                    onclick="window.location.href='https://github.com/hunucho'"
                  >
                    GitHub
                  </button>
                </div>
              </div>
            </div>
          </div>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "InstagramView",
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
</style>
