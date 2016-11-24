var app = angular
            .module('app', ['ui.router', 'ngMessages'])
            .config(config)
            .run(run)

function config($stateProvider, $urlRouterProvider, $httpProvider) {
    // Default route
    $urlRouterProvider.otherwise("/books");

    // App routes
    $stateProvider
        .state('books', {
            url: '/books',
            templateUrl: '../views/books.html',
            controller: 'booksController',
            controllerAs: 'model'
        })
}

function run($rootScope, $location) {
}

