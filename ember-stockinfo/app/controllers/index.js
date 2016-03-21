import Ember from 'ember';
/*
var chartData = [{
    "symbol":"YHOO",
    "date": "2016-03-10",
    "averagePrice": 4252
  }, {
    "symbol":"YHOO",
    "date": "2016-03-11",
    "averagePrice": 1882
  }, {
    "symbol":"YHOO",
    "date": "2016-03-12",
    "averagePrice": 1809
  }, {
    "symbol":"YHOO",
    "date": "2016-03-13",
    "averagePrice": 1322
  }, {
    "symbol":"YHOO",
    "date": "2016-03-14",
    "averagePrice": 1122
  }, {
    "symbol":"YHOO",
    "date": "2016-03-15",
    "averagePrice": 1114
  }, {
    "symbol":"YHOO",
    "date": "2016-03-16",
    "averagePrice": 984
  }];
*/
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
          var resultArray = result.toArray();
          var pojoResultArray = JSON.parse(JSON.stringify(resultArray));

          console.log("Result Array: " + JSON.stringify(resultArray));

          var chart = new AmCharts.AmSerialChart();
          chart.dataProvider = pojoResultArray;
          chart.categoryField = "date";

          var categoryAxis = chart.categoryAxis;
          categoryAxis.labelFrequency = 2;

          var graph = new AmCharts.AmGraph();
          graph.valueField = "averagePrice";
          graph.type = "line";
          graph.balloonText = "Average Price: <b>[[value]]</b>";
          graph.bullet = "round";
          chart.addGraph(graph);
          chart.write('chartdiv');



        });
      }
    }
  }
});
