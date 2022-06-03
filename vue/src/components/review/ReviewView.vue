<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3 class="underline">리뷰상세보기</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" class="w-30" @click="listReview"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          class="w-30 mr-2"
          @click="moveModifyBook"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          class="w-30"
          @click="deleteBook"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <!-- 리뷰정보 상세보기 Component -->
    <view-detail :review="review" />
    <comment-write :no="this.no" />
    <comment-write
      v-if="isModifyShow && this.modifyComment != null"
      :modifyComment="this.modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @modify-comment="onModifyComment"
    />
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";
import ViewDetail from "@/components/review/include/ViewDetail.vue";
import CommentWrite from "@/components/review/include/comment/CommentWrite.vue";
import Comment from "@/components/review/include/comment/Comment.vue";
import http from "@/util/http-common";

export default {
  name: "review-view",
  data() {
    return {
      no: "",
      isModifyShow: false,
      modifyComment: Object,
    };
  },
  computed: {
    // vuex의 getters에 등록된 함수 얻기.
    ...mapGetters(["comments", "review"]),
  },
  components: {
    ViewDetail,
    CommentWrite,
    Comment,
  },
  created() {
    // url이 query에서 isbn 얻기.
    this.no = this.$route.query.no;

    // 도서 정보 얻기.
    this.$store.dispatch("getReview", `/${this.no}`);

    // 도서평(댓글) 얻기.
    this.$store.dispatch("getComments", `/comment/${this.no}`);
  },
  methods: {
    listReview() {
      this.$router.push("/review");
    },
    moveModifyBook() {
      this.$router.push(`/review/modify/${this.no}`);
    },
    deleteBook() {
      if (confirm("정말로 삭제?")) {
        http.delete(`/review/${this.no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/review");
        });
      }
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
};
</script>
