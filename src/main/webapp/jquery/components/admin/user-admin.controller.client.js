(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");

        $userRowTemplate = $(".wbdv-template");
        $tbody = $('tbody');

        $createBtn = $(".wbdv-create");
        $updateBtn = $(".wbdv-update");
        $editBtn = $("#wbdv-edit");
        $removeBtn = $("#wbdv-remove");

        $createBtn.click(createUser);
        $updateBtn.click(updateUser);

        findAllUsers();
    }

    function createUser() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();
        var user = {
            username,
            password,
            firstName,
            lastName,
            role,
        }

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function findUserById(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService.findUserById(userId).then(function(user) {
            renderUser(user);
            $updateBtn.id = user.id;
        });
    }

    function updateUser() {
        var userId = $updateBtn.id;

        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();
        var user = {
            username,
            password,
            firstName,
            lastName,
            role,
        }

        userService
            .updateUser(userId, user)
            .then(findAllUsers);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function renderUser(user) {
        $usernameFld.val(user.username);
        $passwordFld.val(user.password);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
    }

    function renderUsers(users) {
        $tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();

            clone.attr('id', user.id);
            clone.find('#wbdv-remove').click(deleteUser);
            clone.find('#wbdv-edit').click(findUserById);

            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            $tbody.append(clone);
        }
    }
})();