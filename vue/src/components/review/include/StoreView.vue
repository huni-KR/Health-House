<template>
  <b-row class="mb-1">
    <b-col>
      <b-card
        :header-html="`<h3>${this.$store.state.storeInfo.name}</h3>
        <div><h6>${this.$store.state.storeInfo.no}</h6></div>`"
        class="mb-2"
        border-variant="dark"
        no-body
      >
        <b-card-body class="text-left">
          <div>
            {{ this.$store.state.storeInfo.category }}
            <br />
            {{ this.$store.state.storeInfo.newAddress }}
          </div>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "store-view",
  computed: {
    message: function () {
      // 상세설명부분 개행.
      if (this.review.content) return this.review.content.split("\n");
      return "";
    },
  },

  created() {
    console.log();
    this.$store.dispatch("getInfo", "/" + this.$route.params.no);
    this.$store.dispatch("getReview", "/" + this.$route.params.no);
  },
};
</script>
