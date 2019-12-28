<?php
 
class DbOperation
{
    //Database connection link
    private $con;
 
    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }
 

    public function getCommunityPost($query){
        $stmt = $this->con->prepare("SELECT * FROM communityPost WHERE query = ?");
        $stmt->bind_param("s", $query);
        $stmt->execute();
        $result = $stmt->get_result();
	$rows = [];
	while($row = mysqli_fetch_array($result))
	{
 	   $rows[] = $row;
	}      
        $stmt->close();
        return $rows;
    }


    public function getCommunityComments($postid){
        $stmt = $this->con->prepare("SELECT * FROM communityPostComments WHERE postid = ?");
        $stmt->bind_param("s", $postid);
        $stmt->execute();
        $result = $stmt->get_result();
	$rows = [];
	while($row = mysqli_fetch_array($result))
	{
 	   $rows[] = $row;
	}      
        $stmt->close();
        return $rows;
    }


    public function getListOfMinistries(){
        $stmt = $this->con->prepare("SELECT * FROM listofministries");
        $stmt->execute();
        $result = $stmt->get_result();
	$rows = [];
	while($row = mysqli_fetch_array($result))
	{
 	   $rows[] = $row;
	}      
        $stmt->close();
        return $rows;
    }



    public function getUserGrievance($email){
        $stmt = $this->con->prepare("SELECT * FROM userGrievances WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $result = $stmt->get_result();
	$rows = [];
	while($row = mysqli_fetch_array($result))
	{
 	   $rows[] = $row;
	}      
        $stmt->close();
        return $rows;
    }


    public function getUserGrievanceReply($postid){
        $stmt = $this->con->prepare("SELECT * FROM userGrievancesReply WHERE postid = ?");
        $stmt->bind_param("s", $postid);
        $stmt->execute();
        $result = $stmt->get_result();
	$rows = [];
	while($row = mysqli_fetch_array($result))
	{
 	   $rows[] = $row;
	}      
        $stmt->close();
        return $rows;
    }



    public function login($user, $password){
        $stmt = $this->con->prepare("SELECT api_key FROM user WHERE email = ? OR mobile = ? AND password = ?");
        $stmt->bind_param("sss", $user, $user, $password);
        $stmt->execute();
        $result = $stmt->get_result();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();

        if($num_rows>0){
              return $result;
        } else {
              return false;
        }
        
    }



    public function Signup($name, $email, $mobile, $housename, $street, $city, $pincode, $password, $sex, $country, $state, $district){
        date_default_timezone_set("Asia/Kolkata");
        $timestamp = date("d/m/Y-H:i:s");
        $apikey = $this->generateApiKey();
        $stmt = $this->con->prepare("INSERT INTO user (name, email, mobile, housename, street, city, pincode, password, sex, country, state, district, timestamp, api_key) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("ssssssssssssss", $name, $email, $mobile, $housename, $street, $city, $pincode, $password, $sex, $country, $state, $district, $timestamp, $apikey);
        $stmt->execute();
        $stmt->close();
        return $apikey;
    }



    public function postGrievance($subject, $message, $ministry, $image1, $image2, $image3, $email){
        date_default_timezone_set("Asia/Kolkata");
        $timestamp = date("d/m/Y-H:i:s");
        $stmt = $this->con->prepare("INSERT INTO userGrievances (subject, message, ministry, image1, image2, image3, email, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("ssssssss", $subject, $message, $ministry, $image1, $image2, $image3, $email, $timestamp);
        $stmt->execute();
        $stmt->close();
    }



    public function isValidUser($api_key) {
        //Creating an statement
        $stmt = $this->con->prepare("SELECT id from user_info WHERE api_key = ?");
 
        $stmt->bind_param("s", $api_key);
 
        //Executing the statement
        $stmt->execute();
 
        //Storing the results
        $stmt->store_result();
 
        //Getting the rows from the database
        //As API Key is always unique so we will get either a row or no row
        $num_rows = $stmt->num_rows;
 
        //Closing the statment
        $stmt->close();
 
        //If the fetched row is greater than 0 returning  true means user is valid
        return $num_rows > 0;
    }


    public function isValidApp($api_key) {
        //Creating an statement
        $stmt = $this->con->prepare("SELECT id from app_info WHERE api_key = ?");
 
        $stmt->bind_param("s", $api_key);
 
        //Executing the statement
        $stmt->execute();
 
        //Storing the results
        $stmt->store_result();
 
        //Getting the rows from the database
        //As API Key is always unique so we will get either a row or no row
        $num_rows = $stmt->num_rows;
 
        //Closing the statment
        $stmt->close();
 
        //If the fetched row is greater than 0 returning  true means user is valid
        return $num_rows > 0;
    }


 
    //This method will generate a unique api key
    private function generateApiKey(){
        return md5(uniqid(rand(), true));
    }
}