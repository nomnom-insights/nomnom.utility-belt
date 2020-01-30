# utility-belt

[![Clojars Project](https://img.shields.io/clojars/v/nomnom/utility-belt.svg)](https://clojars.org/nomnom/utility-belt)

[![CircleCI](https://circleci.com/gh/nomnom-insights/nomnom.utility-belt.svg?style=svg)](https://circleci.com/gh/nomnom-insights/nomnom.utility-belt)

> Although seemingly unremarkable in appearance, the utility belt is one of Batman's most important tools in fighting crime.


## About

Utility belt has a limited functionality: it only provides commonly used functions wrapping
a limited set of dependencies.

Currently it has:

- `utility-belt.id` - various functions for working with UUIDs
- `utility-belt.json` - sets up automatic conversions of Joda time objects to/from JSON, you need to pull in `clj-time` and `cheshire` if you need to use those
- `utility-belt.time` - common functions for date and time calculations, you need to pull in `clj-time` if you need to use those
- `utilit-belt.conv` - type conversions (string to int, int to string, etc)
- `utlity-belt.component` - small utils which make working with Stuart Sierra's Component a bit easier
- `utility-belt.map-keys` - easy transformations of map keys between kebab and snake case
- `utility-belt.lifecycle` - helpers to manage Clojure application lifecycle (registering shutdown hooks etc)

## nREPL component

Micro wrapper around nREPL server, as easy to use as passing the port number.

> :warning: This is a REPL so take care of securing it!

```clojure

(def system
  {:nrepl-server (utility-belt.component.nrepl/create 23211)})

```

:raising_hand: Note that the server by default binds to `0.0.0.0`

You an pass the address to bind to if you need more control:


```clojure
(def system
  {:nrepl-server (utility-belt.component.nrepl/create 23211"127.0.0.1")})
```

# Lifecycle hooks

`utility-belt.lifecycle` provides a set of helpers to manage application lifecycle. Best used if you're using Component. Example:

```clojure

(ns app.core
  (:require [app.system]
            [com.stuartsierra.component :as component]
            [utility-belt.lifecycle :as life]))

(def system (atom nil))

(def -main []
  (life/register-shutdown-hook :stop-system #(component/stop @system))
  (life/register-shutdown-hook :goodbye #(println "BYYYYEEEE 👋"))
  (life/install-shutdown-hooks!)
  (reset! system (component/start (app.system/create))))
```

# Authors

<sup>In alphabetical order</sup>

- [Afonso Tsukamoto](https://github.com/AfonsoTsukamoto)
- [Łukasz Korecki](https://github.com/lukaszkorecki)
- [Marketa Adamova](https://github.com/MarketaAdamova)
