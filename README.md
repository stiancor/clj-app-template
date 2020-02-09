# clj-app-template

A basic setup of a clojure app accepting and responding to JSON POST requests. 
The project uses tools.deps.alpha for dependency management and aliases. 

Having clojure installed is a requirement. [Here](https://clojure.org/guides/getting_started) is how you do it. 

Start the app running `clj -A:server` from the command line, or fire up `clj -A:repl` and run `(require '[app.webserver])`. 
Then run `(mount.core/start)` and the app should start.
