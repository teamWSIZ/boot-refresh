
angular.module('myApp.controllers',[]);


//Kontroler użyty na panelu userów (panel zarządzania danymi)
angular.module('myApp.controllers')
.controller('masterCtrl',
    ['$rootScope','$scope', '$http', '$timeout', '$interval',
        function ($rootScope, $scope, $http, $timeout, $interval) {
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
