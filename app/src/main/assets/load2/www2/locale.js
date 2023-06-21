
var getLanguage = function() {
	
	var lang = (window.__android__) ? String(__android__.getLanguage()) : navigator.language || navigator.browserLanguage || navigator.userLanguage;
	//console.log("lang: " + lang);
	lang = lang.toLowerCase();
	if (lang.indexOf("ja") === 0) {
		return "ja";
	}
	else if (lang.indexOf("ko") === 0) {
		return "ko";
	}
	else if (lang.indexOf("zh") === 0) {
		if (lang.indexOf("tw") > -1 || lang.indexOf("hk") > -1 || lang.indexOf("hant") > -1) {
			return "zh_tw";
		}
		else {
			return "zh_cn";
		}
	}
	else {
		return "en";
	}
};

var Msg = {};
(function(){
	Msg = {
		init: function() {
			var lang = getLanguage();
			if ("ja" === lang) { 
				Msg = _ja();
			}
			else if ("ko" === lang) {
				Msg = _ko();
			}
			else if ("zh_tw" === lang) {
				Msg = _zhTw();
			}
			else if ("zh_cn" === lang) {
				Msg = _zhCn();
			}
			else {
				Msg = _en();
			}
		}
	};
	
	var _ja = function() {
		return {
			title: "にほんご２",
			resultTmpl: "${total}問中、${correct}問正解です！",
			answerMarkCorrectText: "あたり",
			answerMarkWrongText: "ハズレ",
			answerNext: "つぎのクイズ",
			answerResult: "成績",
			answerBack: "もどる",
//			shareQuiz: "このクイズを共有",
			resultNext: "メニュー"
		};
	};
	var	_en = function() {
		return {
			title: "Japanese 2",
			resultTmpl: "You got ${correct} out of ${total}!",
			answerMarkCorrectText: "You've got it!",
			answerMarkWrongText: "Try again",
			answerNext: "Next",
			answerResult: "Result",
			answerBack: "Back",
//			shareQuiz: "Share this quiz",
			resultNext: "Menu"
		};
	};
	var _ko = function() {
		return {
			title: "배우자! 일본어2",
			resultTmpl: "${total}문제중 ${correct}문제  맞았습니다!",
			answerMarkCorrectText: "딩동댕! 정답입니다",
			answerMarkWrongText: "땡! 틀렸습니다",
			answerNext: "다음 퀴즈",
			answerResult: "성적",
			answerBack: "퀴즈로 돌아가기",
//			shareQuiz: "이 퀴즈를 공유",
			resultNext: "메뉴"
		};
	};
	var _zhCn = function() {
		return {
			title: "日语２",
			resultTmpl: "${total}問中、${correct}問正解です！",
			answerMarkCorrectText: "答对了",
			answerMarkWrongText: "答错了",
			answerNext: "下一个问题",
			answerResult: "结果",
			answerBack: "回到问题",
//			shareQuiz: "分享给朋友",
			resultNext: "选单"
		};
	};
	var _zhTw = function() {
		return {
			title: "日語２",
			resultTmpl: "${total}題中、答對${correct}題！",
			answerMarkCorrectText: "答對了",
			answerMarkWrongText: "答錯了",
			answerNext: "下一個問題",
			answerResult: "結果",
			answerBack: "回到問題",
//			shareQuiz: "分享給朋友",
			resultNext: "選單"
		};
	};
})();
