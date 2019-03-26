var ticTacToe=angular.module('ticTacToe',['ngRoute','gameModule']);

ticTacToe.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/about', {
            templateUrl: 'templates/about.html'
        }),
        when('/player/create', {
            templateUrl: 'templates/game-board.html',
            controller: 'gameController'
        }),
        when('/game/:id', {
            templateUrl: 'templates/game-board.html',
            controller: 'newGameController'
        }),
        when('/player/panel', {
            templateUrl: 'templates/player-panel.html',
            controller: 'newGameController'
        }),
        otherwise({
            redirectTo: '/player/panel'
        });
}])