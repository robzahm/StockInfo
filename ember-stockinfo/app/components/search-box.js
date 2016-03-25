import Ember from 'ember';

export default Ember.Component.extend({
	actions: {
		// Call the function passed into the component to perform
		// the company lookup
		searchBoxAutoComplete() {
			this.get('autoCompleteFunction')(this.get('searchbox'));
		},
		// Handle the company selection, ultimately calling the function
		// passed into the component to execute the stock price history lookup
		choose(company) {
			this.set('searchbox', company);
			this.set('resultList', null);
			this.get('companySelectFunction')(this.get('searchbox'));
		},
		// Clear out the contents of the searchbox when the textbox receives focus
		clearSearchbox() {
			this.set('searchbox', null);
		}
	}
});
