'use strict';

/* Controllers */

var meetingControllers = angular.module('meetingControllers', []);

meetingControllers.controller('AliveCtrl', function($scope, $http) {
  $http.get('../meeting/organizer/alive').success(function(data){
    $scope.alive = data.text;
  })
});

meetingControllers.controller('MeetingRoomsCtrl', function($scope, $http) {
  $http.get('../meeting/organizer/meetingrooms').success(function(data){
    $scope.meetingRooms = data;
  });
  $scope.meetingRoom = "room";
});


