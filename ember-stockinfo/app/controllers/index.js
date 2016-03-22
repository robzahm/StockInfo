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
          var resultArray = result.toArray();
          var pojoResultArray = JSON.parse(JSON.stringify(resultArray));

          //console.log("Result Array: " + JSON.stringify(resultArray));

          var chart = new AmCharts.AmSerialChart();
          chart.dataProvider = pojoResultArray;
          chart.categoryField = "date";

          var categoryAxis = chart.categoryAxis;
          categoryAxis.labelFrequency = 2;
          categoryAxis.title = "Date";

          var graph = new AmCharts.AmGraph();
          graph.valueField = "averagePrice";
          graph.type = "line";
          graph.balloonText = "[[category]]: <b>[[value]]</b>";
          graph.bullet = "round";
          chart.addGraph(graph);
          chart.write('chartdiv');



        });
      }
    }
  }
});
