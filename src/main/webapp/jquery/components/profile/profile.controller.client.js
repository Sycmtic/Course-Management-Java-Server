(function() {
    var $usernameFld;
    var $phoneFld;
    var $emailFld;
    var $roleFld;
    var $dateFld;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
    $(init);

    function init() {
        $usernameFld = $("#username");
        $phoneFld = $("#phone");
        $emailFld = $("#email");
        $roleFld = $("#role");
        $dateFld = $("#date");

        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $logoutBtn = $("#logoutBtn")
            .click(logout);

        userService
            .getProfile()
            .then(function(user) {
                findUserById(user.id);
            });
    }

    function updateProfile() {
        var user = {
            username: $usernameFld.val(),
            phone: $phoneFld.val(),
            email: $emailFld.val(),
            role: $roleFld.val(),
            dateOfBirth: $dateFld.val()
        }

        userService
            .updateProfile(user)
            .then(success);
    }

    function success(response) {
        if (response === null) {
            alert('unable to update')
        } else {
            alert('success');
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        $usernameFld.val(user.username);
        $phoneFld.val(user.phone);
        $emailFld.val(user.email);
        $roleFld.val(user.role);
        $dateFld.val(user.dateOfBirth);
    }

    function logout() {
        var username = $usernameFld.val();

        userService
            .logout(username)
            .then(function() {
                window.location = '../login/login.template.client.html';
            });
    }
})();