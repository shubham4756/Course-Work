<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Registration Form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        .form {
            width: 33%;
            margin-top: 5%;
            margin-left: 33%;
            background: #52c8ec;
            border-radius: 5px;
            margin-bottom: 5%;
        }
        body {
          font-family: 'Nunito', sans-serif;
          font-size: 20px;
          background: #531834;
        }
        label {
          display: block;
          margin-bottom: 8px;
        }
        input[type="text"],input[type="password"],input[type="email"],input[type="date"],input[type="datetime"],input[type="email"],
        input[type="number"],input[type="search"],input[type="tel"],input[type="time"],input[type="url"],textarea,select {
          background: rgba(255,255,255,0.1);
          font-size: 18px;
          padding: 15px;
          width: 100%;
          background-color: #b145b1;
          color: #ffffff;
          margin-bottom: 30px;
        }
        .fieldset div {
          padding-left : 30px;
          padding-right: 30px;
          padding-bottom: 15px;
        }
        .btn {
            background-color: #ff7402;
            color: black;
            margin-left: 27%;
            border: 2px solid #e7e7e7;
        }
    </style>
</head>
<body>
    <form class="form" name="form" action="Five-Help.php" method="POST" enctype="multipart/form-data">
        <div class="fieldset">
            <div style="padding-top: 30px; margin-bottom: 20px;">
              <label for="name">Name:</label>
              <input type="text" name="name" style="margin-bottom: 0px;">
              <small style="text-decoration: bold; font-size: 15px;">Enter Letters Only </small>
            </div>

            <div>
              <label for="email">Email:</label>
              <input type="email" name="email">
            </div>

            <div>
              <label for="dob">DOB:</label>
              <input type="date" name="dob">
            </div>

            <div >
              <label for="mobile">MobileNumber:</label>
              <input type="tel" name="mobile" style="margin-bottom: 0px;">
              <small style="text-decoration: bold; font-size: 15px;">Sample Format : 9876543210 </small>
            </div>

            <div >
              <label for="gender">Gender:</label>
              <input type="radio" name="gender" value="male"> <span style="display:inline-block;"> Male </span> 
              <input type="radio" name="gender" value="female"> <span style="display:inline-block;"> Female </span> <br> 
            </div>

            <div>
              <label for="post">Post:</label>
              <input type="radio" name="post" value="webDeveloper"> <span style="display:inline-block;"> Web Developer </span>
              <input type="radio" name="post" value="programmer"> <span style="display:inline-block;"> Programmer </span>  
              <input type="radio" name="post" value="manager"> <span style="display:inline-block;"> Manager </span> <br> 
            </div>
            
            <div>
              <label for="idCard">Upload Id Card:</label>
              <input type="file" name="idCard"> 
            </div>

            <div style="margin-top: 15px;">
              <input type="submit" style="height: 50px; width: 200px; font-size: 20px; color: #ffffff;" name="submit" class="btn"> 
            </div>

        </div>
    </form>  
</body>
</html>