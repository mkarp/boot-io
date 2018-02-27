(set-env!
  :dependencies  '[[org.clojure/clojure                 "1.8.0"]
                   [boot/core                           "2.7.2"]
                   [commons-io                          "2.6"]
                   [degree9/boot-semver                 "1.7.0" :scope "test"]]
  :resource-paths   #{"src"}
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username (System/getenv "CLOJARS_USER")
                                     :password (System/getenv "CLOJARS_PASS")}]))

(require '[degree9.boot-semver :refer :all])

(task-options!
  pom    {:project 'mkarp/degree9-boot-io
          :description "Boot-clj IO functionality using Apache Commons IO."
          :url         "https://github.com/mkarp/degree9-boot-io"
          :scm         {:url "https://github.com/mkarp/degree9-boot-io"}}
  target {:dir #{"target"}})

(deftask develop
  "Build boot-io for development."
  []
  (comp
   (version :develop true
            :minor 'inc
            :patch 'zero
            :pre-release 'snapshot)
   (watch)
   (target)
   (build-jar)))

(deftask deploy
  "Build boot-io and deploy to clojars."
  []
  (comp
   (version)
   (target)
   (build-jar)
   (push-release)))
