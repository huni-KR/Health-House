<template>
  <div>
    <div>
      <label for="range-1">검색할 범위 지정 (단위 : m)</label>
      <b-form-input
        id="range-1"
        v-model="value"
        type="range"
        min="0"
        max="1500"
      ></b-form-input>
      <div class="mt-2">검색 범위 (0~1500) : {{ value }}m</div>
    </div>
    <div id="map">
      <div id="mapwrap">
        <!-- 지도가 표시될 div -->
        <div id="map" style="width: 100%; height: 400px"></div>
        <!-- 지도 위에 표시될 마커 카테고리 -->
        <div class="category">
          <ul>
            <li id="coffeeMenu" v-on:click="changeMarker('coffee')">
              <span class="ico_coffee"></span>
              헬스장
            </li>
            <li id="storeMenu" v-on:click="changeMarker('store')">
              <span class="ico_store"></span>
              골프장
            </li>
            <li id="carparkMenu" v-on:click="changeMarker('carpark')">
              <span class="ico_carpark"></span>
              체육시설
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  data() {
    return {
      gymIcon:
        "https://image.shutterstock.com/image-vector/barbell-fitness-gym-icon-vector-260nw-1152893645.jpg",
      golfIcon:
        "https://static.vecteezy.com/system/resources/previews/000/421/586/non_2x/golf-icon-vector-illustration.jpg",
      etcIcon: "https://pic.onlinewebfonts.com/svg/img_224763.png",

      value: 750,
      map: null,
      URL: "http://localhost:8080/review/",
      type: "",
      markers: [],
      kakaoCoffeeMarkers: [],
      kakaoStoreMarkers: [],
      kakaoCarparkMarkers: [],
      markerImageSrc:
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png",
      param: null,
    };
  },
  methods: {
    ...mapActions(["getCoffeeList", "getStoreList", "getCarparkList"]),
    ...mapMutations(["CHANGE_POINT"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      this.changeMarker("coffee");
    },
    async displayMarkers(positions) {
      // 1. 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        for (var i = 0; i < this.markers.length; i++) {
          this.markers[i].setMap(null);
        }
      }

      // 2. 마커 이미지 커스터마이징 하기
      // javascript 영역에서 assets의 정보 가져오기
      var imgSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 3. 마커 표시하기
      positions.forEach((position) => {
        // information window 생성
        const infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div style="padding:5px;">${position.dongName} </br>${position.aptName}아파트</div>`,
        });

        const marker = new kakao.maps.Marker({
          map: this.map,
          title: position.aptCode,
          position: new kakao.maps.LatLng(
            Number(position.lat),
            Number(position.lng)
          ),
          image: markerImage,
        });
        // 이벤트 등록
        kakao.maps.event.addListener(marker, "click", () => {
          // 화면 전환
          var moveLatLon = new kakao.maps.LatLng(
            Number(position.lat),
            Number(position.lng)
          );
          this.map.panTo(moveLatLon);

          this.param = {
            aptCode: marker.getTitle(),
            distance: this.value,
          };

          var ttt111 = async () => {
            await this.CLEAR_KAKAO_COFFEE_LIST();
            await this.getCoffeeList(this.param);
            await this.createCoffeeMarkers(this.$store.state.coffeeMarkers);
          };

          var ttt222 = async () => {
            await this.CLEAR_KAKAO_STORE_LIST();
            await this.getStoreList(this.param);
            await this.createStoreMarkers(this.$store.state.storeMarkers); // 편의점 마커를 생성하고 편의점 마커 배열에 추가합니다
          };

          var ttt333 = async () => {
            await this.CLEAR_KAKAO_CARPARK_LIST();
            await this.getCarparkList(this.param);
            await this.createCarparkMarkers(this.$store.state.carparkMarkers); // 주차장 마커를 생성하고 주차장 마커 배열에 추가합니다
          };
          ttt111();
          ttt222();
          ttt333();

          this.CHANGE_POINT();
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "mouseover", () => {
          infowindow.open(this.map, marker);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "mouseout", () => {
          infowindow.close();
        });
        this.markers.push(marker);
      });

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = positions.reduce(
        (bounds, position) =>
          bounds.extend(
            new kakao.maps.LatLng(Number(position.lat), Number(position.lng))
          ),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    CLEAR_KAKAO_COFFEE_LIST() {
      this.setCoffeeMarkers(null);
      this.kakaoCoffeeMarkers = [];
    },
    CLEAR_KAKAO_STORE_LIST() {
      this.setStoreMarkers(null);
      this.kakaoStoreMarkers = [];
    },

    CLEAR_KAKAO_CARPARK_LIST() {
      this.setCarparkMarkers(null);
      this.kakaoCarparkMarkers = [];
    },

    createMarkerImage(src, size, options) {
      var markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },
    // 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
    createMarker(title, position, image) {
      var marker = new kakao.maps.Marker({
        title: title,
        position: new kakao.maps.LatLng(
          Number(position.lat),
          Number(position.lng)
        ),
        image: image,
      });

      return marker;
    },
    async createCoffeeMarkers(tMarkers) {
      for (var i = 0; i < tMarkers.length; i++) {
        var imageSize = new kakao.maps.Size(22, 26);

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(this.gymIcon, imageSize);
        const tmpMarker = this.createMarker(
          tMarkers[i].no,
          tMarkers[i],
          markerImage
        );

        const infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div style="padding:5px;">${tMarkers[i].category} </br> ${tMarkers[i].name} </br> <a href="${this.URL}${tMarkers[i].no}">리뷰 보기</a> </div>`,
        });
        kakao.maps.event.addListener(tmpMarker, "click", () => {
          infowindow.open(this.map, tmpMarker);
        });

        // 생성된 마커를 커피숍 마커 배열에 추가합니다
        this.kakaoCoffeeMarkers.push(tmpMarker);
      }
    },

    // 커피숍 마커들의 지도 표시 여부를 설정하는 함수입니다
    setCoffeeMarkers(map) {
      for (var i = 0; i < this.kakaoCoffeeMarkers.length; i++) {
        this.kakaoCoffeeMarkers[i].setMap(map);
      }
    },

    // 편의점 마커를 생성하고 편의점 마커 배열에 추가하는 함수입니다
    async createStoreMarkers(tMarkers) {
      for (var i = 0; i < tMarkers.length; i++) {
        var imageSize = new kakao.maps.Size(22, 26);

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(this.golfIcon, imageSize);
        const tmpMarker = this.createMarker(
          tMarkers[i].no,
          tMarkers[i],
          markerImage
        );

        const infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div style="padding:5px;">${tMarkers[i].category} </br> ${tMarkers[i].name} </br> <a href="${this.URL}${tMarkers[i].no}">리뷰 보기</a> </div>`,
        });
        kakao.maps.event.addListener(tmpMarker, "click", () => {
          infowindow.open(this.map, tmpMarker);
        });

        // 생성된 마커를 편의점 마커 배열에 추가합니다
        this.kakaoStoreMarkers.push(tmpMarker);
      }
    },

    // 편의점 마커들의 지도 표시 여부를 설정하는 함수입니다
    setStoreMarkers(map) {
      for (var i = 0; i < this.kakaoStoreMarkers.length; i++) {
        this.kakaoStoreMarkers[i].setMap(map);
      }
    },

    // 주차장 마커를 생성하고 주차장 마커 배열에 추가하는 함수입니다
    async createCarparkMarkers(tMarkers) {
      for (var i = 0; i < tMarkers.length; i++) {
        var imageSize = new kakao.maps.Size(22, 26);

        // 마커이미지와 마커를 생성합니다
        var markerImage = this.createMarkerImage(this.etcIcon, imageSize);
        const tmpMarker = this.createMarker(
          tMarkers[i].no,
          tMarkers[i],
          markerImage
        );

        const infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div style="padding:5px;">${tMarkers[i].category} </br> ${tMarkers[i].name} </br> <a href="${this.URL}${tMarkers[i].no}">리뷰 보기</a> </div>`,
        });

        kakao.maps.event.addListener(tmpMarker, "click", () => {
          infowindow.open(this.map, tmpMarker);
        });
        // 생성된 마커를 주차장 마커 배열에 추가합니다
        this.kakaoCarparkMarkers.push(tmpMarker);
      }
    },

    // 주차장 마커들의 지도 표시 여부를 설정하는 함수입니다
    setCarparkMarkers(map) {
      for (var i = 0; i < this.kakaoCarparkMarkers.length; i++) {
        this.kakaoCarparkMarkers[i].setMap(map);
      }
    },

    // 카테고리를 클릭했을 때 type에 따라 카테고리의 스타일과 지도에 표시되는 마커를 변경합니다
    changeMarker(type) {
      var coffeeMenu = document.getElementById("coffeeMenu");
      var storeMenu = document.getElementById("storeMenu");
      var carparkMenu = document.getElementById("carparkMenu");

      this.type = type;

      // 커피숍 카테고리가 클릭됐을 때
      if (type === "coffee") {
        // 커피숍 카테고리를 선택된 스타일로 변경하고
        coffeeMenu.className = "menu_selected";

        // 편의점과 주차장 카테고리는 선택되지 않은 스타일로 바꿉니다
        storeMenu.className = "";
        carparkMenu.className = "";

        // 커피숍 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(this.map);
        this.setStoreMarkers(null);
        this.setCarparkMarkers(null);
      } else if (type === "store") {
        // 편의점 카테고리가 클릭됐을 때

        // 편의점 카테고리를 선택된 스타일로 변경하고
        coffeeMenu.className = "";
        storeMenu.className = "menu_selected";
        carparkMenu.className = "";

        // 편의점 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(null);
        this.setStoreMarkers(this.map);
        this.setCarparkMarkers(null);
      } else if (type === "carpark") {
        // 주차장 카테고리가 클릭됐을 때

        // 주차장 카테고리를 선택된 스타일로 변경하고
        coffeeMenu.className = "";
        storeMenu.className = "";
        carparkMenu.className = "menu_selected";

        // 주차장 마커들만 지도에 표시하도록 설정합니다
        this.setCoffeeMarkers(null);
        this.setStoreMarkers(null);
        this.setCarparkMarkers(this.map);
      }
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },

  computed: {
    ...mapState(["test", "point"]),
  },
  watch: {
    test: async function () {
      await this.displayMarkers(this.$store.state.houses);
    },
    point: async function () {
      // console.log(fun);
      await this.changeMarker(this.type);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 700px;
}
#mapwrap {
  position: relative;
  overflow: hidden;
}
.category,
.category * {
  margin: 0;
  padding: 0;
  color: #000;
}
.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  width: 150px;
  height: 50px;
  z-index: 10;
  border: 1px solid black;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
}
.category .menu_selected {
  background: #ff5f4a;
  color: #fff;
  border-left: 1px solid #915b2f;
  border-right: 1px solid #915b2f;
  margin: 0 -1px;
}
.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 45px;
  padding-top: 5px;
  cursor: pointer;
}
.category .ico_coffee {
  display: block;
  margin: 0 auto 2px;
  width: 26px;
  height: 26px;
  background: url("https://image.shutterstock.com/image-vector/barbell-fitness-gym-icon-vector-260nw-1152893645.jpg");
  background-size: cover;
}

.category .ico_store {
  display: block;
  margin: 0 auto 2px;
  width: 26px;
  height: 26px;
  background: url("https://static.vecteezy.com/system/resources/previews/000/421/586/non_2x/golf-icon-vector-illustration.jpg");
  background-size: cover;
}

.category .ico_carpark {
  display: block;
  margin: 0 auto 2px;
  width: 26px;
  height: 26px;
  background: url("https://pic.onlinewebfonts.com/svg/img_224763.png");
  background-size: cover;
}

/* 
.category .ico_comm {
  display: block;
  margin: 0 auto 2px;
  width: 22px;
  height: 26px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png")
    no-repeat;
}
.category .ico_coffee {
  background-position: -10px 0;
}
.category .ico_store {
  background-position: -10px -36px;
}
.category .ico_carpark {
  background-position: -10px -72px;
} */
</style>
gymIcon:
"https://image.shutterstock.com/image-vector/barbell-fitness-gym-icon-vector-260nw-1152893645.jpg",
golfIcon:
"https://static.vecteezy.com/system/resources/previews/000/421/586/non_2x/golf-icon-vector-illustration.jpg",
etcIcon: "https://pic.onlinewebfonts.com/svg/img_224763.png",
