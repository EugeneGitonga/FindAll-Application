<?php
	include("Conn.php");

	$type=$_POST["type"];

	if(strcmp("signup",$type)==0){
		$user_name=$_POST["user_name"];
		$user_id=$_POST["user_id"];
		$pass=$_POST["pass"];
		$que="SELECT * from FindAll WHERE user_id='$user_id'";
		$query=mysqli_query($con,$que);
		if(mysqli_num_rows($query)>0){
			echo "exists";
		}
		else{
			$que2="INSERT INTO FindAll (user_id,user_name,pass) VALUES ('$user_id','$user_name','$pass')";
			$query2=mysqli_query($con,$que2);
			if($query2){
				echo "success";
			}
			else{
				echo "fail";
			}
		}
	}
	else if(strcmp("login",$type)==0){
		$user_id=$_POST["user_id"];
		$pass=$_POST["pass"];
		$que="SELECT * from FindAll WHERE user_id='$user_id' AND pass='$pass'";
		$query=mysqli_query($con,$que);
		if(mysqli_num_rows($query)==1){
			$res=mysqli_fetch_assoc($query);
			echo $res["user_name"];
		}
		else{
			echo "not_exists";
		}
	}
	else if(strcmp("dobook",$type)==0){
		$user_id=$_POST["user_id"];
		$vendor=$_POST["vendor"];
		$vechicle=$_POST["vehicle"];
		$prob=$_POST["prob"];
		$date=$_POST["date"];
		$que="INSERT INTO FindAll2(email,vendor,vehicle,prob,date) VALUES('$user_id','$vendor','$vehicle','$prob','$date')";
		$query=mysqli_query($con,$que);
		if($query){
			$to      = $user_id;
			$subject = 'Booking Confirmation';
			$message = 'Your Booking of the mechanic has been confirmed by'.$vendor.' on '.$date.' for the following problem '.$prob.
			$headers = 'From: admin@findall.com';
			

			$check=mail($to, $subject, $message, $headers);
			if($check){
				echo "success";
			}
			else{
				echo "fail";
			}
		}
		else{
			echo "fail";
		}
	}
	else if(strcmp("getbook",$type)==0){
	    $user_id=$_POST["user_id"];
		$que="SELECT * FROM FindAll2 WHERE user_id='$user_id'";
		$query=mysqli_query($con,$que);
		$nos=mysqli_num_rows($query);
			if($nos>0){
				while($res=mysqli_fetch_assoc($query)){
					echo $vendor."#".$date."#".$vehicle;
				}
			}
			else{
				echo "empty";
			}
		
		
	}
	
?>