import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    executeCompanySearch(param) {
      // TODO: Require a minimum number of characters
      console.log("Autocomplete");
      if(param !== "") {
      	this.store.query('company', { name: param}).then((result) => {
      		this.set('resultList',result);
      	});
      } else {
      	this.set('resultList', null);
      }
    },
    executeQuoteSearch(param) {
      console.log("Index.quoteSearch");
      if(param !== "") {
        this.store.query('quote', {symbol: param}).then((result) => {
          //alert(result);
        });
      }
    }
  }
});
