package com.chanho.basic.model

data class MovieReqModel(
    // 원하는 검색어 필수
    var query:String,
    // 검색 결과 출력 건수 지정 최대 100 개 가능
    var display:String,
    // 검색의 시작 위치 지정 가능 초히대 1000까지 가능
    var start:String,
    /**
     * 검색을 원하는 장르 코드를 의미한다. 영화 코드는 다음과 같다.
    1: 드라마 2: 판타지
    3: 서부 4: 공포
    5: 로맨스 6: 모험
    7: 스릴러 8: 느와르
    9: 컬트 10: 다큐멘터리
    11: 코미디 12: 가족
    13: 미스터리 14: 전쟁
    15: 애니메이션 16: 범죄
    17: 뮤지컬 18: SF
    19: 액션20: 무협
    21: 에로 22: 서스펜스
    23: 서사 24: 블랙코미디
    25: 실험 26: 영화카툰
    27: 영화음악 28: 영화패러디포스터
     */
    var genre:String?,
    /**
     * 검색을 원하는 국가 코드를 의미한다. 국가코드는 대문자만 사용 가능하며,
    분류는 다음과 같다.
    한국 (KR), 일본 (JP), 미국 (US), 홍콩 (HK),
    영국 (GB), 프랑스 (FR), 기타 (ETC)
     */
    var country:String?,
    /**
     * 검색을 원하는 영화의 제작년도(최소)를 의미한다.
    yearfrom은 yearto와 함께 사용되어야 한다.
     */
    var yearfrom:String?,
    /**
     * 검색을 원하는 영화의 제작년도(최대)를 의미한다.
    yearto는 yearfrom과 함께 사용되어야 한다.
     */
    var yearto:String?

){
    constructor():this("사랑","10","1","","","","")
}