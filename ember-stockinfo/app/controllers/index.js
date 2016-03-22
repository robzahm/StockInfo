import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    // Execute the company search lookup
    executeCompanySearch(param) {
      // TODO: Require a minimum number of characters
      console.log("Autocomplete");
      if(param !== "") {
      	this.store.query('company', { name: param}).then((result) => {
          // TODO: Handle the "No Results Found" case
          this.set('resultList',result);
      	});
      } else {
      	this.set('resultList', null);
      }
    },
    // Execute the quote lookup
    executeQuoteSearch(param) {
      console.log("Index.quoteSearch");
      if(param !== "") {
        this.store.query('quote', {symbol: param}).then((result) => {
          // Turn the array into a plain JS object for use by amCharts
          var resultArray = result.toArray();
          var pojoResultArray = JSON.parse(JSON.stringify(resultArray));

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

          // Tightly coupled to the component name, look for a better solution
          chart.write('chartdiv');
        });
      }
    }
  }
});
