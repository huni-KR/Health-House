<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3 v-if="type == 'create'" class="underline">리뷰 등록</h3>
        <h3 v-else class="underline">리뷰 수정</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            id="title-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요."
          >
            <b-form-input
              id="title"
              ref="title"
              v-model="title"
              type="text"
              required
              placeholder="제목 입력..."
            />
          </b-form-group>

          <b-form-group
            id="content-group"
            label="내용:"
            label-for="content"
            ref="content"
          >
            <b-form-textarea
              id="content"
              v-model="content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button
            v-if="type == 'create'"
            variant="primary"
            class="m-1"
            @click="checkValue"
            >리뷰등록</b-button
          >
          <b-button
            v-if="isSameWriter()"
            variant="success"
            class="m-1"
            @click="checkValue"
            >리뷰수정</b-button
          >
          <b-button
            v-if="isSameWriter()"
            variant="danger"
            class="m-1"
            @click="deleteBook"
            >글삭제</b-button
          >
          <b-button variant="primary" class="m-1" @click="moveList"
            >목록</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "write-form",
  props: {
    type: { type: String },
  },
  data() {
    return {
      store_no: 0,
      writer_email: "",
      review_no: "",
      title: "",
      content: "",
    };
  },
  created() {
    if (this.type === "modify") {
      // 도서 수정 페이지일 경우 isbn에 해당하는 도서 정보를 서버로 부터 얻기.
      http.get(`/review/${this.$route.params.no}`).then(({ data }) => {
        this.store_no = this.$route.params.no;
        this.review_no = data.review_no;
        this.title = data.title;
        this.content = data.content;
        this.writer_email = data.writer_email;
        console.log(data);
      });
    }
  },
  methods: {
    isSameWriter() {
      if (this.$store.getters.getLoginUser.email == this.writer_email) {
        return true;
      } else if (this.writer_email != "") {
        return false;
      }
    },
    // 입력값 체크하기 - 체크가 성공하면 registBook 호출
    checkValue() {
      console.log(this.$store.getters.getLoginUser.email);
      // 사용자 입력값 체크하기
      // isbn, 제목, 저자, 가격, 설명이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";

      !this.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registBook 호출
      else this.type == "create" ? this.registBook() : this.modifyBook();
    },
    registBook() {
      console.log("등록");
      // 저장할 도서 정보를 서버로 전송.
      http
        .post("/review/", {
          store_no: this.$store.state.storeInfo.no,
          writer_email: this.$store.getters.getLoginUser.email,
          title: this.title,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyBook() {
      console.log("수정" + this.review_no);
      // 수정할 도서정보를 서버로 전송
      http
        .put(`/review`, {
          review_no: this.review_no,
          title: this.title,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/review");
        });
    },
    deleteBook() {
      if (confirm("정말로 삭제?")) {
        http.delete(`/review/${this.review_no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/review");
        });
      }
    },
    moveList() {
      this.$router.push("/review");
    },
  },
};
</script>
