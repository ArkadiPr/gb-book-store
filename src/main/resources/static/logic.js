var app = angular.module('app', ['ngRoute']);
var contextPath = 'http://localhost:8189/store'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'about-page.html',
            controller: 'aboutController'
        })
        .when('/books', {
            templateUrl: 'book-store.html',
            controller: 'booksController'
        })
});

app.controller('aboutController', function ($scope, $http) {
    fillTable = function () {
        $http.get(contextPath + '/api/v1/books/dtos')
            .then(function (response) {
                $scope.PopularBooksList = response.data;
            });
    }
    fillTable();
});

app.controller('booksController', function ($scope, $http) {

    $scope.paramlist = [];

    fillGenres = function(){
        $http.get(contextPath + '/api/v1/books/genres')
            .then(function (response) {
                $scope.GenresList = response.data;
            });
    };

    fillTable = function ($page, $size) {
        // $http.get(contextPath + '/api/v1/books')
        // $http.get(contextPath + '/api/v1/books/page?p=2&s=7')
        $http.get(contextPath + '/api/v1/books/page?p=' + $page + '&s=' + $size)
            .then(function (response) {
                // $scope.BooksList = response.data;
                $scope.BooksList = response.data.content;
                $scope.TotalElements = $scope.BooksList.length;
                // $scope.TotalPages = (Math.ceil($scope.BooksList.length/5));
                $scope.TotalPages = response.data.totalPages;
                $scope.range = new Array($scope.TotalPages);
                $scope.currentPage = response.data.number;
                $scope.pagesize = response.data.size;

                // $scope.range = _.range(1, $scope.TotalPages);
                // var range = _.range(1, 11);
            });
    };

    $scope.submitCreateNewBook = function () {
        $http.post(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                $scope.BooksList.push(response.data);
            });
    };

    fillTable(0, 5);
    fillGenres();

    nextpage = function ($page) {
        fillTable(parseInt($page) + 1, 5)
    }

    prevpage = function ($page) {
        fillTable(parseInt($page) - 1, 5)
    }

    $scope.paramlist = [];
    $scope.queryStr = '';
    $scope.titlePart = '';
    $scope.applyFilter = function () {
        // $scope.queryStr = $scope.queryStr.concat('check');
        // $scope.queryStr.concat('check');
        if($scope.titlePart){
            $scope.queryStr.concat(this.titlePart)
            $scope.paramlist.push(this.titlePart);
            // $scope. = '';
            // $scope.list.push(this.ti);
        }
    }

    $scope.list = [];
    $scope.text = 'hello';
    $scope.submit = function() {
        if ($scope.text) {
            $scope.list.push(this.text);
            $scope.text = '';

            $scope.queryStr.concat(this.text);
        }
    };

    // $scope.alert = function() {
    //     $window.alert('angular click');
    // };
});