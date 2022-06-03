<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3 class="underline">리뷰목록</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <!-- <b-button variant="outline-primary" @click="movePage">등록</b-button> -->
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
              v-for="(review, index) in reviews"
              :key="index"
              :review_no="review.review_no"
              :store_no="review.store_no"
              :name="review.name"
              :nickname="review.nickname"
              :title="review.title"
              :content="review.content"
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
import ListRow from "@/components/review/include/ListRow.vue";

export default {
  name: "review-list",
  components: {
    ListRow,
  },
  computed: {
    // vuex의 getters에 등록된 함수 얻기.
    ...mapGetters(["reviews", "review"]),
  },
  created() {
    // vuex의 getBooks라는 ations 호출.
    this.$store.dispatch("getReviews");
    // this.$store.dispatch("getReview", no);
    // console.log("여기서" + this.$store.state.reviews);
  },
  // methods: {
  //   movePage() {
  //     this.$router.push({ name: "review-create" });
  //   },
  // },
};
</script>
