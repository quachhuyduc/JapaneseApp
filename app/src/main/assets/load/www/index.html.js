
(function() {
	
	var QUIZ_COUNT = 20;

	var getCategoryList = function() {
		return data.categoryData;
	};
	var getQuizArray = function(categoryId) {
		return data.quizData[categoryId];
	};

	var _quizList = null, _resultList = null, _quiz = null, _quizIndex = 0, _categoryName = "";
	var $quizNum, $questionText, $answerMsg, $shareQuiz, $answerNext, $answerResult;
	
	var initQuizList = function(categoryId) {
		var quizArray = getQuizArray(categoryId);
		shuffle(quizArray);
		_quizList = quizArray.slice(0, QUIZ_COUNT);
		_quizIndex = 0;
		_resultList = [];
	};
	
	var shuffle = function(li) {
	    for (var i = 0, _len = li.length; i < _len; i++) {
	        var swapIdx = Math.floor(Math.random() * _len);
	        var swap = li[swapIdx];
	        li[swapIdx] = li[i];
	        li[i] = swap;
	    }
	    return li;
	};
	
	var Quiz = function(quizData) {
		this._data = quizData;
	};
	Quiz.prototype = {
		getPk: function() {
			return this._data["k"];
		},
		getQuestionText: function() {
			return this._data.f["q"];
		},
		getChoiceText: function(index) {
			return this._data.f[index];
		},
		getAnswer: function() {
			return this._data.f["a"];
		},
		getAnswerMsg: function() {
			return this._data.f["m"];
		},
		getDescription: function() {
			var strBuf = [this.getQuestionText()];
			for(var choiceIdx = 1; choiceIdx <= 4; choiceIdx++) {
				var choiceText = this.getChoiceText(choiceIdx);
				if (choiceText) {
					strBuf.push("　", choiceIdx, ". ", choiceText);
				}
				else {
					break;
				}
			}
			return strBuf.join("").replace(/<[^>]+>/g, "");
		},
		getShareQuizUrl: function() {
			var pk = this.getPk();
			var name = Msg.title;
			var caption = _categoryName;
			var description = this.getDescription();
			var shareQuizUrl = [
				"http://www.nihongonoki.com/study/share",
				"?pk=", encodeURIComponent(pk),
				"&name=", encodeURIComponent(name),
				"&caption=", encodeURIComponent(caption),
				"&description=", encodeURIComponent(description)
			].join("");
			return shareQuizUrl;
		}
	};
	
	var onDocumentReady = function() {
		
		//console.log("document ready called");
		if (Math.min(screen.width, screen.height) > 540) {
			$("body").css("font-size", "1.3em");
		}
		Msg.init();
		document.title = Msg.title;
		$("#answerMarkCorrectText").text(Msg.answerMarkCorrectText);
		$("#answerMarkWrongText").text(Msg.answerMarkWrongText);
		$("#answerNext").text(Msg.answerNext);
		$("#answerResult").text(Msg.answerResult);
		$("#answerBack").text(Msg.answerBack);
		$("#shareQuiz").text(Msg.shareQuiz);
		$("#resultNext").text(Msg.resultNext);
		$("#recommendMsg").html(Msg.recommendMsg);
		
		$quizNum = $(".quizNum");
		$questionText = $("#questionText");
		$answerMsg = $("#answerMsg");
		$shareQuiz = $("#shareQuiz");
		$answerNext = $("#answerNext");
		$answerResult = $("#answerResult");
		$answerMarkCorrect = $("#answerMarkCorrect");
		$answerMarkWrong = $("#answerMarkWrong");
		
		$shareQuiz.click(onShareQuizClicked);
		
		$("#categoriesHeaderTitle").text(Msg.title);
		document.title = Msg.title;
		var $categoryList = $("#categoryList");
		var categoryList = getCategoryList();
		var strBuf = [];
		for (var i = 0, categoryListLength = categoryList.length; i < categoryListLength; i++) {
			var categoryLevel = categoryList[i];
			var levelName = categoryLevel.name;
			if (levelName) {
				strBuf.push('<li data-role="list-divider" class="divider">' + levelName + '</li>');
			}
			var categories = categoryLevel.categories;
			for(var idx = 0, categoriesLength = categories.length; idx < categoriesLength; idx++) {
				var category = categories[idx];
				var shouldUseBigFont = category.bigFont ? "bigFont='true' " : "";
				strBuf.push("<li><a class='btnCategory' categoryId='" + category.id + "' " + shouldUseBigFont + "href='#questionPage'>" + category.name + "</a></li>");
			}
		}
		strBuf.push(Msg.ads);
		$categoryList.html(strBuf.join(""));
		$("#categoryList .btnCategory").click(onBtnCategoryClicked);
		//$categoryList.listview("refresh");
		setTimeout(checkDataUpdate, 100);
		//checkDataUpdate();
	};
	
	var onCategoryPageBeforeShow = function() {
		//console.log("categoryPage pagebeforeshow called");
		$answerNext.css("display", "block");
		$answerResult.css("display", "none");
		_quizList = null;
		_resultList = null;
		_quiz = null;
		_quizIndex = 0; 
		_categoryName = "";
	};
	
	var onBtnCategoryClicked = function(event) {
		var $this = $(this);
		var categoryId = $this.attr("categoryId");
		_categoryName = $this.text();
		//console.log("clicked: " + categoryId);
		initQuizList(categoryId);
		$(".quizTitle").text(" (" + _categoryName + ")");
		$("#resultHeaderTitle").text(Msg.answerResult + " (" + _categoryName + ")");
		if ($this.attr("bigFont")) {
			$questionText.addClass("big").addClass("central");
			$answerMsg.addClass("big");
		}
		else {
			$questionText.removeClass("big").removeClass("central");
			$answerMsg.removeClass("big");
		}
	};
	
	var onQuestionPageBeforeShow = function() {
		//console.log("questionPage pagebeforeshow called" + _quizIndex);
		if (!_quizList) {
			location.href = "index.html";
			return;
		}
		var quizIndex = _quizIndex;
		var quizList = _quizList;
		_quiz = new Quiz(quizList[quizIndex]);
		var answer = _quiz.getAnswer();
		//タイトル
		var quizNum = quizIndex + 1
		$quizNum.text(quizNum);
		//選択肢を表示
		for (var i = 1; i <= 4; i++) {
			var onClickListener = (answer == i) ? onCorrectChoiceClicked : onWorngChoiceClicked;
			var choiceId = "#questionChoice" + i;
			var choiceHtml = _quiz.getChoiceText(i);
			var display = (choiceHtml) ? "block" : "none";
			$(choiceId + " .ui-btn-text").html(choiceHtml);
			$(choiceId).css("display", display).unbind("click").click(onClickListener);
		}
		//質問を表示
		var questionHtml = _quiz.getQuestionText();
		$questionText.html(questionHtml);
	};
	
	var onQuestionPageShow = function() {
		if (!_quizList) {
			return;
		}
		//正解を解説
		var answerMsgHtml = _quiz.getAnswerMsg();
		$answerMsg.html(answerMsgHtml);
		//最後のクイズなら成績ボタンを表示
		if (_quizIndex + 1 >= QUIZ_COUNT || _quizIndex >= _quizList.length) {
			$answerNext.css("display", "none");
			$answerResult.css("display", "block");
		}
		//console.log("questionPage pageshow called: " + _quizIndex);
	};
	
	var onCorrectChoiceClicked = function(event) {
		//console.log("onCorrectChoiceClicked");
		_resultList[_quizIndex] = true;
		$answerMarkCorrect.css("display", "block");
		$answerMarkWrong.css("display", "none");
		$("em").addClass("correct"); //.css("color", "green");
	};
	
	var onWorngChoiceClicked = function(event) {
		//console.log("onWorngChoiceClicked");
		_resultList[_quizIndex] = false;
		$answerMarkCorrect.css("display", "none");
		$answerMarkWrong.css("display", "block");
		$("em").removeClass("correct"); //.css("color", "red");
	};
	
	var onAnswerPageBeforeShow = function() {
		//console.log("answerPage pagebeforeshow called");
		if (!_quizList) {
			location.href = "index.html";
			return;
		}
	};
	
	var onNextQuizClicked = function(event) {
		_quizIndex += 1;
	};
	
	var onAnswerNextClicked = function() {
		_quizIndex += 1;
	};
	
	var onShareQuizClicked = function(event) {
		var url = _quiz.getShareQuizUrl();
		location.href = url;
		event.preventDefault();
	};
	
	var onResultPageBeforeShow = function() {
		//console.log("resultPage pagebeforeshow called");
		if (!_quizList) {
			location.href = "index.html";
			return;
		}
		var cntCorrects = 0;
		for (var i = 0, _len = QUIZ_COUNT; i < _len; i++) {
			if (_resultList[i]) {
				cntCorrects += 1;
			}
		}
		var msg = Msg.resultTmpl.replace("${total}", QUIZ_COUNT).replace("${correct}", cntCorrects);
		$("#resultMsg").text(msg);
	};
	
	$(document).bind("mobileinit", function() {
		$.mobile.defaultPageTransition = 'none';
		$.mobile.ajaxEnabled = false;
	}).ready(onDocumentReady);
	
	$("#categoryPage").live("pagebeforeshow", onCategoryPageBeforeShow);
	$("#questionPage").live("pagebeforeshow", onQuestionPageBeforeShow).live("pageshow", onQuestionPageShow);
	$("#answerPage").live("pagebeforeshow", onAnswerPageBeforeShow);
	$("#answerNext").live("click", onAnswerNextClicked);
	$("#resultPage").live("pagebeforeshow", onResultPageBeforeShow);
	
})();
