import Ember from 'ember';

var companies = [{
	symbol: 'AAA',
	name: 'Acme Apple Associates'
}, {
	symbol: 'BBB',
	name: 'Big Bad Bears'
}, {
	symbol: 'CCC',
	name: 'Cavorting Capable Carnivores'
}]

export default Ember.Route.extend({
	model() {
		return companies;
	}
});
