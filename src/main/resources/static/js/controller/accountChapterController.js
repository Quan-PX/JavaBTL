var app = angular.module('ngApp', []);

app.controller('accountChapterController', accountChapterCtrl);

accountChapterCtrl.$inject = ['$scope', 'HomeService'];

function accountChapterCtrl($scope, HomeService) {

    $scope.listChapter = [];
    $scope.totalPages = 0;
    $scope.currentPage = 1;
    $scope.page = [];
    $scope.search = '';
    $scope.storyId = 0;

    $scope.init = function (id) {
        $scope.storyId = id;
        $scope.getListChapter(1);
    };

    $scope.getListChapter = function (pagenumber) {
        if ($scope.search.trim().length !== 0 && isNaN(parseFloat($scope.search)) === true) {
            callWarningSweetalert("Số thứ tự phải là số");
        } else {
            if (pagenumber === undefined) {
                pagenumber = 1;
            }

            var url = window.location.origin + '/api/chapterByStory';
            var data = new FormData();
            data.append('pagenumber', pagenumber);
            data.append("storyId", $scope.storyId);
            data.append("search", encryptText($scope.search));
            HomeService.getData(url, data).then(function (response) {
                $scope.listChapter = response.data.content;
                $scope.totalPages = response.data.totalPages;
                $scope.currentPage = response.data.number + 1;
                var startPage = Math.max(1, $scope.currentPage - 2);
                var endPage = Math.min(startPage + 4, $scope.totalPages);
                var pages = [];
                for (var i = startPage; i <= endPage; i++) {
                    pages.push(i);
                }
                $scope.page = pages;
            }, function errorCallback(errResponse) {
                swal({
                    text: 'Bạn chưa đăng nhập',
                    type: 'warning',
                    confirmButtonText: 'Ok'
                }).then(function () {
                    window.location.reload();
                })
            })
        }
    };

    $scope.deleteChapter = function (id) {
        var url = window.location.origin + '/api/deleteChapter/' + id;
        HomeService.deleteData(url).then(function (response) {
            swal({
                text: 'Xóa Chương Thành Công',
                type: 'success',
                confirmButtonText: 'Ok'
            }).then(function () {
                $scope.getListChapter(1);
            })
        }, function errorCallback(errResponse) {
            swal({
                text: errResponse.data.messageError,
                type: 'warning',
                confirmButtonText: 'Ok'
            }).then(function () {
                $scope.getListChapter(1);
            })
        })
    };

}