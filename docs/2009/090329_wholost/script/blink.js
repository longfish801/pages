// Copyright (C) ˆÉD•‘–ç (http://www.losttechnology.jp/)
// ’˜ìŒ •\¦‚ğÁ‚³‚È‚¯‚ê‚Î‚²©—R‚É‚¨g‚¢‚­‚¾‚³‚¢
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
