# mangjakseon


이유 없는 오류시 확인하기 
*이유없이 js 가 안먹을때는 jQuery.noConflict(); 이용
*css가 이상이 있으면 해당 옵션에 효과에 !important



* ~~로그인 회원가입 회원정보 회원정보 수정 css~~ 
* ~~헤더 따라 내려오기~~ 완료
* ~~무비인포 페이지 포스터 따라 내려오기~~ 
* ~~자신의 리뷰만 수정 삭제 가능하게 해야함~~ 
* ~~영상 게시판 관리자만 쓰도록 수정 해야함~~ 
* ~~6점이상 영화 및 6점 이하 영화 차순 정렬~~
* 쓸모 없는 로그 및 주석 지우기
* 회원가입 페이지 CSS (사이트 소개 같이 넣기)
* 리뷰 별점 평균 가져오기 / 리뷰 별점 평균 순위 가져오기
* 미디어 쿼리


07.08 1차 배포 완료

1차 배포 후 결함
1. ~~회원가입 페이지 css 없음 ( CSS 추가하기 )~~(상단우측,하단좌측 이미지 골라서 넣어야함)
2. ~~구글 로그인시 /home으로 이동함 ( / 로 변경하기)~~
3. ~~걸작선페이지css 변경사항있음 (담당자에게 받은 CSS 적용시키면 됨)~~
4. movie페이지에서 movie-info페이지로 이동 후 뒤로가기로 다시 돌아온 이후에
   평점 6점이상보기, 평점 6점이하보기 작동 안됨 (뒤로가기시 페이징 실행됨이 문제)
5. 회원정보 수정시 에러 ( 프로필 변경시 파일 변경 문제 ) 
6. 회원 프로필 path ubuntu경로로 변경해야함
7. 거의없다 소개 만들어야함
8. 리뷰 별점 평균 가져오기 / 리뷰 별점 평균 순위 가져오기 추가해야함
9. movie, movieInfo, index에 리뷰 평균 점수별 이모티콘 넣어줘야함
10. 쓸모 없는 로그 및 주석 지우기
11. ~~index에서 사진의 영화제목을 누르면 로컬호스트 주소로 연결됨(웹 주소로 변경해 줘야함)~~
12. ~~제일 메인 페이지 /index 빼기~~
13. 미디어 쿼리

인덱스 컨트롤러에 로그인회원만 접근하는 페이지 뭔지 ?
게시판 이미지 어떻게 저장 되는지 ?


차후 추가해야할 기능
* 댓글 기능
* 영화 봤음 버튼
* 리뷰 페이징
* 좋아요 기능
* 검색 인물별 영화별 정렬
* 검색 부분 페이징