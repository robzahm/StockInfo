import DS from 'ember-data';

export default DS.Model.extend({
  symbol: DS.attr(),
  averagePrice: DS.attr(),
  date: DS.attr()
});
