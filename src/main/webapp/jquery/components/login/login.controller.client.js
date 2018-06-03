(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");

        $loginBtn = $("#loginBtn")
            .click(verifyLogin);
    }

    function verifyLogin() {
        if ($usernameFld.val() === "" || $passwordFld.val() === "") {
            alert("please input username and password");
            window.location = './login.template.client.html';
        } else {
            login();
        }
    }

    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        userService
            .login(username, password)
            .then(function(user) {
                if (user === null) {
                    alert("incorrect username or password");
                    window.location = './login.template.client.html';
                } else {
                    window.location = '../profile/profile.template.client.html';
                }
            });
    }
})();
