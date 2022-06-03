import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http.js";
import router from "@/router";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,

    test: false,
    coffeeMarkers: [], // 커피숍 마커 객체를 가지고 있을 배열입니다
    storeMarkers: [], // 편의점 마커 객체를 가지고 있을 배열입니다
    carparkMarkers: [], // 주차장 마커 객체를 가지고 있을 배열입니다

    todos: [],

    books: [],
    book: null,
    comments: [],

    reviews: [],
    review: null,

    users: [],
    user: null,
    loginUser: null,

    isLogin: false,
    storeInfo: null,
  },
  getters: {
    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },
    books(state) {
      return state.books;
    },
    book(state) {
      return state.book;
    },
    reviews(state) {
      return state.reviews;
    },
    review(state) {
      return state.review;
    },
    comments(state) {
      return state.comments;
    },
    getToken(state) {
      return state.user_token;
    },
    getCoffee(state) {
      return state.coffeeMarkers;
    },
    getLoginUser(state) {
      return state.loginUser;
    },
  },
  mutations: {
    /////////////////////////////// User start /////////////////////////////////////
    CREATE_USER: function (state, user) {
      state.users.push(user);
    },
    USER_LOGIN: function (state, usertoken, useremail, username, usernickname) {
      state.user_token = usertoken;
      state.user_email = useremail;
      state.user_name = username;
      state.user_nickname = usernickname;
      state.user = username;
    },
    SET_USERS: function (state, users) {
      state.users = users;
    },
    SET_USER: function (state, user) {
      state.user = user;
    },
    SEARCH_NAME: function (state, users) {
      state.searchUsers = users;
    },
    SET_LOGIN_USER: function (state, user) {
      state.loginUser = user;
      state.isLogin = true;
    },
    LOGOUT: function (state) {
      state.loginUser = null;
      state.isLogin = false;
      router.push("/");
    },
    SET_RANDOM_USER: function (state, user) {
      state.randomUser = user;
    },
    /////////////////////////////// User end /////////////////////////////////////

    SET_COFFEE_LIST(state, coffeeMarkers) {
      state.coffeeMarkers = [];
      coffeeMarkers.forEach((marker) => {
        state.coffeeMarkers.push({
          no: marker.no,
          name: marker.name,
          category: marker.category,
          address: marker.newAddress,
          lat: marker.lat,
          lng: marker.lng,
        });
      });
    },
    SET_STORE_LIST(state, storeMarkers) {
      state.storeMarkers = [];
      storeMarkers.forEach((marker) => {
        state.storeMarkers.push({
          no: marker.no,
          name: marker.name,
          category: marker.category,
          address: marker.newAddress,
          lat: marker.lat,
          lng: marker.lng,
        });
      });
    },
    SET_CARPARK_LIST(state, carparkMarkers) {
      state.carparkMarkers = [];
      carparkMarkers.forEach((marker) => {
        state.carparkMarkers.push({
          no: marker.no,
          name: marker.name,
          category: marker.category,
          address: marker.newAddress,
          lat: marker.lat,
          lng: marker.lng,
        });
      });
    },
    // SET_STOREINFO(state, dbInfo) {
    //   state.storeInfo.push({
    //     no: dbInfo.no,
    //     name: dbInfo.name,
    //     category: dbInfo.category,
    //     address: dbInfo.newAddress,
    //   });
    setInfo(state, payload) {
      state.storeInfo = payload;
    },

    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    SET_HOUSE_LIST(state, houses) {
      houses.forEach((house) => {
        state.houses.push({
          aptCode: house.aptCode,
          aptName: house.aptName,
          dongName: house.dongName,
          recentPrice: house.recentPrice,
          lat: house.lat,
          lng: house.lng,
        });
      });
      state.test = !state.test;
    },

    CHANGE_POINT(state) {
      state.point != state.point;
    },

    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    CREATE_TODO(state, todoItem) {
      state.todos.push(todoItem);
    },
    DELETE_TODO(state, todoItem) {
      const index = state.todos.indexOf(todoItem);
      state.todos.splice(index, 1);
    },
    UPDATE_TODO_STATUS(state, todoItem) {
      state.todos = state.todos.map((todo) => {
        if (todo === todoItem) {
          return {
            ...todo,
            completed: !todoItem.completed,
          };
        }
        return todo;
      });
    },
    //////////////////////////// Todo List end //////////////////////////////////
    setBooks(state, payload) {
      state.books = payload;
    },
    setBook(state, payload) {
      state.book = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
    setReviews(state, payload) {
      state.reviews = payload;
    },
    setReview(state, payload) {
      state.review = payload;
    },
  },
  actions: {
    /////////////////////////////// User start /////////////////////////////////////
    createUser: function ({ commit }, user) {
      commit("CREATE_USER", user);
      router.push("/user");
    },
    loginUser: function ({ commit }, user) {
      commit("LOGIN_USER", user);
      router.push("/login");
    },
    snsLogin: function (
      { commit },
      { usertoken, useremail, username, usernickname }
    ) {
      commit("USER_LOGIN", { usertoken, useremail, username, usernickname });
      router.push("/login");
    },
    updateUser: function ({ commit }, user) {
      http
        .put("/user/info", {
          email: user.email,
          pw: user.pw,
          username: user.username,
          nickname: user.nickname,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          router.push("/");
        });
      commit("SET_USER", user);
    },
    deleteUser: function ({ commit }, id) {
      console.log("회원삭제");
      http.delete("/user/info/" + id).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
      });
      commit("LOGOUT");
      router.push("/");
    },
    setUser: function ({ commit, state }, id) {
      for (let i = 0; i < state.users.length; i++) {
        if (state.users[i].id === id) {
          commit("SET_USER", state.users[i]);
          break;
        }
      }
    },
    searchName: function ({ commit, state }, name) {
      let newUserList = [];

      for (let i = 0; i < state.users.length; i++) {
        if (state.users[i].name.indexOf(name) >= 0) {
          newUserList.push(state.users[i]);
        }
      }
      commit("SEARCH_NAME", newUserList);
    },
    setLoginUser: function ({ commit }, user) {
      commit("SET_LOGIN_USER", user);
      alert("로그인 성공");
      router.push("/");
    },

    /////////////////////////////// User start /////////////////////////////////////

    async getCoffeeList({ commit }, params) {
      await http
        .get(`/sport/gym`, { params })
        .then(({ data }) => {
          commit("SET_COFFEE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getStoreList({ commit }, params) {
      await http
        .get(`/sport/golf`, { params })
        .then(({ data }) => {
          commit("SET_STORE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getCarparkList({ commit }, params) {
      await http
        .get(`/sport/etc`, { params })
        .then(({ data }) => {
          commit("SET_CARPARK_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getInfo(context, payload) {
      console.log("info~~");
      http.get("/sport/detail" + payload).then(({ data }) => {
        context.commit("setInfo", data);
      });
    },

    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/map/dong`, { params })
        .then(({ data }) => {
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // 수정해야하는 부분
    getHouseList({ commit }, dongCode) {
      const params = { dong: dongCode };
      http
        .get(`/map/apt`, { params })
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },

    // moveMap({ commit }, house) {
    //   commit("MOVE_MAP", house);
    // },

    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    /////////////////////////////// House end /////////////////////////////////////

    // destructuring 활용
    createTodo({ commit }, todoItem) {
      commit("CREATE_TODO", todoItem);
    },
    deleteTodo({ commit }, todoItem) {
      // console.log(context);
      // console.log(todoItem);
      commit("DELETE_TODO", todoItem);
    },
    updateTodoStatus({ commit }, todoItem) {
      commit("UPDATE_TODO_STATUS", todoItem);
    },
    //////////////////////////// Todo List end //////////////////////////////////
    // 서버에서 도서목록을 얻고 mutation의 setBooks를 호출한다.
    getBooks(context) {
      http
        .get("/book")
        .then(({ data }) => {
          context.commit("setBooks", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    // 서버에서 isbn에 해당하는 도서정보 얻고 mutation의 setBook을 호출한다.
    getBook(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit("setBook", data);
      });
    },
    // 서버에서 isbn에 해당하는 도서평을 얻고 mutation의 setComments를 호출한다.
    getComments(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit("setComments", data);
      });
    },
    getReviews(context) {
      console.log("context : " + context);
      http
        .get("/review")
        .then(({ data }) => {
          console.log(data);
          context.commit("setReviews", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    getReview(context, payload) {
      console.log("payload : " + payload);
      http.get("/review/detail/" + payload).then(({ data }) => {
        context.commit("setReview", data);
      });
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
