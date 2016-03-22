import DS from 'ember-data';

export default DS.RESTAdapter.extend({
	host: 'http://localhost:8080',
	handleResponse: function(status, headers, payload){
    if(status !== 200){
      alert("An error has occurred, please try again later");
    }
    return this._super(...arguments);
  }
});
