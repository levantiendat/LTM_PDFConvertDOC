<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../Css/style.css">
    <title>Document</title>
</head>

<body>
    <div class="container">
        <div class="wrapper">
            <div class="row mt-3">
                <div class="col col-sm-3"></div>
                <div class="col col-sm-6 bg-light rounded-4" style="padding: 30px 60px;">
                    <h1 class="text-center mb-5 mt-1">Create your account</h1>
                    <form action="" class="RegisterForm">
                        <div class="FormGroup mb-3">
                            <label for="Email" class="form-label" style="font-weight:bold">Email
                            </label>
                            <input type="email" class="form-control" id="Email" aria-describedby="emailHelp">
                            <div id="emailHelp" class="form-text"></div>
                        </div>
                        <div class="FormGroup mb-3">
                            <label for="Username" class="form-label" style="font-weight:bold">Username</label>
                            <input type="text" class="form-control" id="Username" aria-describedby="usernameHelp">
                            <div id="usernameHelp" class="form-text">
                            </div>
                        </div>
                        <div class="FormGroup mb-3">
                            <label for="password" class="form-label" style="font-weight:bold">Password</label>
                            <input type="password" class="form-control" id="password" aria-describedby="passwordHelp">
                            <div id="passwordHelp" class="form-text">
                            </div>
                        </div>
                        <div class="FormGroup mb-5">
                            <label for="ConfirmPassword" class="form-label" style="font-weight:bold">Confirm
                                password</label>
                            <input type="password" class="form-control" id="ConfirmPassword"
                                aria-describedby="ConfirmPasswordHelp">
                            <div id="ConfirmPasswordHelp" class="form-text"></div>
                        </div>
                        <div class="FormGroup w-100">
                            <button type="button" class="btn btn-primary w-100">Create your account</button>
                        </div>
                        <div class="FormGroup w-100 bg-light">
                            <p class="text-center mt-3"
                                style="height: 35px; display:flex; align-items: center; justify-content:center;">Already
                                have an account? <a href="">Login</a></p>
                        </div>
                    </form>
                </div>

                <div class="col col-sm-3"></div>
            </div>

        </div>
    </div>
    <script src="https://kit.fontawesome.com/960668e674.js" crossorigin="anonymous"></script>
</body>

</html>