(function () {
    jQuery(main);

    function main() {
        var h1 = jQuery('h1');
        h1.css('color', 'red');
        h1.html('User Administration!');

        var tr = $('.template');

        var tbody = $('tbody');

        var users = [
            {username: 'bob'},
            {username: 'charlie'}
        ]

        for(let i=0; i<users.length; i++) {
            var user = users[i];
            var clone = tr.clone();
            clone.find('.username').html(user.username);
            tbody.append(clone);
        }
    }
})();