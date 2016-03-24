import DS from 'ember-data';

export default DS.Model.extend({
  symbol: DS.attr(),
  averagePrice: DS.attr(),
  date: DS.attr(),
  open: DS.attr(),
  close: DS.attr(),
  high: DS.attr(),
  low: DS.attr()
});
