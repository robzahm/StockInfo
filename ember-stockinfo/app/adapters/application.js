import DS from 'ember-data';

export default DS.RESTAdapter.extend({
	host: 'http://localhost:8080',
	handleResponse: function(status, headers, payload){
    if(status !== 200){
      // Adapter error handling here
      // Improve this
      alert("An error occurred from the server with HTTP code: " + status + " and message: " + payload.message);
    }
    return this._super(...arguments);
  }
});
