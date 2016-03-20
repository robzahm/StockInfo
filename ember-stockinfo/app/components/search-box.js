import Ember from 'ember';

export default Ember.Component.extend({
	searchbox: null,
	resultList: null,
	actions: {
	    autoComplete() {
	      this.get('autoComplete')(this.get('searchbox'));
	    },
	    choose(company) {
	      this.set('searchbox', company);
	    }
	}
});
