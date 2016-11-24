app.controller('booksController', function($scope, $http) {

    $scope.addBook = addBook;
    $scope.updateBook = updateBook;
    $scope.deleteBook = deleteBook;

    loadBooks();

    function loadBooks() {
        $http.get('/books').then(function(response) {
            $scope.books = response.data._embedded.books;
        });
    }
    
    function addBook() {
        var jsonData = '{"isbn": "' + $scope.inputIsbn + ' ", "description": "' + $scope.inputDescription + '"}';
        $http.post('/books', jsonData).then(function() {
            loadBooks();
            $scope.inputIsbn = $scope.inputDescription = "";
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


