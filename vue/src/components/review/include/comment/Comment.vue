<template>
  <b-container class="bv-example-row bg-light mb-2 p-2">
    <b-row class="p-23">
      <b-col class="text-left text-bold"
        ><b>{{ comment.user_name }}</b></b-col
      >
      <b-col class="text-right">{{ comment.comment_time }}</b-col>
    </b-row>
    <b-row class="p-3">
      <b-col class="text-left" v-html="enterToBr(comment.comment)"></b-col>
    </b-row>
    <b-row>
      <b-col class="text-right">
        <b-link @click="modifyCommentView">수정</b-link> |
        <b-link @click="deleteComment">삭제</b-link>
      </b-col>
    </b-row>
    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "comment-vue",
  props: {
    // 상위 component에서 전달한 도서평을 받는다.
    comment: Object,
  },
  methods: {
    modifyCommentView() {
      // 상위 component의 modify-comment인 event listener에 전달.
      this.$emit("modify-comment", {
        comment_no: this.comment.comment_no,
        comment: this.comment.comment,
        no: this.comment.no,
      });
    },
    deleteComment() {
      if (confirm("정말로 삭제?")) {
        // 서버로 삭제할 도서평번호를 전달.
        http.delete(`/comment/${this.comment.comment_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          // 도서평(댓글) 얻기.
          this.$store.dispatch("getComments", `/comment/${this.comment.no}`);
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>
