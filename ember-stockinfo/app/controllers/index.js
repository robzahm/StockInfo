import Ember from 'ember';
var chartConfig = {
            type: "serial",
            categoryField: "date",
            categoryAxis: {
              labelFrequency: 2,
              title: "Date"
            },
            valueAxes: [{
              title: "Average Price"
            }],
            graphs: [{
              valueField: "averagePrice",
              type: "line",
              balloonText: "[[category]]: <b>[[value]]</b>",
              bullet: "round"
            }]
          };

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

          // Set the data on the chart
          chartConfig.dataProvider = pojoResultArray;;

          // Create the chart and write it to the div
          AmCharts.makeChart("chartDiv", chartConfig);
        });
      }
    }
  }
});
