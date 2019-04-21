(ns xzeros.lst
  (:require
    [io.pedestal.http.body-params :as body-params]
    [xzeros.service]
    [clojure.data.json :as json]
    [xzeros.user]
    [xzeros.db.lst :as db-lst]
    [clojure.string :as str]
    )
  )

(defn rest-execute [{:keys [json-params headers] :as request}]
  (let [ user (xzeros.user/getAuthUser request)]
    (if (str/blank? user)
      xzeros.service/permission-denied-response

      (let [list-id (json-params :list-id)
            item-id (json-params :item-id)]
        (xzeros.service/no-impl-response user list-id item-id)
        )
      )
    )
  )

(defn get-or-new [{:keys [params headers] :as request}]
  (let [ user (xzeros.user/getAuthUser request)]
    (if (str/blank? user)
      xzeros.service/permission-denied-response

      (let [name (params :name)
            lst-id (db-lst/get-or-new-lst-id user name)
            items (db-lst/get-lst-items lst-id 0 -1)
            ]
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body (json/write-str {:data {:lst-id lst-id :items items} })}
        )
      )
    )
  )

(defn update-lst [{:keys [json-params headers] :as request}]
  (let [ user (xzeros.user/getAuthUser request)]
    (if (str/blank? user)
      xzeros.service/permission-denied-response

      (let [{:keys [name items]} json-params
            lst-id (db-lst/get-or-new-lst-id user name)
            ]
        (db-lst/set-lst-length lst-id (count items))
        (db-lst/set-lst-items items lst-id 0)
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body (json/write-str {:data lst-id })}
        )
      )
    )
  )

(defn delete-lst [{:keys [params headers] :as request}]
  (let [ user (xzeros.user/getAuthUser request)]
    (if (str/blank? user)
      xzeros.service/permission-denied-response

      (let [name (params :name)
            ]
        (db-lst/delete-lst user name)
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body (json/write-str {:data true})}
        )
      )
    )
  )

(def routes
  [
   "/lst"
   ["/lsts"
    ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
    {:get `xzeros.lst/rest-execute }
    ]
   ["/getOrNew"
    ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
    {:get `xzeros.lst/get-or-new }
    ]
   ["/update"
    ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
    {:post `xzeros.lst/update-lst }
    ]
   ["/delete"
    ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
    {:get `xzeros.lst/delete-lst }
    ]
   ;["/item"
   ; ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
   ; {:get `xzeros.lst/rest-execute }
   ; ]
   ;["/updateItem"
   ; ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
   ; {:get `xzeros.lst/rest-execute }
   ; ]
   ;["/addItem"
   ; ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
   ; {:get `xzeros.lst/rest-execute }
   ; ]
   ;["/deleteItem"
   ; ^:interceptors [xzeros.service/coerce-body xzeros.service/content-neg-intc (body-params/body-params)]
   ; {:get `xzeros.lst/rest-execute }
   ; ]
   ]
  )

