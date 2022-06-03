import store from "@/store";
import api from "@/api/http.js";
const naverService = () => {
  const naverLogin = new window.naver.LoginWithNaverId({
    clientId: "oHNDB9lqvrHFCQCzhL_Q",
    callbackUrl: "http://localhost:8080/user/auth",
    isPopup: false /* 팝업을 통한 연동처리 여부 */,
    loginButton: {
      color: "green",
      type: 3,
      height: 60,
    } /* 로그인 버튼의 타입을 지정 */,
  });
  const setNaver = () => {
    naverLogin.init();
  };
  const getUserInfo = () => {
    setNaver();
    console.log(naverLogin);
    naverLogin.getLoginStatus((status) => {
      if (status) {
        // const email = naverLogin.user.email;
        // const name = naverLogin.user.name;
        api.get(`user/loginchk/` + naverLogin.user.email).then(({ data }) => {
          console.log("네이버 로그인시 확인 가입되어있는지 : " + data);
          if (data === "success") {
            // 가입 되어있음 => 로그인 처리
            console.log("가입된 계정 -> 로그인 ㄱㄱ");
            const params = naverLogin.user;
            params.token = params.id;

            api.post(`user/loginnaver`, params).then(({ data }) => {
              console.log("status : " + naverLogin.user.email);
              if (data == "") alert("로그인 실패");
              else {
                console.log("로그인 성공");
                console.log(data);
                store.dispatch("setLoginUser", data);
              }
            });
          } else {
            api
              .post("/user/regist", {
                email: naverLogin.user.email,
                pw: null,
                username: naverLogin.user.name,
                nickname: naverLogin.user.nickname,
                token: naverLogin.user.id,
              })
              .then(({ data }) => {
                let msg = "등록 처리시 문제가 발생했습니다.";
                if (data === "success") {
                  msg = "등록이 완료되었습니다.";
                }
                alert(msg);
                this.$router.push("/");
              });
          }
        });
      }
    });
  };

  return {
    setNaver,
    getUserInfo,
  };
};

export { naverService };
