var ops =
    [ {
      "className" : "com.rabarbers.restdoc.mockproj.CatalogResource",
      "methodName" : "getCatalogId",
      "returnType" : {
        "className" : "java.lang.Long",
        "genericTypes" : [ ]
      },
      "params" : [ ],
      "path" : "/catalogs/id",
      "verb" : "GET"
    }, {
      "className" : "com.rabarbers.restdoc.mockproj.CatalogResource",
      "methodName" : "getOrderList",
      "returnType" : {
        "className" : "java.util.List",
        "genericTypes" : [ {
          "className" : "com.rabarbers.restdoc.mockproj.domain.Order",
          "genericTypes" : [ ]
        } ]
      },
      "params" : [ ],
      "path" : "/catalogs/orders",
      "verb" : "GET"
    }, {
      "className" : "com.rabarbers.restdoc.mockproj.CatalogResource",
      "methodName" : "createCatalog",
      "returnType" : {
        "className" : "void",
        "genericTypes" : [ ]
      },
      "params" : [ {
        "className" : "java.util.List",
        "genericTypes" : [ {
          "className" : "com.rabarbers.restdoc.mockproj.domain.Order",
          "genericTypes" : [ ]
        } ]
      } ],
      "path" : "/catalogs/id",
      "verb" : "PUT"
    }, {
      "className" : "com.rabarbers.restdoc.mockproj.CatalogResource",
      "methodName" : "getCatalogs",
      "returnType" : {
        "className" : "javax.ws.rs.core.Response",
        "genericTypes" : [ ]
      },
      "params" : [ {
        "className" : "java.lang.Float",
        "genericTypes" : [ ]
      }, {
        "className" : "java.util.Map",
        "genericTypes" : [ {
          "className" : "java.lang.String",
          "genericTypes" : [ ]
        }, {
          "className" : "java.util.Collection",
          "genericTypes" : [ {
            "className" : "java.lang.String",
            "genericTypes" : [ ]
          } ]
        } ]
      }, {
        "className" : "java.lang.Float",
        "genericTypes" : [ ]
      }, {
        "className" : "java.lang.String",
        "genericTypes" : [ ]
      } ],
      "path" : "/catalogs/list",
      "verb" : "POST"
    }, {
      "className" : "com.rabarbers.restdoc.mockproj.GenericResource",
      "methodName" : "getPairList",
      "returnType" : {
        "className" : "com.rabarbers.restdoc.mockproj.domain.Pair",
        "genericTypes" : [ {
          "className" : "java.lang.String",
          "genericTypes" : [ ]
        }, {
          "className" : "java.lang.Integer",
          "genericTypes" : [ ]
        } ]
      },
      "params" : [ ],
      "path" : "/generics/list",
      "verb" : "GET"
    }, {
      "className" : "com.rabarbers.restdoc.mockproj.GenericResource",
      "methodName" : "getPairTree",
      "returnType" : {
        "className" : "com.rabarbers.restdoc.mockproj.domain.Pair",
        "genericTypes" : [ {
          "className" : "java.lang.String",
          "genericTypes" : [ ]
        }, {
          "className" : "com.rabarbers.restdoc.mockproj.domain.Pair",
          "genericTypes" : [ {
            "className" : "java.lang.Long",
            "genericTypes" : [ ]
          }, {
            "className" : "com.rabarbers.restdoc.mockproj.domain.Order",
            "genericTypes" : [ ]
          } ]
        } ]
      },
      "params" : [ ],
      "path" : "/generics/tree",
      "verb" : "GET"
    } ];