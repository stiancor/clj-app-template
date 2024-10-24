# clj-app-template

A basic setup of a clojure app accepting and responding to JSON POST requests. 
Originally intended to be used as a starting
point in a small coding competition where a client sends the server questions.

The project uses tools.deps.alpha for dependency management and aliases.

Having clojure installed is a requirement. [Here](https://clojure.org/guides/getting_started) is how you do it.

### Starting the app
There are two options on starting the app. I prefer option two!

1. Start the app running `clj -M:server` from the command line. This does not provide code reloading out of the box.
2. Use a [REPL](https://clojure.org/guides/repl/introduction). Run `clj -A:repl`, and then execute this inside the REPL
   `(require '[app.webserver])`.
   Next execute `(mount.core/start)` and the app should now start and serve from http://localhost:7331.
   Whenever you have added some code and want to test it, reload the code inside the REPL.

If you want to use a different port than the default port 7331, just change it in `webserver.clj`.

By default all GET routes serve the very original text `Hello world`.

All POST endpoints are currently handled by the function `handle-req` in `service.clj`.

Happy coding!
