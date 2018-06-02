
angular.module('myApp.controllers',[]);


//Kontroler użyty na panelu userów (panel zarządzania danymi)
angular.module('myApp.controllers')
.controller('masterCtrl',
    ['$rootScope','$scope', '$http', '$timeout', '$interval', '$q',
        function ($rootScope, $scope, $http, $timeout, $interval, $q) {
            //To jest uruchamiane przy każdym wejściu do widoku korzystającego z tego kontrolera
            //Lokalny obiekt modelu, tworzony przy każdym uruchomieniu kontrolera
            $scope.M = {};
            $scope.fff = '';

            //////////////////////////////////////////
            // Zestaw funkcji do zarzadzania pytaniami

            $scope.M.questions = [];
            $scope.M.types = [];

            //add adding new question
            $scope.loadQuestions = function() {
                $http.get($rootScope.M.URL + '/questions')
                    .success(function (data) {
                        $scope.M.questions = data;
                    })
            };

            $scope.saveQuestion = function(qu) {
                console.log("zapisuję pytanie");
                return $http({
                    url: $rootScope.M.URL + '/questions',
                    method: 'PUT',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify(qu)
                }).success(function(data){
                    console.log('Saved question qid=' + qu.qid)
                });
            };

            $scope.deleteQuestion = function(qu) {
                console.log("wycinam...");
                return $http({
                    url: $rootScope.M.URL + '/questions/' + qu.qid,
                    method: 'DELETE',
                    headers: {'Content-Type': 'application/json'}
                }).success(function(data){
                    console.log('Deleted question qid=' + qu.qid)
                });
            };

            $scope.saveAllQuestions = function () {
                console.log('Saving all questions');
                $scope.M.questions.forEach(function(q){
                    $scope.saveQuestion(q);
                });
            };

            ///////////////////////////////
            $scope.loadTypes = function() {
                $http.get($rootScope.M.URL + '/qtypes')
                    .success(function (data) {
                        $scope.M.types = data;
                    })
            };

            $scope.saveType = function(qtype) {
                console.log("zapisuję typ pytań " + JSON.stringify(qtype));
                return $http({
                    url: $rootScope.M.URL + '/qtypes',
                    method: 'PUT',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify(qtype)
                }).success(function(data){
                    console.log('Saved qtype:' + JSON.stringify(data));
                    $scope.loadTypes();
                });
            };

            $scope.deleteType = function(qtype) {
                console.log("usuwam typ pytań " + JSON.stringify(qtype));
                return $http({
                    url: $rootScope.M.URL + '/qtypes/' + qtype.qtid,
                    method: 'DELETE'
                }).success(function(data){
                    console.log('Removed qtype:' + JSON.stringify(data));
                    $scope.loadTypes();
                });
            };



            $scope.addQuestion = function () {
                let nowy = {
                    "qid": null,
                    "text": "",
                    "answer1": "",
                    "answer2": "",
                    "answer3": "",
                    "correct": 1,
                    "typeid": 1,
                    "active": true
                };
                $scope.M.questions.push(nowy);
            };


            //startup
            $scope.loadQuestions();
            $scope.loadTypes();


            //////////////////////////////////////////
            // async, primises, CopletableFutures

            //returns CompletableFuture<Integer>
            function longcall(dur) {
                return $http.get($rootScope.M.URL + '/longcall?duration=' + dur)    //already CF
                    .then(function (response) {
                        //in .success() we already get `data`
                        //in .then() we get `response`, of which the we must extract data, response.data
                        console.log('inner res:' + response.data.result);
                        return response.data.result;
                    });     //further (processed) CF
            };

            $scope.executeAsync = function() {
                console.log('start...');
                longcall(501).then(function (res) {
                        console.log('returned: ' + res);
                    });
            };

            $scope.executeTwoAsyncWaitForBoth = function() {
                console.log('start...');
                let cf1 = longcall(1501);
                let cf2 = longcall(2701);
                $q.all([cf1, cf2])
                    .then(function (resarr) {
                            let res1, res2;
                            [res1, res2] = [resarr[0], resarr[1]];
                            console.log('from 1: ' + res1 + ', from 2: ', res2);
                        }, function (reason) {
                            console.log('An error has occurred:' + reason);
                        }
                    );
            };




            //////////////////////////////////////////

            // $timeout(function(){
            //     $scope.M.message += '.';
            //     alert('Done');
            // }, 1000);

            // $interval(function(){
            //     $scope.M.message += 'x';
            // }, 2000);

        }
    ]
);
