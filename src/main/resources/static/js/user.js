let index = {
	init:function(){
		$("#btn-join").on("click", () => {
			this.save();
		});
	},
	
	save:function(){
		//alert('user save called');
		
		//let pw = $("#password").val();
		//let conpw = $("#confirmpassword").val();
		//let len = pw.length;
		
		//if(len < 1) {
		//	$("#CheckPassword").html("Password cannot be blank !");
		//}
		
		//else if(pw != conpw){
		//	$("#CheckPassword").html("Password mismatch !");
		//}
		
		//else {
			
			let data ={
				username: $("#username").val(),
				email: $("#email").val(),
				password: $("#password").val()			
			}
			
			console.log(data);
			
			//$.ajax().done().fail();
			
			
			
		//}		
		
	}
}

index.init();