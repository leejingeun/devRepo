var mainVisualEffect;
if (mainVisualEffect == undefined) {
	mainVisualEffect = function (o) {
		
		var this_s = this;
		this.o =  $.extend({obj:null,contents:".datalist li",numctrs:".ctrl-nums a",toggleBtn:true,prevBtn:null,nextBtn:null,stopBtn:null,playBtn:null,isPlay:false,speed:6000,initNum:1,flickEvt:false}, o||{});

		this.Timer = null;
		this.isPlay =  (this.o.isPlay)? true:false;;
	

		var $this = $(o.obj);
		this.$cont = $(this.o.contents,$this);
		this.$numBtns = $(this.o.numctrs,$this);
		this.seq = 0;
		this.$this = $this;

	


		if(this.o.prevBtn==null) this.o.prevBtn = $(".btn-prev",$this);
		if(this.o.nextBtn==null) this.o.nextBtn = $(".btn-next",$this);
		if(this.o.stopBtn==null) this.o.stopBtn = $(".btn-stop",$this);
		if(this.o.playBtn==null) this.o.playBtn = $(".btn-play",$this);


		this.init();

		this.setOn(this.o.initNum);

	};
}
mainVisualEffect.prototype.init = function () {


	var this_s = this;
	this.setCtrlBtns();
	this.setToggleBtns();

	this.$numBtns.each(function(){
		
		$(this).attr("seq",($(this).index()+1));
		$(this).bind("click",function(){
			this_s.setOn($(this).attr("seq"));
			return false;
		});
	});



	this.$cont.each(function(){
		var n = $(this).index() + 1; 
		

		$("a,button",$(this)).unbind("focus").bind("focus",function(){
			this_s.setOn(n);
		});

		if(this_s.o.flickEvt ){
			
			$(this_s.o.flickContents,$(this)).unbind("flick").bind("flick",function(e){


				switch(e.orientation){
				case "horizontal":
					e.preventDefault();
					if(e.direction >0){
						this_s.goPrev();

					}else if(e.direction <0){
						this_s.goNext();

					}
					break;
				case "vertical":
					break;
				default :break;
			}


			});

		}
	});
	
}

mainVisualEffect.prototype.setCtrlBtns = function () {
	var this_s = this;
	$(this.o.playBtn).bind("click",function(){ this_s.play(); return false; });
	$(this.o.stopBtn).bind("click",function(){ this_s.stop(); return false; });

	$(this.o.nextBtn).bind("click",function(){ this_s.goNext();return false;});
	$(this.o.prevBtn).bind("click",function(){ this_s.goPrev();return false;});
}
mainVisualEffect.prototype.setToggleBtns = function () {
	var this_s = this;
	if(this.o.toggleBtn){
		if(this.isPlay) { $(this.o.playBtn).hide();$(this.o.stopBtn).show();} 
		else { $(this.o.playBtn).show();$(this.o.stopBtn).hide();} 
	}else{
		$(this.o.playBtn).show();$(this.o.stopBtn).show();
	}

}
mainVisualEffect.prototype.setOn = function (n) {
	var this_s = this;
	clearTimeout(this.Timer);
	if (n==undefined) n = 1 ;
	
	if(this.seq!=n){

		for (var i=0; i<this.$cont.length; i++){
			if ( (i+1)==n ){
				$(this.$numBtns[i]).stop().addClass("over");
				$(this.$cont[i]).css({"z-index":100,"opaicty":0}).stop().show().animate({opacity:1});
			}else{
				$(this.$numBtns[i]).stop().removeClass("over");
				$(this.$cont[i]).css({"z-index":1}).stop().animate({opacity:0},function(){
					$(this).hide();
				});
			}
		}
		this.seq = n;
	}

	var nextNum = parseInt(n) + 1;
	if(nextNum> this.$cont.length){
		nextNum = 1;
	}

	if(this.isPlay) this.setNextOn();

}
mainVisualEffect.prototype.setNextOn = function () {
	var this_s = this;
	clearTimeout(this.Timer);

	var nextNum = parseInt(this.seq) + 1;
	if(nextNum>  $(this.$cont).length){
		nextNum = 1;
	}

	this.Timer = setTimeout(function(){this_s.setOn(nextNum);},this_s.o.speed);

}
mainVisualEffect.prototype.play = function(){ 	this.isPlay = true;  this.setToggleBtns();  this.setOn(this.seq);}
mainVisualEffect.prototype.stop = function(){ clearTimeout(this.Timer); 	this.isPlay = false;  this.setToggleBtns();  }
mainVisualEffect.prototype.goPrev = function(){
	clearTimeout(this.Timer); 
	var nextNum = parseInt(this.seq) - 1;
	if(nextNum<1)	nextNum = this.$cont.length;
	this.setOn(nextNum);
}
mainVisualEffect.prototype.goNext = function(){
	clearTimeout(this.Timer); 
	var nextNum = parseInt(this.seq) + 1;

	if(nextNum > $(this.$cont).length)	nextNum = 1;

	this.setOn(nextNum);
}


