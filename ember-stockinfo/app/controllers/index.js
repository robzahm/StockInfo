import Ember from 'ember';
var chartConfig = {
	type: "serial",
	categoryField: "date",
	dataDateFormat:"YYYY-MM-DD",
	categoryAxis: {
		labelFrequency: 2,
		title: "Date",
		parseDates: true
	},
	valueAxes: [{
		title: "Open/Close/Low/High"
	}],
	graphs: [{
		closeField: "close",
		openField: "open",
		lowField: "low",
		highField: "high",
		valueField: "close",
		type: "line",
		balloonText: "Open:<b>[[open]]</b><br>Low:<b>[[low]]</b><br>High:<b>[[high]]</b><br>Close:<b>[[close]]</b><br>",
		bullet: "round",
		type: "candlestick",
		fillColors: "#da6d2e",
		lineColor: "#da6d2e",
		lineAlpha: 1,
		fillAlphas: 0.9,
		negativeFillColors: "#7f8da9",
		negativeLineColor: "#7f8da9",
	}],
	chartCursor: {
		valueLineEnabled: true,
		valueLineBalloonEnabled: true,
		zoomable: false
	}
};

export default Ember.Controller.extend({
	actions: {
		// Execute the company search lookup
		executeCompanySearch(param) {
			// TODO: Require a minimum number of characters
			if(param !== "") {
				this.store.query('company', { name: param}).then((result) => {
					// Set the list for display in the component template
					this.set('resultList',result);

					// Determine if no results were found, and it so, set the component property
					var resultArray = result.toArray();
					var pojoResultArray = JSON.parse(JSON.stringify(resultArray));
					console.log(pojoResultArray.length);
					if (pojoResultArray.length == 0)
						this.set('noResultsFound', true);
					else
						this.set('noResultsFound', false);
				});
			} else {
				this.set('resultList', null);
			}
		},
		// Execute the quote lookup
		executeQuoteSearch(param) {
			if(param !== "") {
				this.store.query('quote', {symbol: param}).then((result) => {
					// Turn the array into a plain JS object for use by amCharts
					var resultArray = result.toArray();
					var pojoResultArray = JSON.parse(JSON.stringify(resultArray));
					this.set('quoteList', pojoResultArray);

					// Set the data on the chart
					chartConfig.dataProvider = pojoResultArray;;

					// Create the chart and write it to the div
					AmCharts.makeChart("chartDiv", chartConfig);
				});
			}
		}
	}
});
