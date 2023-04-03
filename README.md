# Book-Service-DDD
BookService microservice implemented with DDD, Spring Boot and Gradle

## Api guidline:

POST /books -- create 1 or N books, should respond with a list of generated ids

Body example:

         [

         {
         
            "author": "vicente",
            
            "title": "The First Book",
            
            "genre": "Fiction",
            
            "price": "44.95"
            
         },
         
         {
         
            "author": "badis",
            
            "title": "how to become a billionaire",
            
            "genre": "Poem",
            
            "price": "24.95"
            
         }
      ]


GET  /books -- retrieve all books available

GET  /books/{id} -- retrieve 1 book by its id

POST /books/{id}/reviews -- pass a text in the body



