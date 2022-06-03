<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3 class="underline">가게리뷰목록</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <store-view :review="review"></store-view>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="movePage">등록</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="reviews.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>번호</b-th>
              <b-th>상호명</b-th>
              <b-th>작성자</b-th>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <list-row
              v-for="(items, index) in review"
              :key="index"
              :review_no="items.review_no"
              :store_no="items.store_no"
              :name="items.name"
              :nickname="items.nickname"
              :title="items.title"
              :content="items.content"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";
import ListRow from "@/components/review/include/ListRow2.vue";
import StoreView from "@/components/review/include/StoreView.vue";

export default {
  name: "review-list",
  data() {
    return {
      store_no: "",
    };
  },
  components: {
    ListRow,
    StoreView,
  },
  computed: {
    // vuex의 getters에 등록된 함수 얻기.
    ...mapGetters(["reviews", "review"]),
  },
  created() {
    // this.$store.dispatch("getReview", no);
    // console.log("여기서" + this.$store.state.reviews);
  },
  methods: {
    movePage() {
      this.$router.push({ name: "review-create" });
    },
  },
};
</script>
