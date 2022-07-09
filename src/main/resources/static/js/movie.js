var page = 1;
const key = "";
var totalStr = "";

var listLength = page*20;

list();

$(document).ready(function (){
    $("select[name=sortLte]").change(function (){
        $(".movieList").empty();
        if($("select[name=sortLte]").val() == "10"){
            location.href='http://localhost:8181/movieLte';
        }else if($("select[name=sortLte]").val() != "10") {
            location.href='http://localhost:8181/movie';
        }
    })
});

//ajax data 불러오기
function list() {
    function movieList() {
        $.ajax({
            type: "GET",
            url: "https://api.themoviedb.org/3/discover/movie" +
                `?api_key=${key}` +
                "&language=ko&region=krr%2Cus" +
                "&sort_by=popularity.desc" +
                "&include_adult=false" +
                "&include_video=false" +
                `&page=${page}` +
                "&vote_average.gte=1" +
                "&vote_average.lte=6" +
                "&with_watch_monetization_types=flatrate",
            data: {},
            dataType: "json",
            success: function (data) {

                let $page =page;
                let list = [];
                data.results.forEach((item,idx) => {
                    list.push([
                        str = `<form action=/movie/${item.id} method="GET">`,
                        str += '<div class="listGroup'+idx+'page'+$page+'">',
                        str += `<input type="image" name="poster" class="moviePoster" src=https://image.tmdb.org/t/p/w500${item.poster_path}>`,
                        str += '<div class="movieScore">' + `TMDb <span class="tmdbAvg">${item.vote_average}</span> &nbsp 망작선` + '<span class="reviewAvg"> </span>' +'</div>',
                        str += '<div class="movieTitle">' + item.title + '</div>',
                        str += `<input type="checkbox" class="dataId" name="movieId" value=${item.id} checked>`,
                        str += '</div>',
                        str += '</form>'
                    ]);
                    totalStr += str;
                    $('.movieList').append(str)
                });
                sessionStorage.setItem("movieTotalStr", totalStr);
                sessionStorage.setItem("moviePage", page)
            }
        });
        reviewAverageMovieListView();
    }

    // 최초 목록 갱신
    $(document).ready(function (event) {

        if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
            totalStr = sessionStorage.getItem("movieTotalStr");
            $('.movieList').append(totalStr);
            reviewAverageMovieListView();
            if (sessionStorage.getItem("moviePage") != 1) {
                $('.moreButton').hide();
            }
        } else {
            movieList();
        }
    })

// 글릭시 다음 페이지 갱신
    $(document).ready(function () {
        $('.moreButton').click(function () {
            $('.moreButton').hide();
            page++
            movieList();
        });
    });

//스크롤 발생 이벤트 처리
    $(document).ready(function (event) {
        $(window).scroll(function () {
            var scrT = $(window).scrollTop();
            if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
                page = sessionStorage.getItem("moviePage");

                setTimeout(function () {
                    if (scrT == $(document).height() - $(window).height()) {
                        page++
                        movieList();
                    }
                }, 50)
            }
            setTimeout(function () {
                if (scrT == $(document).height() - $(window).height() && page != 1) {
                    page++
                    movieList();
                }
            }, 50)
        });
    });

    $(document).ready(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 200) {
                $('.top').fadeIn();
            } else {
                $('.top').fadeOut();
            }
        });
        $('.top').click(function () {
            $('html, body').animate({scrollTop: 0}, 400);
            return false;
        });
    });
    //리뷰 별점 평균 가져오는 ajax 불러오는 함수
    function reviewAverageMovieListView() {
        let $page =page;
        $.getJSON({
            url: '/reviewInfo/movie',
            success: function (data) {
                $.each(data, function (idx, obj) {
                    //console.log(obj.movieId);
                    //console.log(obj.reviewAvg);
                    for (i = 0; i < listLength; i++) {
                        let $movieId = $('.listGroup'+i+'page'+$page+'>input[name="movieId"]').val();
                        //console.log($movieId);
                        //console.log(obj.movieId);
                        //console.log($movieId == obj.movieId);
                        if ($movieId == obj.movieId) {
                            $('.listGroup'+i+'page'+$page+' .reviewAvg').html(obj.reviewAvg);
                        }
                    }
                });
            },
            error: function () { console.log("error"); }
        });
    }
}
// 스크롤 맨위로 올리기
$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 200) {
            $('.top').fadeIn();
        } else {
            $('.top').fadeOut();
        }
    });
    $('.top').click(function () {
        $('html, body').animate({scrollTop: 0}, 400);
        return false;
    });
});