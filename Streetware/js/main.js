$(document).ready(function(){
  $(function(){$('a').stop().click(function(){
  if(location.pathname.replace(/^\//,'')==this.pathname.replace(/^\//,'')&&location.hostname==this.hostname){
    var A_HASHTAG=this.hash;var A_VAR=$(this.hash);
    if(A_VAR.length){
      var ABSTAND_TOP=A_VAR.offset().top;
      $('html, body').animate({scrollTop:ABSTAND_TOP},1000,function(){window.location.hash=A_HASHTAG;});
      return false;
    }}});});
})
