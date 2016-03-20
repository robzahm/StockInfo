import Ember from 'ember';

var chartData = [{
    "country": "USA",
    "visits": 4252
  }, {
    "country": "China",
    "visits": 1882
  }, {
    "country": "Japan",
    "visits": 1809
  }, {
    "country": "Germany",
    "visits": 1322
  }, {
    "country": "UK",
    "visits": 1122
  }, {
    "country": "France",
    "visits": 1114
  }, {
    "country": "India",
    "visits": 984
  }, {
    "country": "Spain",
    "visits": 711
  }, {
    "country": "Netherlands",
    "visits": 665
  }, {
    "country": "Russia",
    "visits": 580
  }, {
    "country": "South Korea",
    "visits": 443
  }, {
    "country": "Canada",
    "visits": 441
  }, {
    "country": "Brazil",
    "visits": 395
  }, {
    "country": "Italy",
    "visits": 386
  }, {
    "country": "Australia",
    "visits": 384
  }, {
    "country": "Taiwan",
    "visits": 338
  }, {
    "country": "Poland",
    "visits": 328
  }];

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
          //this.set('quoteList',result);




          //AmCharts.ready(function() {
            console.log("Amcharts Ready");
            var chart = new AmCharts.AmSerialChart();
            //chart.dataProvider = result;
            chart.dataProvider = chartData;
            chart.categoryField = "country";

            var graph = new AmCharts.AmGraph();
            graph.valueField = "visits";
            graph.type = "column";
            chart.addGraph(graph);

            chart.write('chartdiv');
          //});


          console.log("Post AmCharts");



        });
      }
    }
  }
});
