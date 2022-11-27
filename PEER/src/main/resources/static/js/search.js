//	지도 버튼 
var subs = document.querySelector("#list");

subs.addEventListener("click", function fun1() {
	if (subs.innerText === '목록 보기') {
		subs.innerText = '지도 펼치기 ';
	} else
		subs.innerText = '목록 보기';
});

subs.addEventListener("click", function fun2() {
	if (subs.innerText === '지도 펼치기') {
		window.scrollTo({ top: document.querySelector("#map_list").offsetTop, behavior: "smooth" });
	} else
		window.scrollTo({ top: 0, left: 0, behavior: "smooth" });
});

// 지도를 표시할 div
var mapContainer = document.getElementById('map'),
mapOption = {
	center : new kakao.maps.LatLng(37.556397, 126.945195), // 지도의 중심좌표
	level : 2
// 지도의 확대 레벨
};

// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomIn() {
	map.setLevel(map.getLevel() - 1);
}

// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomOut() {
	map.setLevel(map.getLevel() + 1);
}

//마커를 표시할 위치와 title 객체 배열입니다 
/*var positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
];*/

// 지도에 마커를 표시합니다
var marker = new kakao.maps.Marker({
	map : map,
	position : new kakao.maps.LatLng(37.556397, 126.945195)
});

// 커스텀 오버레이에 표시할 컨텐츠 입니다
var content = '<div class="wrap">'
		+ '<div class="info">'
		+ '<div class="title">'
		+ '중앙정보기술인재개발원'
		+ '<div class="close" onclick="closeOverlay()" title="닫기"></div>'
		+ '</div>'
		+ '<div class="body">'
		+ '<div class="img">'
		+ '<img src="img/hero-slider-5.jpg" width="73" height="70">'
		+ '</div>'
		+ '<div class="desc">'
		+ '<div class="ellipsis">서울특별시 마포구 신촌로 176</div>'
		+ '<div><a href="https://www.choongang.co.kr/" target="_blank" class="link">홈페이지</a></div>'
		+ '</div>' + '</div>' + '</div>' + '</div>';

// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
var overlay = new kakao.maps.CustomOverlay({
	content : content,
	map : map,
	position : marker.getPosition()
});

// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
kakao.maps.event.addListener(marker, 'click', function() {
	overlay.setMap(map);
});

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
function closeOverlay() {
	overlay.setMap(null);
}

//인포윈도우로 장소에 대한 설명을 표시합니다
var infowindow = new kakao.maps.InfoWindow({
    content: '<div class="wrap">'
		+ '<div class="info">'
		+ '<div class="title">'
		+ '중앙정보기술인재개발원'
		+ '<div class="close" onclick="closeOverlay()" title="닫기"></div>'
		+ '</div>'
		+ '<div class="body">'
		+ '<div class="img">'
		+ '<img src="img/hero-slider-5.jpg" width="73" height="70">'
		+ '</div>'
		+ '<div class="desc">'
		+ '<div class="ellipsis">서울특별시 마포구 신촌로 176</div>'
		+ '<div><a href="https://www.choongang.co.kr/" target="_blank" class="link">홈페이지</a></div>'
		+ '</div>' + '</div>' + '</div>' + '</div>'
});
infowindow.open(map, marker);
