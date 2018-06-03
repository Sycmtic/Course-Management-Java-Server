function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.updateProfile = updateProfile;
    this.logout = logout;
    this.getProfile = getProfile;

    this.url = '/api/user';
    this.reg = '/api/register';
    this.loginURL = '/api/login';
    this.logoutURL = '/api/logout';
    this.profileURL = '/api/profile';
    var self = this;

function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if (response.ok) {
                alert("success Create an Account")
            } else if (response.status == 409) {
                alert("same user name used");
            }
            else throw new Error('cant Create')
        }).catch((error) => {
            alert(error);
        });
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response) {
                return response.json();
            });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response) {
            if (response.bodyUsed) {
                return response.json();
            } else {
                return null;
            }
         });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function register(user) {
        return fetch(self.reg, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function login(username, password) {
        return fetch(self.loginURL, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify({ username, password }),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then((response) => response.text())
        .then((text) => text.length ? JSON.parse(text) : null)
        .catch(error => {
            throw(error);
        });
    }

    function getProfile() {
        return fetch(self.profileURL, {
            credentials: 'same-origin',
        }).then(function(response) {
            return response.json();
        });
    }

    function updateProfile(user) {
        return fetch(self.profileURL, {
            method: 'put',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then((response) => response.text())
        .then((text) => text.length ? JSON.parse(text) : null)
        .catch(error => {
            throw(error);
         });
    }

    function logout(username) {
        return fetch(self.logoutURL, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify({username}),
            headers: {'content-type': 'application/json'}
        }).then(function(response) {

        });
    }
}
