import Ember from 'ember';

export default Ember.Controller.extend({
  filteredList: null,
  actions: {
    autoComplete(param) {
      // TODO: Require a minimum number of characters
      if(param !== "") {
      	this.store.query('company', { name: param}).then((result) => {
      		this.set('resultList',result);
      	});
      } else {
      	this.set('resultList', null);
      }
    }
  }
});
