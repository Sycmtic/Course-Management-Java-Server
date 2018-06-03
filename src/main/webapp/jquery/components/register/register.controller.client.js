(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $verifyPasswordFld = $("#verifyPassword");
        $registerBtn = $("#registerBtn");

        $registerBtn.click(register);
    }

    function register() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var verifyPassword = $verifyPasswordFld.val();

        if (password !== verifyPassword) {
            alert("please input the same password");
        } else {
            var user = {
                username,
                password
            }

            userService
                .register(user)
                .then(function() {
                    window.location = '../profile/profile.template.client.html';
                });
        }
    }

})();
