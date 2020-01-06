var keys = {37: 1, 38: 1, 39: 1, 40: 1};

	function backToHome()
	{
		enableScroll();
		sessionStorage.clear();
		document.getElementById("log").style.display="none";
		document.getElementById("container").style.overflow="scroll";
		document.getElementById("opac").style.opacity=1;
		document.getElementsByTagName("body").style.overflow="scroll";
		document.getElementById("opac").className="";
		document.getElementById("enclose").style.display="block";
		document.getElementById("but").style.display="block";
	}
	
	if(sessionStorage.buttonClicked)
	{
		document.getElementById("opac").style.opacity=0.3;
		document.getElementById("log").style.display="block";
		document.getElementById("opac").className="disableClick";
		document.getElementById("enclose").style.visibility="hidden";
	}

	function loginFunc()
	{
		sessionStorage.buttonClicked=1;
		document.getElementById("opac").style.opacity=0.3;
		document.getElementById("opac").className="disableClick";
			document.getElementById("log").style.display="block";
			
			document.getElementById("enclose").style.visibility="hidden";
	}
	function preventDefault(e) {
		  e = e || window.event;
		  if (e.preventDefault)
		      e.preventDefault();
		  e.returnValue = false;  
		}

		function preventDefaultForScrollKeys(e) {
		    if (keys[e.keyCode]) {
		        preventDefault(e);
		        return false;
		    }
		}

		function disableScroll() {
		  if (window.addEventListener) // older FF
		      window.addEventListener('DOMMouseScroll', preventDefault, false);
		  window.onwheel = preventDefault; // modern standard
		  window.onmousewheel = document.onmousewheel = preventDefault; // older browsers, IE
		  window.ontouchmove  = preventDefault; // mobile
		  document.onkeydown  = preventDefaultForScrollKeys;
		}

		function enableScroll() 
		{
		    if (window.removeEventListener)
		        window.removeEventListener('DOMMouseScroll', preventDefault, false);
		    window.onmousewheel = document.onmousewheel = null; 
		    window.onwheel = null; 
		    window.ontouchmove = null;  
		    document.onkeydown = null;  
		}
		function stopAnimation()
		{
			document.getElementById("recharge").style.animationPlayState="paused";
		}
		function startAnimation()
		{
			document.getElementById("recharge").style.animationPlayState="running";
		}