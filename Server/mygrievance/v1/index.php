<?php
require 'Slim/Slim.php';

require_once '../include/DbOperation.php';


\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();


$app->notFound(function () use ($app) {
    $app->view->setTemplatesDirectory("Slim/");
    $app->render('404.html');
});


$app->get('/communitypost/:query', 'authenticateUser', function ($query) {
        $db = new DbOperation();
        $res = $db->getCommunityPost($query);

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});

$app->get('/communitycomments/:postid', 'authenticateUser', function ($postid) {
        $db = new DbOperation();
        $res = $db->getCommunityComments($postid);

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});


$app->get('/listofministries', 'authenticateUser', function () {
        $db = new DbOperation();
        $res = $db->getListOfMinistries();

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});


$app->get('/getusergrievance/:email', 'authenticateUser', function ($email) {
        $db = new DbOperation();
        $res = $db->getUserGrievance($email);

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});


$app->get('/getusergrievancereply/:postid', 'authenticateUser', function ($postid) {
        $db = new DbOperation();
        $res = $db->getUserGrievanceReply($postid);

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});


$app->get('/login/:user/:password', 'authenticateApp', function ($user, $password) {
        $db = new DbOperation();
        $res = $db->login($user, $password);

        if(!$res){
             	$response["error"] = true;
                $response["message"] = "User not found";
        } else {
	        $response["error"] = false;
                $response["message"] = $result;
        }

        echoResponse(201, $response);

});


$app->post('/signup/:name/:email/:mobile/:housename/:street/:city/:pincode/:password/:sex/:country/:state/:district', 'authenticateApp', function ($name, $email, $mobile, $housename, $street, $city, $pincode, $password, $sex, $country, $state, $district) {
        $db = new DbOperation();
        $res = $db->Signup($name, $email, $mobile, $housename, $street, $city, $pincode, $password, $sex, $country, $state, $district);

	$response["error"] = false;
        $response["message"] = $result;

        echoResponse(201, $response);

});


$app->post('/postgrievance/:subject/:message/:ministry/:image1/:image2/:image3/:email', 'authenticateApp', function ($subject, $message, $ministry, $image1, $image2, $image3, $email) {
        $db = new DbOperation();
        $res = $db->postGrievance($subject, $message, $ministry, $image1, $image2, $image3, $email);

	$response["error"] = false;
        $response["message"] = "Success";

        echoResponse(201, $response);

});






function get_client_ip() {
    $ipaddress = '';
    if (getenv('HTTP_CLIENT_IP'))
        $ipaddress = getenv('HTTP_CLIENT_IP');
    else if(getenv('HTTP_X_FORWARDED_FOR'))
        $ipaddress = getenv('HTTP_X_FORWARDED_FOR');
    else if(getenv('HTTP_X_FORWARDED'))
        $ipaddress = getenv('HTTP_X_FORWARDED');
    else if(getenv('HTTP_FORWARDED_FOR'))
        $ipaddress = getenv('HTTP_FORWARDED_FOR');
    else if(getenv('HTTP_FORWARDED'))
       $ipaddress = getenv('HTTP_FORWARDED');
    else if(getenv('REMOTE_ADDR'))
        $ipaddress = getenv('REMOTE_ADDR');
    else
        $ipaddress = 'UNKNOWN';
    return $ipaddress;
}



function authenticateUser(\Slim\Route $route)
{
    $headers = apache_request_headers();
    $response = array();
    $app = \Slim\Slim::getInstance();
 
    if (isset($headers['Authorization'])) {
        $db = new DbOperation();
        $api_key = $headers['Authorization'];
        if (!$db->isValidUser($api_key)) {
            $response["error"] = true;
            $response["message"] = "User not authorised!";
            echoResponse(401, $response);
            $app->stop();
        }
    } else {
        $response["error"] = true;
        $response["message"] = "Api key is misssing";
        echoResponse(400, $response);
        $app->stop();
    }
}
 

function authenticateApp(\Slim\Route $route)
{
    $headers = apache_request_headers();
    $response = array();
    $app = \Slim\Slim::getInstance();
 
    if (isset($headers['Authorization'])) {
        $db = new DbOperation();
        $api_key = $headers['Authorization'];
        if (!$db->isValidApp($api_key)) {
            $response["error"] = true;
            $response["message"] = "User Agent not authorised!";
            echoResponse(401, $response);
            $app->stop();
        }
    } else {
        $response["error"] = true;
        $response["message"] = "Api key is misssing";
        echoResponse(400, $response);
        $app->stop();
    }
}
 


 
function echoResponse($status_code, $response)
{

    //Getting app instance
    $app = \Slim\Slim::getInstance();
 
    //Setting Http response code
    $app->status($status_code);
 
    //setting response content type to json
    $app->contentType('application/json');
 
    //displaying the response in json format
    echo json_encode($response);


}

$app->run();