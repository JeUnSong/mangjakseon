//별점에 관한 js
$(function () {
    $(".my-rating-9").starRating({
        initialRating: null,
        disableAfterRate: null,
        onHover: function (currentIndex, currentRating, $el) {
            console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
            $('.live-rating').text(currentIndex);
        },
        onLeave: function (currentIndex, currentRating, $el) {
            console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
            $('.live-rating').text(currentRating);
            $('.live-rating-form').val(currentRating);
        }
    });
});
//리뷰 등록에 관한 js
$(document).ready(function () {
    var movieId = $('.movieId').val();
    console.log(movieId);
    var listGroup = $(".reviewContent1");
    jQuery.noConflict();
    //나중에 '/reviews/review/'+movieId로 바꾸기
    $.getJSON('/movies/movie/' + movieId, function (arr) {
        console.log(arr);
    })//end getJSON

    //댓글 추가될 영역 지정
    var listGroup = $(".reviewContent1");

    //날짜 처리를 위한 코드
    function formatTime(str) {
        var date = new Date(str);

        return date.getFullYear() + '/' +
            (date.getMonth() + 1) + '/' +
            date.getDate() + ' ' +
            date.getHours() + ':' +
            date.getMinutes();
    }

    //movieId에 해당하는 댓글 처리 코드 11111을 movieId로 변경해야됨
    //댓글보는곳 모양 만들기
    function loadJSONData() {
        //나중에 '/reviews/review/'+movieId로 바꾸기
        $.getJSON('/movies/movie/' + movieId, function (arr) {

            console.log(arr);
            var str = "";
            $.each(arr, function (idx, review) {
                console.log(review);
                str += '<div class="card-body" >';
                str += ' <div class="card-body-ex" data-reviewNum="' + review.reviewNum + '" data-toggle="modal" data-target="#staticBackdrop">';
                str += '    <div class="card-body1">'
                str += '        <img class="card-body1-memberImage" src="/assets/dummy/green.jpg"  width="70" height="70">';
                str += '        <div class="card-body1-memberNickname">' + review.nickName + '</div>';
                str += '    </div>';
                str += '    <div class="card-body2">'
                str += '        <div class="card-body2-reviewTitle" style="cursor:pointer;">' + review.reviewTitle + '</div>';
                str += '        <div class="card-body2-reviewRegdate">' + formatTime(review.regDate) + '</div>';
                str += '    </div>';
                str += '    <div class="card-body3-rating">' + review.score + '</div>';
                str += '    <div class="card-body4-reviewContent">' + review.reviewContent + '</div>';
                str += '   </div>';
                str += '    <div class="card-body5-like">'
                str += '        <i class="fa-solid fa-thumbs-up"></i>'
                str += '        <span>' + review.likeCount + '<span>'
                str += '    </div>';
                str += '    <div class="card-body6-reply">' + '댓글쓰기' + '</div>';
                str += '    <div class="card-body7-empty">' + '<br>' + '<br>' + '<br>' + '<br>' + '<br>' + '<br>' + '</div>';
                str += '</div>';
            })

            listGroup.html(str);
        });
    }

    //댓글목록 출력
    loadJSONData();

    //리뷰 등록
//    var modal = $('.modal');

    //리뷰쓰기 버튼 누르면 모달창 띄우기, 텍스트 박스 초기화
    $("#loginModalButton").click(function () {


//        $('#writeReview').modal('show');

        $('input[name="reviewTitle"]').val('');
        $('.reviewContent').val('');

        $(".reviewRemove, .reviewModify").hide();
        $(".reviewSave").show();

    });

    //리뷰 저장 버튼 누르면 ajax로 controller보내서 저장
    $(".reviewSave").click(function () {
        var review = {
            reviewTitle: $('input[name="reviewTitle"]').val(),
            reviewContent: $("textarea.reviewContent").val(),
            movieId: $('input[name="movieId"]').val(),
            score: $('input[name="score"]').val(),
            likeCount: 0,
            memberId: "022db29c-d0e2-11e5-dddd-60f81dca7676"
        }
        console.log(review);
        $.ajax({
            url: '/movies',
            method: 'post',
            data: JSON.stringify(review),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                console.log(data);

                var newReviewNum = parseInt(data);

                alert("댓글이 등록되었습니다.")
                jQuery.noConflict();
//                $('#writeReview').hide();
                loadJSONData();
            }
        })
    });

    //리뷰누르면 수정,삭제 창 띄우기
    $('.reviewContent1').on("click", ".card-body-ex", function () {

        var reviewNum = $(this).data(reviewNum);
        console.log(reviewNum);
        const obj = JSON.stringify(reviewNum);
        console.log(obj);
        const obj2 = JSON.parse(obj);
        console.log(obj2.reviewnum);

        $("input[name='reviewTitle']").val($(this).find('.card-body2-reviewTitle').html());
        $('.reviewContent').val($(this).find('.card-body4-reviewContent').html());
        $("input[name='reviewNum']").val(reviewNum.reviewnum);

        $(".reviewSave").hide();
        $(".reviewRemove,.reviewModify").show();

//         $('#writeReview').modal('show');
    });

    $(".reviewRemove").on("click", function () {
        var reviewNum = $("input[name='reviewNum']").val(); //모달창에 보이는 댓글 번호 hidden처리 되어있음
        console.log(reviewNum);
        $.ajax({
            url: '/movies/' + reviewNum,
            method: 'delete',

            success: function (result) {
                console.log("result: " + result);
                if (result === 'success') {
//                    alert("댓글이 삭제되었습니다");
//                     $('#writeReview').modal('hide');
                    loadJSONData();
                }
            }
        })
    });


    $(".reviewModify").click(function () {
        var reviewNum = $("input[name='reviewNum']").val();

        var review = {

            reviewNum: reviewNum,
            reviewTitle: $('input[name="reviewTitle"]').val(),
            reviewContent: $("textarea.reviewContent").val(),
            movieId: $('input[name="movieId"]').val(),
            score: $('input[name="score"]').val()
        }
        console.log(review);

        $.ajax({
            url: '/movies/' + reviewNum,
            method: 'put',
            data: JSON.stringify(review),
            contentType: 'application/json; charset=utf-8',
            success: function (result) {

                console.log("RESULT: " + result);

                if (result === 'success') {
//                    alert("댓글이 수정되었습니다.");
//                    $('#writeReview').modal('hide');
                    loadJSONData();
                }
            }
        });
    });
});






