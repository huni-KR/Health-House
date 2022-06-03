<template>
  <b-row class="mb-1">
    <b-col>
      <b-card
        :header-html="`<h3>${review.title}</h3>
        <div><h6>${review.nickname}</h6></div>`"
        class="mb-2"
        border-variant="dark"
        no-body
      >
        <b-card-body class="text-left">
          <div>
            <template v-for="(msg, index) in message">
              {{ msg }}<br :key="index" />
            </template>
          </div>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
// import { mapGetters } from "vuex";
export default {
  name: "view-detail",
  computed: {
    // ...mapGetters(["review"]),
    message: function () {
      // 상세설명부분 개행.
      if (this.review.content) return this.review.content.split("\n");
      return "";
    },
  },
  props: {
    review: Object,
  },
  methods: {},
};
</script>
