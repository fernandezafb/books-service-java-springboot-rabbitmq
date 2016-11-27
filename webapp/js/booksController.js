app.controller('booksController', function($scope, $http) {

    // This should be refactored to an application property
    var domainBookService = 'http://localhost:9000';

    $scope.addBook    = addBook;
    $scope.syncBook   = syncBook;
    $scope.updateBook = updateBook;
    $scope.deleteBook = deleteBook;

    loadBooks();

    function loadBooks() {
        $http.get(domainBookService + '/books').then(function(response) {
            $scope.books = response.data._embedded.books;
        });
    }
    
    function addBook() {
        var jsonData = '{"isbn": "' + $scope.inputIsbn + ' ", "description": "' + $scope.inputDescription + '"}';
        $http.post(domainBookService + '/books', jsonData).then(function() {
            loadBooks();
            $scope.inputIsbn = $scope.inputDescription = "";
        });
    }

    function syncBook(book) {
        var queryParams = '?id=' + book.id + '&isbn=' + book.isbn;
        $http.get(domainBookService + '/update/description/book' + queryParams).then(function() {
        });
    }

    function updateBook(book) {
        var jsonData = '{"isbn": "' + book.isbn + ' ", "description": "' + book.description + '", "created": "' + book.created +'"}';
        $http.put(book._links.self.href, jsonData).then(function () {
            loadBooks();
        });
    }

    function deleteBook(book) {
        $http.delete(book._links.self.href).then(function () {
            loadBooks();
        });
    }
});


