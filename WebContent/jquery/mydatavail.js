$.extend($.fn.validatebox.defaults.rules, {   
    myEmail: {   
        validator: function(value){   
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);   
        },   
        message: '您输入的邮箱格式不正确（正确格式如：XXXX@163.com）'  
    },
    myTel:{
    	 validator: function(value){   
             return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);   
         },   
         message: '您输入的电话格式不正确'  
    },
    myUserName:{
    	validator: function(value){   
            return /^[\u0391-\uFFE5]+$/.test(value);   
        },   
        message: '用户名只能输入中文汉字'  
    },
    myUserPass:{
    	validator: function(value){   
            return /^[a-zA-z0-9]{6,10}$/.test(value);   
        },   
        message: '密码由字母、下划线、数字组成，长度6到8位'  
    }
});  
