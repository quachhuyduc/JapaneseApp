var data = null;

var checkDataUpdate = function() {
	if (!window.localStorage) {
		console.log("skipped not checkDataUpdate (localStorage is not supported)");
		return;
	}
    //	var timestamp = data && data.ts || "0";
    //	var url = "http://www.nihongonoki.com/study/jlpt/n5/data?ts=" + timestamp;
    //	console.log("check data update: " + url);
    //	$.ajax(url, {
    //		dataType: "script"
    //	});
};
var saveData = function(lang, newData) {
	if (window.localStorage) {
		localStorage[lang] = JSON.stringify(newData);
	}
};

(function(){
 
 var lang = getLanguage();
 console.log("language: " + lang);
 //	var storedData = window.localStorage ? localStorage[lang] : null;
 //	if (!storedData) {
 console.log("load from local file");
 document.write('<script type="text/javascript" src="data/' + lang + '/data.js"></script>');
 //	}
 //	else {
 //		try {
 //			console.log("parse JSON data");
 //			data = JSON.parse(storedData);
 //		}
 //		catch (e) {
 //			console.error("JSON.parse error: " + e);
 //		}
 //	}
 })();
