import Ember from 'ember';

export default Ember.Controller.extend({
  filteredList: null,
  actions: {
    autoComplete(param) {
      if(param !== "") {
      	this.store.findAll('company').then((result) => {
      		this.set('resultList',result);
      	});
      } else {
      	this.set('resultList').clear();
      }
    },
    search(param) {
      if(param !== "") {
        this.store.query('companies', {name: param}).then((result) => {
          this.set('model',result);
        });
      } else {
        this.set('model').clear();
      }
    }
  }
});
