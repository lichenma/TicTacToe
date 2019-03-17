var gameModule=angular.module('gameModule',[]);

gameModule.controller('newGameController', ['$rootScope','$scope', '$http', '$location',
    function (rootScope, scope, http, location) {

        rootScope.gameId = null;
        scope.newGameData = null;

        scope.newGameOptions = {
            availablePieces: [
                {name: 'X'},
                {name: 'O'}
            ],
            selectedPiece: {name: 'O'},
            availableGameTypes: [
                {name: 'COMPETITION'},
                {name: 'COMPUTER'}
            ],
            selectedBoardDimension: {name: 'COMPUTER'}
        };

        scope.createNewGame = function () {

            var data = scope.newGameData;
            var params = JSON.stringify(data);

            http.post("/game/create", params, {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            }).success(function (data, status, headers, config) {
                rootScope.gameId = data.id;
                location.path('/game/' + rootScope.gameId);
            }).error(function (data, status, headers, config) {
                location.path('/player/panel');
            });
        }

    }]);

gameModule.controller('gamesToJoinController', ['$scope', '$http', '$location',
    function (scope, http, location) {

        scope.gamesToJoin=[];

        http.get('/game/list').success(function (data) {
            scope.gamesToJoin=data;
        }).error(function (data, status, headers, config) {
            location.path('/player/panel');
        });

        scope.joinGame=function (id) {
            var params={"id":id}

            http.post('/game/join', params, {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            }).success(function (data) {
                location.path('/game/'+data.id);
            }).error(function (data, status, headers, config) {
                location.path('/player/panel');
            });
        }
    }]);

gameModule.controller('playerGamesController', ['$scope', '$http', '$location', '$routeParams',
    function (scope, http, location, routeParams) {

        scope.playerGames= [];

        http.get('/game/player/list').success(function (data) {
            scope.playerGames = data;
        }).error(function (data, status, headers, config) {
            location.path('/player/panel');
        });

        scope.loadGame = function (id) {
            console.log(id);
            location.path('/game/'+id);
        }
    }]);

gameModule.controller('gameController', ['$rootScope', '$routeParams', '$scope', '$http',
    function (rootScope, routeParams, scope, http) {

        var gameStatus;
        getInitialData()

            function getInitialData() {
                http.get('/game/'+routeParams.id).success(function (data) {
                    scope.gameProperties= data;
                    gameStatus= scope.gameProperties.gameStatus;
                    getMoveHistory();
                }).error(function (data, status, headers, config) {
                    scope.errorMessage="Failed to load game properties";
                });
            }

            function getMoveHistory() {
                scope.movesInGame= [];

                return http.get('/move/list').success(function (data) {
                    scope.movesInGame=data;
                    scope.playerMoves=[];

                    //fill the board with positions from the retrieved moves
                    angular.forEach(scope.movesInGame, function (move) {
                        scope.rows[move.boardRow-1][move.boardColumn-1].letter = move.playerPieceCode;
                    });
                }).error(function (data, status, headers, config) {
                    scope.errorMessage= "Failed to load moves in game"
                });
            }

            function checkPlayerTurn() {
                return http.get('/move/turn').success(function (data) {
                    scope.playerTurn=data;
                }).error(function (data, status, headers, config) {
                    scope.errorMessage="Failed to get the player turn"
                });
            }

            function getNextMove() {
                scope.nextMoveData=[]

                // Computer is a second player
                if(!scope.gameProperties.secondPlayer) {
                    http.get("/move/autocreate").success(function (data, status, headers, config) {
                        scope.nextMoveData= data;
                        getMoveHistory().success(function () {
                            var gameStatus = scope.movesInGame[scope.movesInGame.length-1].gameStatus;
                            if (gameStatus!='IN_PROGRESS') {
                                alert(gameStatus)
                            }
                        });
                    }).error(function (data, status, headers, config) {
                        scope.errorMessage="Can't send move"
                    });
                }

                // Second player is a user
                else {
                    console.log(' another player\'s move');
                }
            }

            function checkIfBoardCellAvailable(boardRow, boardColumn) {

                for (var i=0; i<scope.movesInGame.length; i++) {
                    var move= scope.movesInGame[i];
                    if (move.boardColumn==boardColumn &&move.boardRow==boardRow) {
                        return false;
                    }
                }
                return true;
            }
    }])