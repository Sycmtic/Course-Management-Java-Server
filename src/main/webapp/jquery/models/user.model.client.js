function User(username, password, firstName, lastName, email, phone, role, dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.dateOfBirth = dateOfBirth;

    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    this.setFirstname = setFirstname;
    this.getFirstname = getFirstname;
    this.setLastname = setLastname;
    this.getLastname = getLastname;
    this.setEmail = setEmail;
    this.getEmail = getEmail;
    this.setPhone = setPhone;
    this.getPhone = getPhone;
    this.setRole = setRole;
    this.getRole = getRole;
    this.setDateOfBirth = setDateOfBirth;
    this.getDateOfBirth = getDateOfBirth;

    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }

    function setPassword(password) {
        this.password = password;
    }
    function getPassword() {
        return this.password;
    }

    function setFirstname(firstName) {
        this.firstName = firstName;
    }
    function getFirstname() {
        return this.firstName;
    }

    function setLastname(lastName) {
        this.lastName = lastName;
    }
    function getLastname() {
        return this.lastName;
    }

    function setEmail(email) {
        this.email = email;
    }
    function getEmail() {
        return this.email;
    }

    function setPhone(phone) {
        this.phone = phone;
    }
    function getPhone() {
        return this.phone;
    }

    function setRole(role) {
        this.role = role;
    }
    function getRole() {
        return this.role;
    }

    function setDateOfBirth(dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    function getDateOfBirth() {
        return this.dateOfBirth;
    }
}
