import Ember from 'ember';

export default Ember.Component.extend({
	searchbox: null,
	resultList: null,
	actions: {
	    searchBoxAutoComplete() {
	      this.get('autoCompleteFunction')(this.get('searchbox'));
	    },
	    choose(company) {
	      this.set('searchbox', company);
	      this.set('resultList', null);
	      this.get('companySelectFunction')(this.get('searchbox'));
	    }
	}
});
