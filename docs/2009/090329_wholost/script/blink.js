// Copyright (C) �ɐD���� (http://www.losttechnology.jp/)
// ���쌠�\���������Ȃ���΂����R�ɂ��g����������
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
