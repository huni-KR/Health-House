<template>
  <b-tr>
    <b-td class="text-center">{{ review_no }}</b-td>
    <b-th>
      <router-link :to="`review/${store_no}`">{{ name }}</router-link>
    </b-th>
    <b-td>{{ nickname }}</b-td>
    <b-td>{{ title }}</b-td>
    <b-td>{{ content }}</b-td>
  </b-tr>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "ListRow",
  props: {
    // 상위 component에서 전달한 도서정보를 받는다.
    review_no: Number,
    store_no: Number,
    name: String,
    nickname: String,
    title: String,
    content: String,
  },
  computed: {
    ...mapGetters(["reviews", "review"]),
  },
};
</script>
