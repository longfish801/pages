// Copyright (C) 伊織舞也 (http://www.losttechnology.jp/)
// 著作権表示を消さなければご自由にお使いください
function blink(id,interval,bc) {
  if (document.all) {
    c=document.all(id).style;
    if (!bc) bc=c.color;
    c.color=c.color==c.backgroundColor?bc:c.backgroundColor;
    setTimeout('blink("'+id+'",'+interval+',"'+bc+'")',interval);
  }
}
function blinkBlock(id,interval) {
  if ((document.getElementById)||(document.all)) {
    c=(document.all)?document.all(id):document.getElementById(id);
    c.style.visibility=c.style.visibility=='hidden'?'visible':'hidden';
    setTimeout('blinkBlock("'+id+'",'+interval+')',interval);
  }
}
