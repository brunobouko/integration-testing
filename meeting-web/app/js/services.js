'use strict';

/* Services */

var meetingServices = angular.module('meetingServices', ['ngResource']);

meetingServices.factory('MeetingRooms', ['$resource',
  function($resource){
    return $resource('phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);
