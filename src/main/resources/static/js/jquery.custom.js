/******************** jquery自定义插件 ****************************/
if(typeof jQuery != "function"){
    throw new Error("custom自定义插件必须依赖于jquery，请确定是否引入！");
}
/******  region 输入框验证     ****/
/** 使用方法
 * *html =>
     <div id="div1" class="layui-input-block verification" >
         <input type="text"   class="layui-input">
         <span class="layui-icon"></span>
     </div>
 **css=>
    <style>
         .verification{
                    position:relative;
                }
         .verification>input{
                    padding-right:35px;
                }
         .verification>.layui-icon{
                    position: absolute;top: 50%;right: 10px;transform: translateY(-50%);
                }
         .verification>.layui-icon.layui-icon-ok-circle{
                    color: #1E9FFF;
                }
         .verification>.layui-icon.layui-icon-close-fill{
                    color:#FF5722;
                }
    </style>
 ** js=>
    调用  $("#div1").verificationInput(primose);    //primose为Primose函数
 * */
if(typeof jQuery.fn.verificationInput !="function"){
    jQuery.fn.verificationInput=function(primose){
        var $div=$(this);
        var $input=$div.children("input");
        var $span=$div.children(".layui-icon");
        //绑定键盘事件
        var timer=null ;
        $input.keyup(function(e){
            var $this=$(this);
            var val=$this.val();
            if(val){
                if(timer){
                    clearTimeout(timer);
                }
                timer=setTimeout(function(){
                    primose(val).then(()=>{
                            $span.removeClass("layui-icon-close-fill").addClass("layui-icon-ok-circle").attr("title","验证通过");
                            timer=null;
                    }).catch(message=>{
                            $span.removeClass("layui-icon-ok-circle").addClass("layui-icon-close-fill").attr("title",message);
                            timer=null;
                    });
                },1000);
            }
        });
    }
}
/***********  endregion *************/